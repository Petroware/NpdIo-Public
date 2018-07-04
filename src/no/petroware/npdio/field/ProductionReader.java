package no.petroware.npdio.field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.petroware.npdio.Util;

/**
 * Production reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class ProductionReader
{
  private static final int FIELD_NAME_INDEX = 0;
  private static final int YEAR_INDEX = 1;
  private static final int MONTH_INDEX = 2;
  private static final int OIL_INDEX = 3;
  private static final int GAS_INDEX = 4;
  private static final int NGL_INDEX = 5;
  private static final int CONDENSATE_INDEX = 6;
  private static final int OIL_EQUIVALENTS_INDEX = 7;
  private static final int WATER_INDEX = 8;
  private static final int NPDID_INDEX = 9;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(ProductionReader.class.getName());

  /**
   * Private constructor to prevent client instantiation.
   */
  private ProductionReader()
  {
    // Nothing
  }

  /**
   * Create a new NPD company instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the company database table. Non-null.
   * @return        The created company instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  private static Production.Entry newProductionEntry(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 10)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the field attributes
    //
    int year = Util.parseInt(tokens[YEAR_INDEX]);
    int month = Util.parseInt(tokens[MONTH_INDEX]);

    double oil = Util.parseDouble(tokens[OIL_INDEX]);
    double gas = Util.parseDouble(tokens[GAS_INDEX]);
    double ngl = Util.parseDouble(tokens[NGL_INDEX]);
    double condensate = Util.parseDouble(tokens[CONDENSATE_INDEX]);
    double oilEquivalents = Util.parseDouble(tokens[OIL_EQUIVALENTS_INDEX]);
    double water = Util.parseDouble(tokens[WATER_INDEX]);

    return new Production.Entry(year,
                                month,
                                oil,
                                gas,
                                ngl,
                                condensate,
                                oilEquivalents,
                                water);
  }

  /**
   * Find field among the specified ones, with the given NPD ID.
   *
   * @param fields  Fields to search. Non-null.
   * @param npdId   NPD ID of field to find. Non-null.
   * @return        Requested field, or null if not found
   */
  private static NpdField findField(List<NpdField> fields, String npdId)
  {
    for (NpdField field : fields)
      if (field.getNpdId().equals(npdId))
        return field;

    // Not found
    return null;
  }

  /**
   * Read production information for the specified fields.
   * <p>
   * When reading is complete the production member of the fields
   * will have been populated.
   *
   * @param urlString  URL to the field production table. Non-null.
   * @param fields     Field to read and populate. Non-null.
   * @throws IllegalArgumentException  If urlString or fields are null.
   * @throws IOException  If the read operation fails for some reason.
   * @see NpdField#getProduction()
   */
  public static void readProduction(String urlString, List<NpdField> fields)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    if (fields == null)
      throw new IllegalArgumentException("fields cannot be null");

    Map<NpdField, List<Production.Entry>> allProductionEntries = new HashMap<>();

    // Create URL instance from the specified string
    URL url;
    try {
      url = new URL(urlString);
    }
    catch (MalformedURLException exception) {
      throw new IOException("Malformed URL: " + urlString);
    }

    // Open connection
    logger_.log(Level.INFO, "Connecting to : " + url);
    InputStream stream = url.openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    try {
      // Skip past the header line
      reader.readLine();

      // Read line by line. There is data for one company per line
      while (true) {
        String line = reader.readLine();
        if (line == null)
          break;

        // Skip empty lines
        if (line.trim().length() == 0)
          continue;

        // Capture the tokens
        String[] tokens = Util.csvSplit(line);

        // Trim and nullify
        for (int i = 0; i < tokens.length; i++) {
          String token = tokens[i];
          String newToken = token.trim();
          if (newToken.length() == 0)
            newToken = null;
          tokens[i] = newToken;
        }

        String npdId = tokens[NPDID_INDEX];
        NpdField field = findField(fields, npdId);

        if (field == null)
          continue;

        List<Production.Entry> productionEntries = allProductionEntries.get(field);
        if (productionEntries == null) {
          productionEntries = new ArrayList<Production.Entry>();
          allProductionEntries.put(field, productionEntries);
        }

        try {
          Production.Entry productionEntry = newProductionEntry(tokens);
          productionEntries.add(productionEntry);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line, exception);
        }
      }
    }
    finally {
      reader.close();
    }

    //
    // Populate the field with their associate production
    //
    int nProductionEntries = 0;
    for (Map.Entry<NpdField, List<Production.Entry>> entry : allProductionEntries.entrySet()) {
      NpdField field = entry.getKey();
      List<Production.Entry> productionEntries = entry.getValue();

      nProductionEntries += productionEntries.size();

      field.setProduction(new Production(productionEntries));
    }

    logger_.log(Level.INFO, "Read " + nProductionEntries + " production entries OK.");
  }

  /**
   * Read production information for the specified field.
   * <p>
   * When reading is complete the production member of the field
   * will have been populated.
   *
   * @param urlString  URL to the field production table. Non-null.
   * @param field      Field to read production of. Non-null.
   * @throws IllegalArgumentException  If urlString or fields are null.
   * @throws IOException  If the read operation fails for some reason.
   * @see NpdField#getProduction()
   */
  public static void readProduction(String urlString, NpdField field)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    if (field == null)
      throw new IllegalArgumentException("field cannot be null");

    readProduction(urlString, Arrays.asList(field));
  }
}

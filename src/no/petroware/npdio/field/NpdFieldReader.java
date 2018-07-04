package no.petroware.npdio.field;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.petroware.npdio.Util;

/**
 * NPD field reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdFieldReader
{
  private static final int NAME_INDEX = 0;
  private static final int OPERATOR_NAME_INDEX = 1;
  private static final int STATUS_INDEX = 2;
  private static final int DISCOVERY_WELLBORE_NAME_INDEX = 3;
  private static final int DISCOVERY_WELLBORE_COMPLETION_DATE_INDEX = 4;
  private static final int MAIN_AREA_INDEX = 5;
  private static final int LICENSE_KIND_INDEX = 6;
  private static final int LICENSE_NAME_INDEX = 7;
  private static final int MAIN_SUPPLY_BASE_INDEX = 8;
  private static final int LICENSE_NPDID_INDEX = 9;
  private static final int NPDID_INDEX = 10;
  private static final int DISCOVERY_WELLBORE_NPDID_INDEX = 11;
  private static final int COMPANY_NPDID_INDEX = 12;
  private static final int FACT_PAGE_URL_INDEX = 13;
  private static final int FACT_MAP_URL_INDEX = 14;
  private static final int DATE_MAIN_LEVEL_UPDATED_INDEX = 15;
  private static final int DATE_ALL_UPDATED_INDEX = 16;
  private static final int DATE_SYNCED_INDEX = 17;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdFieldReader.class.getName());

  /**
   * Private constructor to prevent client instantiation.
   */
  private NpdFieldReader()
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
  private static NpdField newNpdField(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 18)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the field attributes
    //
    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String status = tokens[STATUS_INDEX];
    String discoveryWellboreId = tokens[DISCOVERY_WELLBORE_NPDID_INDEX];
    String licenseId = tokens[LICENSE_NPDID_INDEX];
    String mainArea =  tokens[MAIN_AREA_INDEX];
    String mainSupplyBase = tokens[MAIN_SUPPLY_BASE_INDEX];
    String operaorId = tokens[COMPANY_NPDID_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date lastChangedDate = Util.parseDate(tokens[DATE_ALL_UPDATED_INDEX]);
    Date syncDate = Util.parseDate(tokens[DATE_SYNCED_INDEX]);

    return new NpdField(npdId,
                        name,
                        status,
                        discoveryWellboreId,
                        licenseId,
                        mainArea,
                        mainSupplyBase,
                        operaorId,
                        factPageUrl,
                        factMapUrl,
                        lastChangedDate,
                        syncDate);
  }

  /**
   * Read fields from the specified NPD URL.
   *
   * @param urlString  URL to the field database. Non-null.
   * @return           List of fields read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  public static List<NpdField> readFields(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    // Prepare return structure
    List<NpdField> fields = new ArrayList<>();

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

        try {
          NpdField field = newNpdField(tokens);
          fields.add(field);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line, exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.log(Level.INFO, "Read " + fields.size() + " fields OK.");

    return fields;
  }
}

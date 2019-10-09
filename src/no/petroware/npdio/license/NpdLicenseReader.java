package no.petroware.npdio.license;

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
import java.util.logging.Logger;
import java.util.logging.Level;

import no.petroware.npdio.util.Util;

/**
 * NPD license reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdLicenseReader
{
  private static final int NAME_INDEX = 0;
  private static final int ACTIVITY_INDEX = 1;
  private static final int MAIN_AREA_INDEX = 2;
  private static final int STATUS_INDEX = 3;
  private static final int STRATIGRAPHICAL_INDEX = 4;
  private static final int DATE_GRANTED_INDEX = 5;
  private static final int VALID_TO_DATE_INDEX = 6;
  private static final int ORIGINAL_AREA_INDEX = 7;
  private static final int CURRENT_AREA_INDEX = 8;
  private static final int PHASE_INDEX = 9;
  private static final int NPDID_INDEX = 10;
  private static final int FACT_PAGE_URL_INDEX = 11;
  private static final int FACT_MAP_URL_INDEX = 12;
  private static final int DATE_MAIN_LEVEL_UPDATED_INDEX = 13;
  private static final int DATE_ALL_UPDATED_INDEX = 14;
  private static final int SYNC_DATE_INDEX = 15;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdLicenseReader.class.getName());

  /**
   * Create a new NPD license instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the license database table. Non-null.
   * @return        The created license instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  private static NpdLicense newNpdLicense(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 16)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the license attributes
    //
    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String activity = tokens[ACTIVITY_INDEX];
    String mainArea = tokens[MAIN_AREA_INDEX];
    String status = tokens[STATUS_INDEX];
    String stratigraphical = tokens[STRATIGRAPHICAL_INDEX];
    Date dateGranted = Util.parseDate(tokens[DATE_GRANTED_INDEX]);
    Date validToDate = Util.parseDate(tokens[VALID_TO_DATE_INDEX]);
    double originalArea = 1000.0 * 1000.0 * Util.parseDouble(tokens[ORIGINAL_AREA_INDEX]);
    double currentArea = 1000.0 * 1000.0 * Util.parseDouble(tokens[CURRENT_AREA_INDEX]);
    String phase = tokens[PHASE_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date lastChangedDate = Util.parseDate(tokens[DATE_ALL_UPDATED_INDEX]);
    Date syncDate = Util.parseDate(tokens[SYNC_DATE_INDEX]);

    return new NpdLicense(npdId,
                          name,
                          activity,
                          mainArea,
                          status,
                          stratigraphical,
                          dateGranted,
                          validToDate,
                          originalArea,
                          currentArea,
                          phase,
                          factPageUrl,
                          factMapUrl,
                          lastChangedDate,
                          syncDate);
  }

  /**
   * Read licenses from the specified NPD URL.
   *
   * @param urlString  URL to the license table. Non-null.
   * @return           List of licenses read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  public static List<NpdLicense> readLicenses(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    // Prepare return structure
    List<NpdLicense> licenses = new ArrayList<>();

    // Create URL instance from the specified string
    URL url;
    try {
      url = new URL(urlString);
    }
    catch (MalformedURLException exception) {
      throw new IOException("Malformed URL: " + urlString, exception);
    }

    // Open connection
    logger_.log(Level.INFO, "Connecting to : " + url);
    InputStream stream = url.openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    try {
      // Skip past the header line
      reader.readLine();

      // Read line by line. There is data for one license per line
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
          NpdLicense license = newNpdLicense(tokens);
          licenses.add(license);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line, exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.log(Level.INFO, "Read " + licenses.size() + " licenses OK.");

    return licenses;
  }
}

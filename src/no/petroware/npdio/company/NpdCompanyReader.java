package no.petroware.npdio.company;

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

import no.petroware.npdio.Util;

/**
 * NPD company reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdCompanyReader
{
  private static final int NAME_INDEX = 0;
  private static final int ORGANIZATION_NUMBER_INDEX = 1;
  private static final int SHORT_NAME_INDEX = 2;
  private static final int NATION_CODE_INDEX = 3;
  private static final int SURVEY_PREFIX_INDEX = 4;
  private static final int NPDID_INDEX = 5;
  private static final int IS_CURRENT_LICENSE_OPERATOR_INDEX = 6;
  private static final int IS_FORMER_LICENSE_OPERATOR_INDEX = 7;
  private static final int IS_CURRENT_LICENSE_LICENSEE_INDEX = 8;
  private static final int IS_FORMER_LICENSE_LICENSEE_INDEX = 9;
  private static final int DATE_SYNCED_INDEX = 10;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdCompanyReader.class.getName());

  /**
   * Private constructor to prevent client instantiation-
   */
  private NpdCompanyReader()
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
  private static NpdCompany newNpdCompany(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 11)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the company attributes
    //
    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String organizationNumber = tokens[ORGANIZATION_NUMBER_INDEX];
    String shortName = tokens[SHORT_NAME_INDEX];
    String nationCode = tokens[NATION_CODE_INDEX];
    String surveyPrefix = tokens[SURVEY_PREFIX_INDEX];
    boolean isCurrentLicenseOperator = Util.parseBoolean(tokens[IS_CURRENT_LICENSE_OPERATOR_INDEX]);
    boolean isFormerLicenseOperator = Util.parseBoolean(tokens[IS_FORMER_LICENSE_OPERATOR_INDEX]);
    boolean isCurrentLicenseLicensee = Util.parseBoolean(tokens[IS_CURRENT_LICENSE_LICENSEE_INDEX]);
    boolean isFormerLicenseLicensee = Util.parseBoolean(tokens[IS_FORMER_LICENSE_LICENSEE_INDEX]);
    Date syncDate = Util.parseDate(tokens[DATE_SYNCED_INDEX]);

    return new NpdCompany(npdId,
                          name,
                          organizationNumber,
                          shortName,
                          nationCode,
                          surveyPrefix,
                          isCurrentLicenseOperator,
                          isFormerLicenseOperator,
                          isCurrentLicenseLicensee,
                          isFormerLicenseLicensee,
                          syncDate);
  }

  /**
   * Read companies from the specified NPD URL.
   *
   * @param urlString  URL to the company database. Non-null.
   * @return           List of companies read. Never null.
   * @throws IOException  If the read operation failed for some reason.
   */
  public static List<NpdCompany> readCompanies(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    // Prepare return structure
    List<NpdCompany> companies = new ArrayList<>();

    // Create URL instance from the specified string
    URL url;
    try {
      url = new URL(urlString);
    }
    catch (MalformedURLException exception) {
      throw new IOException("Malformed URL: " + urlString);
    }

    // Open connection
    logger_.info("Connecting to : " + url);
    InputStream stream = url.openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    try {
      // Skip past the header line
      String line = reader.readLine();

      // Read line by line. There is data for one company per line
      line = reader.readLine();
      while (line != null) {

        // Skip empty lines
        if (line.trim().length() == 0) {
          line = reader.readLine();
          continue;
        }

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
          NpdCompany company = newNpdCompany(tokens);
          companies.add(company);
        }
        catch (ParseException exception) {
          logger_.warning("Skip illegal line: " + line + " " + exception);
        }

        line = reader.readLine();
      }
    }
    finally {
      reader.close();
    }

    logger_.info("Read " + companies.size() + " companies OK.");

    return companies;
  }
}

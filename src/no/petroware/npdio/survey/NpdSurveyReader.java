package no.petroware.npdio.survey;

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
 * NPD survey reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdSurveyReader
{
  private static final int NAME_INDEX = 0;
  private static final int PLANNED_START_DATE_INDEX = 1;
  private static final int NPDID_INDEX = 2;
  private static final int STATUS_INDEX = 3;
  private static final int AREA_INDEX = 4;
  private static final int MIDPOINT_INDEX = 5;
  private static final int CATEGORY_INDEX = 6;
  private static final int MAIN_TYPE_INDEX = 7;
  private static final int SUB_TYPE_INDEX = 8;
  private static final int COMPANY_INDEX = 9;
  private static final int VESSEL_INDEX = 10;
  private static final int PLANNED_COMPLETE_DATE_INDEX = 11;
  private static final int START_DATE_INDEX = 12;
  private static final int COMPLETE_DATE_INDEX = 13;
  private static final int PLANNED_TOTAL_LENGTH_BOAT_INDEX = 14;
  private static final int PLANNED_TOTAL_LENGTH_CDP_INDEX = 15;
  private static final int TOTAL_AREA_INDEX = 16;
  private static final int IS_AVAILABLE_INDEX = 17;
  private static final int IS_SAMPLING_DONE_INDEX = 18;
  private static final int IS_SHALLOW_DRILLING_DONE_INDEX = 19;
  private static final int IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX = 20;
  private static final int FACT_PAGE_URL_INDEX = 21;
  private static final int FACT_MAP_URL_INDEX = 22;
  private static final int DATE_SYNCED_INDEX = 23;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdSurveyReader.class.getName());

  /**
   * Private constructor to prevent client instantiation.
   */
  private NpdSurveyReader()
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
  private static NpdSurvey newNpdSurvey(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 24)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the survey attributes
    //
    String name = tokens[NAME_INDEX];
    Date plannedStartDate = Util.parseDate(tokens[PLANNED_START_DATE_INDEX]);
    String npdId = tokens[NPDID_INDEX];
    String status = tokens[STATUS_INDEX];
    String area = tokens[AREA_INDEX];
    String midPoint = tokens[MIDPOINT_INDEX];
    String category = tokens[CATEGORY_INDEX];
    String mainType = tokens[MAIN_TYPE_INDEX];
    String subType = tokens[SUB_TYPE_INDEX];
    String company = tokens[COMPANY_INDEX];
    String vessel = tokens[VESSEL_INDEX];
    Date plannedCompleteDate = Util.parseDate(tokens[PLANNED_COMPLETE_DATE_INDEX]);
    Date startDate = Util.parseDate(tokens[START_DATE_INDEX]);
    Date completeDate = Util.parseDate(tokens[COMPLETE_DATE_INDEX]);
    Double plannedTotalLengthBoat = Util.parseDouble(tokens[PLANNED_TOTAL_LENGTH_BOAT_INDEX]);
    Double plannedTotalLengthCdp = Util.parseDouble(tokens[PLANNED_TOTAL_LENGTH_CDP_INDEX]);
    Double totalArea = Util.parseDouble(tokens[TOTAL_AREA_INDEX]);
    Boolean isAvailable = Util.parseBoolean(tokens[IS_AVAILABLE_INDEX]);
    Boolean isSamplingDone = Util.parseBoolean(tokens[IS_SAMPLING_DONE_INDEX]);
    Boolean isShallowDrillingDone = Util.parseBoolean(tokens[IS_SHALLOW_DRILLING_DONE_INDEX]);
    Boolean isGeotechnicalMeasurementDone = Util.parseBoolean(tokens[IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX]);
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date syncDate = Util.parseDate(tokens[DATE_SYNCED_INDEX]);

    return new NpdSurvey(npdId,
                         name,
                         status,
                         area,
                         midPoint,
                         category,
                         mainType,
                         subType,
                         company,
                         vessel,
                         plannedStartDate,
                         plannedCompleteDate,
                         startDate,
                         completeDate,
                         plannedTotalLengthBoat,
                         plannedTotalLengthCdp,
                         totalArea,
                         isAvailable,
                         isSamplingDone,
                         isShallowDrillingDone,
                         isGeotechnicalMeasurementDone,
                         factPageUrl,
                         factMapUrl,
                         syncDate);
  }

  /**
   * Read surveys from the specified NPD URL.
   *
   * @param urlString  URL to the survey database. Non-null.
   * @return           List of surveys read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  public static List<NpdSurvey> readSurveys(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    // Prepare return structure
    List<NpdSurvey> surveys = new ArrayList<>();

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

      // Read line by line. There is data for one survey per line
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
          NpdSurvey survey = newNpdSurvey(tokens);
          surveys.add(survey);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line, exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.log(Level.INFO, "Read " + surveys.size() + " surveys OK.");

    return surveys;
  }
}

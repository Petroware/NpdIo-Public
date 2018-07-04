package no.petroware.npdio.well;

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
 * Reader for NPD wellbores.
 * <p>
 * This class is thread-safe.

 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdWellboreReader
{
  /**
   * Column indices according to the NPD specification. The data are
   * presented int CSV format and these are the column specifications.
   */

  //
  // Exploration wells
  //
  private static final int E_WELLBORE_NAME_INDEX = 0;
  private static final int E_WELL_NAME_INDEX = 1;
  private static final int E_DRILLING_OPERATOR_INDEX = 2;
  private static final int E_PRODUCTION_LICENSE_INDEX = 3;
  private static final int E_PURPOSE_INDEX = 4;
  private static final int E_STATUS_INDEX = 5;
  private static final int E_CONTENT_INDEX = 6;
  private static final int E_WELL_TYPE_INDEX = 7;
  private static final int E_IS_SUBSEA_INDEX = 8;
  private static final int E_ENTRY_DATE_INDEX = 9;
  private static final int E_COMPLETION_DATE_INDEX = 10;
  private static final int E_FIELD_INDEX = 11;
  private static final int E_DRILL_PERMIT_INDEX = 12;
  private static final int E_DISCOVERY_INDEX = 13;
  private static final int E_DISCOVERY_WELLBORE_INDEX = 14;
  private static final int E_BOTTOM_HOLE_TEMPERATURE_INDEX = 15;
  private static final int E_SEISMIC_LOCATION_INDEX = 16;
  private static final int E_MAX_INCLINATION_INDEX = 17;
  private static final int E_KELLY_BUSH_ELEVATION_INDEX = 18;
  private static final int E_FINAL_VERTICAL_DEPTH_INDEX = 19;
  private static final int E_TOTAL_DEPTH_INDEX = 20;
  private static final int E_WATER_DEPTH_INDEX = 21;
  private static final int E_KICK_OFF_POINT = 22;
  private static final int E_AGE_AT_TD_INDEX = 23;
  private static final int E_FORMATION_AT_TD_INDEX = 24;
  private static final int E_MAIN_AREA_INDEX = 25;
  private static final int E_DRILLING_FACILITY_INDEX = 26;
  private static final int E_FACILITY_TYPE_DRILLING_INDEX = 27;
  private static final int E_DRILLING_FACILITY_FIXED_OR_MOVEABLE = 28;
  private static final int E_LICENSING_ACTIVITY_INDEX = 29;
  private static final int E_MULTILATERAL_INDEX = 30;
  private static final int E_PURPOSE_PLANNED_INDEX = 31;
  private static final int E_ENTRY_YEAR_INDEX = 32;
  private static final int E_COMPLETION_YEAR_INDEX = 33;
  private static final int E_RECLASS_FROM_WELLBORE_INDEX = 34;
  private static final int E_REENTRYEXPLORATIONACTIVITY_INDEX = 35;
  private static final int E_PLOT_SYMBOL_INDEX = 36;
  private static final int E_FORMATION_WITH_HC1_INDEX = 37;
  private static final int E_AGE_WITH_HC1_INDEX = 38;
  private static final int E_FORMATION_WITH_HC2_INDEX = 39;
  private static final int E_AGE_WITH_HC2_INDEX = 40;
  private static final int E_FORMATION_WITH_HC3_INDEX = 41;
  private static final int E_AGE_WITH_HC3_INDEX = 42;
  private static final int E_DRILLING_DAYS_INDEX = 43;
  private static final int E_REENTRY_INDEX = 44;
  private static final int E_GEODETIC_DATUM_INDEX = 45;
  private static final int E_NS_DEG_INDEX = 46;
  private static final int E_NS_MIN_INDEX = 47;
  private static final int E_NS_SEC_INDEX = 48;
  private static final int E_NS_CODE_INDEX = 49;
  private static final int E_EW_DEG_INDEX = 50;
  private static final int E_EW_MIN_INDEX = 51;
  private static final int E_EW_SEC_INDEX = 52;
  private static final int E_EW_CODE_INDEX = 53;
  private static final int E_LATITUDE_INDEX = 54;
  private static final int E_LONGITUDE_INDEX = 55;
  private static final int E_NS_UTM_INDEX = 56;
  private static final int E_EW_UTM_INDEX = 57;
  private static final int E_UTM_ZONE_INDEX = 58;
  private static final int E_NAME_PART1_INDEX = 59;
  private static final int E_NAME_PART2_INDEX = 60;
  private static final int E_NAME_PART3_INDEX = 61;
  private static final int E_NAME_PART4_INDEX = 62;
  private static final int E_NAME_PART5_INDEX = 63;
  private static final int E_NAME_PART6_INDEX = 64;
  private static final int E_PRESS_RELEASE_URL_INDEX = 65;
  private static final int E_FACT_PAGE_URL_INDEX = 66;
  private static final int E_FACT_MAP_URL_INDEX = 67;
  private static final int E_DISKOS_WELLBORE_TYPE_INDEX = 68;
  private static final int E_DISKOS_WELLBORE_PARENT_INDEX = 69;
  private static final int E_WDSS_QC_DATE_INDEX = 70;
  private static final int E_RELEASE_DATE_INDEX = 71;
  private static final int E_NPDID_WELLBORE_INDEX = 72;
  private static final int E_NPDID_DISCOVERY_INDEX = 73;
  private static final int E_NPDID_FIELD_INDEX = 74;
  private static final int E_NPDID_FACILITY_DRILLING_INDEX = 75;
  private static final int E_NPDID_WELLBORE_RECLASS_INDEX = 76;
  private static final int E_NPDID_PRODUCTION_LICENSE_INDEX = 77;
  private static final int E_DATE_UPDATED = 78; // TODO
  private static final int E_DATE_UPDATED_MAX = 79; // TODO
  private static final int E_DATESYNC_NPD_INDEX = 80;

  //
  // Development wells
  //
  private static final int D_WELLBORE_NAME_INDEX = 0;
  private static final int D_WELL_NAME_INDEX = 1;
  private static final int D_DRILLING_OPERATOR_INDEX = 2;
  private static final int D_PRODUCTION_LICENSE_INDEX = 3;
  private static final int D_STATUS = 4;
  private static final int D_PURPOSE_INDEX = 5;
  private static final int D_PURPOSE_PLANNED_INDEX = 6;
  private static final int D_CONTENT_INDEX = 7;
  private static final int D_WELL_TYPE_INDEX = 8;
  private static final int D_IS_SUBSEA_INDEX = 9;
  private static final int D_ENTRY_DATE_INDEX = 10;
  private static final int D_COMPLETION_DATE_INDEX = 11;
  private static final int D_ENTRY_PRE_DRILL_DATE_INDEX = 12;
  private static final int D_COMP_PRE_DRILL_DATE_INDEX = 13;
  private static final int D_FIELD_INDEX = 14;
  private static final int D_DRILL_PERMIT_INDEX = 15;
  private static final int D_DISCOVERY_INDEX = 16;
  private static final int D_DISCOVERY_WELLBORE_INDEX = 17;
  private static final int D_KELLY_BUSH_ELEVATION_INDEX = 18;
  private static final int D_FINAL_VERTICAL_DEPTH_INDEX = 19;
  private static final int D_TOTAL_DEPTH_INDEX = 20;
  private static final int D_WATER_DEPTH_INDEX = 21;
  private static final int D_KICK_OFF_POINT_INDEX = 22;
  private static final int D_MAIN_AREA_INDEX = 23;
  private static final int D_DRILLING_FACILITY_INDEX = 24;
  private static final int D_FACILITY_TYPE_DRILLING_INDEX = 25;
  private static final int D_DRILLING_FACILITY_FIXED_OR_MOVEABLE_INDEX = 26;
  private static final int D_PRODUCTION_FACILITY_INDEX = 27;
  private static final int D_LICENSING_ACTIVITY_INDEX = 28;
  private static final int D_MULTILATERAL_INDEX = 29;
  private static final int D_CONTENT_PLANNED_INDEX = 30;
  private static final int D_ENTRY_YEAR_INDEX = 31;
  private static final int D_COMPLETION_YEAR_INDEX = 32;
  private static final int D_RECLASS_FROM_WELLBORE_INDEX = 33;
  private static final int D_PLOT_SYMBOL_INDEX = 34;
  private static final int D_GEODETIC_DATUM_INDEX = 35;
  private static final int D_NS_DEG_INDEX = 36;
  private static final int D_NS_MIN_INDEX = 37;
  private static final int D_NS_SEC_INDEX = 38;
  private static final int D_NS_CODE_INDEX = 39;
  private static final int D_EW_DEG_INDEX = 40;
  private static final int D_EW_MIN_INDEX = 41;
  private static final int D_EW_SEC_INDEX = 42;
  private static final int D_EW_CODE_INDEX = 43;
  private static final int D_LATITUDE_INDEX = 44;
  private static final int D_LONGITUDE_INDEX = 45;
  private static final int D_NS_UTM_INDEX = 46;
  private static final int D_EW_UTM_INDEX = 47;
  private static final int D_UTM_ZONE_INDEX = 48;
  private static final int D_NAME_PART1_INDEX = 49;
  private static final int D_NAME_PART2_INDEX = 50;
  private static final int D_NAME_PART3_INDEX = 51;
  private static final int D_NAME_PART4_INDEX = 52;
  private static final int D_NAME_PART5_INDEX = 53;
  private static final int D_NAME_PART6_INDEX = 54;
  private static final int D_FACT_PAGE_URL_INDEX = 55;
  private static final int D_FACT_MAP_URL_INDEX = 56;
  private static final int D_DISKOS_WELLBORE_TYPE_INDEX = 57;
  private static final int D_DISKOS_WELLBORE_PARENT_INDEX = 58;
  private static final int D_NPDID_WELLBORE_INDEX = 59;
  private static final int D_NPDID_DISCOVERY_INDEX = 60;
  private static final int D_NPDID_FIELD_INDEX = 61;
  private static final int D_WDSS_QC_DATE_INDEX = 62;
  private static final int D_RELEASE_DATE_INDEX = 63;
  private static final int D_NPDID_PRODUCTION_LICENSE_INDEX = 64;
  private static final int D_NPDID_FACILITY_DRILLING_INDEX = 65;
  private static final int D_NPDID_FACILITY_PRODUCING_INDEX = 66;
  private static final int D_NPDID_WELLBORE_RECLASS_INDEX = 67;
  private static final int D_DATE_UPDATED = 68; // TODO
  private static final int D_DATE_UPDATED_MAX = 69; // TODO
  private static final int D_DATESYNC_NPD_INDEX = 70;

  //
  // Other wells
  //
  private static final int O_WELLBORE_NAME_INDEX = 0;
  private static final int O_WELL_NAME_INDEX = 1;
  private static final int O_WELL_TYPE_INDEX = 2;
  private static final int O_PURPOSE_INDEX = 3;
  private static final int O_DRILLING_OPERATOR_INDEX = 4;
  private static final int O_PRODUCTION_LICENSE_INDEX = 5;
  private static final int O_DRILLING_FACILITY_INDEX = 6;
  private static final int O_ENTRY_DATE_INDEX = 7;
  private static final int O_COMPLETION_DATE_INDEX = 8;
  private static final int O_DRILL_PERMIT_INDEX = 9;
  private static final int O_TOTAL_DEPTH_INDEX = 10;
  private static final int O_KELLY_BUSH_ELEVATION_INDEX = 11;
  private static final int O_WATER_DEPTH_INDEX = 12;
  private static final int O_MAIN_AREA_INDEX = 13;
  private static final int O_ENTRY_YEAR_INDEX = 14;
  private static final int O_COMPLETION_YEAR_INDEX = 15;
  private static final int O_SEISMIC_LOCATION_INDEX = 16;
  private static final int O_GEODETIC_DATUM_INDEX = 17;
  private static final int O_NS_DEG_INDEX = 18;
  private static final int O_NS_MIN_INDEX = 19;
  private static final int O_NS_SEC_INDEX = 20;
  private static final int O_NS_CODE_INDEX = 21;
  private static final int O_EW_DEG_INDEX = 22;
  private static final int O_EW_MIN_INDEX = 23;
  private static final int O_EW_SEC_INDEX = 24;
  private static final int O_EW_CODE_INDEX = 25;
  private static final int O_LATITUDE_INDEX = 26;
  private static final int O_LONGITUDE_INDEX = 27;
  private static final int O_NS_UTM_INDEX = 28;
  private static final int O_EW_UTM_INDEX = 29;
  private static final int O_UTM_ZONE_INDEX = 30;
  private static final int O_NAME_PART1_INDEX = 31;
  private static final int O_NAME_PART2_INDEX = 32;
  private static final int O_NAME_PART3_INDEX = 33;
  private static final int O_NAME_PART4_INDEX = 34;
  private static final int O_NAME_PART5_INDEX = 35;
  private static final int O_NAME_PART6_INDEX = 36;
  private static final int O_NPDID_WELLBORE_INDEX = 37;
  private static final int O_DATE_UPDATED = 38; // TODO
  private static final int O_DATE_UPDATED_MAX = 39; // TODO
  private static final int O_DATESYNC_NPD_INDEX = 40;

  /** NPD date format description */
  private static final String DATE_FORMAT = "dd.MM.yyyy";

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdWellboreReader.class.getName());

  /**
   * Private constructor to prevent client instantiation.
   */
  private NpdWellboreReader()
  {
    assert false : "This should never be called";
  }

  /**
   * Read well data from the specified URL and return as list of
   * wellbore instances.
   *
   * @param urlString  URL to read from.
   * @param wellboreClass  Type of wellbores to read, such as NpdDebvelopmentWellbore.class.
   * @return           List of well instances read.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If read operation failed somehow.
   */
  private static List<? extends NpdWellbore> readWellbores(String urlString, Class<? extends NpdWellbore> wellboreClass)
    throws IOException
  {
    assert urlString != null : "urlString cannot be null";
    assert wellboreClass != null : "wellboreClass cannot be null";

    // Prepare return structure
    List<NpdWellbore> wellbores = new ArrayList<>();

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

      // Read line by line. There is data for one wellbore per line
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

        NpdWellbore wellbore;
        try {
          if (wellboreClass == NpdDevelopmentWellbore.class)
            wellbore = newNpdDevelopmentWellbore(tokens);
          else if (wellboreClass == NpdExplorationWellbore.class)
            wellbore = newNpdExplorationWellbore(tokens);
          else
            wellbore = newNpdOtherWellbore(tokens);

          wellbores.add(wellbore);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line + " " + exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.log(Level.INFO, "Read " + wellbores.size() + " wellbores OK.");

    return wellbores;
  }

  /**
   * Read exploration well data from the specified URL and return as list of
   * wellbore instances.
   *
   * @param urlString  URL to read from. Non-null.
   * @return           List of exploration wellbores read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  @SuppressWarnings("unchecked")
  public static List<NpdExplorationWellbore> readExplorationWellbores(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    return (List<NpdExplorationWellbore>) readWellbores(urlString, NpdExplorationWellbore.class);
  }

  /**
   * Read development well data from the specified URL and return as list of
   * wellbore instances.
   *
   * @param urlString  URL to read from. Non-null.
   * @return           List of development wellbores read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  @SuppressWarnings("unchecked")
  public static List<NpdDevelopmentWellbore> readDevelopmentWellbores(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    return (List<NpdDevelopmentWellbore>) readWellbores(urlString, NpdDevelopmentWellbore.class);
  }

  /**
   * Read other (that is non-development, non-exploration) well data from the specified URL
   * and return as list of wellbore instances.
   *
   * @param urlString  URL to read from. Non-null.
   * @return           List of other wellbores read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  @SuppressWarnings("unchecked")
  public static List<NpdOtherWellbore> readOtherWellbores(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    return (List<NpdOtherWellbore>) readWellbores(urlString, NpdOtherWellbore.class);
  }

  /**
   * Create a new NPD exploration wellbore instance based on the tokens
   * from the CSV file.
   *
   * @param tokens  Tokens of all wellbore attributes.
   * @return        The new NPD wellbore instance.
   * @throws ParseException  If parsing any of the tokens failes.
   */
  private static NpdWellbore newNpdOtherWellbore(String[] tokens)
    throws ParseException
  {
    if (tokens.length != 41)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the wellbore attributes
    //
    String name = tokens[O_WELLBORE_NAME_INDEX];
    String wellName = tokens[O_WELL_NAME_INDEX];
    String wellType = tokens[O_WELL_TYPE_INDEX];
    String purpose = tokens[O_PURPOSE_INDEX];
    String drillingOperator = tokens[O_DRILLING_OPERATOR_INDEX];
    String productionLicense = tokens[O_PRODUCTION_LICENSE_INDEX];
    String drillingFacility = tokens[O_DRILLING_FACILITY_INDEX];
    Date entryDate = Util.parseDate(tokens[O_ENTRY_DATE_INDEX]);
    Date completionDate = Util.parseDate(tokens[O_COMPLETION_DATE_INDEX]);
    String drillPermit = tokens[O_DRILL_PERMIT_INDEX];
    Double totalDepth = Util.parseDouble(tokens[O_TOTAL_DEPTH_INDEX]);
    Double kellyBushElevation = Util.parseDouble(tokens[O_KELLY_BUSH_ELEVATION_INDEX]);
    Double waterDepth = Util.parseDouble(tokens[O_WATER_DEPTH_INDEX]);
    String mainArea = tokens[O_MAIN_AREA_INDEX];
    Integer entryYear = Util.parseInt(tokens[O_ENTRY_YEAR_INDEX]);
    Integer completionYear = Util.parseInt(tokens[O_COMPLETION_YEAR_INDEX]);
    String seismicLocation = tokens[O_SEISMIC_LOCATION_INDEX];
    String geodeticDatum = tokens[O_GEODETIC_DATUM_INDEX];
    Integer nsDeg = Util.parseInt(tokens[O_NS_DEG_INDEX]);
    Integer nsMin = Util.parseInt(tokens[O_NS_MIN_INDEX]);
    Double nsSec = Util.parseDouble(tokens[O_NS_SEC_INDEX]);
    String nsCode = tokens[O_NS_CODE_INDEX];
    Integer ewDeg = Util.parseInt(tokens[O_EW_DEG_INDEX]);
    Integer ewMin = Util.parseInt(tokens[O_EW_MIN_INDEX]);
    Double ewSec = Util.parseDouble(tokens[O_EW_SEC_INDEX]);
    String ewCode = tokens[O_EW_CODE_INDEX];
    Double latitude = Util.parseDouble(tokens[O_LATITUDE_INDEX]);
    Double longitude = Util.parseDouble(tokens[O_LONGITUDE_INDEX]);
    Double nsUtm = Util.parseDouble(tokens[O_NS_UTM_INDEX]);
    Double ewUtm = Util.parseDouble(tokens[O_EW_UTM_INDEX]);
    Integer utmZone = Util.parseInt(tokens[O_UTM_ZONE_INDEX]);
    Integer namePart1 = Util.parseInt(tokens[O_NAME_PART1_INDEX]);
    Integer namePart2 = Util.parseInt(tokens[O_NAME_PART2_INDEX]);
    String namePart3 = tokens[O_NAME_PART3_INDEX];
    Integer namePart4 = Util.parseInt(tokens[O_NAME_PART4_INDEX]);
    String namePart5 = tokens[O_NAME_PART5_INDEX];
    String namePart6 = tokens[O_NAME_PART6_INDEX];
    String npdId = tokens[O_NPDID_WELLBORE_INDEX];
    // TODO
    // TODO
    Date syncDate = Util.parseDate(tokens[O_DATESYNC_NPD_INDEX]);

    //
    // Instantiate the wellbore and set all attributes
    //
    NpdOtherWellbore wellbore = new NpdOtherWellbore(npdId,
                                                     name,
                                                     wellName,
                                                     drillingOperator,
                                                     productionLicense,
                                                     null,
                                                     purpose,
                                                     null,
                                                     null,
                                                     wellType,
                                                     false,
                                                     entryDate,
                                                     completionDate,
                                                     null,
                                                     drillPermit,
                                                     null,
                                                     false,
                                                     kellyBushElevation,
                                                     Double.NaN,
                                                     totalDepth,
                                                     waterDepth,
                                                     null,
                                                     mainArea,
                                                     drillingFacility,
                                                     null,
                                                     null,
                                                     null,
                                                     false,
                                                     entryYear,
                                                     completionYear,
                                                     null,
                                                     -1,
                                                     geodeticDatum,
                                                     nsDeg,
                                                     nsMin,
                                                     nsSec,
                                                     nsCode,
                                                     ewDeg,
                                                     ewMin,
                                                     ewSec,
                                                     ewCode,
                                                     latitude,
                                                     longitude,
                                                     nsUtm,
                                                     ewUtm,
                                                     utmZone,
                                                     namePart1,
                                                     namePart2,
                                                     namePart3,
                                                     namePart4,
                                                     namePart5,
                                                     namePart6,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     null,
                                                     syncDate,
                                                     seismicLocation);

    return wellbore;
  }

  /**
   * Create a new NPD exploration wellbore instance based on the tokens
   * from the CSV file.
   *
   * @param tokens  Tokens of all wellbore attributes.
   * @return        The new NPD wellbore instance.
   * @throws ParseException  If parsing any of the tokens failes.
   */
  private static NpdWellbore newNpdExplorationWellbore(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 81)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the wellbore attributes
    //
    String name = tokens[E_WELLBORE_NAME_INDEX];
    String wellName = tokens[E_WELL_NAME_INDEX];
    String drillingOperator = tokens[E_DRILLING_OPERATOR_INDEX];
    String productionLicense = tokens[E_PRODUCTION_LICENSE_INDEX];
    String purpose = tokens[E_PURPOSE_INDEX];
    String status = tokens[E_STATUS_INDEX];
    String content = tokens[E_CONTENT_INDEX];
    String wellType = tokens[E_WELL_TYPE_INDEX];
    Boolean isSubsea = Util.parseBoolean(tokens[E_IS_SUBSEA_INDEX]);
    Date entryDate = Util.parseDate(tokens[E_ENTRY_DATE_INDEX]);
    Date completionDate = Util.parseDate(tokens[E_COMPLETION_DATE_INDEX]);
    String field = tokens[E_FIELD_INDEX];
    String drillPermit = tokens[E_DRILL_PERMIT_INDEX];
    String discovery = tokens[E_DISCOVERY_INDEX];
    Boolean isDiscoveryWellbore = Util.parseBoolean(tokens[E_DISCOVERY_WELLBORE_INDEX]);
    Integer bottomHoleTemperature = Util.parseInt(tokens[E_BOTTOM_HOLE_TEMPERATURE_INDEX]);
    String seismicLocation = tokens[E_SEISMIC_LOCATION_INDEX];
    Double maxInclination = Util.parseDouble(tokens[E_MAX_INCLINATION_INDEX]);
    Double kellyBushElevation = Util.parseDouble(tokens[E_KELLY_BUSH_ELEVATION_INDEX]);
    Double finalVerticalDepth = Util.parseDouble(tokens[E_FINAL_VERTICAL_DEPTH_INDEX]);
    Double totalDepth = Util.parseDouble(tokens[E_TOTAL_DEPTH_INDEX]);
    Double waterDepth = Util.parseDouble(tokens[E_WATER_DEPTH_INDEX]);
    Double kickOffPoint = Util.parseDouble(tokens[E_KICK_OFF_POINT]);
    String ageAtTd = tokens[E_AGE_AT_TD_INDEX];
    String formationAtTd = tokens[E_FORMATION_AT_TD_INDEX];
    String mainArea = tokens[E_MAIN_AREA_INDEX];
    String drillingFacility = tokens[E_DRILLING_FACILITY_INDEX];
    String drillingFacilityType = tokens[E_FACILITY_TYPE_DRILLING_INDEX];
    String drillingFacilityCategory = tokens[E_DRILLING_FACILITY_FIXED_OR_MOVEABLE];
    String licensingActivity = tokens[E_LICENSING_ACTIVITY_INDEX];
    Boolean isMultilateral = Util.parseBoolean(tokens[E_MULTILATERAL_INDEX]);
    String purposePlanned = tokens[E_PURPOSE_PLANNED_INDEX];
    Integer entryYear = Util.parseInt(tokens[E_ENTRY_YEAR_INDEX]);
    Integer completionYear = Util.parseInt(tokens[E_COMPLETION_YEAR_INDEX]);
    String reclassFromWellbore = tokens[E_RECLASS_FROM_WELLBORE_INDEX];
    String reentryExplorationActivity = tokens[E_REENTRYEXPLORATIONACTIVITY_INDEX];
    Integer plotSymbol = Util.parseInt(tokens[E_PLOT_SYMBOL_INDEX]);
    String formationWithHc1 = tokens[E_FORMATION_WITH_HC1_INDEX];
    String ageWithHc1 = tokens[E_AGE_WITH_HC1_INDEX];
    String formationWithHc2 = tokens[E_FORMATION_WITH_HC2_INDEX];
    String ageWithHc2 = tokens[E_AGE_WITH_HC2_INDEX];
    String formationWithHc3 = tokens[E_FORMATION_WITH_HC3_INDEX];
    String ageWithHc3 = tokens[E_AGE_WITH_HC3_INDEX];
    Integer drillingDays = Util.parseInt(tokens[E_DRILLING_DAYS_INDEX]);
    Boolean isReentry = Util.parseBoolean(tokens[E_REENTRY_INDEX]);
    String geodeticDatum = tokens[E_GEODETIC_DATUM_INDEX];
    Integer nsDeg = Util.parseInt(tokens[E_NS_DEG_INDEX]);
    Integer nsMin = Util.parseInt(tokens[E_NS_MIN_INDEX]);
    Double nsSec = Util.parseDouble(tokens[E_NS_SEC_INDEX]);
    String nsCode = tokens[E_NS_CODE_INDEX];
    Integer ewDeg = Util.parseInt(tokens[E_EW_DEG_INDEX]);
    Integer ewMin = Util.parseInt(tokens[E_EW_MIN_INDEX]);
    Double ewSec = Util.parseDouble(tokens[E_EW_SEC_INDEX]);
    String ewCode = tokens[E_EW_CODE_INDEX];
    Double latitude = Util.parseDouble(tokens[E_LATITUDE_INDEX]);
    Double longitude = Util.parseDouble(tokens[E_LONGITUDE_INDEX]);
    Double nsUtm = Util.parseDouble(tokens[E_NS_UTM_INDEX]);
    Double ewUtm = Util.parseDouble(tokens[E_EW_UTM_INDEX]);
    Integer utmZone = Util.parseInt(tokens[E_UTM_ZONE_INDEX]);
    Integer namePart1 = Util.parseInt(tokens[E_NAME_PART1_INDEX]);
    Integer namePart2 = Util.parseInt(tokens[E_NAME_PART2_INDEX]);
    String namePart3 = tokens[E_NAME_PART3_INDEX];
    Integer namePart4 = Util.parseInt(tokens[E_NAME_PART4_INDEX]);
    String namePart5 = tokens[E_NAME_PART5_INDEX];
    String namePart6 = tokens[E_NAME_PART6_INDEX];
    String pressReleaseUrl = tokens[E_PRESS_RELEASE_URL_INDEX];
    String factPageUrl = tokens[E_FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[E_FACT_MAP_URL_INDEX];
    String diskosWellboreType = tokens[E_DISKOS_WELLBORE_TYPE_INDEX];
    String diskosWellboreParent = tokens[E_DISKOS_WELLBORE_PARENT_INDEX];
    Date wdssQcDate = Util.parseDate(tokens[E_WDSS_QC_DATE_INDEX]);
    Date releaseDate = Util.parseDate(tokens[E_RELEASE_DATE_INDEX]);
    String npdId = tokens[E_NPDID_WELLBORE_INDEX];
    String npdidDiscovery = tokens[E_NPDID_DISCOVERY_INDEX];
    String npdidField = tokens[E_NPDID_FIELD_INDEX];
    String npdidFacilityDrilling = tokens[E_NPDID_FACILITY_DRILLING_INDEX];
    String npdidWellboreReclass = tokens[E_NPDID_WELLBORE_RECLASS_INDEX];
    String npdidProductionLicense = tokens[E_NPDID_PRODUCTION_LICENSE_INDEX];
    Date syncDate = Util.parseDate(tokens[E_DATESYNC_NPD_INDEX]);

    //
    // Instantiate the wellbore and set all attributes
    //
    NpdExplorationWellbore wellbore =
      new NpdExplorationWellbore(npdId,
                                 name,
                                 wellName,
                                 drillingOperator,
                                 productionLicense,
                                 status,
                                 purpose,
                                 purposePlanned,
                                 content,
                                 wellType,
                                 isSubsea,
                                 entryDate,
                                 completionDate,
                                 field,
                                 drillPermit,
                                 discovery,
                                 isDiscoveryWellbore,
                                 kellyBushElevation,
                                 finalVerticalDepth,
                                 totalDepth,
                                 waterDepth,
                                 kickOffPoint,
                                 mainArea,
                                 drillingFacility,
                                 drillingFacilityType,
                                 drillingFacilityCategory,
                                 licensingActivity,
                                 isMultilateral,
                                 entryYear,
                                 completionYear,
                                 reclassFromWellbore,
                                 plotSymbol,
                                 geodeticDatum,
                                 nsDeg,
                                 nsMin,
                                 nsSec,
                                 nsCode,
                                 ewDeg,
                                 ewMin,
                                 ewSec,
                                 ewCode,
                                 latitude,
                                 longitude,
                                 nsUtm,
                                 ewUtm,
                                 utmZone,
                                 namePart1,
                                 namePart2,
                                 namePart3,
                                 namePart4,
                                 namePart5,
                                 namePart6,
                                 factPageUrl,
                                 factMapUrl,
                                 diskosWellboreType,
                                 diskosWellboreParent,
                                 npdidDiscovery,
                                 npdidField,
                                 wdssQcDate,
                                 releaseDate,
                                 npdidProductionLicense,
                                 npdidFacilityDrilling,
                                 npdidWellboreReclass,
                                 syncDate,
                                 bottomHoleTemperature,
                                 seismicLocation,
                                 maxInclination,
                                 ageAtTd,
                                 formationAtTd,
                                 reentryExplorationActivity,
                                 formationWithHc1,
                                 ageWithHc1,
                                 formationWithHc2,
                                 ageWithHc2,
                                 formationWithHc3,
                                 ageWithHc3,
                                 drillingDays,
                                 isReentry,
                                 pressReleaseUrl);

    return wellbore;
  }

  /**
   * Create a new NPD development wellbore instance based on the tokens from
   * the CSV file.
   *
   * @param tokens  Tokens of all wellbore attributes.
   * @return        The new NPD wellbore instance.
   * @throws ParseException  If parsing any of the tokens failes.
   */
  private static NpdWellbore newNpdDevelopmentWellbore(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 71)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    //
    // Capture all the wellbore attributes
    //
    String name = tokens[D_WELLBORE_NAME_INDEX];
    String wellName = tokens[D_WELL_NAME_INDEX];
    String drillingOperator = tokens[D_DRILLING_OPERATOR_INDEX];
    String productionLicense = tokens[D_PRODUCTION_LICENSE_INDEX];
    String status = tokens[D_STATUS];
    String purpose = tokens[D_PURPOSE_INDEX];
    String purposePlanned = tokens[D_PURPOSE_PLANNED_INDEX];
    String content = tokens[D_CONTENT_INDEX];
    String wellType = tokens[D_WELL_TYPE_INDEX];
    Boolean isSubsea = Util.parseBoolean(tokens[D_IS_SUBSEA_INDEX]);
    Date entryDate = Util.parseDate(tokens[D_ENTRY_DATE_INDEX]);
    Date completionDate = Util.parseDate(tokens[D_COMPLETION_DATE_INDEX]);
    Date preDrillEntryDate = Util.parseDate(tokens[D_ENTRY_PRE_DRILL_DATE_INDEX]);
    Date preDrillCompletionDate = Util.parseDate(tokens[D_COMP_PRE_DRILL_DATE_INDEX]);
    String field = tokens[D_FIELD_INDEX];
    String drillPermit = tokens[D_DRILL_PERMIT_INDEX];
    String discovery = tokens[D_DISCOVERY_INDEX];
    Boolean isDiscoveryWellbore = Util.parseBoolean(tokens[D_DISCOVERY_WELLBORE_INDEX]);
    Double kellyBushElevation = Util.parseDouble(tokens[D_KELLY_BUSH_ELEVATION_INDEX]);
    Double finalVerticalDepth = Util.parseDouble(tokens[D_FINAL_VERTICAL_DEPTH_INDEX]);
    Double totalDepth = Util.parseDouble(tokens[D_TOTAL_DEPTH_INDEX]);
    Double waterDepth = Util.parseDouble(tokens[D_WATER_DEPTH_INDEX]);
    Double kickOffPoint = Util.parseDouble(tokens[D_KICK_OFF_POINT_INDEX]);
    String mainArea = tokens[D_MAIN_AREA_INDEX];
    String drillingFacility = tokens[D_DRILLING_FACILITY_INDEX];
    String drillingFacilityType = tokens[D_FACILITY_TYPE_DRILLING_INDEX];
    String drillingFacilityCategory = tokens[D_DRILLING_FACILITY_FIXED_OR_MOVEABLE_INDEX];
    String productionFacility = tokens[D_PRODUCTION_FACILITY_INDEX];
    String licensingActivity = tokens[D_LICENSING_ACTIVITY_INDEX];
    Boolean isMultilateral = Util.parseBoolean(tokens[D_MULTILATERAL_INDEX]);
    String contentPlanned = tokens[D_CONTENT_PLANNED_INDEX];
    Integer entryYear = Util.parseInt(tokens[D_ENTRY_YEAR_INDEX]);
    Integer completionYear = Util.parseInt(tokens[D_COMPLETION_YEAR_INDEX]);
    String reclassFromWellbore = tokens[D_RECLASS_FROM_WELLBORE_INDEX];
    Integer plotSymbol = Util.parseInt(tokens[D_PLOT_SYMBOL_INDEX]);
    String geodeticDatum = tokens[D_GEODETIC_DATUM_INDEX];
    Integer nsDeg = Util.parseInt(tokens[D_NS_DEG_INDEX]);
    Integer nsMin = Util.parseInt(tokens[D_NS_MIN_INDEX]);
    Double nsSec = Util.parseDouble(tokens[D_NS_SEC_INDEX]);
    String nsCode = tokens[D_NS_CODE_INDEX];
    Integer ewDeg = Util.parseInt(tokens[D_EW_DEG_INDEX]);
    Integer ewMin = Util.parseInt(tokens[D_EW_MIN_INDEX]);
    Double ewSec = Util.parseDouble(tokens[D_EW_SEC_INDEX]);
    String ewCode = tokens[D_EW_CODE_INDEX];
    Double latitude = Util.parseDouble(tokens[D_LATITUDE_INDEX]);
    Double longitude = Util.parseDouble(tokens[D_LONGITUDE_INDEX]);
    Double nsUtm = Util.parseDouble(tokens[D_NS_UTM_INDEX]);
    Double ewUtm = Util.parseDouble(tokens[D_EW_UTM_INDEX]);
    Integer utmZone = Util.parseInt(tokens[D_UTM_ZONE_INDEX]);
    Integer namePart1 = Util.parseInt(tokens[D_NAME_PART1_INDEX]);
    Integer namePart2 = Util.parseInt(tokens[D_NAME_PART2_INDEX]);
    String namePart3 = tokens[D_NAME_PART3_INDEX];
    Integer namePart4 = Util.parseInt(tokens[D_NAME_PART4_INDEX]);
    String namePart5 = tokens[D_NAME_PART5_INDEX];
    String namePart6 = tokens[D_NAME_PART6_INDEX];
    String factPageUrl = tokens[D_FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[D_FACT_MAP_URL_INDEX];
    String diskosWellboreType = tokens[D_DISKOS_WELLBORE_TYPE_INDEX];
    String diskosWellboreParent = tokens[D_DISKOS_WELLBORE_PARENT_INDEX];
    String npdId = tokens[D_NPDID_WELLBORE_INDEX];
    String npdidDiscovery = tokens[D_NPDID_DISCOVERY_INDEX];
    String npdidField = tokens[D_NPDID_FIELD_INDEX];
    Date wdssQcDate = Util.parseDate(tokens[D_WDSS_QC_DATE_INDEX]);
    Date releaseDate = Util.parseDate(tokens[D_RELEASE_DATE_INDEX]);
    String npdidProductionLicense = tokens[D_NPDID_PRODUCTION_LICENSE_INDEX];
    String npdidFacilityDrilling = tokens[D_NPDID_FACILITY_DRILLING_INDEX];
    String npdidFacilityProducing = tokens[D_NPDID_FACILITY_PRODUCING_INDEX];
    String npdidWellboreReclass = tokens[D_NPDID_WELLBORE_RECLASS_INDEX];
    Date syncDate = Util.parseDate(tokens[D_DATESYNC_NPD_INDEX]);

    //
    // Create wellbore
    //
    NpdDevelopmentWellbore wellbore =
      new NpdDevelopmentWellbore(npdId,
                                 name,
                                 wellName,
                                 drillingOperator,
                                 productionLicense,
                                 status,
                                 purpose,
                                 purposePlanned,
                                 content,
                                 wellType,
                                 isSubsea,
                                 entryDate,
                                 completionDate,
                                 preDrillEntryDate,
                                 preDrillCompletionDate,
                                 field,
                                 drillPermit,
                                 discovery,
                                 isDiscoveryWellbore,
                                 kellyBushElevation,
                                 finalVerticalDepth,
                                 totalDepth,
                                 waterDepth,
                                 kickOffPoint,
                                 mainArea,
                                 drillingFacility,
                                 drillingFacilityType,
                                 drillingFacilityCategory,
                                 licensingActivity,
                                 isMultilateral,
                                 entryYear,
                                 completionYear,
                                 reclassFromWellbore,
                                 plotSymbol,
                                 geodeticDatum,
                                 nsDeg,
                                 nsMin,
                                 nsSec,
                                 nsCode,
                                 ewDeg,
                                 ewMin,
                                 ewSec,
                                 ewCode,
                                 latitude,
                                 longitude,
                                 nsUtm,
                                 ewUtm,
                                 utmZone,
                                 namePart1,
                                 namePart2,
                                 namePart3,
                                 namePart4,
                                 namePart5,
                                 namePart6,
                                 factPageUrl,
                                 factMapUrl,
                                 diskosWellboreType,
                                 diskosWellboreParent,
                                 npdidDiscovery,
                                 npdidField,
                                 wdssQcDate,
                                 releaseDate,
                                 npdidProductionLicense,
                                 npdidFacilityDrilling,
                                 npdidWellboreReclass,
                                 syncDate,
                                 productionFacility,
                                 contentPlanned,
                                 npdidFacilityProducing);

      return wellbore;
  }
}

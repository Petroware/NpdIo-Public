package no.petroware.npdio.well;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD <em>other</em> wellbores.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdOtherWellboreReader extends NpdReader<NpdOtherWellbore>
{
  /** URL to the NPD file containing the data. */
  private static final String URL = "https://npdfactpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/wellbore_other_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=80.239.106.206&CultureCode=en";

  /**
   * The development wellbore properties and their order is as follows:
   *
   *   wlbWellboreName
   *   wlbWell
   *   wlbWellType
   *   wlbPurpose
   *   wlbDrillingOperator
   *   wlbProductionLicence
   *   wlbDrillingFacility
   *   wlbEntryDate
   *   wlbCompletionDate
   *   wlbDrillPermit
   *   wlbTotalDepth
   *   wlbKellyBushElevation
   *   wlbWaterDepth
   *   wlbMainArea
   *   wlbEntryYear
   *   wlbCompletionYear
   *   wlbSiteSurvey
   *   wlbSeismicLocation
   *   wlbGeodeticDatum
   *   wlbLicenceTargetName
   *   wlbPluggedAbandonDate
   *   wlbPluggedDate
   *   wlbNsDeg
   *   wlbNsMin
   *   wlbNsSec
   *   wlbNsCode
   *   wlbEwDeg
   *   wlbEwMin
   *   wlbEwSec
   *   wlbEwCode
   *   wlbNsDecDeg
   *   wlbEwDesDeg
   *   wlbNsUtm
   *   wlbEwUtm
   *   wlbUtmZone
   *   wlbNamePart1
   *   wlbNamePart2
   *   wlbNamePart3
   *   wlbNamePart4
   *   wlbNamePart5
   *   wlbNamePart6
   *   wlbNpdidWellbore
   *   wlbNpdidSiteSurvey
   *   wlbFactPageUrl
   *   wlbDateUpdated
   *   wlbDateUpdatedMax
   *   datesyncNPD
   */
  private static final int WELLBORE_NAME_INDEX = 0;
  private static final int WELL_NAME_INDEX = 1;
  private static final int WELL_TYPE_INDEX = 2;
  private static final int PURPOSE_INDEX = 3;
  private static final int DRILLING_OPERATOR_INDEX = 4;
  private static final int PRODUCTION_LICENSE_INDEX = 5;
  private static final int DRILLING_FACILITY_INDEX = 6;
  private static final int ENTRY_DATE_INDEX = 7;
  private static final int COMPLETION_DATE_INDEX = 8;
  private static final int DRILL_PERMIT_INDEX = 9;
  private static final int TOTAL_DEPTH_INDEX = 10;
  private static final int KELLY_BUSH_ELEVATION_INDEX = 11;
  private static final int WATER_DEPTH_INDEX = 12;
  private static final int MAIN_AREA_INDEX = 13;
  private static final int ENTRY_YEAR_INDEX = 14;
  private static final int COMPLETION_YEAR_INDEX = 15;
  private static final int SITE_SURVEY_INDEX = 16;
  private static final int SEISMIC_LOCATION_INDEX = 17;
  private static final int GEODETIC_DATUM_INDEX = 18;
  private static final int LICENSE_TARGET_NAME_INDEX = 19;
  private static final int PLUGGED_AND_ABANDON_DATE_INDEX = 20;
  private static final int PLUGGED_DATE_INDEX = 21;
  private static final int NS_DEG_INDEX = 22;
  private static final int NS_MIN_INDEX = 23;
  private static final int NS_SEC_INDEX = 24;
  private static final int NS_CODE_INDEX = 25;
  private static final int EW_DEG_INDEX = 26;
  private static final int EW_MIN_INDEX = 27;
  private static final int EW_SEC_INDEX = 28;
  private static final int EW_CODE_INDEX = 29;
  private static final int LATITUDE_INDEX = 30;
  private static final int LONGITUDE_INDEX = 31;
  private static final int NS_UTM_INDEX = 32;
  private static final int EW_UTM_INDEX = 33;
  private static final int UTM_ZONE_INDEX = 34;
  private static final int NAME_PART1_INDEX = 35;
  private static final int NAME_PART2_INDEX = 36;
  private static final int NAME_PART3_INDEX = 37;
  private static final int NAME_PART4_INDEX = 38;
  private static final int NAME_PART5_INDEX = 39;
  private static final int NAME_PART6_INDEX = 40;
  private static final int NPDID_WELLBORE_INDEX = 41;
  private static final int NPDID_SITE_SURVEY_INDEX = 42;
  private static final int FACT_PAGE_URL_INDEX = 43;
  private static final int MAIN_LEVEL_UPDATED_DATE_INDEX = 44;
  private static final int UPDATED_DATE_INDEX = 45;
  private static final int DATESYNC_NPD_INDEX = 46;

  /**
   * Create a reader for NPD <em>other</em> wellbores.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdOtherWellboreReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD <em>other</em> wellbores.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdOtherWellboreReader reader = new NpdOtherWellboreReader(url);
   *   List&lt;NpdOtherWellbore&gt; wellbores = reader.read();
   * </pre>
   *
   * @return  All NPD other wellbores. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdOtherWellbore> readAll()
    throws IOException
  {
    NpdOtherWellboreReader reader = new NpdOtherWellboreReader(URL);
    return reader.read();
  }

  /**
   * Create a new NPD other wellbore instance based on the tokens
   * from the CSV file.
   *
   * @param tokens  Tokens of all wellbore attributes.
   * @return        The new NPD wellbore instance.
   * @throws ParseException  If parsing any of the tokens fails.
   */
  protected NpdOtherWellbore newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 47)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[WELLBORE_NAME_INDEX];
    String wellName = tokens[WELL_NAME_INDEX];
    String wellType = tokens[WELL_TYPE_INDEX];
    String purpose = tokens[PURPOSE_INDEX];
    String drillingOperator = tokens[DRILLING_OPERATOR_INDEX];
    String productionLicense = tokens[PRODUCTION_LICENSE_INDEX];
    String drillingFacility = tokens[DRILLING_FACILITY_INDEX];
    Date entryDate = parseDate(tokens[ENTRY_DATE_INDEX]);
    Date completionDate = parseDate(tokens[COMPLETION_DATE_INDEX]);
    String drillPermit = tokens[DRILL_PERMIT_INDEX];
    Double totalDepth = parseDouble(tokens[TOTAL_DEPTH_INDEX]);
    Double kellyBushElevation = parseDouble(tokens[KELLY_BUSH_ELEVATION_INDEX]);
    Double waterDepth = parseDouble(tokens[WATER_DEPTH_INDEX]);
    String mainArea = tokens[MAIN_AREA_INDEX];
    Integer entryYear = parseInt(tokens[ENTRY_YEAR_INDEX]);
    Integer completionYear = parseInt(tokens[COMPLETION_YEAR_INDEX]);
    String siteSurvey = tokens[SITE_SURVEY_INDEX];
    String seismicLocation = tokens[SEISMIC_LOCATION_INDEX];
    String geodeticDatum = tokens[GEODETIC_DATUM_INDEX];
    String licenseTargetName = tokens[LICENSE_TARGET_NAME_INDEX];
    Date pluggedAndAbondonDate = parseDate(tokens[PLUGGED_AND_ABANDON_DATE_INDEX]);
    Date pluggedDate = parseDate(tokens[PLUGGED_DATE_INDEX]);
    Integer nsDeg = parseInt(tokens[NS_DEG_INDEX]);
    Integer nsMin = parseInt(tokens[NS_MIN_INDEX]);
    Double nsSec = parseDouble(tokens[NS_SEC_INDEX]);
    String nsCode = tokens[NS_CODE_INDEX];
    Integer ewDeg = parseInt(tokens[EW_DEG_INDEX]);
    Integer ewMin = parseInt(tokens[EW_MIN_INDEX]);
    Double ewSec = parseDouble(tokens[EW_SEC_INDEX]);
    String ewCode = tokens[EW_CODE_INDEX];
    Double latitude = parseDouble(tokens[LATITUDE_INDEX]);
    Double longitude = parseDouble(tokens[LONGITUDE_INDEX]);
    Double nsUtm = parseDouble(tokens[NS_UTM_INDEX]);
    Double ewUtm = parseDouble(tokens[EW_UTM_INDEX]);
    Integer utmZone = parseInt(tokens[UTM_ZONE_INDEX]);
    Integer namePart1 = parseInt(tokens[NAME_PART1_INDEX]);
    Integer namePart2 = parseInt(tokens[NAME_PART2_INDEX]);
    String namePart3 = tokens[NAME_PART3_INDEX];
    Integer namePart4 = parseInt(tokens[NAME_PART4_INDEX]);
    String namePart5 = tokens[NAME_PART5_INDEX];
    String namePart6 = tokens[NAME_PART6_INDEX];
    String npdId = tokens[NPDID_WELLBORE_INDEX];
    String npdidSiteSurvey = tokens[NPDID_SITE_SURVEY_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    Date mainLevelUpdatedDate = parseDate(tokens[MAIN_LEVEL_UPDATED_DATE_INDEX]);
    Date updatedDate = parseDate(tokens[UPDATED_DATE_INDEX]);
    Date syncDate = parseDate(tokens[DATESYNC_NPD_INDEX]);

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
                                                     null, // reclassificationDate
                                                     siteSurvey,
                                                     npdidSiteSurvey,
                                                     pluggedAndAbondonDate,
                                                     pluggedDate,
                                                     licenseTargetName,
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
                                                     factPageUrl,
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
                                                     mainLevelUpdatedDate,
                                                     updatedDate,
                                                     syncDate,
                                                     seismicLocation);

    return wellbore;
  }
}

package no.petroware.npdio.well;

import java.text.ParseException;
import java.util.Date;

import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD exploration wellbores.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdExplorationWellboreReader extends NpdReader<NpdExplorationWellbore>
{
  /**
   * The exploration wellbore properties and their order is as follows:
   *
   *   wlbWellboreName
   *   wlbWell
   *   wlbDrillingOperator
   *   wlbProductionLicence
   *   wlbPurpose
   *   wlbStatus
   *   wlbContent
   *   wlbWellType
   *   wlbSubSea
   *   wlbEntryDate
   *   wlbCompletionDate
   *   wlbField
   *   wlbDrillPermit
   *   wlbDiscovery
   *   wlbDiscoveryWellbore
   *   wlbBottomHoleTemperature
   *   wlbSiteSurvey
   *   wlbSeismicLocation
   *   wlbMaxInclation
   *   wlbKellyBushElevation
   *   wlbFinalVerticalDepth
   *   wlbTotalDepth
   *   wlbWaterDepth
   *   wlbKickOffPoint
   *   wlbAgeAtTd
   *   wlbFormationAtTd
   *   wlbMainArea
   *   wlbDrillingFacility
   *   wlbFacilityTypeDrilling
   *   wlbDrillingFacilityFixedOrMoveable
   *   wlbLicensingActivity
   *   wlbMultilateral
   *   wlbPurposePlanned
   *   wlbEntryYear
   *   wlbCompletionYear
   *   wlbReclassFromWellbore
   *   wlbReentryExplorationActivity
   *   wlbPlotSymbol
   *   wlbFormationWithHc1
   *   wlbAgeWithHc1
   *   wlbFormationWithHc2
   *   wlbAgeWithHc2
   *   wlbFormationWithHc3
   *   wlbAgeWithHc3
   *   wlbDrillingDays
   *   wlbReentry
   *   wlbLicenceTargetName
   *   wlbPluggedAbandonDate
   *   wlbPluggedDate
   *   wlbGeodeticDatum
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
   *   wlbPressReleaseUrl
   *   wlbFactPageUrl
   *   wlbFactMapUrl
   *   wlbDiskosWellboreType
   *   wlbDiskosWellboreParent
   *   wlbWdssQcDate
   *   wlbReleasedDate
   *   wlbDateReclass
   *   wlbNpdidWellbore
   *   dscNpdidDiscovery
   *   fldNpdidField
   *   fclNpdidFacilityDrilling
   *   wlbNpdidWellboreReclass
   *   prlNpdidProductionLicence
   *   wlbNpdidSiteSurvey
   *   wlbDateUpdated
   *   wlbDateUpdatedMax
   *   datesyncNPD
   */
  private static final int WELLBORE_NAME_INDEX = 0;
  private static final int WELL_NAME_INDEX = 1;
  private static final int DRILLING_OPERATOR_INDEX = 2;
  private static final int PRODUCTION_LICENSE_INDEX = 3;
  private static final int PURPOSE_INDEX = 4;
  private static final int STATUS_INDEX = 5;
  private static final int CONTENT_INDEX = 6;
  private static final int WELL_TYPE_INDEX = 7;
  private static final int IS_SUBSEA_INDEX = 8;
  private static final int ENTRY_DATE_INDEX = 9;
  private static final int COMPLETION_DATE_INDEX = 10;
  private static final int FIELD_INDEX = 11;
  private static final int DRILL_PERMIT_INDEX = 12;
  private static final int DISCOVERY_INDEX = 13;
  private static final int DISCOVERY_WELLBORE_INDEX = 14;
  private static final int BOTTOM_HOLE_TEMPERATURE_INDEX = 15;
  private static final int SITE_SURVEY_INDEX = 16;
  private static final int SEISMIC_LOCATION_INDEX = 17;
  private static final int MAX_INCLINATION_INDEX = 18;
  private static final int KELLY_BUSH_ELEVATION_INDEX = 19;
  private static final int FINAL_VERTICAL_DEPTH_INDEX = 20;
  private static final int TOTAL_DEPTH_INDEX = 21;
  private static final int WATER_DEPTH_INDEX = 22;
  private static final int KICK_OFF_POINT = 23;
  private static final int AGE_AT_TD_INDEX = 24;
  private static final int FORMATION_AT_TD_INDEX = 25;
  private static final int MAIN_AREA_INDEX = 26;
  private static final int DRILLING_FACILITY_INDEX = 27;
  private static final int FACILITY_TYPE_DRILLING_INDEX = 28;
  private static final int DRILLING_FACILITY_FIXED_OR_MOVEABLE = 29;
  private static final int LICENSING_ACTIVITY_INDEX = 30;
  private static final int MULTILATERAL_INDEX = 31;
  private static final int PURPOSE_PLANNED_INDEX = 32;
  private static final int ENTRY_YEAR_INDEX = 33;
  private static final int COMPLETION_YEAR_INDEX = 34;
  private static final int RECLASS_FROM_WELLBORE_INDEX = 35;
  private static final int REENTRYEXPLORATIONACTIVITY_INDEX = 36;
  private static final int PLOT_SYMBOL_INDEX = 37;
  private static final int FORMATION_WITH_HC1_INDEX = 38;
  private static final int AGE_WITH_HC1_INDEX = 39;
  private static final int FORMATION_WITH_HC2_INDEX = 40;
  private static final int AGE_WITH_HC2_INDEX = 41;
  private static final int FORMATION_WITH_HC3_INDEX = 42;
  private static final int AGE_WITH_HC3_INDEX = 43;
  private static final int DRILLING_DAYS_INDEX = 44;
  private static final int REENTRY_INDEX = 45;
  private static final int LICENSE_TARGET_NAME_INDEX = 46;
  private static final int PLUGGED_AND_ABANDON_DATE_INDEX = 47;
  private static final int PLUGGED_DATE_INDEX = 48;
  private static final int GEODETIC_DATUM_INDEX = 49;
  private static final int NS_DEG_INDEX = 50;
  private static final int NS_MIN_INDEX = 51;
  private static final int NS_SEC_INDEX = 52;
  private static final int NS_CODE_INDEX = 53;
  private static final int EW_DEG_INDEX = 54;
  private static final int EW_MIN_INDEX = 55;
  private static final int EW_SEC_INDEX = 56;
  private static final int EW_CODE_INDEX = 57;
  private static final int LATITUDE_INDEX = 58;
  private static final int LONGITUDE_INDEX = 59;
  private static final int NS_UTM_INDEX = 60;
  private static final int EW_UTM_INDEX = 61;
  private static final int UTM_ZONE_INDEX = 62;
  private static final int NAME_PART1_INDEX = 63;
  private static final int NAME_PART2_INDEX = 64;
  private static final int NAME_PART3_INDEX = 65;
  private static final int NAME_PART4_INDEX = 66;
  private static final int NAME_PART5_INDEX = 67;
  private static final int NAME_PART6_INDEX = 68;
  private static final int PRESS_RELEASE_URL_INDEX = 69;
  private static final int FACT_PAGE_URL_INDEX = 70;
  private static final int FACT_MAP_URL_INDEX = 71;
  private static final int DISKOS_WELLBORE_TYPE_INDEX = 72;
  private static final int DISKOS_WELLBORE_PARENT_INDEX = 73;
  private static final int WDSS_QC_DATE_INDEX = 74;
  private static final int RELEASE_DATE_INDEX = 75;
  private static final int RECLASSIFICATION_DATE_INDEX = 76;
  private static final int NPDID_WELLBORE_INDEX = 77;
  private static final int NPDID_DISCOVERY_INDEX = 78;
  private static final int NPDID_FIELD_INDEX = 79;
  private static final int NPDID_FACILITY_DRILLING_INDEX = 80;
  private static final int NPDID_WELLBORE_RECLASS_INDEX = 81;
  private static final int NPDID_PRODUCTION_LICENSE_INDEX = 82;
  private static final int NPDID_SITE_SURVEY_INDEX = 83;
  private static final int MAIN_LEVEL_UPDATED_DATE_INDEX = 84;
  private static final int UPDATED_DATE_INDEX = 85;
  private static final int DATESYNC_NPD_INDEX = 86;

  /**
   * Create a reader for NPD exploration wellbores.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdExplorationWellboreReader(String url)
  {
    super(url);
  }

  /**
   * Create a new NPD exploration wellbore instance based on the tokens
   * from the CSV file.
   *
   * @param tokens  Tokens of all wellbore attributes.
   * @return        The new NPD wellbore instance.
   * @throws ParseException  If parsing any of the tokens fails.
   */
  protected NpdExplorationWellbore newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 87)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[WELLBORE_NAME_INDEX];
    String wellName = tokens[WELL_NAME_INDEX];
    String drillingOperator = tokens[DRILLING_OPERATOR_INDEX];
    String productionLicense = tokens[PRODUCTION_LICENSE_INDEX];
    String purpose = tokens[PURPOSE_INDEX];
    String status = tokens[STATUS_INDEX];
    String content = tokens[CONTENT_INDEX];
    String wellType = tokens[WELL_TYPE_INDEX];
    Boolean isSubsea = parseBoolean(tokens[IS_SUBSEA_INDEX]);
    Date entryDate = parseDate(tokens[ENTRY_DATE_INDEX]);
    Date completionDate = parseDate(tokens[COMPLETION_DATE_INDEX]);
    String field = tokens[FIELD_INDEX];
    String drillPermit = tokens[DRILL_PERMIT_INDEX];
    String discovery = tokens[DISCOVERY_INDEX];
    Boolean isDiscoveryWellbore = parseBoolean(tokens[DISCOVERY_WELLBORE_INDEX]);
    Integer bottomHoleTemperature = parseInt(tokens[BOTTOM_HOLE_TEMPERATURE_INDEX]);
    String siteSurvey = tokens[SITE_SURVEY_INDEX];
    String seismicLocation = tokens[SEISMIC_LOCATION_INDEX];
    Date pluggedAndAbandonDate = parseDate(tokens[PLUGGED_AND_ABANDON_DATE_INDEX]);
    Date pluggedDate = parseDate(tokens[PLUGGED_DATE_INDEX]);
    String licenseTargetName = tokens[LICENSE_TARGET_NAME_INDEX];
    Double maxInclination = parseDouble(tokens[MAX_INCLINATION_INDEX]);
    Double kellyBushElevation = parseDouble(tokens[KELLY_BUSH_ELEVATION_INDEX]);
    Double finalVerticalDepth = parseDouble(tokens[FINAL_VERTICAL_DEPTH_INDEX]);
    Double totalDepth = parseDouble(tokens[TOTAL_DEPTH_INDEX]);
    Double waterDepth = parseDouble(tokens[WATER_DEPTH_INDEX]);
    Double kickOffPoint = parseDouble(tokens[KICK_OFF_POINT]);
    String ageAtTd = tokens[AGE_AT_TD_INDEX];
    String formationAtTd = tokens[FORMATION_AT_TD_INDEX];
    String mainArea = tokens[MAIN_AREA_INDEX];
    String drillingFacility = tokens[DRILLING_FACILITY_INDEX];
    String drillingFacilityType = tokens[FACILITY_TYPE_DRILLING_INDEX];
    String drillingFacilityCategory = tokens[DRILLING_FACILITY_FIXED_OR_MOVEABLE];
    String licensingActivity = tokens[LICENSING_ACTIVITY_INDEX];
    Boolean isMultilateral = parseBoolean(tokens[MULTILATERAL_INDEX]);
    String purposePlanned = tokens[PURPOSE_PLANNED_INDEX];
    Integer entryYear = parseInt(tokens[ENTRY_YEAR_INDEX]);
    Integer completionYear = parseInt(tokens[COMPLETION_YEAR_INDEX]);
    String reclassFromWellbore = tokens[RECLASS_FROM_WELLBORE_INDEX];
    Date reclassificationDate = parseDate(tokens[RECLASSIFICATION_DATE_INDEX]);
    String reentryExplorationActivity = tokens[REENTRYEXPLORATIONACTIVITY_INDEX];
    Integer plotSymbol = parseInt(tokens[PLOT_SYMBOL_INDEX]);
    String formationWithHc1 = tokens[FORMATION_WITH_HC1_INDEX];
    String ageWithHc1 = tokens[AGE_WITH_HC1_INDEX];
    String formationWithHc2 = tokens[FORMATION_WITH_HC2_INDEX];
    String ageWithHc2 = tokens[AGE_WITH_HC2_INDEX];
    String formationWithHc3 = tokens[FORMATION_WITH_HC3_INDEX];
    String ageWithHc3 = tokens[AGE_WITH_HC3_INDEX];
    Integer drillingDays = parseInt(tokens[DRILLING_DAYS_INDEX]);
    Boolean isReentry = parseBoolean(tokens[REENTRY_INDEX]);
    String geodeticDatum = tokens[GEODETIC_DATUM_INDEX];
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
    String pressReleaseUrl = tokens[PRESS_RELEASE_URL_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    String diskosWellboreType = tokens[DISKOS_WELLBORE_TYPE_INDEX];
    String diskosWellboreParent = tokens[DISKOS_WELLBORE_PARENT_INDEX];
    Date wdssQcDate = parseDate(tokens[WDSS_QC_DATE_INDEX]);
    Date releaseDate = parseDate(tokens[RELEASE_DATE_INDEX]);
    String npdId = tokens[NPDID_WELLBORE_INDEX];
    String npdidDiscovery = tokens[NPDID_DISCOVERY_INDEX];
    String npdidField = tokens[NPDID_FIELD_INDEX];
    String npdidFacilityDrilling = tokens[NPDID_FACILITY_DRILLING_INDEX];
    String npdidWellboreReclass = tokens[NPDID_WELLBORE_RECLASS_INDEX];
    String npdidProductionLicense = tokens[NPDID_PRODUCTION_LICENSE_INDEX];
    String npdidSiteSurvey = tokens[NPDID_SITE_SURVEY_INDEX];
    Date mainLevelUpdatedDate = parseDate(tokens[MAIN_LEVEL_UPDATED_DATE_INDEX]);
    Date updatedDate = parseDate(tokens[UPDATED_DATE_INDEX]);
    Date syncDate = parseDate(tokens[DATESYNC_NPD_INDEX]);

    NpdExplorationWellbore wellbore = new NpdExplorationWellbore(npdId,
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
                                                                 reclassificationDate,
                                                                 siteSurvey,
                                                                 npdidSiteSurvey,
                                                                 pluggedAndAbandonDate,
                                                                 pluggedDate,
                                                                 licenseTargetName,
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
                                                                 mainLevelUpdatedDate,
                                                                 updatedDate,
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
}

package no.petroware.npdio.survey;

import java.text.ParseException;
import java.util.Date;

import no.petroware.npdio.NpdReader;

/**
 * NPD survey reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdSurveyReader extends NpdReader<NpdSurvey>
{
  /**
   * The survey properties and their order is as follows:
   *
   *   seaName
   *   seaPlanFromDate
   *   seaNpdidSurvey
   *   seaStatus
   *   seaGeographicalArea
   *   seaMidPoint
   *   seaCategory
   *   seaSurveyTypeMain
   *   seaSurveyTypePart
   *   seaCompanyReported
   *   seaVesselAll
   *   seaPlanToDate
   *   seaDateStarting
   *   seaDateFinalized
   *   seaCdpTotalKm
   *   seaBoatTotalKm
   *   sea3DKm2
   *   seaSurveyAcquired
   *   seaMarketAvailable
   *   seaSampling
   *   seaShallowDrilling
   *   seaGeotechnical
   *   seaFactPageUrl
   *   seaFactMapHtml5Url
   *   datesyncNPD
   */
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
  private static final int PLANNED_TOTAL_LENGTH_CDP_INDEX = 14;
  private static final int PLANNED_TOTAL_LENGTH_BOAT_INDEX = 15;
  private static final int NET_AREA_PLANNED_INDEX = 16;
  private static final int NET_AREA_ACTUAL_INDEX = 17;
  private static final int IS_AVAILABLE_INDEX = 18;
  private static final int IS_SAMPLING_DONE_INDEX = 19;
  private static final int IS_SHALLOW_DRILLING_DONE_INDEX = 20;
  private static final int IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX = 21;
  private static final int FACT_PAGE_URL_INDEX = 22;
  private static final int FACT_MAP_URL_INDEX = 23;
  private static final int DATE_SYNCED_INDEX = 24;

  /**
   * Create a reader for NPD surveys.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdSurveyReader(String url)
  {
    super(url);
  }

  /**
   * Create a new NPD company instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the survey file. Non-null.
   * @return        The created company instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdSurvey newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 25)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[NAME_INDEX];
    Date plannedStartDate = parseDate(tokens[PLANNED_START_DATE_INDEX]);
    String npdId = tokens[NPDID_INDEX];
    String status = tokens[STATUS_INDEX];
    String area = tokens[AREA_INDEX];
    String midPoint = tokens[MIDPOINT_INDEX];
    String category = tokens[CATEGORY_INDEX];
    String mainType = tokens[MAIN_TYPE_INDEX];
    String subType = tokens[SUB_TYPE_INDEX];
    String company = tokens[COMPANY_INDEX];
    String vessel = tokens[VESSEL_INDEX];
    Date plannedCompleteDate = parseDate(tokens[PLANNED_COMPLETE_DATE_INDEX]);
    Date startDate = parseDate(tokens[START_DATE_INDEX]);
    Date completeDate = parseDate(tokens[COMPLETE_DATE_INDEX]);
    Double plannedTotalLengthCdp = parseDouble(tokens[PLANNED_TOTAL_LENGTH_CDP_INDEX]);
    Double plannedTotalLengthBoat = parseDouble(tokens[PLANNED_TOTAL_LENGTH_BOAT_INDEX]);
    Double netAreaPlanned = parseDouble(tokens[NET_AREA_PLANNED_INDEX]);
    Double netAreaActual = parseDouble(tokens[NET_AREA_ACTUAL_INDEX]);
    Boolean isAvailable = parseBoolean(tokens[IS_AVAILABLE_INDEX]);
    Boolean isSamplingDone = parseBoolean(tokens[IS_SAMPLING_DONE_INDEX]);
    Boolean isShallowDrillingDone = parseBoolean(tokens[IS_SHALLOW_DRILLING_DONE_INDEX]);
    Boolean isGeotechnicalMeasurementDone = parseBoolean(tokens[IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX]);
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

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
                         plannedTotalLengthCdp,
                         plannedTotalLengthBoat,
                         netAreaPlanned,
                         netAreaActual,
                         isAvailable,
                         isSamplingDone,
                         isShallowDrillingDone,
                         isGeotechnicalMeasurementDone,
                         factPageUrl,
                         factMapUrl,
                         syncDate);
  }
}

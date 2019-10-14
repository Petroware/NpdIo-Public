package no.petroware.npdio.facility;

import java.text.ParseException;
import java.util.Date;

import no.petroware.npdio.NpdObject;
import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD fixed facilities.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdFixedFacilityReader extends NpdReader<NpdFixedFacility>
{
  /**
   * The fixed facility properties and their order is as follows:
   *
   *   fclName,
   *   fclPhase,
   *   fclSurface,
   *   fclCurrentOperatorName,
   *   fclKind,
   *   fclBelongsToName,
   *   fclBelongsToKind,
   *   fclBelongsToS,
   *   fclStartupDate,
   *   fclGeodeticDatum,
   *   fclNsDeg,
   *   fclNsMin,
   *   fclNsSec,
   *   fclNsCode,
   *   fclEwDeg,
   *   fclEwMin,
   *   fclEwSec,
   *   fclEwCode,
   *   fclWaterDepth,
   *   fclFunctions,
   *   fclDesignLifetime,
   *   fclFactPageUrl,
   *   fclFactMapUrl,
   *   fclNpdidFacility,
   *   fclDateUpdated,
   *   datesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int PHASE_INDEX = 1;
  private static final int IS_SURFACE_INDEX = 2;
  private static final int CURRENT_OPERATOR_INDEX = 3;
  private static final int KIND_INDEX = 4;
  private static final int BELONGS_TO_NAME_INDEX = 5;
  private static final int BELONGS_TO_KIND_INDEX = 6;
  private static final int BELONGS_TO_ID_INDEX = 7;
  private static final int STARTUP_DATE_INDEX = 8;
  private static final int GEODETIC_DATUM_INDEX = 9;
  private static final int NS_DEGREES_INDEX = 10;
  private static final int NS_MINUTES_INDEX = 11;
  private static final int NS_SECONDS_INDEX = 12;
  private static final int NS_CODE_INDEX = 13;
  private static final int EW_DEGREES_INDEX = 14;
  private static final int EW_MINUTES_INDEX = 15;
  private static final int EW_SECONDS_INDEX = 16;
  private static final int EW_CODE_INDEX = 17;
  private static final int WATER_DEPTH_INDEX = 18;
  private static final int FUNCTIONS_INDEX = 19;
  private static final int DESIGNED_LIFETIME_INDEX = 20;
  private static final int FACT_PAGE_URL_INDEX = 21;
  private static final int FACT_MAP_URL_INDEX = 22;
  private static final int NPDID_INDEX = 23;
  private static final int DATE_UPDATED_INDEX = 24;
  private static final int SYNC_DATE_INDEX = 25;

  /**
   * Create a reader for NPD fixed facilities.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdFixedFacilityReader(String url)
  {
    super(url);
  }

  /**
   * Create a new NPD fixed facility instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the facility file. Non-null.
   * @return        The created facility instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdFixedFacility newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 26)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String kind = tokens[KIND_INDEX];
    String functions = tokens[FUNCTIONS_INDEX];
    String phase = tokens[PHASE_INDEX];
    boolean isSurfaceFacility = parseBoolean(tokens[IS_SURFACE_INDEX]);
    Class<? extends NpdObject> belongsToClass = parseClass(tokens[BELONGS_TO_KIND_INDEX]);
    String belongsToId = tokens[BELONGS_TO_ID_INDEX];
    Date startupDate = parseDate(tokens[STARTUP_DATE_INDEX]);
    String geodeticDatum = tokens[GEODETIC_DATUM_INDEX];
    Integer nsDeg = parseInt(tokens[NS_DEGREES_INDEX]);
    Integer nsMin = parseInt(tokens[NS_MINUTES_INDEX]);
    Double nsSec = parseDouble(tokens[NS_SECONDS_INDEX]);
    String nsCode = tokens[NS_CODE_INDEX];
    Integer ewDeg = parseInt(tokens[EW_DEGREES_INDEX]);
    Integer ewMin = parseInt(tokens[EW_MINUTES_INDEX]);
    Double ewSec = parseDouble(tokens[EW_SECONDS_INDEX]);
    String ewCode = tokens[EW_CODE_INDEX];
    Double waterDepth = parseDouble(tokens[WATER_DEPTH_INDEX]);
    Integer designedLifetime = parseInt(tokens[DESIGNED_LIFETIME_INDEX]);
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date lastChangedDate = parseDate(tokens[DATE_UPDATED_INDEX]);
    Date syncDate = parseDate(tokens[SYNC_DATE_INDEX]);

    return new NpdFixedFacility(npdId,
                                name,
                                kind,
                                functions,
                                phase,
                                isSurfaceFacility,
                                belongsToClass,
                                belongsToId,
                                startupDate,
                                geodeticDatum,
                                nsDeg,
                                nsMin,
                                nsSec,
                                nsCode,
                                ewDeg,
                                ewMin,
                                ewSec,
                                ewCode,
                                waterDepth,
                                designedLifetime,
                                factPageUrl,
                                factMapUrl,
                                lastChangedDate,
                                syncDate);
  }
}

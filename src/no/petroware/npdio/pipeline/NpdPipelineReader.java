package no.petroware.npdio.pipeline;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD pipelines.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdPipelineReader extends NpdReader<NpdPipeline>
{
  /** URL to the NPD file containing the data. */
  private static final String URL = "https://factpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/tuf_pipeline_overview&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.71.51&CultureCode=en";

  /**
   * The pipline properties and their order is as follows:
   *
   *   pplName
   *   pplMapLabel
   *   fclNameFrom
   *   fclNameTo
   *   pplBelongsToName
   *   cmpLongName
   *   pplCurrentPhase
   *   pplCurrentPhaseFromDate
   *   pplMedium
   *   pplMainGroupingName
   *   pplDimension
   *   pplWaterDepth
   *   cmpNpdidCompany
   *   fclNpdidFacilityFrom
   *   fclNpdidFacilityTo
   *   pplFactPageUrl
   *   pplFactMapUrl
   *   pplNpdidPipeline
   *   pplDateUpdated
   *   DatesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int MAP_LABEL_INDEX = 1;
  private static final int FROM_FACILITY_INDEX = 2;
  private static final int TO_FACILITY_INDEX = 3;
  private static final int BELONGS_TO_INDEX = 4;
  private static final int OPERATOR_INDEX = 5;
  private static final int CURRENT_PHASE_INDEX = 6;
  private static final int CURRENT_PHASE_FROM_DATE_INDEX = 7;
  private static final int MEDIUM_INDEX = 8;
  private static final int MAIN_GROUPING_INDEX = 9;
  private static final int DIMENSION_INDEX = 10;
  private static final int WATER_DEPTH_INDEX = 11;
  private static final int NPDID_OPERATOR_INDEX = 12;
  private static final int NPDID_FROM_FACILITY_INDEX = 13;
  private static final int NPDID_TO_FACILITY_INDEX = 14;
  private static final int FACT_PAGE_URL_INDEX = 15;
  private static final int FACT_MAP_URL_INDEX = 16;
  private static final int NPDID_INDEX = 17;
  private static final int LAST_CHANGED_DATE_INDEX = 18;
  private static final int SYNCED_DATE_INDEX = 19;

  /**
   * Create a reader for NPD pipelines.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdPipelineReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD pipelines.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdPipelineReader reader = new NpdPipelineReader(url);
   *   List&lt;NpdPipeline&gt; pipelines = reader.read();
   * </pre>
   *
   * @return  All NPD pipelines. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdPipeline> readAll()
    throws IOException
  {
    NpdPipelineReader reader = new NpdPipelineReader(URL);
    return reader.read();
  }

  /**
   * Create a new NPD pipeline instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the pipeline file. Non-null.
   * @return        The created pipeline instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdPipeline newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokesn cannot be null";

    if (tokens.length != 20)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[NAME_INDEX];
    String mapLabel = tokens[MAP_LABEL_INDEX];
    String fromFacility = tokens[FROM_FACILITY_INDEX];
    String toFacility = tokens[TO_FACILITY_INDEX];
    String belongsTo = tokens[BELONGS_TO_INDEX];
    String operator = tokens[OPERATOR_INDEX];
    String currentPhase = tokens[CURRENT_PHASE_INDEX];
    Date currentPhaseFromDate = parseDate(tokens[CURRENT_PHASE_FROM_DATE_INDEX]);
    String medium = tokens[MEDIUM_INDEX];
    String mainGrouping = tokens[MAIN_GROUPING_INDEX];
    Double dimension = parseDouble(tokens[DIMENSION_INDEX]);
    Double waterDepth = parseDouble(tokens[WATER_DEPTH_INDEX]);
    String npdidOperator  = tokens[NPDID_OPERATOR_INDEX];
    String npdidFromFacility = tokens[NPDID_FROM_FACILITY_INDEX];
    String npdidToFacility = tokens[NPDID_TO_FACILITY_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    String npdId = tokens[NPDID_INDEX];
    Date lastChangedDate = parseDate(tokens[LAST_CHANGED_DATE_INDEX]);
    Date syncDate = parseDate(tokens[SYNCED_DATE_INDEX]);

    return new NpdPipeline(npdId,
                           name,
                           mapLabel,
                           fromFacility,
                           toFacility,
                           belongsTo,
                           operator,
                           currentPhase,
                           currentPhaseFromDate,
                           medium,
                           mainGrouping,
                           dimension,
                           waterDepth,
                           npdidOperator,
                           npdidFromFacility,
                           npdidToFacility,
                           factPageUrl,
                           factMapUrl,
                           lastChangedDate,
                           syncDate);
  }
}

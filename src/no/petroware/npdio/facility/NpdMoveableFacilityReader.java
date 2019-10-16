package no.petroware.npdio.facility;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.petroware.npdio.NpdObject;
import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD moveable facilities.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdMoveableFacilityReader extends NpdReader<NpdMoveableFacility>
{
  /** URL to the NPD file containing the data. */
  private static final String URL = "https://npdfactpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/facility_moveable&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.121.112&CultureCode=en";

  /**
   * The moveable facility properties and their order is as follows:
   *
   *   fclName
   *   fclCurrentRespCompanyName
   *   fclKind
   *   fclFunctions
   *   fclStatus
   *   fclNationName
   *   fclFactPageUrl
   *   fclNpdidFacility
   *   fclNpdidCurrentRespCompany
   *   fclDateUpdated
   *   datesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int RESPONSIBLE_COMPANY_NAME_INDEX = 1;
  private static final int KIND_INDEX = 2;
  private static final int FUNCTIONS_INDEX = 3;
  private static final int AOC_STATUS_INDEX = 4;
  private static final int NATION_INDEX = 5;
  private static final int FACT_PAGE_URL_INDEX = 6;
  private static final int NPDID_INDEX = 7;
  private static final int RESPONSIBLE_COMPANY_ID_INDEX = 8;
  private static final int DATE_UPDATED_INDEX = 9;
  private static final int SYNC_DATE_INDEX = 10;

  /**
   * Create a reader for NPD moveable facilities.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdMoveableFacilityReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD moveable facilities.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdMoveableFacilityReader reader = new NpdMoveableFacilityReader(url);
   *   List&lt;NpdMoveableFacility&gt; moveableFacilities = reader.read();
   * </pre>
   *
   * @return  All NPD moveable facilities. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdMoveableFacility> readAll()
    throws IOException
  {
    NpdMoveableFacilityReader reader = new NpdMoveableFacilityReader(URL);
    return reader.read();
  }

  /**
   * Create a new NPD moveable facility instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the facility file. Non-null.
   * @return        The created facility instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdMoveableFacility newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 11)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String responsibleCompanyName = tokens[RESPONSIBLE_COMPANY_NAME_INDEX];
    String responsibleCompanyId = tokens[RESPONSIBLE_COMPANY_ID_INDEX];
    String kind = tokens[KIND_INDEX];
    String functions = tokens[FUNCTIONS_INDEX];
    String aocStatus = tokens[AOC_STATUS_INDEX];
    String nation = tokens[NATION_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    Date lastChangedDate = parseDate(tokens[DATE_UPDATED_INDEX]);
    Date syncDate = parseDate(tokens[SYNC_DATE_INDEX]);

    return new NpdMoveableFacility(npdId,
                                   name,
                                   responsibleCompanyName,
                                   kind,
                                   functions,
                                   aocStatus,
                                   nation,
                                   factPageUrl,
                                   responsibleCompanyId,
                                   lastChangedDate,
                                   syncDate);
  }
}

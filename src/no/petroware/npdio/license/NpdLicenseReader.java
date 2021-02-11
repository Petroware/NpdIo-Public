package no.petroware.npdio.license;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.petroware.npdio.NpdReader;

/**
 * NPD license reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdLicenseReader extends NpdReader<NpdLicense>
{
  /** URL to the NPD file containing the data. */
  private static final String URL = "https://factpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/licence&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=213.225.65.178&CultureCode=en";

  /**
   * The license properties and their order is as follows:
   *
   *   prlName
   *   prlLicensingActivityName
   *   prlMainArea
   *   prlStatus
   *   prlStratigraphical
   *   prlDateGranted
   *   prlDateValidTo
   *   prlOriginalArea
   *   prlCurrentArea
   *   prlPhaseCurrent
   *   prlNpdidLicence
   *   prlFactPageUrl
   *   prlFactMapUrl
   *   prlDateUpdated
   *   prlDateUpdatedMax
   *   DatesyncNPD
   */
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
  private static final int MAIN_LEVEL_UPDATED_DATE_INDEX = 13;
  private static final int LAST_CHANGED_DATE_INDEX = 14;
  private static final int SYNC_DATE_INDEX = 15;

  /**
   * Create a reader for NPD licenses.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdLicenseReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD licenses.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdLicenseReader reader = new NpdLicenseReader(url);
   *   List&lt;NpdLicense&gt; licenses = reader.read();
   * </pre>
   *
   * @return  All NPD licenses. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdLicense> readAll()
    throws IOException
  {
    NpdLicenseReader reader = new NpdLicenseReader(URL);
    return reader.read();
  }

  /**
   * Create a new NPD license instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the license file. Non-null.
   * @return        The created license instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdLicense newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 16)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String activity = tokens[ACTIVITY_INDEX];
    String mainArea = tokens[MAIN_AREA_INDEX];
    String status = tokens[STATUS_INDEX];
    String stratigraphical = tokens[STRATIGRAPHICAL_INDEX];
    Date dateGranted = parseDate(tokens[DATE_GRANTED_INDEX]);
    Date validToDate = parseDate(tokens[VALID_TO_DATE_INDEX]);
    double originalArea = 1000.0 * 1000.0 * parseDouble(tokens[ORIGINAL_AREA_INDEX]);
    double currentArea = 1000.0 * 1000.0 * parseDouble(tokens[CURRENT_AREA_INDEX]);
    String phase = tokens[PHASE_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date mainLevelUpdatedDate = parseDate(tokens[MAIN_LEVEL_UPDATED_DATE_INDEX]);
    Date lastChangedDate = parseDate(tokens[LAST_CHANGED_DATE_INDEX]);
    Date syncDate = parseDate(tokens[SYNC_DATE_INDEX]);

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
                          mainLevelUpdatedDate,
                          lastChangedDate,
                          syncDate);
  }
}

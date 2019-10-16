package no.petroware.npdio.discovery;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.petroware.npdio.NpdReader;

/**
 * Reader for NPD discoveries.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdDiscoveryReader extends NpdReader<NpdDiscovery>
{
  /** URL to the NPD file containing the data. */
  private static final String URL = "https://npdfactpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/discovery&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=92.221.71.51&CultureCode=en";

  /**
   * The pipline properties and their order is as follows:
   *
   *   dscName
   *   cmpLongName
   *   dscCurrentActivityStatus
   *   dscHcType
   *   wlbName
   *   nmaName
   *   fldName
   *   dscDateFromInclInField
   *   dscDiscoveryYear
   *   dscResInclInDiscoveryName
   *   dscOwnerKind
   *   dscOwnerName
   *   dscNpdidDiscovery
   *   fldNpdidField
   *   wlbNpdidWellbore
   *   dscFactPageUrl
   *   dscFactMapUrl
   *   dscDateUpdated
   *   dscDateUpdatedMax
   *   DatesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int OPERATOR_INDEX = 1;
  private static final int ACTIVITY_STATUS_INDEX = 2;
  private static final int HYDROCARBON_TYPE_INDEX = 3;
  private static final int WELLBORE_NAME_INDEX = 4;
  private static final int MAIN_AREA_INDEX = 5;
  private static final int FIELD_NAME_INDEX = 6;
  private static final int INCLUDED_IN_FIELD_FROM_DATE_INDEX = 7;
  private static final int DISCOVERY_YEAR_INDEX = 8;
  private static final int RESOURCES_DISCOVERY_NAME_INDEX = 9;
  private static final int OWNER_KIND_INDEX = 10;
  private static final int OWNER_NAME_INDEX = 11;
  private static final int NPDID_INDEX = 12;
  private static final int NPDID_FIELD_INDEX = 13;
  private static final int NPDID_WELLBORE_INDEX = 14;
  private static final int FACT_PAGE_URL_INDEX = 15;
  private static final int FACT_MAP_URL_INDEX = 16;
  private static final int DATE_MAIN_LEVEL_UPDATED_INDEX = 17;
  private static final int DATE_ALL_UPDATED_INDEX = 18;
  private static final int DATE_SYNCED_INDEX = 19;

  /**
   * Create a reader for NPD discoveries.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdDiscoveryReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD discoveries.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdDiscoveryReader reader = new NpdDiscoveryReader(url);
   *   List&lt;NpdDiscovery&gt; discoveries = reader.read();
   * </pre>
   *
   * @return  All NPD discoveries. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdDiscovery> readAll()
    throws IOException
  {
    NpdDiscoveryReader reader = new NpdDiscoveryReader(URL);
    return reader.read();
  }

  /**
   * Create a new NPD discovery instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the discovery file. Non-null.
   * @return        The created discovery instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdDiscovery newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 20)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String activityStatus = tokens[ACTIVITY_STATUS_INDEX];
    String hydrocarbonType = tokens[HYDROCARBON_TYPE_INDEX];
    String wellboreName = tokens[WELLBORE_NAME_INDEX];
    String mainArea =  tokens[MAIN_AREA_INDEX];
    String fieldName = tokens[FIELD_NAME_INDEX];
    Date includedInFieldFromDate = parseDate(tokens[INCLUDED_IN_FIELD_FROM_DATE_INDEX]);
    String discoevryYear = tokens[DISCOVERY_YEAR_INDEX];
    String resourcesDiscoveryName = tokens[RESOURCES_DISCOVERY_NAME_INDEX];
    String ownerKindIndex = tokens[OWNER_KIND_INDEX];
    String ownerNameIndex = tokens[OWNER_NAME_INDEX];
    String npdidField = tokens[NPDID_FIELD_INDEX];
    String npdidWellbore = tokens[NPDID_WELLBORE_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date mainLevelUpdatedIndex = parseDate(tokens[DATE_MAIN_LEVEL_UPDATED_INDEX]);
    Date lastChangedDate = parseDate(tokens[DATE_ALL_UPDATED_INDEX]);
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

    return new NpdDiscovery(npdId,
                            name,
                            activityStatus,
                            hydrocarbonType,
                            wellboreName,
                            mainArea,
                            fieldName,
                            includedInFieldFromDate,
                            discoevryYear,
                            resourcesDiscoveryName,
                            ownerKindIndex,
                            ownerNameIndex,
                            npdidField,
                            npdidWellbore,
                            factPageUrl,
                            factMapUrl,
                            mainLevelUpdatedIndex,
                            lastChangedDate,
                            syncDate);
  }
}
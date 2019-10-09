package no.petroware.npdio.facility;

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
import java.util.logging.Logger;

import no.petroware.npdio.NpdObject;
import no.petroware.npdio.util.Util;

/**
 * Reader for NPD facilities.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdFacilityReader
{
  //
  // Fixed
  //
  private static final int F_NAME_INDEX = 0;
  private static final int F_PHASE_INDEX = 1;
  private static final int F_IS_SURFACE_INDEX = 2;
  private static final int F_CURRENT_OPERATOR_INDEX = 3;
  private static final int F_KIND_INDEX = 4;
  private static final int F_BELONGS_TO_NAME_INDEX = 5;
  private static final int F_BELONGS_TO_KIND_INDEX = 6;
  private static final int F_BELONGS_TO_ID_INDEX = 7;
  private static final int F_STARTUP_DATE_INDEX = 8;
  private static final int F_GEODETIC_DATUM_INDEX = 9;
  private static final int F_NS_DEGREES_INDEX = 10;
  private static final int F_NS_MINUTES_INDEX = 11;
  private static final int F_NS_SECONDS_INDEX = 12;
  private static final int F_NS_CODE_INDEX = 13;
  private static final int F_EW_DEGREES_INDEX = 14;
  private static final int F_EW_MINUTES_INDEX = 15;
  private static final int F_EW_SECONDS_INDEX = 16;
  private static final int F_EW_CODE_INDEX = 17;
  private static final int F_WATER_DEPTH_INDEX = 18;
  private static final int F_FUNCTIONS_INDEX = 19;
  private static final int F_DESIGNED_LIFETIME_INDEX = 20;
  private static final int F_FACT_PAGE_URL_INDEX = 21;
  private static final int F_FACT_MAP_URL_INDEX = 22;
  private static final int F_NPDID_INDEX = 23;
  private static final int F_DATE_UPDATED_INDEX = 24;
  private static final int F_SYNC_DATE_INDEX = 25;

  //
  // Moveable
  //
  private static final int M_NAME_INDEX = 0;
  private static final int M_RESPONSIBLE_COMPANY_NAME_INDEX = 1;
  private static final int M_KIND_INDEX = 2;
  private static final int M_FUNCTIONS_INDEX = 3;
  private static final int M_AOC_STATUS_INDEX = 4;
  private static final int M_NATION_INDEX = 5;
  private static final int M_FACT_PAGE_URL_INDEX = 6;
  private static final int M_NPDID_INDEX = 7;
  private static final int M_RESPONSIBLE_COMPANY_ID_INDEX = 8;
  private static final int M_DATE_UPDATED_INDEX = 9;
  private static final int M_SYNC_DATE_INDEX = 10;

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(NpdFacilityReader.class.getName());

  /**
   * Private constructor to prevent client instantiation.
   */
  private NpdFacilityReader()
  {
    assert false : "This should never be called";
  }

  /**
   * Create new moveable facility instance from the given tokens.
   *
   * @param tokens  Attribute tokens of the instance to create. Non-null.
   * @return        Created instance. Never null.
   */
  private static NpdMoveableFacility newNpdMoveableFacility(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 11)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[M_NPDID_INDEX];
    String name = tokens[M_NAME_INDEX];
    String responsibleCompanyId = tokens[M_RESPONSIBLE_COMPANY_ID_INDEX];
    String kind = tokens[M_KIND_INDEX];
    String functions = tokens[M_FUNCTIONS_INDEX];
    String aocStatus = tokens[M_AOC_STATUS_INDEX];
    String nation = tokens[M_NATION_INDEX];
    String factPageUrl = tokens[M_FACT_PAGE_URL_INDEX];
    Date lastChangedDate = Util.parseDate(tokens[M_DATE_UPDATED_INDEX]);
    Date syncDate = Util.parseDate(tokens[M_SYNC_DATE_INDEX]);

    return new NpdMoveableFacility(npdId,
                                   name,
                                   responsibleCompanyId,
                                   kind,
                                   functions,
                                   aocStatus,
                                   nation,
                                   factPageUrl,
                                   lastChangedDate,
                                   syncDate);
  }

  /**
   * Create new fixed facility instance from the given tokens.
   *
   * @param tokens  Attribute tokens of the instance to create. Non-null.
   * @return        Created instance. Never null.
   */
  private static NpdFixedFacility newNpdFixedFacility(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 26)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[F_NPDID_INDEX];
    String name = tokens[F_NAME_INDEX];
    String kind = tokens[F_KIND_INDEX];
    String functions = tokens[F_FUNCTIONS_INDEX];
    String phase = tokens[F_PHASE_INDEX];
    boolean isSurfaceFacility = Util.parseBoolean(tokens[F_IS_SURFACE_INDEX]);
    Class<? extends NpdObject> belongsToClass = Util.parseClass(tokens[F_BELONGS_TO_KIND_INDEX]);
    String belongsToId = tokens[F_BELONGS_TO_ID_INDEX];
    Date startupDate = Util.parseDate(tokens[F_STARTUP_DATE_INDEX]);
    String geodeticDatum = tokens[F_GEODETIC_DATUM_INDEX];
    Integer nsDeg = Util.parseInt(tokens[F_NS_DEGREES_INDEX]);
    Integer nsMin = Util.parseInt(tokens[F_NS_MINUTES_INDEX]);
    Double nsSec = Util.parseDouble(tokens[F_NS_SECONDS_INDEX]);
    String nsCode = tokens[F_NS_CODE_INDEX];
    Integer ewDeg = Util.parseInt(tokens[F_EW_DEGREES_INDEX]);
    Integer ewMin = Util.parseInt(tokens[F_EW_MINUTES_INDEX]);
    Double ewSec = Util.parseDouble(tokens[F_EW_SECONDS_INDEX]);
    String ewCode = tokens[F_EW_CODE_INDEX];
    Double waterDepth = Util.parseDouble(tokens[F_WATER_DEPTH_INDEX]);
    Integer designedLifetime = Util.parseInt(tokens[F_DESIGNED_LIFETIME_INDEX]);
    String factPageUrl = tokens[F_FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[F_FACT_MAP_URL_INDEX];
    Date lastChangedDate = Util.parseDate(tokens[F_DATE_UPDATED_INDEX]);
    Date syncDate = Util.parseDate(tokens[F_SYNC_DATE_INDEX]);

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

  /**
   * Read well data from the specified URL and return as list of
   * wellbore instances.
   *
   * @param urlString      URL to read from.
   * @param facilityClass  Type of facility to read.
   * @return               List of facilities read.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If read operation failed somehow.
   */
  private static List<? extends NpdFacility> readFacilities(String urlString, Class<? extends NpdFacility> facilityClass)
    throws IOException
  {
    assert urlString != null : "urlString cannot be null";
    assert facilityClass != null : "facilityClass cannot be null";

    // Prepare return structure
    List<NpdFacility> facilities = new ArrayList<>();

    // Create URL instance from the specified string
    URL url;
    try {
      url = new URL(urlString);
    }
    catch (MalformedURLException exception) {
      throw new IOException("Malformed URL: " + urlString, exception);
    }

    // Open connection
    logger_.info("Connecting to : " + url);
    InputStream stream = url.openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    try {
      // Skip past the header line
      reader.readLine();

      // Read line by line. There is data for one facility per line
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

        NpdFacility facility;
        try {
          if (facilityClass == NpdMoveableFacility.class)
            facility = newNpdMoveableFacility(tokens);
          else
            facility = newNpdFixedFacility(tokens);

          facilities.add(facility);
        }
        catch (ParseException exception) {
          logger_.warning("Skip illegal line: " + line + " " + exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.info("Read " + facilities.size() + " facilities OK.");

    return facilities;
  }

  /**
   * Read moveable facility data from the specified URL and return as list of
   * facility instances.
   *
   * @param urlString  URL to read from. Non-null.
   * @return           List of moveable facilities read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  @SuppressWarnings("unchecked")
  public static List<NpdMoveableFacility> readMoveableFacilities(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    return (List<NpdMoveableFacility>) readFacilities(urlString, NpdMoveableFacility.class);
  }

  /**
   * Read fixed facility data from the specified URL and return as list of
   * facility instances.
   *
   * @param urlString  URL to read from. Non-null.
   * @return           List of fixed facilities read. Never null.
   * @throws IllegalArgumentException  If urlString is null.
   * @throws IOException  If the read operation failed for some reason.
   */
  @SuppressWarnings("unchecked")
  public static List<NpdFixedFacility> readFixedFacilities(String urlString)
    throws IOException
  {
    if (urlString == null)
      throw new IllegalArgumentException("urlString cannot be null");

    return (List<NpdFixedFacility>) readFacilities(urlString, NpdFixedFacility.class);
  }
}

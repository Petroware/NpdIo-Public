package no.petroware.npdio.license;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * A <em>production license</em> as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdLicense extends NpdObject
{
  private final String activity_;

  private final String mainArea_;

  private final String status_;

  private final String stratigraphical_;

  private final Date dateGranted_;

  private final Date validToDate_;

  private final double originalArea_;

  private final double currentArea_;

  private final String phase_;

  NpdLicense(String npdId,
             String name,
             String activity,
             String mainArea,
             String status,
             String stratigraphical,
             Date dateGranted,
             Date validToDate,
             double originalArea,
             double currentArea,
             String phase,
             String factPageUrl,
             String factMapUrl,
             Date lastChangedDate,
             Date syncDate)
  {
    // NOTE: NPD has misspelled the type name "licence"!
    super("licence", npdId, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    activity_ = activity;
    mainArea_ = mainArea;
    status_ = status;
    stratigraphical_ = stratigraphical;
    dateGranted_ = dateGranted != null ? new Date(dateGranted.getTime()) : null;
    validToDate_ = validToDate != null ? new Date(validToDate.getTime()) : null;
    originalArea_ = originalArea;
    currentArea_ = currentArea;
    phase_ = phase;
  }

  /**
   * Return activity of this license.
   * <p>
   * This is the name of the licensing activity the production license
   * was awarded in.
   *
   * @return Activity of this license. Null if unknown.
   */
  public String getActivity()
  {
    return activity_;
  }

  /**
   * Return main area of this license.
   * <p>
   * Name of the area on the Norwegian Continental Shelf where the wellbore
   * is located. Example of legal values: Barents Sea, Norwegian Sea,
   * North Sea
   *
   * @return Activity of this license. Null if unknown.
   */
  public String getMainArea()
  {
    return mainArea_;
  }

  /**
   * Return status of this license.
   * <p>
   * Current status of production license.
   * <p>
   * Example of legal values:
   * <ul>
   *   <li>ACTIVE</li>
   *   <li>INACTIVE</li>
   * </ul>
   *
   * @return  Status of this license. Null if unknown.
   */
  public String getStatus()
  {
    return status_;
  }

  /**
   * Return the stratigraphical state of this license.
   * <p>
   * Indicates if the licence has vertical limitations based on stratigraphy.
   * 'NO' means no such limitations, 'YES OR PARTLY' means that either whole
   * or parts of the license has such limitations.
   *
   * @return  Stratigraphical state of this license. Null if unknown.
   */
  public String getStratigraphical()
  {
    return stratigraphical_;
  }

  /**
   * Return the date granted for this license.
   * <p>
   * Date the production license was granted by the Norwegian authorities.
   *
   * @return Date granted for this license. Null if unknown.
   */
  public Date getDateGranted()
  {
    return dateGranted_ != null ? new Date(dateGranted_.getTime()) : null;
  }

  /**
   * Return valid to date for this license.
   * <p>
   * Date the production license is valid to.
   *
   * @return  Date this license is valid to. Null if unknown.
   */
  public Date getValidToDate()
  {
    return validToDate_ != null ? new Date(validToDate_.getTime()) : null;
  }

  /**
   * Return original area of the production licence. Square metres.
   *
   * @return  Original area of the license in square metres.
   */
  public double getOriginalArea()
  {
    return originalArea_;
  }

  /**
   * Return current area of the production license. Square meters.
   *
   * @return  Current area of the license in square meters.
   */
  public double getCurrentArea()
  {
    return currentArea_;
  }

  /**
   * Return current phase of this license.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>INITIAL</li>
   *   <li>INITIAL EXTENDED</li>
   *   <li>PRODUCTION</li>
   *   <li>PRODUCTION EXTENDED</li>
   * </ul>
   *
   * @return Current phase of this  license. Null if unknown.
   */
  public String getPhase()
  {
    return phase_;
  }
}

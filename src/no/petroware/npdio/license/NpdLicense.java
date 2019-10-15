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
  /** Activity. Null if N/A or unknown. */
  private final String activity_;

  /** Main area. Null if N/A or unknown. */
  private final String mainArea_;

  /** Status. Null if N/A or unknown. */
  private final String status_;

  /** Stratigraphical state. Null if N/A or unknown. */
  private final String stratigraphical_;

  /** Date granted. Null if N/A or unknown. */
  private final Date dateGranted_;

  /** Valid to date. Null if N/A or unknown. */
  private final Date validToDate_;

  /** Original area. Null if N/A or unknown. */
  private final double originalArea_;

  /** Current area. Null if N/A or unknown. */
  private final double currentArea_;

  /** Phase. Null if N/A or unknown. */
  private final String phase_;

  /**
   * Create an NPD license instance.
   */
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
             Date mainLevelUpdatedDate,
             Date lastChangedDate,
             Date syncDate)
  {
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
   * <b>NPD description:</b><br>
   * Name of the licensing activity the production licence was awarded in.
   * <p>
   * varchar(40), corresponds to NPD property <em>prlLicensingActivityName</em>.
   *
   * @return Activity of this license. Null if N/A or unknown.
   */
  public String getActivity()
  {
    return activity_;
  }

  /**
   * Return main area of this license.
   * <p>
   * <b>NPD description:</b><br>
   * Name of the area on the Norwegian Continental Shelf where the wellbore
   * is located. Example of legal values:
   * <ul>
   *   <li>Barents Sea</li>
   *   <li>Norwegian Sea</li>
   *   <li>North Sea</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>prlMainArea</em>.
   *
   * @return Activity of this license. Null if N/A or unknown.
   */
  public String getMainArea()
  {
    return mainArea_;
  }

  /**
   * Return status of this license.
   * <p>
   * <b>NPD description:</b><br>
   * Current status of production license.
   * Example of legal values:
   * <ul>
   *   <li>ACTIVE</li>
   *   <li>INACTIVE</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>prlStatus</em>.
   *
   * @return  Status of this license. Null if N/A or unknown.
   */
  public String getStatus()
  {
    return status_;
  }

  /**
   * Return the stratigraphical state of this license.
   * <p>
   * <b>NPD description:</b><br>
   * Indicates if the license has vertical limitations based on stratigraphy.
   * 'NO' means no such limitations, 'YES OR PARTLY' means that either whole
   * or parts of the license has such limitations.
   * <p>
   * varchar(40), corresponds to NPD property <em>prlStratigraphical</em>.
   *
   * @return  Stratigraphical state of this license. Null if N/A or unknown.
   */
  public String getStratigraphical()
  {
    return stratigraphical_;
  }

  /**
   * Return the date granted for this license.
   * <p>
   * <b>NPD description:</b><br>
   * Date the production license was granted by the Norwegian authorities.
   * <p>
   * datetime, corresponds to NPD property <em>prlDateGranted</em>.
   *
   * @return Date granted for this license. Null if N/A or unknown.
   */
  public Date getDateGranted()
  {
    return dateGranted_ != null ? new Date(dateGranted_.getTime()) : null;
  }

  /**
   * Return valid to date for this license.
   * <p>
   * <b>NPD description:</b><br>
   * Date the production license is valid to.
   * <p>
   * datetime, corresponds to NPD property <em>prlDateValidTo</em>.
   *
   * @return  Date this license is valid to. Null if unknown.
   */
  public Date getValidToDate()
  {
    return validToDate_ != null ? new Date(validToDate_.getTime()) : null;
  }

  /**
   * Return original area of the production license. Square meters.
   * <p>
   * <b>NPD description:</b><br>
   * Original area of the production licence in square kilometres.
   * <p>
   * decimal, corresponds to NPD property <em>prlOriginalArea</em>.
   *
   * @return  Original area of the license in square meters.
   */
  public double getOriginalArea()
  {
    return originalArea_;
  }

  /**
   * Return current area of the production license. Square meters.
   * <p>
   * <b>NPD description:</b><br>
   * Current area of the production licence in square kilometres.
   * <p>
   * decimal, corresponds to NPD property <em>prlCurrentArea</em>.
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
   * <b>NPD description:</b><br>
   * Current phase for the prodcution licence.
   * Examples of legal values:
   * <ul>
   *   <li>INITIAL</li>
   *   <li>INITIAL EXTENDED</li>
   *   <li>PRODUCTION</li>
   *   <li>PRODUCTION EXTENDED</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>prlPhaseCurrent</em>.
   *
   * @return Current phase of this  license. Null if unknown.
   */
  public String getPhase()
  {
    return phase_;
  }
}

package no.petroware.npdio.field;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * A field as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdField extends NpdObject
{
  /** Current activity status. **/
  private final String status_;

  /** Discovery wellbore. */
  private final String discoveryWellboreId_;

  private final String licenseId_;

  private final String mainArea_;

  private final String mainSupplyBase_;

  private final String operatorId_;

  private final Object lock_ = new Object();

  private Production production_ = null;

  /**
   * Create a NPD field instance.
   *
   * @param npdId
   * @param name
   * @param status
   * @param discoveryWellboreId
   * @param licenseId
   * @param mainArea
   * @param mainSupplyBase
   * @param operatorId
   * @param factPageUrl
   * @param factMapUrl
   * @param syncDate
   */
  NpdField(String npdId,
           String name,
           String status,
           String discoveryWellboreId,
           String licenseId,
           String mainArea,
           String mainSupplyBase,
           String operatorId,
           String factPageUrl,
           String factMapUrl,
           Date lastChangedDate,
           Date syncDate)
  {
    super("field", npdId, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    status_ = status;
    discoveryWellboreId_ = discoveryWellboreId;
    licenseId_ = licenseId;
    mainArea_ = mainArea;
    mainSupplyBase_ = mainSupplyBase;
    operatorId_ = operatorId;
  }

  /**
   * Return current activity status of the field.
   * Example of legal values: SHUT DOWN, PRODUCING, PDO APPROVED.
   *
   * @return  Status of the field. Null if unknown.
   */
  public String getStatus()
  {
    return status_;
  }

  /**
   * Unique ID of wellbore which proved the field.
   *
   * @return  ID of wellbore which proved the field. Null if unknown.
   */
  public String getDiscoveryWellboreId()
  {
    return discoveryWellboreId_;
  }

  /**
   * Unique ID of the license of this field.
   *
   * @return  Unique ID of the license of this field.
   */
  public String getLicenseId()
  {
    return licenseId_;
  }

  /**
   * Return main area of this field.
   * <p>
   * Main NCS area. Example of legal values:
   * NORTH SEA, NORWEGIAN SEA, BARENTS SEA
   * <p>
   * varchar(40), corresponds to the NPD property <em>fldMainArea</em>.
   *
   * @return  Main area of this field. Null if unknown.
   */
  public String getMainArea()
  {
    return mainArea_;
  }

  /**
   * Return the main supply base of this field.
   *
   * @return  Main supply base of this field. Null if unknown.
   */
  public String getMainSupplyBase()
  {
    return mainSupplyBase_;
  }

  /**
   * Unique ID for operator company.
   *
   * @return  Unique ID for operator company.
   */
  public String getOperatorId()
  {
    return operatorId_;
  }

  void setProduction(Production production)
  {
    synchronized (lock_) {
      production_ = production;
    }
  }

  /**
   * Return production of this field.
   * <p>
   * Production is null until it is instantiated by the
   * production reader.
   *
   * @return  Production of this field. Null if not loaded.
   */
  public Production getProduction()
  {
    synchronized (lock_) {
      return production_;
    }
  }
}

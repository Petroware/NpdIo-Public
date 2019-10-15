package no.petroware.npdio.discovery;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * A discovery as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdDiscovery extends NpdObject
{
  private final String activityStatus_;

  private final String hydrocarbonType_;

  private final String wellboreName_;

  private final String mainArea_;

  private final String fieldName_;

  private final Date includedInFieldFromDate_;

  private final String discoveryYear_;

  private final String resourcesDiscoveryName_;

  private final String ownerKind_;

  private final String ownerName_;

  private final String npdidField_;

  private final String npdidWellbore_;

  /**
   * Create an NPD discovery instance.
   */
  NpdDiscovery(String npdId,
               String name,
               String activityStatus,
               String hydrocarbonType,
               String wellboreName,
               String mainArea,
               String fieldName,
               Date includedInFieldFromDate,
               String discoveryYear,
               String resourcesDiscoveryName,
               String ownerKind,
               String ownerName,
               String npdidField,
               String npdidWellbore,
               String factPageUrl,
               String factMapUrl,
               Date mainLevelUpdatedIndex,
               Date lastChangedDate,
               Date syncDate)
  {
    super("discovery", npdId, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    activityStatus_ = activityStatus;
    hydrocarbonType_ = hydrocarbonType;
    wellboreName_ = wellboreName;
    mainArea_ = mainArea;
    fieldName_ = fieldName;
    includedInFieldFromDate_ = includedInFieldFromDate != null ? new Date(includedInFieldFromDate.getTime()) : null;
    discoveryYear_ = discoveryYear;
    resourcesDiscoveryName_ = resourcesDiscoveryName;
    ownerKind_ = ownerKind;
    ownerName_ = ownerName;
    npdidField_ = npdidField;
    npdidWellbore_ = npdidWellbore;
  }

  /**
   * Return activity status of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Current activity status of the discovery. Legal values:
   * <ul>
   *   <li>Producing</li>
   *   <li>Production is unlikely</li>
   *   <li>Shut down</li>
   *   <li>Production likely, but unclarified</li>
   *   <li>Included in other discovery</li>
   *   <li>Approved for production</li>
   *   <li>Production in clarification phase</li>
   *   <li>Production not evaluated</li>
   *   <li>Decided for production</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>dscCurrentActivityStatus</em>.
   *
   * @return  Activity status of this discovery. Null if unknown or N/A.
   */
  public String getActivityStatus()
  {
    return activityStatus_;
  }

  /**
   * Return hydrocarbon type of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Type of hydrocarbon. Example of legal values:
   * <ul>
   *   <li>GAS</li>
   *   <li>GAS/CONDENSATE</li>
   *   <li>OIL</li>
   *   <li>OIL/GAS</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>dscHcType</em>.
   *
   * @return  Hydrocarbon type of this discovery. Null if unknown or N/A.
   */
  public String getHydrocarbonType()
  {
    return hydrocarbonType_;
  }

  /**
   * Return wellbore name of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Name of the wellbore which made the discovery.
   * <p>
   * varchar(60), corresponds to NPD property <em>wlbName</em>.
   *
   * @return  Wellbore name of this discovery. Null if unknown or N/A.
   */
  public String getWellboreName()
  {
    return wellboreName_;
  }

  /**
   * Return main area of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Main NCS area. Example of legal values:
   * <ul>
   *   <li>NORTH SEA</li>
   *   <li>NORWEGIAN SEA</li>
   *   <li>BARENTS SEA</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>nmaName</em>.
   *
   * @return  Main area of this discovery. Null if unknown or N/A.
   */
  public String getMainArea()
  {
    return mainArea_;
  }

  /**
   * Return field name of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Official field name.
   * <p>
   * varchar(40), corresponds to NPD property <em>fldName</em>.
   *
   * @return  Field name of this discovery. Null if unknown or N/A.
   */
  public String getFieldName()
  {
    return fieldName_;
  }

  /**
   * Return date when this discovery was included in the field.
   * <p>
   * <b>NPD description:</b><br>
   * Included in field from date.
   * <p>
   * datetime, corresponds to NPD property <em>dscDateFromInclInField</em>.
   *
   * @return  Date when this discovery was included in the field. Null if unknown or N/A.
   */
  public Date getIncludedInFieldFromDate()
  {
    return includedInFieldFromDate_ != null ? new Date(includedInFieldFromDate_.getTime()) : null;
  }

  /**
   * Return discovery year of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * The year the discovery was made.
   * <p>
   * int, corresponds to NPD property <em>dscDiscoveryYear</em>.
   *
   * @return  Discovery year of this discovery. Null if unknown or N/A.
   */
  public String getDiscoveryYear()
  {
    return discoveryYear_;
  }

  /**
   * Return discovery name of the resources of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Name of the discovery the resources are included in.
   * <p>
   * varchar(40), corresponds to NPD property <em>dscResInclInDiscoveryName</em>.
   *
   * @return  Discovery name of the resources of this discovery. Null if unknown or N/A.
   */
  public String getResourcesDiscoveryName()
  {
    return resourcesDiscoveryName_;
  }

  /**
   * Return owner kind of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Kind of owner. Example of legal values:
   * <ul>
   *   <li>BUSINESS ARRANGEMENT AREA</li>
   *   <li>PRODUCTION LICENCE</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>dscOwnerKind</em>.
   *
   * @return  Owner kind of this discovery. Null if unknown or N/A.
   */
  public String getOwnerKind()
  {
    return ownerKind_;
  }

  /**
   * Return owner name of this discovery.
   * <p>
   * <b>NPD description:</b><br>
   * Owner name.
   * <p>
   * varchar(40), corresponds to NPD property <em>dscOwnerName</em>.
   *
   * @return  Owner name of this discovery. Null if unknown or N/A.
   */
  public String getOwnerName()
  {
    return ownerName_;
  }

  /**
   * Return NPDID of the field of this discovery.
   *
   * @return  NPDID of the field of this discovery. Null if unknown or N/A.
   */
  public String getNpdidField()
  {
    return npdidField_;
  }

  /**
   * Return NPDID of the wellbore of this discovery.
   *
   * @return  NPDID of the wellbore of this discovery. Null if unknown or N/A.
   */
  public String getNpdidWellbore()
  {
    return npdidWellbore_;
  }
}

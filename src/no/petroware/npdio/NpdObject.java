package no.petroware.npdio;

import java.util.Date;

/**
 * Base class for all first-order NPD instances.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public abstract class NpdObject
{
  /** The NPD type name. Non-null. */
  private final String type_;

  /** The NPD unique ID of the object. Non-null. */
  private final String npdId_;

  /** The NPD object name. Non-null. */
  private final String name_;

  /** URL to NPD fact page for this instance. Null if N/A. */
  private final String factPageUrl_;

  /** URL to NPD fact map for this instance. Null if N/A. */
  private final String factMapUrl_;

  /** Date this object was last changed. Null if unknown. */
  private final Date lastChangedDate_;

  /** Date synced with NPD back-end database. Null if unknown. */
  private final Date syncDate_;

  /**
   * Create a new NPD object.
   *
   * @param type             Type name as used by NPD. Non-null.
   * @param npdId            NPD unique ID. Non-null.
   * @param name             Object name. Non-null.
   * @param factPageUrl      URL to NPD fact page for this instance. Null if N/A.
   * @param factMapUrl       URL to NPD fact map for this instance. Null if N/A.
   * @param lastChangedDate  Date this instance were last changed at NPD. Null if unknown.
   * @param syncDate         Date synced with NPD back-end database. Null if unknown.
   */
  protected NpdObject(String type,
                      String npdId,
                      String name,
                      String factPageUrl,
                      String factMapUrl,
                      Date lastChangedDate,
                      Date syncDate)
  {
    if (type == null)
      throw new IllegalArgumentException("type cannot be null");

    if (npdId == null)
      throw new IllegalArgumentException("npdId cannot be null");

    if (name == null)
      throw new IllegalArgumentException("name cannot be null");

    type_ = type;
    npdId_ = npdId;
    name_ = name;
    factPageUrl_ = factPageUrl;
    factMapUrl_ = factMapUrl;
    lastChangedDate_ = lastChangedDate != null ? new Date(lastChangedDate.getTime()) : null;
    syncDate_ = syncDate != null ? new Date(syncDate.getTime()) : null;
  }

  /**
   * Return the NPD type name.
   *
   * @return  The NPD type name. Never null.
   */
  public String getType()
  {
    return type_;
  }

  /**
   * Return the NPD unique ID of this instance.
   *
   * @return  The NPD unique ID of this instance. Never null.
   */
  public String getNpdId()
  {
    return npdId_;
  }

  /**
   * Return the name of this instance.
   *
   * @return  The name of this instance. Never null.
   */
  public String getName()
  {
    return name_;
  }

  /**
   * Return URL to the instance fact page on NPD's FactWEB.
   *
   * @return  URL to the instance fact page on NPD's FactWEB. Null if N/A.
   */
  public String getFactPageUrl()
  {
    return factPageUrl_;
  }

  /**
   * Return URL to the instance fact map on NPD's FactWEB.
   *
   * @return  URL to the instance fact map on NPD's FactWEB. Null if N/A.
   */
  public String getFactMapUrl()
  {
    return factMapUrl_;
  }

  /**
   * Return date (some aspect of) this instance was last changed.
   * <p>
   * Refers to the NPD term <em>Date all updated</em>.
   *
   * @return Sync date of this instance. Null if unknown.
   */
  public Date getLastChangedDate()
  {
    return lastChangedDate_ != null ? new Date(lastChangedDate_.getTime()) : null;
  }

  /**
   * Return date this instance was synced from the NPD back-end database.
   *
   * @return Sync date of this instance. Null if unknown.
   */
  public Date getSyncDate()
  {
    return syncDate_ != null ? new Date(syncDate_.getTime()) : null;
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return npdId_.hashCode();
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(Object object)
  {
    if (object == null)
      return false;

    if (object == this)
      return true;

    if (!(object instanceof NpdObject))
      return false;

    NpdObject npdObject = (NpdObject) object;

    return npdId_.equals(npdObject.npdId_);
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return name_;
  }
}

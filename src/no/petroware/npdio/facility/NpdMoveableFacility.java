package no.petroware.npdio.facility;

import java.util.Date;

/**
 * A moveable facility as modeled by the NPD.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdMoveableFacility extends NpdFacility
{
  /** AOC status of the facility. */
  private final String aocStatus_;

  /** Unique ID of responsible company. Null if none or unknown. */
  private final String responsibleCompanyId_;

  /** Country of registration. Null if unknown. */
  private final String nation_;

  NpdMoveableFacility(String npdId,
                      String name,
                      String responsibleCompanyId,
                      String kind,
                      String functions,
                      String aocStatus,
                      String nation,
                      String factPageUrl,
                      Date lastChangedDate,
                      Date syncDate)
  {
    super("facility_moveable", npdId, name, kind, functions, factPageUrl, null,  lastChangedDate, syncDate);

    aocStatus_ = aocStatus;
    responsibleCompanyId_ = responsibleCompanyId;
    nation_ = nation;
  }

  /**
   * Return the AOC status of this facility.
   *
   * @return  AOC status of this facility. Null if none or unknown.
   */
  public String getAocStatus()
  {
    return aocStatus_;
  }

  /**
   * Return NPD unique ID of current responsible company of this facility.
   *
   * @return  NPD ID of responsible company. Null if none or unknown.
   */
  public String getResponsibleCompanyId()
  {
    return responsibleCompanyId_;
  }

  /**
   * Return name of the country the facility is currently registered in.
   *
   * @return  Name of country of registration. Null if unknown.
   */
  public String getNation()
  {
    return nation_;
  }
}

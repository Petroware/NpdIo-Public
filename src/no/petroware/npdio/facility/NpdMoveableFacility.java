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

  /** Name of responsible company. Null if none or unknown. */
  private final String responsibleCompanyName_;

  /** Unique ID of responsible company. Null if none or unknown. */
  private final String responsibleCompanyId_;

  /** Country of registration. Null if unknown. */
  private final String nation_;

  /**
   * Create an NPD moveable facility instance.
   */
  NpdMoveableFacility(String npdId,
                      String name,
                      String responsibleCompanyName,
                      String kind,
                      String functions,
                      String aocStatus,
                      String nation,
                      String factPageUrl,
                      String responsibleCompanyId,
                      Date lastChangedDate,
                      Date syncDate)
  {
    super("facility_moveable", npdId, name, kind, functions, factPageUrl, null,  lastChangedDate, syncDate);

    aocStatus_ = aocStatus;
    responsibleCompanyName_ = responsibleCompanyName;
    responsibleCompanyId_ = responsibleCompanyId;
    nation_ = nation;
  }

  /**
   * Return the AOC status of this facility.
   * <p>
   * <b>NPD description:</b><br>
   * AOC status of the facility. Example of legal values:
   * <ul>
   *   <li>AOC ok</li>
   *   <li>In queue for AOC</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>fclStatus</em>.
   *
   * @return  AOC status of this facility. Null if N/A or unknown.
   */
  public String getAocStatus()
  {
    return aocStatus_;
  }

  /**
   * Return name responsible company of this facility.
   * <p>
   * <b>NPD description:</b><br>
   * Official name of currently responsible company for a moveable facility.
   * <p>
   * varchar(100), corresponds to NPD property <em>fclCurrentRespCompanyName</em>.
   *
   * @return  Name of responsible company. Null if N/A or unknown.
   */
  public String getResponsibleCompanyName()
  {
    return responsibleCompanyName_;
  }

  /**
   * Return NPD unique ID of current responsible company of this facility.
   * <p>
   * <b>NPD description:</b><br>
   * NPD's unique id for companies.
   * <p>
   * int, corresponds to NPD property <em>fclNpdidCurrentRespCompany</em>.
   *
   * @return  NPD ID of responsible company. Null if N/A or unknown.
   */
  public String getResponsibleCompanyId()
  {
    return responsibleCompanyId_;
  }

  /**
   * Return name of the country of registration.
   * <p>
   * <b>NPD description:</b><br>
   * Name of the country the facility is currently registered in.
   * <p>
   * , corresponds to NPD property <em>fclNationName</em>.
   *
   * @return  Name of country of registration. Null if unknown.
   */
  public String getNation()
  {
    return nation_;
  }
}

package no.petroware.npdio.facility;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * Base class for fixed and movable facilities as modeled by the NPD.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public abstract class NpdFacility extends NpdObject
{
  /** Kind of this facility. May be null if unknown. */
  private final String kind_;

  /** Functions of this facility. May be null if unknown. */
  private final String functions_;

  /**
   * Create an NPD facility instance.
   */
  protected NpdFacility(String type,
                        String npdId,
                        String name,
                        String kind,
                        String functions,
                        String factPageUrl,
                        String factMapUrl,
                        Date lastChangedDate,
                        Date syncDate)
  {
    super(type, npdId, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    kind_ = kind;
    functions_ = functions;
  }

  /**
   * Return kind of this facility.
   * <p>
   * <b>NPD description:</b><br>
   * Kind of facility.
   * Example of legal values:
   * <ul>
   *   <li>CONCRETE STRUCTURE</li>
   *   <li>CONDEEP 3 SHAFTS</li>
   *   <li>CONDEEP 4 SHAFTS</li>
   *   <li>CONDEEP MONOSHAFT</li>
   *   <li>DORIS</li>
   *   <li>FPSO</li>
   *   <li>FSU</li>
   *   <li>JACKET 12 LEGS</li>
   *   <li>JACKET 4 LEGS</li>
   *   <li>JACKET 6 LEGS</li>
   *   <li>JACKET 8 LEGS</li>
   *   <li>JACKET TRIPOD</li>
   *   <li>JACK-UP 3 LEGS</li>
   *   <li>JACK-UP 4 LEGS</li>
   *   <li>LOADING SYSTEM</li>
   *   <li>MONOTOWER</li>
   *   <li>MULTI WELL TEMPLATE</li>
   *   <li>ONSHORE FACILITY</li>
   *   <li>SEMISUB CONCRETE</li>
   *   <li>SEMISUB STEEL</li>
   *   <li>SINGLE WELL TEMPLATE</li>
   *   <li>SUBSEA STRUCTURE</li>
   *   <li>TLP CONCRETE</li>
   *   <li>TLP STEEL</li>
   *   <li>VESSEL</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to NPD property <em>fclKind</em>.
   *
   * @return  Kind of this facility. Null if N/A or unknown.
   */
  public String getKind()
  {
    return kind_;
  }

  /**
   * Return functions of this facility. Tells what functions the
   * facility covers.
   * <p>
   * Examples:
   * <ul>
   *   <li>DRILLING</li>
   *   <li>DRILLING TEMPLATE</li>
   *   <li>FIELD CONTROL CENTER</li>
   *   <li>FISCAL METERING</li>
   *   <li>FLARE STACK</li>
   *   <li>FLOTEL</li>
   *   <li>FULL STABILIZATION</li>
   *   <li>GAS EXPORT</li>
   *   <li>GAS INJECTION</li>
   *   <li>GAS INJECTOR</li>
   *   <li>GAS PRODUCER</li>
   *   <li>ISOLATION VALVE</li>
   *   <li>LOADING BOUY</li>
   *   <li>MANIFOLD</li>
   *   <li>MANIFOLD STATION</li>
   *   <li>OFFLOADING</li>
   *   <li>OIL PRODUCER</li>
   *   <li>PIG RECIVER</li>
   *   <li>PIPELINE END MANIFOLD</li>
   *   <li>QUARTER</li>
   *   <li>RISER</li>
   *   <li>RISER BASE</li>
   *   <li>RISER SUPPORT</li>
   *   <li>SEPARATION</li>
   *   <li>SILO</li>
   *   <li>STORAGE</li>
   *   <li>T-CONNECTION</li>
   *   <li>TERMINAL</li>
   *   <li>TRAWLGEAR PROTECTION</li>
   *   <li>TUNNEL</li>
   *   <li>UMBILICAL SUPPORT</li>
   *   <li>WATER INJECTION</li>
   *   <li>WATER/GAS INJECTION</li>
   *   <li>WELLHEAD</li>
   *   <li>Y-CONNECTION</li>
   *   <li>PROCESSING</li>
   *   <li>ACCOMMODATION</li>
   *   <li>SUPPORT</li>
   *   <li>BOOSTER</li>
   *   <li>DISTRIBUTION</li>
   *   <li>WATER PRODUCER</li>
   * </ul>
   * or a combination of these.
   * varchar(400), corresponds to NPD property <em>fclFunctions</em>.
   *
   * @return  Functions of this facility. Null if N/A or unknown.
   */
  public String getFunctions()
  {
    return functions_;
  }
}




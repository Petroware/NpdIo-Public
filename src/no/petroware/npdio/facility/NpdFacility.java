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
   * Example of legal values:
   * <ul>
   *   <li>CONCRETE STRUCTURE
   *   <li>CONDEEP 3 SHAFTS
   *   <li>CONDEEP 4 SHAFTS
   *   <li>CONDEEP MONOSHAFT
   *   <li>DORIS
   *   <li>FPSO
   *   <li>FSU
   *   <li>JACKET 12 LEGS
   *   <li>JACKET 4 LEGS
   *   <li>JACKET 6 LEGS
   *   <li>JACKET 8 LEGS
   *   <li>JACKET TRIPOD
   *   <li>JACK-UP 3 LEGS
   *   <li>JACK-UP 4 LEGS
   *   <li>LOADING SYSTEM
   *   <li>MONOTOWER
   *   <li>MULTI WELL TEMPLATE
   *   <li>ONSHORE FACILITY
   *   <li>SEMISUB CONCRETE
   *   <li>SEMISUB STEEL
   *   <li>SINGLE WELL TEMPLATE
   *   <li>SUBSEA STRUCTURE
   *   <li>TLP CONCRETE
   *   <li>TLP STEEL
   *   <li>VESSEL
   * </ul>
   *
   * @return   Kind of this facility. Null if unknown.
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
   *   <li>DRILLING
   *   <li> DRILLING TEMPLATE
   *   <li> FIELD CONTROL CENTER
   *   <li> FISCAL METERING
   *   <li> FLARE STACK
   *   <li> FLOTEL
   *   <li> FULL STABILIZATION
   *   <li> GAS EXPORT
   *   <li> GAS INJECTION
   *   <li> GAS INJECTOR
   *   <li> GAS PRODUCER
   *   <li> ISOLATION VALVE
   *   <li> LOADING BOUY
   *   <li> MANIFOLD
   *   <li> MANIFOLD STATION
   *   <li> OFFLOADING
   *   <li> OIL PRODUCER
   *   <li> PIG RECIVER
   *   <li> PIPELINE END MANIFOLD
   *   <li> QUARTER
   *   <li> RISER
   *   <li> RISER BASE
   *   <li> RISER SUPPORT
   *   <li> SEPARATION
   *   <li> SILO
   *   <li> STORAGE
   *   <li> T-CONNECTION
   *   <li> TERMINAL
   *   <li> TRAWLGEAR PROTECTION
   *   <li> TUNNEL
   *   <li> UMBILICAL SUPPORT
   *   <li> WATER INJECTION
   *   <li> WATER/GAS INJECTION
   *   <li> WELLHEAD
   *   <li> Y-CONNECTION
   *   <li> PROCESSING
   *   <li> ACCOMMODATION
   *   <li> SUPPORT
   *   <li> BOOSTER
   *   <li> DISTRIBUTION
   *   <li> WATER PRODUCER
   * </ul>
   * or a combination of these.
   *
   * @return  Functions of this facility. Null if unknown.
   */
  public String getFunctions()
  {
    return functions_;
  }
}




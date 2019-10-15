package no.petroware.npdio.facility;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * Fixed facility as modeled by the NPD.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdFixedFacility extends NpdFacility
{
  private final String phase_;

  private final boolean isSurfaceFacility_;

  private final Class<? extends NpdObject> belongsToClass_;

  private final String belongsToId_;

  private final Date startupDate_;

  private final String geodeticDatum_;

  private final Integer nsDegrees_;

  private final Integer nsMinutes_;

  private final Double nsSeconds_;

  private final String nsCode_;

  private final Integer ewDegrees_;

  private final Integer ewMinutes_;

  private final Double ewSeconds_;

  private final String ewCode_;

  private final Double waterDepth_;

  private final Integer designedLifetime_;

  /**
   * Create a NPD fixed facility.
   */
  NpdFixedFacility(String npdId,
                   String name,
                   String kind,
                   String functions,
                   String phase,
                   boolean isSurfaceFacility,
                   Class<? extends NpdObject> belongsToClass,
                   String belongsToId,
                   Date startupDate,
                   String geodeticDatum,
                   Integer nsDegrees,
                   Integer nsMinutes,
                   Double nsSeconds,
                   String nsCode,
                   Integer ewDegrees,
                   Integer ewMinutes,
                   Double ewSeconds,
                   String ewCode,
                   double waterDepth,
                   Integer designedLifetime,
                   String factPageUrl,
                   String factMapUrl,
                   Date lastChangedDate,
                   Date syncDate)
  {
    super("facility_fixed", npdId, name, kind, functions, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    phase_ = phase;
    isSurfaceFacility_ = isSurfaceFacility;
    belongsToClass_ = belongsToClass;
    belongsToId_ = belongsToId;
    startupDate_ = startupDate != null ? new Date(startupDate.getTime()) : null;
    geodeticDatum_ = geodeticDatum;
    nsDegrees_ = nsDegrees;
    nsMinutes_ = nsMinutes;
    nsSeconds_ = nsSeconds;
    nsCode_ = nsCode;
    ewDegrees_ = ewDegrees;
    ewMinutes_ = ewMinutes;
    ewSeconds_ = ewSeconds;
    ewCode_ = ewCode;
    waterDepth_ = waterDepth;
    designedLifetime_ = designedLifetime;
  }

  /**
   * Return current phase of the facility.
   * <p>
   * Example of legal values:
   * <ul>
   *   <li>ABANDONED IN PLACE
   *   <li>DECOMMISSIONED
   *   <li>FABRICATION
   *   <li>FUTURE
   *   <li>IN SERVICE
   *   <li>INSTALLATION
   *   <li>LAID UP
   *   <li>REMOVED
   * </ul>
   *
   * @return Current phase of the facility.
   */
  public String getPhase()
  {
    return phase_;
  }

  /**
   * Indicator telling if the facility is a surface facility.
   *
   * @return Indicator telling if the facility is a surface facility.
   *         True if it is a surface facility, false if it is a
   *         subsurface facility.
   */
  public boolean isSurfaceFacility()
  {
    return isSurfaceFacility_;
  }

  /**
   * Class of instance this facility belongs to, such as NpdField.class
   * etc.
   *
   * @return  Class of instance this facility belongs to. Null if none.
   */
  public Class<? extends NpdObject> getBelongsToClass()
  {
    return belongsToClass_;
  }

  /**
   * NPD unique ID of instance this facility belongs to.
   *
   * @return  NPD unique ID of instance this facility belongs to.
   *          Null if unknown.
   */
  public String getBelongsToId()
  {
    return belongsToId_;
  }

  /**
   * Return the date the facility was set in production.
   *
   * @return  The date the facility was set in production.
   *          Null if unknown.
   */
  public Date getStartupDate()
  {
    return startupDate_ != null ? new Date(startupDate_.getTime()) : null;
  }

  /**
   * Geodetic datum for the coordinates of the position of the facility.
   *
   * @return  Geodetic datum for the coordinates. Null if unknown.
   */
  public String getGeodeticDatum()
  {
    return geodeticDatum_;
  }

  /**
   * Coordinate, north-south degrees.
   *
   * @return Coordinate, north-south degrees.
   */
  public Integer getNsDegrees()
  {
    return nsDegrees_;
  }

  /**
   * Coordinate, north-south minutes.
   *
   * @return  Coordinate, north-south minutes.
   */
  public Integer getNsMinutes()
  {
    return nsMinutes_;
  }

  /**
   * Coordinate, north-south milliseconds.
   *
   * @return  Coordinate, north-south milliseconds.
   */
  public double getNsSeconds()
  {
    return nsSeconds_;
  }

  /**
   * Indicator which tells if the coordinate is in the northern or
   * southern hemisphere. Example of legal values: N, S.
   *
   * @return  North or south indicator. Null if unknown.
   */
  public String getNsCode()
  {
    return nsCode_;
  }

  /**
   * Coordinate, east-west degrees.
   *
   * @return Coordinate, east-west degrees.
   */
  public Integer getEwDegrees()
  {
    return ewDegrees_;
  }

  /**
   * Coordinate, east-west minutes.
   *
   * @return Coordinate, east-west minutes.
   */
  public Integer getEwMinutes()
  {
    return ewMinutes_;
  }

  /**
   * Coordinate, east-west milliseconds.
   *
   * @return Coordinate, east-west milliseconds.
   */
  public Double getEwSeconds()
  {
    return ewSeconds_;
  }

  /**
   * Indicator which tells if the coordinate is in the eastern or
   * western hemisphere. Example of legal values: E, W.
   *
   * @return East or west indicator. Null if unknown.
   */
  public String getEwCode()
  {
    return ewCode_;
  }

  /**
   * Return facility location as a formatted string.
   *
   * @return  Facility location.
   */
  public String getUtmPosition()
  {
    // TODO: Handle nulls

    StringBuilder s = new StringBuilder();
    s.append(nsDegrees_);
    s.append("\u00b0");
    s.append(nsMinutes_);
    s.append("'");
    s.append(nsSeconds_);
    s.append("\"");
    s.append(nsCode_);
    s.append(" ");
    s.append(ewDegrees_);
    s.append("\u00b0");
    s.append(ewMinutes_);
    s.append("'");
    s.append(ewSeconds_);
    s.append("\"");
    s.append(ewCode_);

    return s.toString();
  }

  /**
   * Water depth from mean sea level at well site.
   *
   * @return  Water depth.
   */
  public Double getWaterDepth()
  {
    return waterDepth_;
  }

  /**
   * The number of years the facility was designed for.
   *
   * @return  Number of years the facility was designed for.
   *          Null if not known.
   */
  public Integer getDesignedLifetime()
  {
    return designedLifetime_;
  }
}

package no.petroware.npdio.survey;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * A survey as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdSurvey extends NpdObject
{
  /** Survey status. Null if N/A or unknown. */;
  private final String status_;

  /** Geographical area. Null if N/A or unknown. */;
  private final String area_;

  /** Survey midpoint. Null if N/A or unknown. */;
  private final String midPoint_;

  /** Survey category. Null if N/A or unknown. */;
  private final String category_;

  /** Survey type. Null if N/A or unknown. */;
  private final String mainType_;

  /** Survey sub type. Null if N/A or unknown. */;
  private final String subType_;

  /** Company responsible for the survey. Null if N/A or unknown. */;
  private final String company_;

  /** Vessel(s) used in the survey. Null if N/A or unknown. */;
  private final String vessel_;

  /** Planned start date. Null if N/A or unknown. */;
  private final Date plannedStartDate_;

  /** Planned completion date. Null if N/A or unknown. */;
  private final Date plannedCompleteDate_;

  /** Date the survey started. Null if N/A or unknown. */;
  private final Date startDate_;

  /** Date the acquisition was completed. Null if N/A or unknown. */;
  private final Date completeDate_;

  /** Total planned acquisition length. Null if N/A or unknown. */;
  private final Double plannedTotalLengthBoat_;

  /** Total planned acquisition length. Null if N/A or unknown. */;
  private final Double plannedTotalLengthCdp_;

  /** Planned net area. Null if N/A or unknown. */;
  private final Double netAreaPlanned_;

  /** Actual net area. Null if N/A or unknown. */;
  private final Double netAreaActual_;

  /** Is survey market available. Null if N/A or unknown. */;
  private final Boolean isAvailable_;

  /** Has sampling been performed? Null if N/A or unknown. */;
  private final Boolean isSamplingDone_;

  /** Has shallow drilling been performed? Null if N/A or unknown. */;
  private final Boolean isShallowDrillingDone_;

  /** Has geotechnical measurement been performed? Null if N/A or unknown. */;
  private final Boolean isGeotechnicalMeasurementDone_;

  /**
   * Create an NPD survey instance.
   */
  NpdSurvey(String npdId,
            String name,
            String status,
            String area,
            String midPoint,
            String category,
            String mainType,
            String subType,
            String company,
            String vessel,
            Date plannedStartDate,
            Date plannedCompleteDate,
            Date startDate,
            Date completeDate,
            Double plannedTotalLengthBoat,
            Double plannedTotalLengthCdp,
            Double netAreaPlanned,
            Double netAreaActual,
            Boolean isAvailable,
            Boolean isSamplingDone,
            Boolean isShallowDrillingDone,
            Boolean isGeotechnicalMeasurementDone,
            String factPageUrl,
            String factMapUrl,
            Date syncDate)
  {
    super("survey_level1_planning_finished", npdId, name, factPageUrl, factMapUrl, null, syncDate);

    status_ = status;
    area_ = area;
    midPoint_ = midPoint;
    category_ = category;
    mainType_ = mainType;
    subType_ = subType;
    company_ = company;
    vessel_ = vessel;
    plannedStartDate_ = plannedStartDate != null ? new Date(plannedStartDate.getTime()) : null;
    plannedCompleteDate_ = plannedCompleteDate != null ? new Date(plannedCompleteDate.getTime()) : null;
    startDate_ = startDate != null ? new Date(startDate.getTime()) : null;
    completeDate_ = completeDate != null ? new Date(completeDate.getTime()) : null;
    plannedTotalLengthBoat_ = plannedTotalLengthBoat;
    plannedTotalLengthCdp_ = plannedTotalLengthCdp;
    netAreaPlanned_ = netAreaPlanned;
    netAreaActual_ = netAreaActual;
    isAvailable_ = isAvailable;
    isSamplingDone_ = isSamplingDone;
    isShallowDrillingDone_ = isShallowDrillingDone;
    isGeotechnicalMeasurementDone_ = isGeotechnicalMeasurementDone;
  }

  /**
   * Return status of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Example of legal values:
   * <ul>
   *   <li>Høring</li>
   *   <li>Planlagt</li>
   *   <li>Pågående</li>
   *   <li>Pause</li>
   *   <li>Ferdig</li>
   * </ul>
   * <p>
   * varchar(100), corresponds to NPD property <em>seaStatus</em>.
   *
   * @return  Status of this survey. Null if N/A or unknown.
   */
  public String getStatus()
  {
    return status_;
  }

  /**
   * Return geographical area of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Geographical area in the Norwegian Continental Shelf.
   * Legal values are:
   * <ul>
   *   <li>Nordsjøen</li>
   *   <li>Norskehavet</li>
   *   <li>Barentshavets - Sør</li>
   *   <li>Barentshavet - Nord</li>
   * </ul>
   * <p>
   * varchar(255), corresponds to NPD property <em>seaGeographicalArea</em>.
   *
   * @return  Geographical area of this survey. Null if N/A or unknown.
   */
  public String getArea()
  {
    return area_;
  }

  /**
   * Return midpoint of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Midpoint of maximum og minimum North / South position and for the
   * East/West posistion. The midpoint is given in degrees and decimal
   * minutes and using Norwegian notation for East (Ø),
   * i.e 73'' 29.8' N, 33'' 15.7' Ø
   * <p>
   * varchar(100), corresponds to NPD property <em>seaMidPoint</em>.
   *
   * @return  Midpoint of this survey. Null if unknown.
   */
  public String getMidPoint()
  {
    return midPoint_;
  }

  /**
   * Return category of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Indicates what method that is used in the survey.
   * If seismic source is used, the category is set to "Seismisk undersøkelse",
   * if electromagnetic source "Elektromagnetisk undersøkelse" is set.
   * Otherwise "Andre undersøkelser" is set.
   * See also "Main type" and "Sub type".
   * <p>
   * varchar(100), corresponds to NPD property <em>seaCategory</em>.
   *
   * @return  Category of this survey.
   */
  public String getCategory()
  {
    return category_;
  }

  /**
   * Return main type of the survey.
   * <p>
   * <b>NPD description:</b><br>
   * Main type of the survey. Legal values are:
   * <ul>
   *   <li>Ordinær seismisk undersøkelse</li>
   *   <li>Havbunnseismisk undersøkelse</li>
   *   <li>Elektromagnetisk undersøkelse</li>
   *   <li>Borestedsundersøkelse / Site survey</li>
   *   <li>Grunnundersøkelse / Soil survey</li>
   * </ul>
   * See also description of <em>category</em>.
   * <p>
   * varchar(100), corresponds to NPD property <em>seaSurveyTypeMain</em>.
   *
   * @return  Main type of this survey.
   */
  public String getMainType()
  {
    return mainType_;
  }

  /**
   * Return sub type of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Sub type of the survey. Legal values are:
   * <ul>
   *   <li>2D</li>
   *   <li>3D</li>
   *   <li>4D</li>
   *   <li>Ikke seismikk</li>
   * </ul>
   *
   * All values are relevant for the different main types, except of
   * "Ikke seismikk" that is only relevant for ,
   * "Borestedsundersøkelse / Site survey", "Grunnundersøkelse / Soil survey".
   * <p>
   * varchar(100), corresponds to NPD property <em>seaSurveyTypePart</em>.
   *
   * @return  Sub type of this survey. Null if N/A.
   */
  public String getSubType()
  {
    return subType_;
  }

  /**
   * Return company responsible for the survey.
   * <p>
   * <b>NPD description:</b><br>
   * Company responsible for the survey.
   * <p>
   * varchar(100), corresponds to NPD property <em>seaCompanyReported</em>.
   *
   * @return  Company responsible for the survey. Null if unknown or N/A.
   */
  public String getCompany()
  {
    return company_;
  }

  /**
   *
   * Return vessel(s) used in the survey.
   * <p>
   * <b>NPD description:</b><br>
   * Vessels used in the survey (<em>main vessel</em> = "hovedfartøy" and
   * <em>escort vessels</em> = "følgefartøy").
   * The boats shown here are entered in  the message sent NPD by the company
   * doing the survey.
   * <p>
   * varchar(1000), corresponds to NPD property <em>seaVesselAll</em>.
   *
   * @return  The vessel(s) used in this survey. Null if unknown or N/A.
   */
  public String getVessel()
  {
    return vessel_;
  }

  /**
   * Return planned start date for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Date when the company has reported plans to start the acquisition.
   * <p>
   * datetime, corresponds to NPD property <em>seaPlanFromDate</em>.
   *
   * @return  Planned start date. Null if unknown or not specified.
   */
  public Date getPlannedStartDate()
  {
    return plannedStartDate_ != null ? new Date(plannedStartDate_.getTime()) : null;
  }

  /**
   * Return planned completion date for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Date when the company has reported that the acquisition shall be finished.
   * <p>
   * datetime, corresponds to NPD property <em>seaPlanToDate</em>.
   *
   * @return  Planned completion date. Null if unknown or not specified.
   */
  public Date getPlannedCompleteDate()
  {
    return plannedCompleteDate_ != null ? new Date(plannedCompleteDate_.getTime()) : null;
  }

  /**
   * Return start date for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Date the survey started. Started means when the vessel arrives
   * the acquisition area.
   * <p>
   * datetime, corresponds to NPD property <em>seaDateStarting</em>.
   *
   * @return  Survey start date. Null if unknown or if survey is not yet started.
   */
  public Date getStartDate()
  {
    return startDate_ != null ? new Date(startDate_.getTime()) : null;
  }

  /**
   * Return completion date for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Date the acquistion was completed. Completed means departure
   * from the acquisition area.
   * <p>
   * datetime, corresponds to NPD property <em>seaDateFinalized</em>.
   *
   * @return  Survey completion date. Null if unknown or if survey is not completed.
   */
  public Date getCompleteDate()
  {
    return completeDate_ != null ? new Date(completeDate_.getTime()) : null;
  }

  /**
   * Return total planned acquisition length for the boat.
   * <p>
   * <b>NPD description:</b><br>
   * Total planned acquistion length.
   * <p>
   * int, corresponds to NPD property <em>seaPlanBoatKm</em>.
   *
   * @return  Total planned acquisition length in km.
   *          Null if unknown or unspecified.
   */
  public Double getPlannedTotalLengthBoat()
  {
    return plannedTotalLengthBoat_;
  }

  /**
   * Return total planned acquisition length along CDP.
   * <p>
   * <b>NPD description:</b><br>
   * Total planned acquistion length.
   * <p>
   * int, corresponds to NPD property <em>seaPlanCdpKm</em>.
   *
   * @return  Total planned acquisition length along CDP in km. Null if unknown or unspecified.
   */
  public Double getPlannedTotalLengthCdp()
  {
    return plannedTotalLengthCdp_;
  }

  /**
   * Return planned net area of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Planned net area - calculated by NPD map tool if 3D or 4D.
   * <p>
   * decimal, corresponds to NPD property <em>sea3DKm2</em>.
   *
   * @return  Planned net area of this survey in km2. Null if unknown or unspecified.
   */
  public Double getNetAreaPlanned()
  {
    return netAreaPlanned_;
  }

  /**
   * Return actual area of this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Actual net area as reported by the company - 3D/4D [km2].
   * <p>
   * int, corresponds to NPD property <em>seaSurveyAcquired</em>.
   *
   * @return  Actual area of this survey in km2. Null if unknown or unspecified.
   */
  public Double getNetAreaActual()
  {
    return netAreaPlanned_;
  }

  /**
   * Return if the survey is classified as <em>market available</em>.
   * <p>
   * <b>NPD description:</b><br>
   * Indicator which tells if the survey is classified as market
   * available (see Petroleum regulation § 85).
   * <p>
   * varchar(20), corresponds to NPD property <em>seaMarketAvailable</em>.
   *
   * @return  True if the survey is market available, false otherwise.
   *          Null in unknown or N/A.
   */
  public Boolean isAvailable()
  {
    return isAvailable_;
  }

  /**
   * Return if sampling has been performed for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Has sampling been performed? (Ja/Nei).
   * <p>
   * varchar(20), corresponds to NPD property <em>seaSampling</em>.
   *
   * @return  True if sampling has been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isSamplingDone()
  {
    return isSamplingDone_;
  }

  /**
   * Return if shallow drilling has been performed for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Has shallow drilling been performed? (Ja/Nei).
   * <p>
   * varchar(20), corresponds to NPD property <em>seaShallowDrilling</em>.
   *
   * @return  True if shallow drilling has been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isShallowDrillingDone()
  {
    return isShallowDrillingDone_;
  }

  /**
   * Check if geotechnical measurement has been performed for this survey.
   * <p>
   * <b>NPD description:</b><br>
   * Has geotechnical measurement been performed? (Ja/Nei).
   * <p>
   * varchar(20), corresponds to NPD property <em>seaGeotechnical</em>.
   *
   * @return  True if geotechnical measurement been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isGeotechnicalMeasurementDone()
  {
    return isGeotechnicalMeasurementDone_;
  }
}

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
  /**
   * Status for the survey. Example of legal values:
   * "Høring", "Planlagt", "Pågående", "Pause", "Ferdig".
   * <p>
   * varchar(100)
   */
  private final String status_;

  /**
   * Geographical area in the Norwegian Continental Shelf.
   * Legal values are "Nordsjøen", "Norskehavet", "Barentshavets - Sør" ,
   * Barentshavet - Nord".
   * <p>
   * varchar(255)
   */
  private final String area_;

  /**
   * Midpoint of maximum and minimum North / South position and for the East/West posistion.
   * The midpoint is given in degrees and decimal minutes and using Norwegian notation
   * for East (Ø), i.e 73'' 29.8' N, 33'' 15.7' Ø.
   * <p>
   * varchar(100)
   */
  private final String midPoint_;

  /**
   * Indicates what method that is used in the survey.
   * If seismic source is used the category is set to "Seismisk undersøkelse",
   * if electromagnetic source "Elektromagnetisk undersøkelse" is set.
   * Otherwise "Andre undersøkelser" is set. See also "Main type" and "Sub type".
   * <p>
   * varchar(100)
   */
  private final String category_;

  /**
   * Main type of the survey. Legal values are "Ordinær seismisk undersøkelse",
   * "Havbunnseismisk undersøkelse", "Elektromagnetisk undersøkelse",
   * "Borestedsundersøkelse / site survey", "Grunnundersøkelse".
   * See also description of" Category".
   * <p>
   * varchar(100)
   */
  private final String mainType_;

  /**
   * Sub type of the survey. Legal values are: "2D", "3D", "4D" and "Ikke seismikk".
   * All values are relevant for the different main types, except of "Ikke seismikk"
   * that is only relevant for ,"Borestedsundersøkelse / site survey", "Grunnundersøkelse".
   * <p>
   * varchar(100)
   */
  private final String subType_;

  /**
   * Company responsible for the survey.
   * <p>
   * varchar(100)
   */
  private final String company_;

  /**
   * Vessels used in the survey (main vessel = hovedfartøy and escort vessels = følgefartøy).
   * The boats shown here are entered in the message sent NPD by the company doing the survey.
   * <p>
   * varchar(1000)
   */
  private final String vessel_;

  /**
   * Date when the company has reported plans to start the acquisition.
   * <p>
   * datetime
   */
  private final Date plannedStartDate_;

  /**
   * Date when the company has reported that the acquisition shall be finished.
   * <p>
   * datetime
   */
  private final Date plannedCompleteDate_;

  /**
   * Date the survey started. Started means when the vessel arrives the acquisition area.
   * <p>
   * datetime
   */
  private final Date startDate_;

  /**
   * Date the acquisition was completed. Completed means departure from the acquisition area.
   * <p>
   * datetime
   */
  private final Date completeDate_;

  /**
   * Total planned acquisition length. km.
   * <p>
   * int
   */
  private final Double plannedTotalLengthBoat_;

  /**
   * Total planned acquisition length. km.
   * <p>
   * int
   */
  private final Double plannedTotalLengthCdp_;

  /**
   * Planned net area - calculated by NPD map tool if 3D or 4D. km2.
   * <p>
   * decimal
   */
  private final Double netAreaPlanned_;

  /**
   * Actual net area as reported by the company - 3D/4D [km2]
   * <p>
   * decimal
   */
  private final Double netAreaActual_;

  /**
   * Indicator which tells if the survey is classified as market available
   * (see Petroleum regulation § 85). Legal values, "Yes" , "No".
   * <p>
   * varchar(20)
   */
  private final Boolean isAvailable_;

  /**
   * Has sampling been performed? (Ja/Nei).
   * <p>
   * varchar(20)
   */
  private final Boolean isSamplingDone_;

  /**
   * Has shallow drilling been performed? (Ja/Nei).
   * <p>
   * varchar(20)
   */
  private final Boolean isShallowDrillingDone_;

  /**
   * Has geotechnical measurement been performed? (Ja/Nei).
   * <p>
   * varchar(20)
   */
  private final Boolean isGeotechnicalMeasurementDone_;

  /**
   * Create a NPD survey.
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
   * Example of legal values:
   * <ul>
   *   <li>Høring</li>
   *   <li>Planlagt</li>
   *   <li>Pågående</li>
   *   <li>Pause</li>
   *   <li>Ferdig</li>
   * </ul>
   *
   * @return  Status for this survey.
   */
  public String getStatus()
  {
    return status_;
  }

  /**
   * Return geographical area in the Norwegian Continental Shelf.
   * <p>
   * Legal values are
   * <ul>
   *   <li>Nordsjøen</li>
   *   <li>Norskehavet</li>
   *   <li>Barentshavets - Sør</li>
   *   <li>Barentshavet - Nord</li>
   * </ul>
   *
   * @return  Geographical area of this survey.
   */
  public String getArea()
  {
    return area_;
  }

  /**
   * Return midpoint of maximum and minimum North / South position and for the
   * East/West position.
   * <p>
   * The midpoint is given as a string in degrees and decimal minutes and using
   * Norwegian notation for East (Ø), i.e 73'' 29.8' N, 33'' 15.7' Ø.
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
   * Indicates what method that is used in the survey.
   * If seismic source is used, the category is set to "Seismisk undersøkelse",
   * if electromagnetic source "Elektromagnetisk undersøkelse" is set.
   * Otherwise "Andre undersøkelser" is set. See also "Main type" and "Sub type".
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
   * Legal values are
   * <ul>
   *   <li>Ordinær seismisk undersøkelse</li>
   *   <li>Havbunnseismisk undersøkelse</li>
   *   <li>Elektromagnetisk undersøkelse</li>
   *   <li>Borestedsundersøkelse / site survey</li>
   *   <li>Grunnundersøkelse</li>
   * </ul>
   *
   * See also description of <em>category</em>.
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
   * Legal values are
   * <ul>
   *   <li>2D</li>
   *   <li>3D</li>
   *   <li>4D</li>
   *   <li>Ikke seismikk</li>
   * </ul>
   *
   * All values are relevant for the different main types, except of "Ikke seismikk"
   * that is only relevant for ,"Borestedsundersøkelse / site survey", "Grunnundersøkelse".
   *
   * @return  Sub type of this survey. Null if N/A.
   */
  public String getSubType()
  {
    return subType_;
  }

  /**
   * Return company responsible for the survey.
   *
   * @return  Company responsible for the survey. Null if unknown or N/A.
   */
  public String getCompany()
  {
    return company_;
  }

  /**
   * Return vessel(s) used in the survey.
   * <p>
   * <em>main vessel</em> = "hovedfartøy" and <em>escort vessels</em> = "følgefartøy".
   *
   * The boats shown here are entered in  the message sent NPD by the company
   * doing the survey.
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
   * This is the date when the company has reported plans to start the acquisition.
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
   * Date when the company has reported that the acquisition shall be finished.
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
   * This is the date the survey started.
   * Started means when the vessel arrives the acquisition area.
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
   * This is the date the acquisition was completed.
   * Completed means departure from the acquisition area.
   *
   * @return  Survey completion date. Null if unknown or if survey is not completed.
   */
  public Date getCompleteDate()
  {
    return completeDate_ != null ? new Date(completeDate_.getTime()) : null;
  }

  /**
   * Return total planned acquisition length for the boat.
   *
   * @return  Total planned acquisition length in km. Null if unknown or unspecified.
   */
  public Double getPlannedTotalLengthBoat()
  {
    return plannedTotalLengthBoat_;
  }

  /**
   * Return total planned acquisition length along CDP.
   *
   * @return  Total planned acquisition length along CDP in km. Null if unknown or unspecified.
   */
  public Double getPlannedTotalLengthCdp()
  {
    return plannedTotalLengthCdp_;
  }

  /**
   * Return planned net area for the survey.
   * Calculated by NPD map tool if 3D or 4D.
   *
   * @return  Survey planned area in km2. Null if unknown or unspecified.
   */
  public Double getNetAreaPlanned()
  {
    return netAreaPlanned_;
  }

  /**
   * Return actual area for the survey.
   * Actual net area as reported by the company - 3D/4D.
   *
   * @return  Survey actual area in km2. Null if unknown or unspecified.
   */
  public Double getNetAreaActual()
  {
    return netAreaPlanned_;
  }

  /**
   * Check if the survey is classified as <em>market available</em>.
   *
   * @return  True if the survey is market available, false otherwise.
   *          Null in unknown or N/A.
   */
  public Boolean isAvailable()
  {
    return isAvailable_;
  }

  /**
   * Check if sampling been performed.
   *
   * @return  True if sampling has been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isSamplingDone()
  {
    return isSamplingDone_;
  }

  /**
   * Check if shallow drilling been performed.
   *
   * @return  True if shallow drilling has been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isShallowDrillingDone()
  {
    return isShallowDrillingDone_;
  }

  /**
   * Check if geotechnical measurement been performed.
   *
   * @return  True if geotechnical measurement been performed, false otherwise.
   *          Null if unknown or N/A.
   */
  public Boolean isGeotechnicalMeasurementDone()
  {
    return isGeotechnicalMeasurementDone_;
  }
}

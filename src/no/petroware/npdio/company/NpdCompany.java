package no.petroware.npdio.company;

import java.util.Date;

import no.petroware.npdio.NpdObject;

/**
 * A company as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdCompany extends NpdObject
{
  private final String organizationNumber_;
  private final String shortName_;
  private final String nationCode_;
  private final String surveyPrefix_;
  private final boolean isCurrentLicenseOperator_;
  private final boolean isFormerLicenseOperator_;
  private final boolean isCurrentLicenseLicensee_;
  private final boolean isFormerLicenseLicensee_;

  /**
   * Create a NPD company instance.
   *
   * @param npdId                     NPD ID. Non-null.
   * @param name                      Company name. Non-null.
   * @param organizationNumber        Organization number. Null if N/A.
   * @param shortName                 Company short name. Null if N/A.
   * @param nationCode                Nation code. Null if not known.
   * @param surveyPrefix              Survey prefix. Null if N/A.
   * @param isCurrentLicenseOperator  Is company currently license operator?
   * @param isFormerLicenseOperator   Is company former license operator?
   * @param isCurrentLicenseLicensee  Is company currently license licensee?
   * @param isFormerLicenseLicensee   Is company former license licensee?
   * @param syncDate
   */
  NpdCompany(String npdId,
             String name,
             String organizationNumber,
             String shortName,
             String nationCode,
             String surveyPrefix,
             boolean isCurrentLicenseOperator,
             boolean isFormerLicenseOperator,
             boolean isCurrentLicenseLicensee,
             boolean isFormerLicenseLicensee,
             Date syncDate)
  {
    super("company", npdId, name, null, null, null, syncDate);

    organizationNumber_ = organizationNumber;
    shortName_ = shortName;
    nationCode_ = nationCode;
    surveyPrefix_ = surveyPrefix;
    isCurrentLicenseOperator_ = isCurrentLicenseOperator;
    isFormerLicenseOperator_ = isFormerLicenseOperator;
    isCurrentLicenseLicensee_ = isCurrentLicenseLicensee;
    isFormerLicenseLicensee_ = isFormerLicenseLicensee;
  }

  /**
   * Return the official Norwegian organization number.
   *
   * @return Official Norwegian organization number. Null if N/A.
   */
  public String getOrganizationNumber()
  {
    return organizationNumber_;
  }

  /**
   * Return company short name.
   *
   * @return  Company short name. Null if N/A.
   */
  public String getShortName()
  {
    return shortName_;
  }

  /**
   * Return two letter nation code (ISO) for the nation the company is
   * registered in.
   *
   * @return Nation code. Null if N/A or unknown.
   */
  public String getNationCode()
  {
    return nationCode_;
  }

  /**
   * Return prefix for survey names. In "ST14001", ST prefix for Statoil.
   *
   * @return  Prefix for survey names. Null if N/A.
   */
  public String getSurveyPrefix()
  {
    return surveyPrefix_;
  }

  /**
   * Indicator which tells if the company is currently a production
   * license operator.
   *
   * @return True if the company is currently a production
   *         license operator, false otherwise.
   */
  public boolean isCurrentLicenseOperator()
  {
    return isCurrentLicenseOperator_;
  }

  /**
   * Indicator which tells if the company has previously been a
   * production license operator.
   *
   * @return True if the company has previously been a
   *         production license operator, false otherwise.
   */
  public boolean isFormerLicenseOperator()
  {
    return isFormerLicenseOperator_;
  }

  /**
   * Indicator which tells if the company is currently a licensee in
   * production licenses.
   *
   * @return True if the company is currently a licensee in
   *         production licenses, fale otherwise.
   */
  public boolean isCurrentLicenseLicensee()
  {
    return isCurrentLicenseLicensee_;
  }

  /**
   * Indicator which tells if the company has been a licensee in production
   * licenses.
   *
   * @return True if the company has been a licensee in production
   *         licenses, false otherwise.
   */
  public boolean isFormerLicenseLicensee()
  {
    return isFormerLicenseLicensee_;
  }
}

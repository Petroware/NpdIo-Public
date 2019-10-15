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
  /** Norwegian organization number. Null if N/A. */
  private final String organizationNumber_;

  /** Company short name. Null if N/A. */
  private final String shortName_;

  /** Nation ISO code. Null if unknown. */
  private final String nationCode_;

  /** Survey prefix. Null if N/A or unknown. */
  private final String surveyPrefix_;

  /** Flag indicating if company is an operator. */
  private final boolean isCurrentLicenseOperator_;

  /** Flag indicating if company was an operator. */
  private final boolean isFormerLicenseOperator_;

  /** Flag indicating if company is a licensee. */
  private final boolean isCurrentLicenseLicensee_;

  /** Flag indicating if company was a licensee. */
  private final boolean isFormerLicenseLicensee_;

  /**
   * Create an NPD company instance.
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
   * Return the official Norwegian organization number of this company.
   * <p>
   * <b>NPD description:</b><br>
   * Official Norwegian organisation number.
   * <p>
   * varchar(100), corresponds to NPD property <em>cmpOrgNumberBrReg</em>.
   *
   * @return Official Norwegian organization number. Null if N/A.
   */
  public String getOrganizationNumber()
  {
    return organizationNumber_;
  }

  /**
   * Return short name of this company.
   * <p>
   * <b>NPD description:</b><br>
   * Missing.
   * <p>
   * Corresponds to NPD property <em>cmpShortName</em>.
   *
   * @return  Company short name. Null if N/A.
   */
  public String getShortName()
  {
    return shortName_;
  }

  /**
   * Return two letter nation code (ISO) of this company.
   * registered in.
   * <p>
   * <b>NPD description:</b><br>
   * 2 letter nation code (ISO) for the nation the company is registered in.
   * <p>
   * varchar(2), corresponds to NPD property <em>cmpNationCode</em>.
   *
   * @return  Nation code of this company. Null if N/A or unknown.
   */
  public String getNationCode()
  {
    return nationCode_;
  }

  /**
   * Return prefix for survey names of this company.
   * <p>
   * <b>NPD description:</b><br>
   * Prefix for survey names. In "ST14001" is ST prefx for Statoil.
   * <p>
   * varchar(4), corresponds to NPD property <em>cmpSurveyPrefix</em>.
   *
   * @return  Prefix for survey names. Null if N/A.
   */
  public String getSurveyPrefix()
  {
    return surveyPrefix_;
  }

  /**
   * Check if this company is currently a license operator.
   * <p>
   * <b>NPD description:</b><br>
   * Indicator which tells if the company is currently a production licence
   * operator.
   * <p>
   * varchar(1), corresponds to NPD property <em>cmpLicenceOperCurrent</em>.
   *
   * @return True if the company is currently a license operator, false otherwise.
   */
  public boolean isCurrentLicenseOperator()
  {
    return isCurrentLicenseOperator_;
  }

  /**
   * Check if this company was a former license operator.
   * <p>
   * <b>NPD description:</b><br>
   * Indicator which tells if the company has previously been a
   * production license operator.
   * <p>
   * varchar(1), corresponds to NPD property <em>cmpLicenceOperFormer</em>.
   *
   * @return True if the company was a former license operator, false otherwise.
   */
  public boolean isFormerLicenseOperator()
  {
    return isFormerLicenseOperator_;
  }

  /**
   * Check if this company is currently a licensee.
   * <p>
   * <b>NPD description:</b><br>
   * Indicator which tells if the company is currently a licensee in
   * production licenses.
   * <p>
   * varchar(1), corresponds to NPD property <em>cmpLicenceLicenseeCurrent</em>.
   *
   * @return True if this company is currently a licensee, false otherwise.
   */
  public boolean isCurrentLicenseLicensee()
  {
    return isCurrentLicenseLicensee_;
  }

  /**
   * Check if this company was a former licensee.
   * <p>
   * <b>NPD description:</b><br>
   * Indicator which tells if the company has been a licensee in production
   * licenses.
   * <p>
   * varchar(1), corresponds to NPD property <em>cmpLicenceLicenseeFormer</em>.
   *
   * @return True if this company was a former licensee, false otherwise.
   */
  public boolean isFormerLicenseLicensee()
  {
    return isFormerLicenseLicensee_;
  }
}

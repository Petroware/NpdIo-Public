package no.petroware.npdio.company;

import java.text.ParseException;
import java.util.Date;

import no.petroware.npdio.NpdReader;

/**
 * NPD company reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdCompanyReader extends NpdReader<NpdCompany>
{
  /**
   * The company properties and their order is as follows:
   *
   *   cmpLongName
   *   cmpOrgNumberBrReg
   *   cmpShortName
   *   cmpNationCode
   *   cmpSurveyPrefix
   *   cmpNpdidCompany
   *   cmpLicenceOperCurrent
   *   cmpLicenceOperFormer
   *   cmpLicenceLicenseeCurrent
   *   cmpLicenceLicenseeFormer
   *   dateSyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int ORGANIZATION_NUMBER_INDEX = 1;
  private static final int SHORT_NAME_INDEX = 2;
  private static final int NATION_CODE_INDEX = 3;
  private static final int SURVEY_PREFIX_INDEX = 4;
  private static final int NPDID_INDEX = 5;
  private static final int IS_CURRENT_LICENSE_OPERATOR_INDEX = 6;
  private static final int IS_FORMER_LICENSE_OPERATOR_INDEX = 7;
  private static final int IS_CURRENT_LICENSE_LICENSEE_INDEX = 8;
  private static final int IS_FORMER_LICENSE_LICENSEE_INDEX = 9;
  private static final int DATE_SYNCED_INDEX = 10;

  /**
   * Create a reader for NPD companies.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdCompanyReader(String url)
  {
    super(url);
  }

  /**
   * Create a new NPD company instance from the given tokens.
   *
   * @param tokens  Tokens that makes up one row in the company database table. Non-null.
   * @return        The created company instance. Never null.
   * @throws ParseException  If some of the tokens doesn't meet the requirements for its
   *                property.
   */
  protected NpdCompany newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 11)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String organizationNumber = tokens[ORGANIZATION_NUMBER_INDEX];
    String shortName = tokens[SHORT_NAME_INDEX];
    String nationCode = tokens[NATION_CODE_INDEX];
    String surveyPrefix = tokens[SURVEY_PREFIX_INDEX];
    boolean isCurrentLicenseOperator = parseBoolean(tokens[IS_CURRENT_LICENSE_OPERATOR_INDEX]);
    boolean isFormerLicenseOperator = parseBoolean(tokens[IS_FORMER_LICENSE_OPERATOR_INDEX]);
    boolean isCurrentLicenseLicensee = parseBoolean(tokens[IS_CURRENT_LICENSE_LICENSEE_INDEX]);
    boolean isFormerLicenseLicensee = parseBoolean(tokens[IS_FORMER_LICENSE_LICENSEE_INDEX]);
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

    return new NpdCompany(npdId,
                          name,
                          organizationNumber,
                          shortName,
                          nationCode,
                          surveyPrefix,
                          isCurrentLicenseOperator,
                          isFormerLicenseOperator,
                          isCurrentLicenseLicensee,
                          isFormerLicenseLicensee,
                          syncDate);
  }
}

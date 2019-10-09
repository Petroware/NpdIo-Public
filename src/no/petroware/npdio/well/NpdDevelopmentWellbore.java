package no.petroware.npdio.well;

import java.util.Date;

/**
 * Development wellbore as modeled by the NPD.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdDevelopmentWellbore extends NpdWellbore
{
  private final Date preDrillEntryDate_;

  private final Date preDrillCompletionDate_;

  private final String productionFacility_;

  private final String contentPlanned_;

  private final String npdidFacilityProducing_;

  private final String npdidTargetProductionLicense_;

  NpdDevelopmentWellbore(String npdId,
                         String name,
                         String wellName,
                         String drillingOperator,
                         String productionLicense,
                         String status,
                         String purpose,
                         String purposePlanned,
                         String content,
                         String wellType,
                         Boolean isSubsea,
                         Date entryDate,
                         Date completionDate,
                         Date preDrillEntryDate,
                         Date preDrillCompletionDate,
                         String field,
                         String drillPermit,
                         String discovery,
                         Boolean isDiscoveryWellbore,
                         Double kellyBushElevation,
                         Double finalVerticalDepth,
                         Double totalDepth,
                         Double waterDepth,
                         Double kickOffPoint,
                         String mainArea,
                         String drillingFacility,
                         String drillingFacilityType,
                         String drillingFacilityCategory,
                         String licensingActivity,
                         Boolean isMultilateral,
                         Integer entryYear,
                         Integer completionYear,
                         String reclassFromWellbore,
                         Date pluggedAndAbandonDate,
                         Date pluggedDate,
                         String licenseTargetName,
                         Integer plotSymbol,
                         String geodeticDatum,
                         Integer nsDeg,
                         Integer nsMin,
                         Double nsSec,
                         String nsCode,
                         Integer ewDeg,
                         Integer ewMin,
                         Double ewSec,
                         String ewCode,
                         Double latitude,
                         Double longitude,
                         Double nsUtm,
                         Double ewUtm,
                         Integer utmZone,
                         Integer namePart1,
                         Integer namePart2,
                         String namePart3,
                         Integer namePart4,
                         String namePart5,
                         String namePart6,
                         String factPageUrl,
                         String factMapUrl,
                         String diskosWellboreType,
                         String diskosWellboreParent,
                         String npdidDiscovery,
                         String npdidField,
                         Date wdssQcDate,
                         Date releaseDate,
                         String npdidProductionLicense,
                         String npdidFacilityDrilling,
                         String npdidWellboreReclass,
                         Date mainLevelUpdatedDate,
                         Date updatedDate,
                         Date syncDate,
                         String productionFacility,
                         String contentPlanned,
                         String npdidFacilityProducing,
                         String npdidTargetProductionLicense)
  {
    super("wellbore_development",
          npdId,
          name,
          wellName,
          drillingOperator,
          productionLicense,
          status,
          purpose,
          purposePlanned,
          content,
          wellType,
          isSubsea,
          entryDate,
          completionDate,
          field,
          drillPermit,
          discovery,
          isDiscoveryWellbore,
          kellyBushElevation,
          finalVerticalDepth,
          totalDepth,
          waterDepth,
          kickOffPoint,
          mainArea,
          drillingFacility,
          drillingFacilityType,
          drillingFacilityCategory,
          licensingActivity,
          isMultilateral,
          entryYear,
          completionYear,
          reclassFromWellbore,
          null, // reclassificationDate
          null, // siteSurvey
          null, // npdidSiteSurvey
          pluggedAndAbandonDate,
          pluggedDate,
          licenseTargetName,
          plotSymbol,
          geodeticDatum,
          nsDeg,
          nsMin,
          nsSec,
          nsCode,
          ewDeg,
          ewMin,
          ewSec,
          ewCode,
          latitude,
          longitude,
          nsUtm,
          ewUtm,
          utmZone,
          namePart1,
          namePart2,
          namePart3,
          namePart4,
          namePart5,
          namePart6,
          factPageUrl,
          factMapUrl,
          diskosWellboreType,
          diskosWellboreParent,
          npdidDiscovery,
          npdidField,
          wdssQcDate,
          releaseDate,
          npdidProductionLicense,
          npdidFacilityDrilling,
          npdidWellboreReclass,
          mainLevelUpdatedDate,
          updatedDate,
          syncDate);

    productionFacility_ = productionFacility;
    contentPlanned_ = contentPlanned;
    npdidFacilityProducing_ = npdidFacilityProducing;
    preDrillEntryDate_ = preDrillEntryDate;
    preDrillCompletionDate_ = preDrillCompletionDate;
    npdidTargetProductionLicense_ = npdidTargetProductionLicense;
  }

  /**
   * Return the pre drill entry date of this wellbore.
   * <p>
   * Date when the predrilling activity of the wellbore started (see also Entry date).
   * <p>
   * datetime, corresponds to the NPD property <em>wlbEntryPreDrillDate</em>.
   *
   * @return  Pre drill entry date of this wellbore. Null if not specified.
   */
  public Date getPreDrillEntryDate()
  {
    return preDrillEntryDate_ != null ? new Date(preDrillEntryDate_.getTime()) : null;
  }

  /**
   * Return the pre drill completion date of this wellbore.
   * <p>
   * Date when the predrilling activity of the wellbore was completed
   * (see also completion date).
   * <p>
   * datetime, corresponds to the NPD property <em>wlbCompPreDrillDate</em>.
   *
   * @return  Pre drill completion date of this wellbore. Null if not specified.
   */
  public Date getPreDrillCompletionDate()
  {
    return preDrillCompletionDate_ != null ? new Date(preDrillCompletionDate_.getTime()) : null;
  }

  /**
   * Return production facility of this wellbore.
   * <p>
   * The NPD's name for the production facility, for development wells.
   * <p>
   * varchar(50), corresponds to the NPD property <em>wlbProductionFacility</em>.
   *
   * @return  Production facility of this wellbore.
   */
  public String getProductionFacility()
  {
    return productionFacility_;
  }

  /**
   * Return planned content of this wellbore.
   * <p>
   * Only relevant for development wellbores, planned type of produced or injected fluid.<br>
   * Examples of legal values:
   * <ul>
   *   <li>CO2</li>
   *   <li>CUTTINGS</li>
   *   <li>GAS</li>
   *   <li>NOT APPLICABLE</li>
   *   <li>NOT AVAILABLE</li>
   *   <li>WATER</li>
   *   <li>WATER/GAS</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to the NPD property <em>wlbContentPlanned</em>.
   *
   * @return  Planned content of this wellbore.
   */
  public String getContentPlanned()
  {
    return contentPlanned_;
  }

  /**
   * Return ID of the producing facility of this wellbore.
   * <p>
   * NPD's unique id for the production facility, for development wellbores.
   * <p>
   * bigint, corresponds to the NPD property <em>fclNpdidFacilityProducing</em>.
   *
   * @return  ID of the producing facility of this wellbore.
   */
  public String getNpdidFacilityProducing()
  {
    return npdidFacilityProducing_;
  }

  /**
   * Return ID of the target production license.
   * <p>
   * NPDID production license target.
   * <p>
   * NPD unique key for the production license of the wellbore target.
   * This can be another production license then where the wellhead
   * position.
   * <p>
   * int, corresponds to the NPD property <em>prlNpdidProdLicenceTarget</em>.
   *
   * @return  ID of the target production license. Null if N/A.
   */
  public String getNpdidTargetProductionLicense()
  {
    return npdidTargetProductionLicense_;
  }
}

package no.petroware.npdio.well;

import java.util.Date;

/**
 * Other (that is non-exploration, non-development) wellbore as
 * modeled by the NPD.
 *
 * @author <a href="mailto:info@petroware.no">Petroware AS</a>
 */
public final class NpdOtherWellbore extends NpdWellbore
{
  /**
   * Position of wellbore on seismic survey lines. SP: shot point.
   */
  private final String seismicLocation_;

  /**
   * Create a new <em>other</em> wellbore.
   */
  NpdOtherWellbore(String npdId,
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
                   String siteSurvey,
                   String npdidSiteSurvey,
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
                   Double latitude, // NsDecDeg
                   Double longitude, // EwDecDeg
                   Double nsUtm,
                   Double ewUtm,
                   Integer utmZone,
                   Integer namePart1, // quadrant
                   Integer namePart2, // block
                   String namePart3, // installation
                   Integer namePart4, // installation
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

                   // Other wellbore specific
                   String seismicLocation)
  {
    super("wellbore_other",
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
          siteSurvey,
          npdidSiteSurvey,
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

    seismicLocation_ = seismicLocation;
  }

  /**
   * Return seismic location of this wellbore.
   * <p>
   * This is the position of wellbore on seismic survey lines. SP: shot point.
   * <p>
   * varchar(200), corresponds to NPD property <em>wlbSeismicLocation</em>.
   *
   * @return  Seismic location of this wellbore.
   */
  public String getSeismicLocation()
  {
    return seismicLocation_;
  }
}

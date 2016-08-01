package plantmegapack.worldgen;

import java.util.EnumSet;
import plantmegapack.data.feature.PMPDataFeature;
import plantmegapack.data.feature.PMPDataFeatureBedp;
import plantmegapack.data.feature.PMPDataFeatureBedt;
import plantmegapack.data.feature.PMPDataFeatureFarm;
import plantmegapack.data.feature.PMPDataFeatureGrou;
import plantmegapack.data.feature.PMPDataFeatureKelp;
import plantmegapack.data.feature.PMPDataFeatureReef;
import plantmegapack.data.feature.PMPDataFeatureWedg;
import plantmegapack.habitat.PMPBiomeType;

public enum PMPWorldgenFeature
{
	bedp(PMPDataFeatureBedp.class, EnumSet.of(PMPBiomeType.beac, new PMPBiomeType[] { PMPBiomeType.desc, PMPBiomeType.desw, PMPBiomeType.forc, PMPBiomeType.forw, PMPBiomeType.jung, PMPBiomeType.mesa, PMPBiomeType.moun, PMPBiomeType.plai, PMPBiomeType.rive, PMPBiomeType.sava, PMPBiomeType.swam })),	bedt(PMPDataFeatureBedt.class, EnumSet.of(PMPBiomeType.beac, new PMPBiomeType[] { PMPBiomeType.desc, PMPBiomeType.desw, PMPBiomeType.forc, PMPBiomeType.forw, PMPBiomeType.jung, PMPBiomeType.mesa, PMPBiomeType.moun, PMPBiomeType.plai, PMPBiomeType.rive, PMPBiomeType.sava, PMPBiomeType.swam })),	farm(PMPDataFeatureFarm.class, EnumSet.of(PMPBiomeType.forc, new PMPBiomeType[] { PMPBiomeType.forw, PMPBiomeType.mesa, PMPBiomeType.moun, PMPBiomeType.plai, PMPBiomeType.sava, PMPBiomeType.swam })),	grou(PMPDataFeatureGrou.class, EnumSet.of(PMPBiomeType.beac, new PMPBiomeType[] { PMPBiomeType.desc, PMPBiomeType.desw, PMPBiomeType.forc, PMPBiomeType.forw, PMPBiomeType.jung, PMPBiomeType.mesa, PMPBiomeType.moun, PMPBiomeType.plai, PMPBiomeType.rive, PMPBiomeType.sava, PMPBiomeType.swam })),	kelp(PMPDataFeatureKelp.class, EnumSet.of(PMPBiomeType.beac, PMPBiomeType.ocea)),	reef(PMPDataFeatureReef.class, EnumSet.of(PMPBiomeType.beac, PMPBiomeType.ocea)),	wedg(PMPDataFeatureWedg.class, EnumSet.of(PMPBiomeType.desc, new PMPBiomeType[] { PMPBiomeType.desw, PMPBiomeType.forc, PMPBiomeType.forw, PMPBiomeType.jung, PMPBiomeType.moun, PMPBiomeType.plai, PMPBiomeType.rive, PMPBiomeType.sava, PMPBiomeType.swam }));
	
	public final Class<? extends PMPDataFeature> dataClass;
	public final EnumSet<PMPBiomeType> biomes;
	
	private PMPWorldgenFeature(Class<? extends PMPDataFeature> dataClass, EnumSet<PMPBiomeType> biomes) {
		this.dataClass = dataClass;
		this.biomes = biomes;
	}
	
	public boolean isFeatureInBiome(PMPBiomeType biomeType) {
		return this.biomes.contains(biomeType);
	}
}

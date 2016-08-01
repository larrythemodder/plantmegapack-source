package plantmegapack.habitat;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public enum PMPBiomeType {
	beac('e', 10, 20, 8, 20, -1),
	desc('e', 2, 20, 0, 20, -1),
	desw('e', 2, 20, 0, 20, -1),
	forc('2', 8, 20, 8, 20, 0),
	forw('2', 8, 20, 8, 20, 0),
	jung('a', 12, 20, 8, 20, 0),
	mesa('6', 8, 20, 0, 20, 0),
	moun('d', 6, 20, 8, 20, 0),
	mush('c', 2, 20, -1, -1, -1),
	ocea('9', 8, 20, -1, -1, -1),
	plai('a', 6, 20, 8, 20, 15),
	rive('b', 10, 20, 8, 20, -1),
	sava('e', 4, 20, 8, 20, 0),
	swam('6', 8, 20, 8, 20, 0);
	
	public final char formatCode;
	public final int plantPassesPerChunk;
	public final int plantEmptyChunks;
	public final int treePassesPerChunk;
	public final int treeEmptyChunks;
	public final int featureAbandonedFarmChance;
	
	private PMPBiomeType(char formatCode, int plantPassesPerChunk, int plantEmptyChunks, int treePassesPerChunk, int treeEmptyChunks, int featureAbandonedFarmChance) {
		this.formatCode = formatCode;
		this.plantPassesPerChunk = plantPassesPerChunk;
		this.plantEmptyChunks = plantEmptyChunks;
		this.treePassesPerChunk = treePassesPerChunk;
		this.treeEmptyChunks = treeEmptyChunks;
		this.featureAbandonedFarmChance = featureAbandonedFarmChance;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("biome." + name());
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s%s§r", new Object[] { Character.valueOf(this.formatCode), getLocalizedName() });
	}
	
	public static PMPBiomeType getBiomeTypeFromName(String biomeTypeName) {
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			if (biomeTypeName.matches(biomeType.name())) {
				return biomeType;
			}
		}
		return null;
	}
	
	public static PMPBiomeType getBiomeType(Biome biome) {
		if (biome == null) {
			return null;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.BEACH)) {
			return beac;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.JUNGLE)) {
			return jung;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MESA)) {
			return mesa;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MOUNTAIN)) {
			return moun;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.MUSHROOM)) {
			return mush;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.OCEAN)) {
			return ocea;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.RIVER)) {
			return rive;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SAVANNA)) {
			return sava;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SWAMP)) {
			return swam;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.PLAINS)) {
			if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SNOWY)) {
				return moun;
			}
			return plai;
		}
		PMPTemperature biomeTemp = BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HOT) ? PMPTemperature.hot : BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.COLD) ? PMPTemperature.cold : PMPTemperature.warm;
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.SANDY)) {
			return biomeTemp == PMPTemperature.cold ? desc : desw;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.HILLS)) {
			if (!BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)) {
				return moun;
			}
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.FOREST)) {
			return biomeTemp == PMPTemperature.cold ? forc : forw;
		}
		if (biome.getBaseHeight() >= 0.5F) {
			return moun;
		}
		if (BiomeDictionary.isBiomeOfType(biome, BiomeDictionary.Type.CONIFEROUS)) {
			return forc;
		}
		return null;
	}
	
	public boolean canPlantSpawnInBiome() {
		return this.plantPassesPerChunk >= 0;
	}
	
	public boolean canTreeSpawnInBiome() {
		return this.treePassesPerChunk >= 0;
	}
	
	public boolean isMarineBiome() {
		return this == ocea;
	}
}

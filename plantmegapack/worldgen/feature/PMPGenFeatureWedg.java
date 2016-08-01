package plantmegapack.worldgen.feature;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.data.feature.PMPDataFeatureWedg;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.habitat.PMPTemperature;
import plantmegapack.worldgen.PMPWorldgenHelper;
import plantmegapack.worldgen.PMPWorldgenSpawn;
import plantmegapack.worldgen.spawn.PMPSpawnPlant;

public abstract class PMPGenFeatureWedg {
	private static int FEATURE_RADIUS = 4;
	
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureWedg featureData) {
		for (int ix = 0; ix < 16; ix += 2) {
			for (int iz = 0; iz < 16; iz += 2) {
				BlockPos posInitial = new BlockPos(posChunk.getX() + ix, 64, posChunk.getZ() + iz);
				if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (spawnData.habitat == PMPHabitat.wedg)) {
					posInitial = new BlockPos(posInitial.getX() + random.nextInt(FEATURE_RADIUS), 64, posInitial.getZ() + random.nextInt(FEATURE_RADIUS));
					if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (random.nextInt(100) < getGenChance(featureData, spawnData.habitat))) {
						PMPBiomeType biome = PMPWorldgenHelper.scanRiverAreaForBiome(worldIn, posInitial, 3);
						if ((biome != PMPBiomeType.beac) && (biome != PMPBiomeType.ocea)) {
							PMPTemperature temperature = PMPWorldgenHelper.scanAreaForTemperature(worldIn, posInitial, 3);
							if (temperature != PMPTemperature.cold)
							{
								PMPDataSpawnPlant plantSpawnData = featureData.getRandomPlantSpawnData(spawnData.habitat, random);
								if (plantSpawnData != null) {
									PMPSpawnPlant.generatePlantCluster(worldIn, random, spawnData, plantSpawnData, getGenChance(featureData, spawnData.habitat));
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static int getGenChance(PMPDataFeatureWedg featureData, PMPHabitat habitat) {
		if (habitat == PMPHabitat.frfl) {
			return featureData.genFloating;
		}
		if (habitat == PMPHabitat.frmr) {
			return featureData.genImmersed;
		}
		if (habitat == PMPHabitat.wedg) {
			return featureData.genLand;
		}
		if (habitat == PMPHabitat.slop) {
			return featureData.genSlope;
		}
		return 0;
	}
}

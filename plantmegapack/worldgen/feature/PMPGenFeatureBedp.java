package plantmegapack.worldgen.feature;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.data.feature.PMPDataFeatureBedp;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenSpawn;
import plantmegapack.worldgen.spawn.PMPSpawnPlant;

public class PMPGenFeatureBedp {
	private static int FEATURE_RADIUS = 6;
	
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureBedp featureData) {
		PMPBiomeType origBiomeType = spawnData.biomeType;
		for (int ix = 0; ix < 16; ix += 2) {
			for (int iz = 0; iz < 16; iz += 2) {
				BlockPos posInitial = new BlockPos(posChunk.getX() + ix, 64, posChunk.getZ() + iz);
				if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (spawnData.habitat == PMPHabitat.bedg) && (origBiomeType == spawnData.biomeType)) {
					posInitial = new BlockPos(posChunk.getX() + random.nextInt(FEATURE_RADIUS), 64, posChunk.getZ() + random.nextInt(FEATURE_RADIUS));
					if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (random.nextInt(100) < featureData.genPlants)) {
						PMPDataSpawnPlant plantSpawnData = featureData.getRandomPlantSpawnData(spawnData.habitat, random);
						if (plantSpawnData != null) {
							PMPSpawnPlant.generatePlantCluster(worldIn, random, spawnData, plantSpawnData, featureData.genPlants);
						}
					}
				}
			}
		}
	}
}

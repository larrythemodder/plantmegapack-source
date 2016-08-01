package plantmegapack.worldgen.feature;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSubmerged;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.feature.PMPDataFeatureReef;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPGenFeatureReef {
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureReef featureData) {
		BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
		if ((!spawnData.initSpawnLocation(worldIn, posInitial)) || ((spawnData.habitat != PMPHabitat.frdp) && (spawnData.habitat != PMPHabitat.madp))) {
			return;
		}
		for (int cluster = 0; cluster < featureData.clusterAmount; cluster++) {
			PMPBlockSubmerged coralBlock = (PMPBlockSubmerged)PlantMegaPack.blocks.corals.get(random.nextInt(PlantMegaPack.blocks.corals.size()));
			int randX = random.nextInt(featureData.clusterSize);
			int randZ = random.nextInt(featureData.clusterSize);
			BlockPos posSpawn = worldIn.getTopSolidOrLiquidBlock(spawnData.spawnPos.add(randX, 0, randZ));
			if (coralBlock.canPlaceBlockAt(worldIn, posSpawn)) {
				worldIn.setBlockState(posSpawn, coralBlock.getDefaultState(), 2);
			}
		}
	}
}

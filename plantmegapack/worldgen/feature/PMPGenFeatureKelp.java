package plantmegapack.worldgen.feature;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSubmerged;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.feature.PMPDataFeatureKelp;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.worldgen.PMPWorldgenSpawn;
import plantmegapack.worldgen.spawn.PMPSpawnPlant;

public abstract class PMPGenFeatureKelp {
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureKelp featureData) {
		BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
		if ((!spawnData.initSpawnLocation(worldIn, posInitial)) || ((spawnData.habitat != PMPHabitat.frdp) && (spawnData.habitat != PMPHabitat.madp))) {
			return;
		}
		for (int ix = 0; ix < 16; ix++) {
			for (int iz = 0; iz < 16; iz++) {
				BlockPos posSpawn = worldIn.getTopSolidOrLiquidBlock(spawnData.spawnPos.add(ix, 0, iz));
				if ((posSpawn.getY() >= featureData.limitLow) && (posSpawn.getY() <= featureData.limitHigh)) {
					PMPBlockSubmerged kelpBlock = (PMPBlockSubmerged)PlantMegaPack.blocks.kelps.get(random.nextInt(PlantMegaPack.blocks.kelps.size()));
					if (kelpBlock.canPlaceBlockAt(worldIn, posSpawn)) {
						PMPSpawnPlant.spawnSeaweedPlant(worldIn, posSpawn, random, kelpBlock);
					}
				}
			}
		}
	}
}

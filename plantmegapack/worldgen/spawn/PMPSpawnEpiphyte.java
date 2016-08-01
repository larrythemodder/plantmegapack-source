package plantmegapack.worldgen.spawn;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockEpiphyte;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPSpawnEpiphyte {
	public static void generateEpiphyteCluster(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPDataHabitat habitatData, PMPDataSpawnPlant plantSpawnData) {
		PMPBlockEpiphyte plantBlock = (PMPBlockEpiphyte)PlantMegaPack.blocks.getPlantBlockByName(plantSpawnData.plantAttributes.name());
		worldIn.setBlockState(spawnData.spawnPos, plantBlock.getDefaultState().withProperty(PMPBlockEpiphyte.FACING, spawnData.spawnFacing), 2);
	}
}

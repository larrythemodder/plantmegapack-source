package plantmegapack.worldgen.spawn;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataHabitat;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.worldgen.PMPWorldgenHelper;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPSpawnVine {
	public static void generateVineCluster(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPDataHabitat habitatData, PMPDataSpawnPlant plantSpawnData) {
		spawnVinePlant(worldIn, random, spawnData, plantSpawnData);
	}
	
	private static void spawnVinePlant(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPDataSpawnPlant plantSpawnData) {
		Block plantBlock = PlantMegaPack.blocks.getPlantBlockByName(plantSpawnData.plantAttributes.name());
		int maxHeight = 0;
		BlockPos posTemp = spawnData.spawnPos;
		while (PMPWorldgenHelper.canVineSpawnAtBlock(worldIn, posTemp)) {
			posTemp = posTemp.down();
			maxHeight++;
		}
		if (maxHeight < 1) {
			return;
		}
		int height = random.nextInt(maxHeight);
		posTemp = spawnData.spawnPos;
		for (int i = 0; i < height; i++) {
			worldIn.setBlockState(posTemp, plantBlock.getDefaultState()
				.withProperty(BlockVine.NORTH, Boolean.valueOf(spawnData.spawnFacing == EnumFacing.NORTH))
				.withProperty(BlockVine.SOUTH, Boolean.valueOf(spawnData.spawnFacing == EnumFacing.SOUTH))
				.withProperty(BlockVine.WEST, Boolean.valueOf(spawnData.spawnFacing == EnumFacing.WEST))
				.withProperty(BlockVine.EAST, Boolean.valueOf(spawnData.spawnFacing == EnumFacing.EAST)), 2);
			posTemp = posTemp.down();
		}
	}
}

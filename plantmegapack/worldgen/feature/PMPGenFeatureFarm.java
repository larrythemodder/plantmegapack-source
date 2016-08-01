package plantmegapack.worldgen.feature;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockCropDouble;
import plantmegapack.block.PMPBlockHalf;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.data.feature.PMPDataFeatureFarm;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantSoilType;
import plantmegapack.worldgen.PMPWorldgenFeature;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPGenFeatureFarm {
	private static final int FARM_SIZE = 3;
	
	public static void generateFeature(World worldIn, BlockPos posChunk, Random random, PMPWorldgenSpawn spawnData, PMPDataFeatureFarm featureData) {
		BlockPos posInitial = new BlockPos(posChunk.getX() + random.nextInt(16), 0, posChunk.getZ() + random.nextInt(16));
		if (!spawnData.initSpawnLocation(worldIn, posInitial)) {
			return;
		}
		int chancesToFindAGoodSpot = 4;
		BlockPos posSpawn = null;
		int curChances = 0;
		while ((posSpawn == null) && (curChances < chancesToFindAGoodSpot)) {
			curChances++;
			posSpawn = findSuitableSpawnLocation(worldIn, random, spawnData.spawnPos);
		}
		if (posSpawn == null) {
			return;
		}
		generateFarm(worldIn, random, posSpawn, spawnData);
	}
	
	private static BlockPos findSuitableSpawnLocation(World worldIn, Random random, BlockPos posChunk) {
		BlockPos posSpawn = worldIn.getTopSolidOrLiquidBlock(posChunk.add(random.nextInt(16), 0, random.nextInt(16)));
		if ((worldIn.getBlockState(posSpawn).getBlock().getMaterial(worldIn.getBlockState(posSpawn)) == Material.WATER) || (worldIn.getBlockState(posSpawn.up()).getBlock().getMaterial(worldIn.getBlockState(posSpawn.up())) != Material.AIR)) {
			return null;
		}
		boolean canSpawn = true;
		for (int ix = 0; ix < 3; ix++) {
			for (int iz = 0; iz < 3; iz++) {
				Block blockSoil = worldIn.getBlockState(posSpawn.add(ix, -1, iz)).getBlock();
				if (!PMPPlantSoilType.isDirt(blockSoil, worldIn.getBlockState(posSpawn.add(ix, -1, iz)))) {
					canSpawn = false;
				}
				Block blockSpawn = worldIn.getBlockState(posSpawn.add(ix, 0, iz)).getBlock();
				if (!blockSpawn.isReplaceable(worldIn, posSpawn.add(ix, 0, iz))) {
					canSpawn = false;
				}
				blockSpawn = worldIn.getBlockState(posSpawn.add(ix, 1, iz)).getBlock();
				if (!blockSpawn.isReplaceable(worldIn, posSpawn.add(ix, 1, iz))) {
					canSpawn = false;
				}
			}
		}
		return canSpawn ? posSpawn : null;
	}
	
	private static boolean generateFarm(World worldIn, Random random, BlockPos pos, PMPWorldgenSpawn spawnData) {
		PMPDataFeatureFarm featureData = (PMPDataFeatureFarm)spawnData.biomeData.getFeatureData(PMPWorldgenFeature.farm);
		if (featureData == null) {
			return false;
		}
		for (int ix = 0; ix < 3; ix++) {
			for (int iz = 0; iz < 3; iz++) {
				if (random.nextInt(100) < featureData.cropRate) {
					BlockPos posSpawn = new BlockPos(pos.add(ix, 0, iz));
					if (PMPPlantSoilType.isDirt(worldIn.getBlockState(posSpawn.down()).getBlock(), worldIn.getBlockState(posSpawn.down()))) {
						Block cropBlock = (Block)PlantMegaPack.blocks.crops.get(random.nextInt(PlantMegaPack.blocks.crops.size()));
						IPMPPlant plant = (IPMPPlant)cropBlock;
						worldIn.setBlockState(posSpawn.down(), Blocks.FARMLAND.getDefaultState(), 2);
						if (plant.getPlantData().attributes.blockType == PMPPlantBlockType.crpd) {
							worldIn.setBlockState(posSpawn, cropBlock.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(7)).withProperty(PMPBlockCropDouble.HALF, PMPBlockHalf.lower), 2);
							worldIn.setBlockState(posSpawn.up(), cropBlock.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(7)).withProperty(PMPBlockCropDouble.HALF, PMPBlockHalf.upper), 2);
						} else {
							worldIn.setBlockState(posSpawn, cropBlock.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(7)), 2);
							worldIn.setBlockState(posSpawn.up(), Blocks.AIR.getDefaultState(), 2);
						}
					}
				}
			}
		}
		EnumFacing direction = EnumFacing.getHorizontal(random.nextInt(4));
		int offset = 3;
		int chanceToSpawn = featureData.fenceRate;
		switch (direction) {
		case SOUTH: 
			spawnFenceBlock(worldIn, random, pos.south(offset), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.south(offset).east(1), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.south(offset).east(2), chanceToSpawn);
			break;
		case WEST: 
			spawnFenceBlock(worldIn, random, pos.west(), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.west().south(1), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.west().south(2), chanceToSpawn);
			break;
		case NORTH: 
			spawnFenceBlock(worldIn, random, pos.north(), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.north().east(1), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.north().east(2), chanceToSpawn);
			break;
		case EAST: 
			spawnFenceBlock(worldIn, random, pos.east(offset), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.east(offset).south(1), chanceToSpawn);
			spawnFenceBlock(worldIn, random, pos.east(offset).south(2), chanceToSpawn);
			break;
		default: 
			return false;
		}
		return true;
	}
	
	private static void spawnFenceBlock(World worldIn, Random random, BlockPos pos, int chanceToSpawn) {
		if ((random.nextInt(100) < chanceToSpawn) && (PMPPlantSoilType.isDirt(worldIn.getBlockState(pos.down()).getBlock(), worldIn.getBlockState(pos.down())))) {
			worldIn.setBlockState(pos, Blocks.OAK_FENCE.getDefaultState(), 2);
		}
	}
}

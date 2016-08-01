package plantmegapack.worldgen.spawn;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSapling;
import plantmegapack.core.PMPBlocks;
import plantmegapack.data.PMPDataSpawnTree;
import plantmegapack.data.PMPDataTree;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.object.PMPSapling;
import plantmegapack.plant.PMPPlantSoilType;
import plantmegapack.tree.PMPTree;
import plantmegapack.worldgen.PMPWorldgenHelper;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPSpawnTree {
	public static void generateTreeCluster(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPDataSpawnTree treeSpawnData, PMPTree tree, int genRateTree) {
		PMPBiomeType biomeInitial = spawnData.biomeType;
		if (canTreeSpawnHere(worldIn, spawnData.spawnPos, tree)) {
			tree.generate(worldIn, random, spawnData.spawnPos);
		}
		if ((treeSpawnData.clusterChance > 0) && (random.nextInt(100) < treeSpawnData.clusterChance)) {
			BlockPos posClusterOrigin = new BlockPos(spawnData.spawnPos);
			for (int j = 0; j < treeSpawnData.clusterAmount; j++) {
				int offsetX = random.nextInt(treeSpawnData.clusterRadius) * random.nextInt(100) < 50 ? 1 : -1;
				int offsetZ = random.nextInt(treeSpawnData.clusterRadius) * random.nextInt(100) < 50 ? 1 : -1;
				BlockPos posInitial = new BlockPos(posClusterOrigin.getX() + offsetX, 64, posClusterOrigin.getZ() + offsetZ);
				if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (spawnData.isTreeSpawn) && (random.nextInt(100) < genRateTree)) {
					if ((!treeSpawnData.restrictToBiome) || (biomeInitial == PMPWorldgenHelper.getBiomeFromPosition(worldIn, spawnData.spawnPos))) {
						if (canTreeSpawnHere(worldIn, spawnData.spawnPos, tree)) {
							tree.generate(worldIn, random, spawnData.spawnPos);
						}
					}
				}
			}
		}
	}
	
	public static void generateTreeCluster(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPTree tree, int genRateTree, int genAmount, int genRadius) {
		if (canTreeSpawnHere(worldIn, spawnData.spawnPos, tree)) {
			tree.generate(worldIn, random, spawnData.spawnPos);
		}
		if ((genRateTree > 0) && (random.nextInt(100) < genRateTree)) {
			BlockPos posClusterOrigin = new BlockPos(spawnData.spawnPos);
			for (int j = 0; j < genAmount; j++) {
				int offsetX = random.nextInt(genRadius);
				int offsetZ = random.nextInt(genRadius);
				BlockPos posInitial = new BlockPos(posClusterOrigin.getX() + offsetX, 64, posClusterOrigin.getZ() + offsetZ);
				if ((spawnData.initSpawnLocation(worldIn, posInitial)) && (spawnData.isTreeSpawn) && (random.nextInt(100) < genRateTree)) {
					if (canTreeSpawnHere(worldIn, spawnData.spawnPos, tree)) {
						tree.generate(worldIn, random, spawnData.spawnPos);
					}
				}
			}
		}
	}
	
	public static boolean canTreeSpawnHere(World worldIn, BlockPos pos, PMPTree tree) {
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		Block blockSoil = worldIn.getBlockState(pos.down()).getBlock();
		if ((!PMPWorldgenHelper.isBlockReplaceable(worldIn, pos)) || (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.WATER) || (blockSoil.getMaterial(worldIn.getBlockState(pos.down())) == Material.WATER)) {
			return false;
		}
		if (!isTreeRadiusClear(worldIn, pos.up())) {
			return false;
		}
		if (((blockSoil instanceof BlockFarmland)) || ((blockSpawn instanceof BlockCrops))) {
			return false;
		}
		if ((blockSoil.getMaterial(worldIn.getBlockState(pos.down())) == Material.LEAVES) || (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.LEAVES)) {
			return false;
		}
		PMPDataTree treeData = PlantMegaPack.blocks.getSapling(tree.getSapling().name()).getTreeData();
		if (PMPPlantSoilType.isSand(blockSoil, worldIn.getBlockState(pos.down()))) {
			return (treeData.canSpawnOnSand) && (blockSoil.canSustainPlant(worldIn.getBlockState(pos.down()), worldIn, pos.down(), EnumFacing.UP, Blocks.CACTUS));
		}
		return (blockSoil.canSustainPlant(worldIn.getBlockState(pos.down()), worldIn, pos.down(), EnumFacing.UP, Blocks.RED_FLOWER)) || (PMPPlantSoilType.isDirt(blockSoil, worldIn.getBlockState(pos.down())));
	}
	
	private static boolean isTreeRadiusClear(World worldIn, BlockPos pos) {
		for (int ix = -2; ix < 3; ix++) {
			for (int iz = -2; iz < 3; iz++) {
				BlockPos posTest = new BlockPos(pos.getX() + ix, pos.getY(), pos.getZ() + iz);
				if (worldIn.getBlockState(posTest).getBlock().isWood(worldIn, posTest)) {
					return false;
				}
			}
		}
		return true;
	}
}

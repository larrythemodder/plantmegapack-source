package plantmegapack.worldgen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSand.EnumType;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockBase;
import plantmegapack.block.PMPBlockClimbing;
import plantmegapack.block.PMPBlockColored;
import plantmegapack.block.PMPBlockDouble;
import plantmegapack.block.PMPBlockEpiphyte;
import plantmegapack.block.PMPBlockHalf;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPPowder;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSoilType;
import plantmegapack.worldgen.PMPWorldgenHelper;

public abstract class PMPGenPowder {
	public static boolean applyPowder(World worldIn, BlockPos pos, PMPPowder powder, boolean sameElevation) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block == null) {
			return false;
		}
		boolean usedOnLeaves = PMPHelper.isValidLeavesBlock(block);
		boolean usedOnPlant = PMPHelper.isValidPlantBlock(block);
		boolean usedOnSoil = PMPPlantSoilType.isDirt(block, worldIn.getBlockState(pos));
		if ((!usedOnLeaves) && (!usedOnPlant) && (!usedOnSoil)) {
			return false;
		}
		int elevation;
		int radius;
		int strength;
		switch (powder) {
		case powderConditioner: 
			if ((usedOnLeaves) || (usedOnPlant)) {
				return false;
			}
			radius = PlantMegaPack.settings.powderConditionerRadius;
			strength = PlantMegaPack.settings.powderConditionerStrength;
			elevation = sameElevation ? 0 : PlantMegaPack.settings.powderConditionerElevation;
			break;
		case powderDefoliant: 
			radius = PlantMegaPack.settings.powderDefoliantRadius;
			strength = PlantMegaPack.settings.powderDefoliantStrength;
			elevation = sameElevation ? 0 : PlantMegaPack.settings.powderDefoliantElevation;
			break;
		case powderFertilizer: 
			if (usedOnLeaves) {
				return false;
			}
			radius = PlantMegaPack.settings.powderFertilizerRadius;
			strength = PlantMegaPack.settings.powderFertilizerStrength;
			elevation = sameElevation ? 0 : PlantMegaPack.settings.powderFertilizerElevation;
			break;
		default: 
			return false;
		}
		Random random = new Random();
		PMPHelper.spawnParticles(worldIn, pos, random, usedOnSoil, powder.particleType);
		if ((usedOnPlant) && (powder == PMPPowder.powderFertilizer)) {
			clonePlant(worldIn, pos, random, radius, strength);
			return true;
		}
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int r2 = radius * radius;
		int area = r2 << 2;
		int rr = radius << 1;
		
		boolean blockAffected = false;
		for (int i = 0; i < area; i++) {
			if (random.nextInt(100) < strength) {
				int tx = i % rr - radius;
				int tz = i / rr - radius;
				if (tx * tx + tz * tz <= r2) {
					BlockPos posTarget = new BlockPos(x + tx, y, z + tz);
					block = worldIn.getBlockState(posTarget).getBlock();
					if ((!PMPHelper.isValidLeavesBlock(block)) && (!PMPHelper.isValidPlantBlock(block)) && (!PMPPlantSoilType.isDirt(block, worldIn.getBlockState(posTarget)))) {
						posTarget = adjustForAirBlock(worldIn, posTarget, elevation);
						if (posTarget != null) {
							block = worldIn.getBlockState(posTarget).getBlock();
						}
					} else {
						Block blockAbove = worldIn.getBlockState(posTarget.up()).getBlock();
						if ((worldIn.isAirBlock(posTarget.up())) || (PMPHelper.isValidLeavesBlock(blockAbove)) || (PMPHelper.isValidPlantBlock(blockAbove))) {
							switch (powder)
							{
							case powderConditioner: 
								blockAffected = applyConditionerToBlock(worldIn, posTarget, block, random);
								break;
							case powderDefoliant: 
								blockAffected = applyDefoliantToBlock(worldIn, posTarget, block, random);
								break;
							case powderFertilizer: 
								blockAffected = applyFertilizerToBlock(worldIn, posTarget, block, random);
								break;
							default: 
								blockAffected = false;
							}
							if (blockAffected) {
								PMPHelper.spawnParticles(worldIn, posTarget, random, usedOnSoil, powder.particleType);
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private static BlockPos adjustForAirBlock(World worldIn, BlockPos pos, int elevation) {
		if ((elevation < 1) || (elevation > 6)) {
			elevation = 4;
		}
		int elevCount = 0;
		do
		{
			pos = pos.down();
			elevCount++;
		} while ((!PMPPlantSoilType.isDirt(worldIn.getBlockState(pos).getBlock(), worldIn.getBlockState(pos))) && (elevCount < elevation));
		if (elevCount >= elevation) {
			return null;
		}
		return pos;
	}
	
	private static boolean applyConditionerToBlock(World worldIn, BlockPos pos, Block block, Random random) {
		if ((block == Blocks.DIRT) || ((block instanceof BlockDirt)) || (block == Blocks.GRASS) || ((block instanceof BlockGrass))) {
			worldIn.setBlockState(pos, Blocks.FARMLAND.getDefaultState(), 2);
			return true;
		}
		return false;
	}
	
	private static boolean applyDefoliantToBlock(World worldIn, BlockPos pos, Block block, Random random) {
		IBlockState state = worldIn.getBlockState(pos);
		boolean applied = false;
		if ((block == Blocks.GRASS) || ((block instanceof BlockGrass)) || (block == Blocks.FARMLAND) || ((block instanceof BlockFarmland))) {
			worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 2);
			applied = true;
		} else if (block == Blocks.MOSSY_COBBLESTONE) {
			worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState(), 2);
			applied = true;
		} else if ((block == Blocks.STONEBRICK) && (state.getValue(BlockStoneBrick.VARIANT) == BlockStoneBrick.EnumType.MOSSY)) {
			worldIn.setBlockState(pos, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.DEFAULT), 2);
			applied = true;
		} else if ((block == Blocks.SAND) && (worldIn.getBlockState(pos).getValue(BlockSand.VARIANT) == BlockSand.EnumType.SAND)) {
			worldIn.setBlockState(pos, Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND), 2);
			applied = true;
		} else if (block.getMaterial(state) == Material.LEAVES) {
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
			applied = true;
		} else if (PMPHelper.isValidPlantBlock(block)) {
			destroyPlant(worldIn, pos, random);
			return true;
		}
		if (applied) {
			destroyPlant(worldIn, pos.up(), random);
		}
		return applied;
	}
	
	private static boolean applyFertilizerToBlock(World worldIn, BlockPos pos, Block block, Random random) {
		IBlockState state = worldIn.getBlockState(pos);
		if (block == Blocks.COBBLESTONE) {
			worldIn.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
			return true;
		}
		if ((block == Blocks.DIRT) || ((block instanceof BlockDirt))) {
			worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState(), 2);
			return true;
		}
		if ((block == Blocks.MYCELIUM) || ((block instanceof BlockMycelium))) {
			worldIn.setBlockState(pos, Blocks.GRASS.getDefaultState(), 2);
			return true;
		}
		if ((block == Blocks.STONEBRICK) && (state.getValue(BlockStoneBrick.VARIANT) == BlockStoneBrick.EnumType.DEFAULT)) {
			worldIn.setBlockState(pos, Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.MOSSY), 2);
		}
		if ((block == Blocks.SAND) && (worldIn.getBlockState(pos).getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND)) {
			worldIn.setBlockState(pos, Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.SAND), 2);
			return true;
		}
		return false;
	}
	
	private static void destroyPlant(World worldIn, BlockPos pos, Random random) {
		BlockPos posCur = pos;
		Block block = worldIn.getBlockState(posCur).getBlock();
		while (PMPHelper.isValidPlantBlock(block)) {
			worldIn.setBlockState(posCur, Blocks.AIR.getDefaultState(), 2);
			PMPHelper.spawnParticles(worldIn, posCur, random, false, EnumParticleTypes.SMOKE_LARGE);
			posCur = posCur.up();
			block = worldIn.getBlockState(posCur).getBlock();
		}
		posCur = pos.down();
		block = worldIn.getBlockState(posCur).getBlock();
		while (PMPHelper.isValidPlantBlock(block)) {
			worldIn.setBlockState(posCur, Blocks.AIR.getDefaultState(), 2);
			PMPHelper.spawnParticles(worldIn, posCur, random, false, EnumParticleTypes.SMOKE_LARGE);
			posCur = posCur.down();
			block = worldIn.getBlockState(posCur).getBlock();
		}
	}
	
	private static void clonePlant(World worldIn, BlockPos pos, Random random, int radius, int strength) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		int metaData = block.getMetaFromState(state);
		if ((block instanceof PMPBlockBase)) {
			PMPPlant plant = ((IPMPPlant)block).getPlantData().attributes;
			if ((plant.category == PMPPlantCategory.vine) || (plant.blockType == PMPPlantBlockType.seaw) || (plant.blockType == PMPPlantBlockType.subm)) {
				return;
			}
			if (((plant.blockType == PMPPlantBlockType.doub) && (state.getValue(PMPBlockDouble.HALF) == PMPBlockHalf.upper)) || (plant.category == PMPPlantCategory.imme)) {
				BlockPos posAppy = new BlockPos(pos.getX(), pos.getY() - (plant.category == PMPPlantCategory.imme ? 0 : 1), pos.getZ());
				spawnPlantRadius(worldIn, posAppy, block, 0);
			} else if (plant.category == PMPPlantCategory.clim) {
				spawnClimbingRadius(worldIn, pos, block);
			} else if (plant.category == PMPPlantCategory.epip) {
				spawnEpiphyteRadius(worldIn, pos, block);
			} else if (PMPPlant.hasColorVariants(plant)) {
				spawnPlantRadius(worldIn, pos, block, metaData);
			} else if (plant.category == PMPPlantCategory.bamb) {
				spawnPlantRadius(worldIn, pos, block, 4);
			} else
			{
				spawnPlantRadius(worldIn, pos, block, 0);
			}
		} else {
			if ((block instanceof BlockSapling)) {
				((BlockSapling)block).grow(worldIn, pos, state, worldIn.rand);
				PMPHelper.spawnParticles(worldIn, pos, random, false, EnumParticleTypes.VILLAGER_HAPPY);
				return;
			}
			if (((block instanceof BlockCactus)) || (block.getMaterial(state) == Material.CACTUS)) {
				spawnPlantRadius(worldIn, pos, block, 0);
			} else if (((block instanceof BlockBush)) || ((block instanceof BlockFlower)) || ((block instanceof BlockDoublePlant)) || ((block instanceof BlockReed))) {
				spawnPlantRadius(worldIn, pos, block, metaData);
			}
		}
	}
	
	private static boolean spawnClimbingRadius(World worldIn, BlockPos pos, Block block) {
		if ((worldIn.getBlockState(pos.down()).getBlock() instanceof PMPBlockClimbing)) {
			return false;
		}
		PMPBlockClimbing plantBlock = (PMPBlockClimbing)block;
		while (plantBlock.canGrow(worldIn, pos, worldIn.getBlockState(pos), worldIn.isRemote)) {
			plantBlock.grow(worldIn, worldIn.rand, pos, worldIn.getBlockState(pos));
		}
		BlockPos posTarget = new BlockPos(pos);
		while ((worldIn.getBlockState(posTarget).getBlock() instanceof PMPBlockClimbing)) {
			PMPHelper.spawnParticles(worldIn, posTarget, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
			posTarget = posTarget.up();
		}
		return true;
	}
	
	private static boolean spawnEpiphyteRadius(World worldIn, BlockPos pos, Block plantBlock) {
		int radius = PlantMegaPack.settings.powderFertilizerRadius;
		
		int iy = pos.getY();
		
		int lowX = pos.getX() - radius;
		int lowZ = pos.getZ() - radius;
		int highX = pos.getX() + radius;
		int highZ = pos.getZ() + radius;
		for (int ix = lowX; ix <= highX; ix++) {
			for (int iz = lowZ; iz <= highZ; iz++) {
				for (int y = iy - PlantMegaPack.settings.powderFertilizerElevation; y <= iy + PlantMegaPack.settings.powderFertilizerElevation; y++) {
					BlockPos posNew = new BlockPos(ix, y, iz);
					Block block = worldIn.getBlockState(posNew).getBlock();
					if ((block.getMaterial(worldIn.getBlockState(posNew)) == Material.AIR) || ((block instanceof BlockVine)) || (block.getMaterial(worldIn.getBlockState(posNew)) == Material.VINE)) {
						EnumFacing direction = PMPWorldgenHelper.isAdjacentToBlockMaterial(worldIn, posNew, Material.WOOD);
						if (direction != null) {
							worldIn.setBlockState(posNew, plantBlock.getDefaultState().withProperty(PMPBlockEpiphyte.FACING, direction), 2);
							PMPHelper.spawnParticles(worldIn, posNew, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
						}
					}
				}
			}
		}
		return true;
	}
	
	private static boolean spawnPlantRadius(World worldIn, BlockPos pos, Block plantBlock, int metaData) {
		int radius = PlantMegaPack.settings.powderFertilizerRadius;
		
		int iy = pos.getY();
		int lowX = pos.getX() - radius;
		int lowZ = pos.getZ() - radius;
		int highX = pos.getX() + radius;
		int highZ = pos.getZ() + radius;
		for (int ix = lowX; ix <= highX; ix++) {
			for (int iz = lowZ; iz <= highZ; iz++) {
				BlockPos posNew = new BlockPos(ix, iy, iz);
				while (worldIn.isAirBlock(posNew.down())) {
					iy--;
					posNew = new BlockPos(ix, iy, iz);
				}
				if (((ix != lowX) && (ix != highX)) || ((iz != lowZ) && (iz != highZ) && (worldIn.rand.nextInt(100) < PlantMegaPack.settings.powderFertilizerStrength) && (worldIn.isAirBlock(posNew)))) {
					if ((plantBlock instanceof PMPBlockBase)) {
						if (((PMPBlockBase)plantBlock).canPlaceBlockAt(worldIn, posNew)) {
							PMPPlant plant = ((IPMPPlant)plantBlock).getPlantData().attributes;
							IBlockState state = PlantMegaPack.blocks.getPlantBlockByName(plant.name()).getDefaultState();
							if (PMPPlant.hasColorVariants(plant)) {
								state = state.withProperty(PMPBlockColored.VARIANT, PMPColor.getColorFromID(metaData));
								worldIn.setBlockState(posNew, state, 2);
								PMPHelper.spawnParticles(worldIn, posNew, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
							} else {
								worldIn.setBlockState(posNew, state, 2);
								PMPHelper.spawnParticles(worldIn, posNew, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
								if ((plant.blockType == PMPPlantBlockType.doub) || (plant.blockType == PMPPlantBlockType.immd))
								{
									worldIn.setBlockState(posNew.up(), state.withProperty(PMPBlockDouble.HALF, PMPBlockHalf.upper), 2);
									PMPHelper.spawnParticles(worldIn, posNew.up(), worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
								}
							}
						}
					} else if (plantBlock.canPlaceBlockAt(worldIn, posNew)) {
						IBlockState state = worldIn.getBlockState(pos);
						if (plantBlock == Blocks.DOUBLE_PLANT) {
							if ((worldIn.isAirBlock(posNew)) && (worldIn.isAirBlock(posNew.up())) && (Blocks.DOUBLE_PLANT.canPlaceBlockAt(worldIn, posNew))) {
								if (plantBlock == worldIn.getBlockState(pos.up()).getBlock()) {
									worldIn.setBlockState(posNew, state, 2);
									state = worldIn.getBlockState(pos.up());
									worldIn.setBlockState(posNew.up(), state, 2);
								} else {
									state = worldIn.getBlockState(pos.down());
									worldIn.setBlockState(posNew, state, 2);
									state = worldIn.getBlockState(pos);
									worldIn.setBlockState(posNew.up(), state, 2);
								}
							}
						} else if ((plantBlock instanceof BlockCrops)) {
							state = worldIn.getBlockState(pos).withProperty(BlockCrops.AGE, Integer.valueOf(0));
							worldIn.setBlockState(posNew, state, 2);
						} else {
							worldIn.setBlockState(posNew, state, 2);
						}
						PMPHelper.spawnParticles(worldIn, posNew, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
					}
				}
				iy = pos.getY();
			}
		}
		return true;
	}
}

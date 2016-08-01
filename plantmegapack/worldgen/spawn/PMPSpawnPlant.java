package plantmegapack.worldgen.spawn;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockBamboo;
import plantmegapack.block.PMPBlockClimbing;
import plantmegapack.block.PMPBlockColored;
import plantmegapack.block.PMPBlockDouble;
import plantmegapack.block.PMPBlockHalf;
import plantmegapack.block.PMPBlockThird;
import plantmegapack.block.PMPBlockTriple;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.data.PMPDataSpawnPlant;
import plantmegapack.object.PMPColor;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantSpawnType;
import plantmegapack.worldgen.PMPWorldgenHelper;
import plantmegapack.worldgen.PMPWorldgenSpawn;

public abstract class PMPSpawnPlant {
	public static void generatePlantCluster(World worldIn, Random random, PMPWorldgenSpawn spawnData, PMPDataSpawnPlant plantSpawnData, int genChance) {
		Block plantBlock = PlantMegaPack.blocks.getPlantBlockByName(plantSpawnData.plantAttributes.name());
		IPMPPlant plant = (IPMPPlant)plantBlock;
		PMPPlantSpawnType spawnType = getRandomSpawnType(random, plant.getPlantData());
		if ((plantBlock == null) || (plant == null)) {
			return;
		}
		if (spawnType == null) {
			if (PlantMegaPack.settings.debugMode) {
				System.out.println(String.format("NULL spawn type for plant: %s", new Object[] { plantSpawnData.plantAttributes.name() }));
			}
			return;
		}
		BlockPos spawnPos = new BlockPos(spawnData.spawnPos);
		
		int iy = spawnData.spawnPos.getY();
		int clusterAmount = Math.max(1, spawnType.clusterAmount);
		for (int i = 0; i < clusterAmount; i++) {
			if ((spawnData.habitat != null) && (plantBlock.canPlaceBlockAt(worldIn, spawnData.spawnPos)) && (random.nextInt(100) < genChance)) {
				if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.bamb) {
					spawnBambooPlant(worldIn, spawnData.spawnPos, random, plantBlock);
				} else if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.berr) {
					spawnBerrybushPlant(worldIn, spawnData.spawnPos, random, plantBlock);
				} else if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.clim) {
					spawnClimbingPlant(worldIn, spawnData.spawnPos, random, plantBlock);
				} else if ((plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.doub) || (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.immd)) {
					spawnDoublePlant(worldIn, spawnData.spawnPos, plantBlock);
				} else if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.trpl) {
					spawnTriplePlant(worldIn, spawnData.spawnPos, plantBlock);
				} else if (PMPPlant.hasColorVariants(plantSpawnData.plantAttributes)) {
					spawnColoredPlant(worldIn, spawnData.spawnPos, random, plantBlock);
				} else if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.seaw) {
					spawnSeaweedPlant(worldIn, spawnData.spawnPos, random, plantBlock);
				} else if (plantSpawnData.plantAttributes.blockType == PMPPlantBlockType.subm) {
					spawnSubmergedPlant(worldIn, spawnData.spawnPos, plantBlock);
				} else {
					spawnNormalPlant(worldIn, spawnData.spawnPos, plantBlock);
				}
			}
			int ix = random.nextInt(spawnType.clusterRadius);
			if (random.nextInt(100) < 50) {
				ix *= -1;
			}
			int iz = random.nextInt(spawnType.clusterRadius);
			if (random.nextInt(100) < 50) {
				iz *= -1;
			}
			spawnData.initSpawnLocation(worldIn, spawnPos.add(ix, 0, iz));
			if (plantSpawnData.elevationVariance == 0) {
				spawnData.spawnPos = new BlockPos(spawnData.spawnPos.getX(), iy, spawnData.spawnPos.getZ());
			} else if (spawnData.spawnPos.getY() > iy) {
				if (spawnData.spawnPos.getY() - iy > plantSpawnData.elevationVariance) {
					spawnData.spawnPos = new BlockPos(spawnData.spawnPos.getX(), iy + plantSpawnData.elevationVariance, spawnData.spawnPos.getZ());
				}
			} else if (spawnData.spawnPos.getY() < iy) {
				if (iy - spawnData.spawnPos.getY() > plantSpawnData.elevationVariance) {
					spawnData.spawnPos = new BlockPos(spawnData.spawnPos.getX(), iy - plantSpawnData.elevationVariance, spawnData.spawnPos.getZ());
				}
			}
		}
	}
	
	private static PMPPlantSpawnType getRandomSpawnType(Random random, PMPDataPlant plantData) {
		ArrayList<PMPPlantSpawnType> types = new ArrayList();
		for (PMPPlantSpawnType spawnType : PMPPlantSpawnType.values()) {
			if ((plantData.spawnTypes.containsKey(spawnType.name())) && (((Boolean)plantData.spawnTypes.get(spawnType.name())).booleanValue())) {
				types.add(spawnType);
			}
		}
		return types.size() == 0 ? PMPPlantSpawnType.clsm : (PMPPlantSpawnType)types.get(random.nextInt(types.size()));
	}
	
	public static void clearBlockAbovePlant(World worldIn, BlockPos posPlant) {
		Block blockAbove = worldIn.getBlockState(posPlant.up()).getBlock();
		if ((blockAbove.getMaterial(worldIn.getBlockState(posPlant.up())) == Material.PLANTS) || ((blockAbove instanceof BlockBush)) || ((blockAbove instanceof BlockCrops))) {
			worldIn.setBlockToAir(posPlant.up());
		}
	}
	
	public static void spawnBambooPlant(World worldIn, BlockPos pos, Random random, Block block) {
		PMPBlockBamboo bambooBlock = (PMPBlockBamboo)block;
		IBlockState state = bambooBlock.getDefaultState();
		worldIn.setBlockState(pos, state, 2);
		int randomHeight = Math.max(2, random.nextInt(PlantMegaPack.settings.plantBambooMaxHeight));
		int spawnHeight = 0;
		while ((spawnHeight < randomHeight) && (bambooBlock.canGrow(worldIn, pos, state, worldIn.isRemote))) {
			bambooBlock.grow(worldIn, random, pos, state);
			spawnHeight++;
		}
	}
	
	public static void spawnBerrybushPlant(World worldIn, BlockPos pos, Random random, Block block) {
		worldIn.setBlockState(pos, block.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(1 + random.nextInt(6))), 2);
		clearBlockAbovePlant(worldIn, pos);
	}
	
	public static void spawnClimbingPlant(World worldIn, BlockPos pos, Random random, Block block) {
		EnumFacing facing = PMPWorldgenHelper.getSolidFacing(worldIn, pos);
		if (facing != null) {
			PMPBlockClimbing climbingBlock = (PMPBlockClimbing)block;
			IBlockState state = climbingBlock.getDefaultState().withProperty(PMPBlockClimbing.FACING, facing);
			worldIn.setBlockState(pos, state, 2);
			int randomHeight = Math.max(2, random.nextInt(PlantMegaPack.settings.plantClimbingMaxHeight));
			int spawnHeight = 0;
			while ((spawnHeight < randomHeight) && (climbingBlock.canGrow(worldIn, pos, state, worldIn.isRemote))) {
				climbingBlock.grow(worldIn, random, pos, state);
				spawnHeight++;
			}
		}
	}
	
	public static void spawnColoredPlant(World worldIn, BlockPos pos, Random random, Block block) {
		PMPColor color = PMPColor.getColorFromID(random.nextInt(PMPColor.values().length));
		worldIn.setBlockState(pos, block.getDefaultState().withProperty(PMPBlockColored.VARIANT, color), 2);
		clearBlockAbovePlant(worldIn, pos);
	}
	
	public static void spawnDoublePlant(World worldIn, BlockPos pos, Block block) {
		worldIn.setBlockState(pos, block.getDefaultState().withProperty(PMPBlockDouble.HALF, PMPBlockHalf.lower), 2);
		worldIn.setBlockState(pos.up(), block.getDefaultState().withProperty(PMPBlockDouble.HALF, PMPBlockHalf.upper), 2);
	}
	
	public static void spawnTriplePlant(World worldIn, BlockPos pos, Block block) {
		worldIn.setBlockState(pos, block.getDefaultState().withProperty(PMPBlockTriple.SEGMENT, PMPBlockThird.lower), 2);
		worldIn.setBlockState(pos.up(), block.getDefaultState().withProperty(PMPBlockTriple.SEGMENT, PMPBlockThird.middle), 2);
		worldIn.setBlockState(pos.up(2), block.getDefaultState().withProperty(PMPBlockTriple.SEGMENT, PMPBlockThird.upper), 2);
	}
	
	public static void spawnNormalPlant(World worldIn, BlockPos pos, Block block) {
		worldIn.setBlockState(pos, block.getDefaultState(), 2);
		clearBlockAbovePlant(worldIn, pos);
	}
	
	public static void spawnSeaweedPlant(World worldIn, BlockPos pos, Random random, Block block) {
		int maxHeight = 0;
		BlockPos posTemp = pos;
		while ((worldIn.getBlockState(posTemp).getBlock().getMaterial(worldIn.getBlockState(posTemp)) == Material.WATER) && (worldIn.getBlockState(posTemp.up()).getBlock().getMaterial(worldIn.getBlockState(posTemp.up())) == Material.WATER)) {
			posTemp = posTemp.up();
			maxHeight++;
		}
		int height = random.nextInt(maxHeight);
		if (height > 2) {
			height /= 2;
		}
		posTemp = pos;
		for (int i = 0; i < height; i++) {
			worldIn.setBlockState(posTemp, block.getDefaultState(), 2);
			posTemp = posTemp.up();
		}
	}
	
	public static void spawnSubmergedPlant(World worldIn, BlockPos pos, Block block) {
		worldIn.setBlockState(pos, block.getDefaultState(), 2);
	}
}

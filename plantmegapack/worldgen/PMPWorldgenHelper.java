package plantmegapack.worldgen;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPTemperature;

public abstract class PMPWorldgenHelper
{
	public static EnumFacing isAdjacentToBlockMaterial(World worldIn, BlockPos pos, Material material) {
		Block block = worldIn.getBlockState(pos.south()).getBlock();
		if (block.getMaterial(worldIn.getBlockState(pos.south())) == material) {
			return EnumFacing.SOUTH;
		}
		block = worldIn.getBlockState(pos.west()).getBlock();
		if (block.getMaterial(worldIn.getBlockState(pos.west())) == material) {
			return EnumFacing.WEST;
		}
		block = worldIn.getBlockState(pos.north()).getBlock();
		if (block.getMaterial(worldIn.getBlockState(pos.north())) == material) {
			return EnumFacing.NORTH;
		}
		block = worldIn.getBlockState(pos.east()).getBlock();
		if (block.getMaterial(worldIn.getBlockState(pos.east())) == material) {
			return EnumFacing.EAST;
		}
		return null;
	}
	
	public static EnumFacing isAdjacentToLog(World worldIn, BlockPos posInitial) {
		BlockPos pos = new BlockPos(posInitial.south());
		if (worldIn.getBlockState(pos).getBlock().isWood(worldIn, pos)) {
			return EnumFacing.SOUTH;
		}
		pos = new BlockPos(posInitial.east());
		if (worldIn.getBlockState(pos).getBlock().isWood(worldIn, pos)) {
			return EnumFacing.EAST;
		}
		pos = new BlockPos(posInitial.west());
		if (worldIn.getBlockState(pos).getBlock().isWood(worldIn, pos)) {
			return EnumFacing.WEST;
		}
		pos = new BlockPos(posInitial.north());
		if (worldIn.getBlockState(pos).getBlock().isWood(worldIn, pos)) {
			return EnumFacing.NORTH;
		}
		return null;
	}
	
	public static PMPBiomeType getBiomeFromPosition(World worldIn, BlockPos pos) {
		Biome biome = worldIn.getBiomeGenForCoords(pos);
		if (biome == null) {
			return null;
		}
		PMPBiomeType biomeType = PMPBiomeType.getBiomeType(biome);
		if (biomeType == null) {
			return null;
		}
		return biomeType;
	}
	
	public static EnumFacing getSolidFacing(World worldIn, BlockPos pos) {
		if (worldIn.isSideSolid(pos.north(), EnumFacing.SOUTH)) {
			return EnumFacing.NORTH;
		}
		if (worldIn.isSideSolid(pos.west(), EnumFacing.EAST)) {
			return EnumFacing.WEST;
		}
		if (worldIn.isSideSolid(pos.east(), EnumFacing.WEST)) {
			return EnumFacing.EAST;
		}
		if (worldIn.isSideSolid(pos.south(), EnumFacing.NORTH)) {
			return EnumFacing.SOUTH;
		}
		return null;
	}
	
	public static boolean isAdjacentToBlockMaterialDiagonal(World worldIn, BlockPos pos, Material material) {
		for (int ix = -1; ix <= 1; ix++) {
			for (int iz = -1; iz <= 1; iz++) {
				BlockPos posTest = pos.add(ix, 0, iz);
				Block block = worldIn.getBlockState(posTest).getBlock();
				if (block.getMaterial(worldIn.getBlockState(posTest)) == material) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isAdjacentToDifferentBiome(Biome biome, World worldIn, BlockPos pos, int radius) {
		if (biome == null) {
			return false;
		}
		if (radius < 1) {
			radius = 1;
		}
		if (radius > 4) {
			radius = 4;
		}
		PMPBiomeType type1 = PMPBiomeType.getBiomeType(biome);
		if (type1 == null) {
			return false;
		}
		for (int ix = -radius; ix < radius; ix++) {
			for (int iz = -radius; iz < radius; iz++) {
				BlockPos posTarget = new BlockPos(pos.getX() + ix, pos.getY(), pos.getZ() + iz);
				PMPBiomeType type2 = PMPBiomeType.getBiomeType(worldIn.getBiomeGenForCoords(posTarget));
				if (type1 != type2) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isBlockReplaceable(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block == null) {
			return false;
		}
		Material material = block.getMaterial(worldIn.getBlockState(pos));
		if ((material == Material.AIR) || (material == Material.LEAVES) || (material == Material.PLANTS) || (material == Material.SNOW) || (material == Material.VINE)) {
			return true;
		}
		return block.isReplaceable(worldIn, pos);
	}
	
	public static boolean isGroundSloped(World worldIn, BlockPos pos, int slopeMax) {
		boolean s1 = false;
		boolean s2 = false;
		boolean s3 = false;
		if (slopeMax < 1) {
			slopeMax = 1;
		} else if (slopeMax > 3) {
			slopeMax = 3;
		}
		if (slopeMax == 1) {
			return (checkForSlope(worldIn, pos, 1, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 1, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.WEST));
		}
		if (slopeMax == 2) {
			s1 = (checkForSlope(worldIn, pos, 1, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 1, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.WEST));
			s2 = (checkForSlope(worldIn, pos, 2, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 2, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 2, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 2, EnumFacing.WEST));
			return (s1) && (s2);
		}
		if (slopeMax == 3) {
			s1 = (checkForSlope(worldIn, pos, 1, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 1, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 1, EnumFacing.WEST));
			s2 = (checkForSlope(worldIn, pos, 2, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 2, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 2, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 2, EnumFacing.WEST));
			s3 = (checkForSlope(worldIn, pos, 3, EnumFacing.NORTH)) || (checkForSlope(worldIn, pos, 3, EnumFacing.EAST)) || (checkForSlope(worldIn, pos, 3, EnumFacing.SOUTH)) || (checkForSlope(worldIn, pos, 3, EnumFacing.WEST));
			return (s1) && (s2) && (s3);
		}
		return false;
	}
	
	private static boolean checkForSlope(World worldIn, BlockPos pos, int slope, EnumFacing direction) {
		boolean downSlope = false;
		boolean upSlope = false;
		BlockPos posSlope = new BlockPos(pos);
		switch (direction) {
		case NORTH: 
			downSlope = (isBlockReplaceable(worldIn, posSlope.south(slope).down(slope))) && (!isBlockReplaceable(worldIn, posSlope.south(slope).down(slope - 1)));
			upSlope = (!isBlockReplaceable(worldIn, posSlope.north(slope))) && (isBlockReplaceable(worldIn, posSlope.north(slope).up(slope)));
			break;
		case EAST: 
			downSlope = (isBlockReplaceable(worldIn, posSlope.west(slope).down(slope))) && (!isBlockReplaceable(worldIn, posSlope.west(slope).down(slope - 1)));
			upSlope = (!isBlockReplaceable(worldIn, posSlope.east(slope))) && (isBlockReplaceable(worldIn, posSlope.east(slope).up(slope)));
			break;
		case SOUTH: 
			downSlope = (isBlockReplaceable(worldIn, posSlope.north(slope).down(slope))) && (!isBlockReplaceable(worldIn, posSlope.north(slope).down(slope - 1)));
			upSlope = (!isBlockReplaceable(worldIn, posSlope.south(slope))) && (isBlockReplaceable(worldIn, posSlope.south(slope).up(slope)));
			break;
		case WEST: 
			downSlope = (isBlockReplaceable(worldIn, posSlope.east(slope).down(slope))) && (!isBlockReplaceable(worldIn, posSlope.east(slope).down(slope - 1)));
			upSlope = (!isBlockReplaceable(worldIn, posSlope.west(slope))) && (isBlockReplaceable(worldIn, posSlope.west(slope).up(slope)));
			break;
		default: 
			return false;
		}
		return (downSlope) || (upSlope);
	}
	
	public static boolean isSurroundedByAir(World worldIn, BlockPos pos) {
		for (int ix = -1; ix <= 1; ix++) {
			for (int iz = -1; iz <= 1; iz++) {
				if (!worldIn.isAirBlock(new BlockPos(pos.getX() + ix, pos.getY(), pos.getZ() + iz))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isSurroundedByWater(World worldIn, BlockPos pos) {
		for (int ix = -1; ix <= 1; ix++) {
			for (int iz = -1; iz <= 1; iz++) {
				if (worldIn.getBlockState(pos.add(ix, 0, iz)).getBlock().getMaterial(worldIn.getBlockState(pos.add(ix, 0, iz))) != Material.WATER) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isCropBlock(Block blockSpawn) {
		return blockSpawn instanceof BlockCrops;
	}
	
	public static boolean canEpiphyteSpawnAtBlock(Block blockSpawn, IBlockState spawnState) {
		return (blockSpawn.getMaterial(spawnState) == Material.AIR) || (blockSpawn.getMaterial(spawnState) == Material.VINE);
	}
	
	public static boolean canVineSpawnAtBlock(World worldIn, BlockPos pos) {
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		return blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.AIR;
	}
	
	public static PMPBiomeType scanRiverAreaForBiome(World worldIn, BlockPos posInitial, int distance) {
		Map<PMPBiomeType, Integer> map = new HashMap(8);
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.north(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.south(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.east(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.west(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.north(distance).west(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.north(distance).east(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.south(distance).west(distance));
		updateRiverBiomeScanMapWithPos(map, worldIn, posInitial.south(distance).east(distance));
		if (map.isEmpty()) {
			return null;
		}
		PMPBiomeType biome = null;
		
		int hits = 0;
		for (Map.Entry<PMPBiomeType, Integer> entry : map.entrySet()) {
			int curValue = ((Integer)entry.getValue()).intValue();
			if (curValue > hits) {
				hits = curValue;
				biome = (PMPBiomeType)entry.getKey();
			}
		}
		return biome;
	}
	
	private static void updateRiverBiomeScanMapWithPos(Map<PMPBiomeType, Integer> map, World worldIn, BlockPos pos) {
		PMPBiomeType biome = getBiomeFromPosition(worldIn, pos);
		if ((biome == null) || (biome == PMPBiomeType.rive)) {
			return;
		}
		Integer count = (Integer)map.get(biome);
		if (count == null) {
			map.put(biome, Integer.valueOf(1));
		} else {
			map.put(biome, new Integer(count.intValue() + 1));
		}
	}
	
	public static PMPTemperature scanAreaForTemperature(World worldIn, BlockPos posInitial, int distance) {
		float averageTemp = 0.0F;
		Biome biome = worldIn.getBiomeGenForCoords(posInitial.north(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.south(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.east(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.west(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.north(distance).west(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.north(distance).east(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.south(distance).west(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		biome = worldIn.getBiomeGenForCoords(posInitial.south(distance).east(distance));
		averageTemp += biome.getFloatTemperature(posInitial);
		averageTemp /= 8.0F;
		if (averageTemp > 0.85F) {
			return PMPTemperature.hot;
		}
		if (averageTemp < 0.15F) {
			return PMPTemperature.cold;
		}
		return PMPTemperature.warm;
	}
}

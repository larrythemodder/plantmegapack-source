package plantmegapack.plant;

import net.minecraft.block.Block;
import net.minecraft.block.BlockClay;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockGravel;
import net.minecraft.block.BlockHardenedClay;
import net.minecraft.block.BlockMycelium;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public enum PMPPlantSoilType {
	aqua,
	beac,
	grou,
	norm,
	sand,
	wate,
	wetl;
	
	private PMPPlantSoilType() {}
	
	public static boolean canPlantGrowOnBlock(Block block, IBlockState state, PMPPlantSoilType soilType) {
		switch (soilType) {
		case aqua: 
			return (isDirt(block, state)) || (isGravel(block, state)) || (isSand(block, state));
		case beac: 
			return (isDirt(block, state)) || (isSand(block, state));
		case grou: 
			return canPlaceAny(block, state);
		case norm: 
			return isDirt(block, state);
		case sand: 
			return isSand(block, state);
		case wate: 
			return isWater(block, state);
		case wetl: 
			return (isDirt(block, state)) || (isGravel(block, state)) || (isSand(block, state));
		}
		return false;
	}
	
	public static boolean canPlaceAny(Block block, IBlockState state) {
		return (isClay(block, state)) || (isDirt(block, state)) || (isGravel(block, state)) || (isMycelium(block, state)) || (isSand(block, state));
	}
	
	public static boolean isClay(Block block, IBlockState state) {
		return ((block instanceof BlockClay)) || ((block instanceof BlockHardenedClay)) || (block.getMaterial(state) == Material.CLAY);
	}
	
	public static boolean isDirt(Block block, IBlockState state) {
		return ((block instanceof BlockDirt)) || ((block instanceof BlockGrass)) || ((block instanceof BlockFarmland));
	}
	
	public static boolean isGravel(Block block, IBlockState state) {
		return block instanceof BlockGravel;
	}
	
	public static boolean isMycelium(Block block, IBlockState state) {
		return block instanceof BlockMycelium;
	}
	
	public static boolean isSand(Block block, IBlockState state) {
		return ((block instanceof BlockSand)) || (block.getMaterial(state) == Material.SAND);
	}
	
	public static boolean isStone(Block block, IBlockState state) {
		return ((block instanceof BlockStone)) || (block.getMaterial(state) == Material.ROCK);
	}
	
	public static boolean isWater(Block block, IBlockState state) {
		return block.getMaterial(state) == Material.WATER;
	}
}

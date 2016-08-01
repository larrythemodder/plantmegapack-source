package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPBlockSeaweed
	extends PMPBlockSubmerged
	implements IGrowable, IPMPPlant
{
	public PMPBlockSeaweed(PMPDataPlant plantData) {
		super(plantData);
		setTickRandomly(true);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if ((worldIn.getBlockState(pos).getBlock().getMaterial(worldIn.getBlockState(pos)) != Material.WATER) || (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.WATER)) {
			return false;
		}
		return (worldIn.getBlockState(pos.down()).getBlock() == this) || (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(pos.down()).getBlock(), worldIn.getBlockState(pos.down()), this.plantData.attributes.category.soilType));
	}
	
	private boolean canBlockStay(World worldIn, BlockPos pos) {
		if ((worldIn.getBlockState(pos.up()).getBlock() != this) && (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.WATER)) {
			return false;
		}
		return (worldIn.getBlockState(pos.down()).getBlock() == this) || (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(pos.down()).getBlock(), worldIn.getBlockState(pos.down()), this.plantData.attributes.category.soilType));
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		//super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
		checkAndDropBlock(worldIn, pos, state);
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(8) == 0) {
			if (canGrow(worldIn, pos, state, !worldIn.isRemote)) {
				grow(worldIn, rand, pos, state);
			}
		}
		checkAndDropBlock(worldIn, pos, state);
	}
	
	private void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!canBlockStay(worldIn, pos)) {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
		}
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if ((worldIn.getBlockState(pos.up()).getBlock() != Blocks.WATER) || (worldIn.getBlockState(pos.up(2)).getBlock() != Blocks.WATER)) {
			return false;
		}
		int height = PMPBlockCore.countPlantHeightFromTop(worldIn, pos, this);
		if (height >= PlantMegaPack.settings.plantSeaweedMaxHeight) {
			return false;
		}
		return true;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return false;
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos.up(), getDefaultState(), 2);
	}
}

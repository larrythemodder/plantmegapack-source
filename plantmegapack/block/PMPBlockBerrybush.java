package plantmegapack.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.worldgen.PMPWorldgenHelper;

public class PMPBlockBerrybush extends PMPBlockCrop implements IPMPPlant {
	protected static final AxisAlignedBB AABB_BERRYBUSH = new AxisAlignedBB(0.1D, 0.0D, 0.1D, 0.9D, 1.0D, 0.9D);
	
	public PMPBlockBerrybush(PMPDataPlant plantData) {
		super(plantData);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock().getMaterial(worldIn.getBlockState(pos)) == Material.WATER) {
			return false;
		}
		if (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.AIR) {
			return false;
		}
		if (!PMPWorldgenHelper.isBlockReplaceable(worldIn, pos)) {
			return false;
		}
		return super.canPlaceBlockAt(worldIn, pos);
	}
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, 0);
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return ((Integer)state.getValue(BlockCrops.AGE)).intValue() < 7;
	}
	
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		int age = ((Integer)state.getValue(BlockCrops.AGE)).intValue();
		if (age == 7) {
			Block.spawnAsEntity(worldIn, pos, new ItemStack(getCrop(), 1, 0));
			worldIn.setBlockState(pos, getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)), 2);
			return true;
		}
		return false;
	}
	
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList();
		Random rand = (world instanceof World) ? ((World)world).rand : RANDOM;
		int count = quantityDropped(state, fortune, rand);
		for (int i = 0; i < count; i++) {
			Item item = getItemDropped(state, rand, fortune);
			if (item != null) {
				ret.add(new ItemStack(item, 1, damageDropped(state)));
			}
		}
		return ret;
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		int age = ((Integer)state.getValue(BlockCrops.AGE)).intValue();
		if (age == 7) {
			Block.spawnAsEntity(worldIn, pos, new ItemStack(getCrop(), 1, 0));
		}
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (this.plantData.attributes.category != PMPPlantCategory.berr) {
			return PMPBlockCore.getPlantAABB(PMPBlockBase.AABB_ALL, pos);
		}
		return PMPBlockCore.getPlantAABB(AABB_BERRYBUSH, pos);
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes.category == PMPPlantCategory.cact) {
			return state.getBoundingBox(worldIn, pos);
		}
		return PMPBlockCore.getPlantAABB(AABB_BERRYBUSH, pos);
	}
}

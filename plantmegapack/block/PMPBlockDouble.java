package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;

public class PMPBlockDouble
	extends PMPBlockBase
	implements IPMPPlant
{
	public static final PropertyEnum<PMPBlockHalf> HALF = PropertyEnum.create("half", PMPBlockHalf.class);
	protected static final AxisAlignedBB AABB_UPPER = new AxisAlignedBB(0.15D, -1.0D, 0.15D, 0.85D, 1.0D, 0.85D);
	protected static final AxisAlignedBB AABB_LOWER = new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 2.0D, 0.85D);
	
	public PMPBlockDouble(PMPDataPlant plantData) {
		super(plantData);
		setDefaultState(getDefaultState().withProperty(HALF, PMPBlockHalf.lower));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { HALF });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(HALF, PMPBlockHalf.byMetadata(meta & 0x8));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((PMPBlockHalf)state.getValue(HALF)).getMetadata() << 3;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.upper) {
			IBlockState stateBelow = worldIn.getBlockState(pos.offset(EnumFacing.DOWN));
			if ((stateBelow.getBlock() == this) && ((PMPBlockHalf)stateBelow.getValue(HALF) == PMPBlockHalf.lower)) {
				return true;
			}
		}
		return super.canBlockStay(worldIn, pos, state);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return (super.canPlaceBlockAt(worldIn, pos)) && (worldIn.isAirBlock(pos.up()));
	}
	
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!canBlockStay(worldIn, pos, state)) {
			if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.lower) {
				dropBlockAsItem(worldIn, pos, state, 0);
			}
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		PMPBlockHalf position = (PMPBlockHalf)state.getValue(HALF);
		worldIn.setBlockToAir(pos.offset(position == PMPBlockHalf.upper ? EnumFacing.DOWN : EnumFacing.UP));
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos.offset(EnumFacing.UP), getDefaultState().withProperty(HALF, PMPBlockHalf.upper), 3);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.upper) {
			return PMPBlockCore.getPlantAABB(AABB_UPPER, pos);
		}
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.lower) {
			return PMPBlockCore.getPlantAABB(AABB_LOWER, pos);
		}
		return Block.FULL_BLOCK_AABB;
	}
}

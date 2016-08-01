package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;

public class PMPBlockTriple
	extends PMPBlockBase
	implements IPMPPlant
{
	public static final PropertyEnum<PMPBlockThird> SEGMENT = PropertyEnum.create("segment", PMPBlockThird.class);
	protected static final AxisAlignedBB AABB_UPPER = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, -3.0D, 1.0D);
	protected static final AxisAlignedBB AABB_MIDDLE = new AxisAlignedBB(0.0D, -1.0D, 0.0D, 1.0D, 1.85D, 1.0D);
	protected static final AxisAlignedBB AABB_LOWER = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 2.85D, 1.0D);
	
	public PMPBlockTriple(PMPDataPlant plantData) {
		super(plantData);
		setDefaultState(getDefaultState().withProperty(SEGMENT, PMPBlockThird.lower));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SEGMENT });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SEGMENT, PMPBlockThird.byMetadata(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((PMPBlockThird)state.getValue(SEGMENT)).getMetadata();
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() != this) {
			return false;
		}
		IBlockState stateBelow = worldIn.getBlockState(pos.down());
		if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.upper) {
			if ((stateBelow.getBlock() == this) && ((PMPBlockThird)stateBelow.getValue(SEGMENT) == PMPBlockThird.middle)) {
				return true;
			}
			return false;
		}
		if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.middle) {
			if ((stateBelow.getBlock() == this) && ((PMPBlockThird)stateBelow.getValue(SEGMENT) == PMPBlockThird.lower)) {
				return true;
			}
			return false;
		}
		return super.canBlockStay(worldIn, pos, state);
	}
	
	protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!canBlockStay(worldIn, pos, state)) {
			if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.lower) {
				dropBlockAsItem(worldIn, pos, state, 0);
			}
			worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
		}
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		PMPBlockThird position = (PMPBlockThird)state.getValue(SEGMENT);
		if (position == PMPBlockThird.upper) {
			worldIn.setBlockToAir(pos.down());
			worldIn.setBlockToAir(pos.down(2));
		} else if (position == PMPBlockThird.middle) {
			worldIn.setBlockToAir(pos.up());
			worldIn.setBlockToAir(pos.down());
		} else {
			worldIn.setBlockToAir(pos.up());
			worldIn.setBlockToAir(pos.up(2));
		}
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos.up(), getDefaultState().withProperty(SEGMENT, PMPBlockThird.middle), 3);
		worldIn.setBlockState(pos.up(2), getDefaultState().withProperty(SEGMENT, PMPBlockThird.upper), 3);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.lower) {
			return PMPBlockCore.getPlantAABB(AABB_LOWER, pos);
		}
		if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.middle) {
			return PMPBlockCore.getPlantAABB(AABB_MIDDLE, pos);
		}
		if ((PMPBlockThird)state.getValue(SEGMENT) == PMPBlockThird.upper) {
			return PMPBlockCore.getPlantAABB(AABB_UPPER, pos);
		}
		return Block.FULL_BLOCK_AABB;
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes == PMPPlant.largeSaguaro) {
			return state.getBoundingBox(worldIn, pos);
		}
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes == PMPPlant.largeSaguaro) {
			return state.getBoundingBox(worldIn, pos).offset(pos);
		}
		return super.getCollisionBoundingBox(state, worldIn, pos);
	}
}

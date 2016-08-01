package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.core.IPMPPlant;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantRender;

public class PMPBlockEpiphyte
	extends PMPBlockBase
	implements IPMPPlant
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB AABB_HORZ_S = new AxisAlignedBB(0.0D, 0.12D, 0.375D, 1.0D, 0.38D, 1.125D);
	protected static final AxisAlignedBB AABB_HORZ_N = new AxisAlignedBB(0.0D, 0.12D, 0.0D, 1.0D, 0.38D, 0.625D);
	protected static final AxisAlignedBB AABB_HORZ_E = new AxisAlignedBB(0.375D, 0.12D, 0.0D, 1.0D, 0.38D, 1.0D);
	protected static final AxisAlignedBB AABB_HORZ_W = new AxisAlignedBB(0.0D, 0.12D, 0.0D, 0.625D, 0.38D, 1.0D);
	protected static final AxisAlignedBB AABB_VERT_S = new AxisAlignedBB(0.125D, 0.125D, 0.125D, 0.875D, 0.875D, 1.0D);
	protected static final AxisAlignedBB AABB_VERT_N = new AxisAlignedBB(0.125D, 0.125D, 0.0D, 0.875D, 0.875D, 0.875D);
	protected static final AxisAlignedBB AABB_VERT_E = new AxisAlignedBB(0.125D, 0.125D, 0.125D, 1.0D, 0.875D, 0.875D);
	protected static final AxisAlignedBB AABB_VERT_W = new AxisAlignedBB(0.0D, 0.125D, 0.125D, 0.875D, 0.875D, 0.875D);
	
	public PMPBlockEpiphyte(PMPDataPlant plantData) {
		super(plantData);
		if (plantData.attributes.renderType == PMPPlantRender.epih) {
			setSoundType(SoundType.WOOD);
		}
		setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		return canEpiphyteGrowHere(worldIn, pos);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return (block.isReplaceable(worldIn, pos)) && (canEpiphyteGrowHere(worldIn, pos));
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int metaData, EntityLivingBase placer) {
		if (worldIn.getBlockState(pos.offset(facing.getOpposite())).getBlock().isWood(worldIn, pos.offset(facing.getOpposite()))) {
			return getStateFromMeta(facing.getOpposite().getHorizontalIndex());
		}
		return getStateFromMeta(metaData);
	}
	
	private boolean canEpiphyteGrowHere(World worldIn, BlockPos pos) {
		if (canAttachToBlock(worldIn, pos.south(), EnumFacing.NORTH)) {
			return true;
		}
		if (canAttachToBlock(worldIn, pos.north(), EnumFacing.SOUTH)) {
			return true;
		}
		if (canAttachToBlock(worldIn, pos.east(), EnumFacing.WEST)) {
			return true;
		}
		if (canAttachToBlock(worldIn, pos.west(), EnumFacing.EAST)) {
			return true;
		}
		return false;
	}
	
	private boolean canAttachToBlock(World worldIn, BlockPos pos, EnumFacing attachSide) {
		Block hostBlock = worldIn.getBlockState(pos).getBlock();
		if (!worldIn.getBlockState(pos).isSideSolid(worldIn, pos, attachSide)) {
			return false;
		}
		if ((hostBlock.isWood(worldIn, pos)) || ((hostBlock instanceof PMPBlockTrellis))) {
			return true;
		}
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
		AxisAlignedBB aabb = Block.FULL_BLOCK_AABB;
		if (this.plantData.attributes.renderType == PMPPlantRender.epih) {
			switch (facing) {
			case SOUTH: 
				aabb = AABB_HORZ_S;
			case NORTH: 
				aabb = AABB_HORZ_N;
			case EAST: 
				aabb = AABB_HORZ_E;
			case WEST: 
				aabb = AABB_HORZ_W;
			default:
				break;
			}
		} else if (this.plantData.attributes.renderType == PMPPlantRender.epiv) {
			switch (facing) {
			case SOUTH: 
				aabb = AABB_VERT_S;
			case NORTH: 
				aabb = AABB_VERT_N;
			case EAST: 
				aabb = AABB_VERT_E;
			case WEST: 
				aabb = AABB_VERT_W;
			default:
				break;
			}
		}
		return aabb;
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes.renderType == PMPPlantRender.epih) {
			return state.getBoundingBox(worldIn, pos).offset(pos);
		}
		return super.getCollisionBoundingBox(state, worldIn, pos);
	}
}

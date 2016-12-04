package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.item.PMPItemTrellis;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPTrellis;

public class PMPBlockTrellis
	extends BlockDirectional
{
	private PMPTrellis trellis;
	public static final PropertyInteger CONNECTED = PropertyInteger.create("connected", 0, 3);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	
	public PMPBlockTrellis(PMPTrellis trellis) {
		super(Material.WOOD);
		this.trellis = trellis;
		setDefaultState(this.blockState.getBaseState().withProperty(CONNECTED, Integer.valueOf(0)).withProperty(BlockDirectional.FACING, EnumFacing.NORTH));
		setUnlocalizedName(this.trellis.name());
		setCreativeTab(PlantMegaPack.tabItem);
		setTickRandomly(false);
		setSoundType(SoundType.WOOD);
		setHardness(0.6F);
		setSoundType(SoundType.WOOD);
		this.useNeighborBrightness = true;
		this.enableStats = false;
		GameRegistry.registerBlock(this, PMPItemTrellis.class, this.trellis.name());
		OreDictionary.registerOre(this.trellis.oreDictName, this);
	}
	
	public PMPTrellis getTrellis() {
		return this.trellis;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { CONNECTED, BlockDirectional.FACING });
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		IBlockState iblockstate1 = worldIn.getBlockState(pos);
		iblockstate1 = iblockstate1.withProperty(CONNECTED, Integer.valueOf(getConnectValue(state, worldIn, pos)));
		return iblockstate1;
	}
	
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((EnumFacing)state.getValue(BlockDirectional.FACING)).getHorizontalIndex();
		i |= ((Integer)state.getValue(CONNECTED)).intValue() << 2;
		
		return i >= 0 ? i : 0;
	}
	
	public IBlockState getStateFromMeta(int meta) {
		int direction = meta &= 0xFFFFFFF3;
		EnumFacing enumfacing = EnumFacing.getHorizontal(direction);
		return getDefaultState().withProperty(BlockDirectional.FACING, enumfacing).withProperty(CONNECTED, Integer.valueOf((meta | 0xC) >> 2));
	}
	
	public int getConnectValue(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		if (state.getValue(BlockDirectional.FACING) == EnumFacing.NORTH) {
			if ((worldIn.getBlockState(pos.west()).getBlock() == this) && (worldIn.getBlockState(pos.west()).getValue(BlockDirectional.FACING) == EnumFacing.NORTH)) {
				if ((worldIn.getBlockState(pos.east()).getBlock() == this) && (worldIn.getBlockState(pos.east()).getValue(BlockDirectional.FACING) == EnumFacing.NORTH)) {
					return 3;
				}
				return 2;
			}
			if ((worldIn.getBlockState(pos.east()).getBlock() == this) && (worldIn.getBlockState(pos.east()).getValue(BlockDirectional.FACING) == EnumFacing.NORTH)) {
				if ((worldIn.getBlockState(pos.west()).getBlock() == this) && (worldIn.getBlockState(pos.west()).getValue(BlockDirectional.FACING) == EnumFacing.NORTH)) {
					return 3;
				}
				return 1;
			}
		} else if (state.getValue(BlockDirectional.FACING) == EnumFacing.SOUTH) {
			if ((worldIn.getBlockState(pos.west()).getBlock() == this) && (worldIn.getBlockState(pos.west()).getValue(BlockDirectional.FACING) == EnumFacing.SOUTH)) {
				if ((worldIn.getBlockState(pos.east()).getBlock() == this) && (worldIn.getBlockState(pos.east()).getValue(BlockDirectional.FACING) == EnumFacing.SOUTH)) {
					return 3;
				}
				return 1;
			}
			if ((worldIn.getBlockState(pos.east()).getBlock() == this) && (worldIn.getBlockState(pos.east()).getValue(BlockDirectional.FACING) == EnumFacing.SOUTH)) {
				if ((worldIn.getBlockState(pos.west()).getBlock() == this) && (worldIn.getBlockState(pos.west()).getValue(BlockDirectional.FACING) == EnumFacing.SOUTH)) {
					return 3;
				}
				return 2;
			}
		} else if (state.getValue(BlockDirectional.FACING) == EnumFacing.WEST) {
			if ((worldIn.getBlockState(pos.north()).getBlock() == this) && (worldIn.getBlockState(pos.north()).getValue(BlockDirectional.FACING) == EnumFacing.WEST)) {
				if ((worldIn.getBlockState(pos.south()).getBlock() == this) && (worldIn.getBlockState(pos.south()).getValue(BlockDirectional.FACING) == EnumFacing.WEST)) {
					return 3;
				}
				return 1;
			}
			if ((worldIn.getBlockState(pos.south()).getBlock() == this) && (worldIn.getBlockState(pos.south()).getValue(BlockDirectional.FACING) == EnumFacing.WEST)) {
				if ((worldIn.getBlockState(pos.north()).getBlock() == this) && (worldIn.getBlockState(pos.north()).getValue(BlockDirectional.FACING) == EnumFacing.WEST)) {
					return 3;
				}
				return 2;
			}
		} else if (state.getValue(BlockDirectional.FACING) == EnumFacing.EAST) {
			if ((worldIn.getBlockState(pos.north()).getBlock() == this) && (worldIn.getBlockState(pos.north()).getValue(BlockDirectional.FACING) == EnumFacing.EAST)) {
				if ((worldIn.getBlockState(pos.south()).getBlock() == this) && (worldIn.getBlockState(pos.south()).getValue(BlockDirectional.FACING) == EnumFacing.EAST)) {
					return 3;
				}
				return 2;
			}
			if ((worldIn.getBlockState(pos.south()).getBlock() == this) && (worldIn.getBlockState(pos.south()).getValue(BlockDirectional.FACING) == EnumFacing.EAST)) {
				if ((worldIn.getBlockState(pos.north()).getBlock() == this) && (worldIn.getBlockState(pos.north()).getValue(BlockDirectional.FACING) == EnumFacing.EAST)) {
					return 3;
				}
				return 1;
			}
		}
		return 0;
	}
	
	public boolean isFlammable(IBlockAccess worldIn, BlockPos pos, EnumFacing face) {
		return true;
	}
	
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 80;
	}
	
	public boolean isSideSolid(IBlockState base_state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == (EnumFacing)worldIn.getBlockState(pos).getValue(BlockDirectional.FACING);
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		switch (((EnumFacing)state.getValue(BlockDirectional.FACING)).getHorizontalIndex()) {
		case 0: 
			return AABB_S;
		case 1: 
			return AABB_W;
		case 2: 
			return AABB_N;
		case 3: 
			return AABB_E;
		}
		return Block.FULL_BLOCK_AABB;
	}
}

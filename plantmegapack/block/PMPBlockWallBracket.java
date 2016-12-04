package plantmegapack.block;

import net.minecraft.block.Block;
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
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.item.PMPItemWallBracket;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWallBracket;
import plantmegapack.object.PMPWallBracketType;

public class PMPBlockWallBracket
	extends Block
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.25D, 0.0D, 0.43D, 0.75D, 0.5D, 1.0D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.25D, 0.57D, 0.5D, 0.75D);
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.75D, 0.5D, 0.57D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.43D, 0.0D, 0.25D, 1.0D, 0.5D, 0.75D);
	private PMPWallBracket bracket;
	
	public PMPBlockWallBracket(PMPWallBracket bracket) {
		super(bracket.type.material);
		this.bracket = bracket;
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		setTickRandomly(false);
		this.useNeighborBrightness = true;
		this.enableStats = false;
		setHardness(0.2F);
		setSoundType(this.bracket.type.sound);
		setUnlocalizedName(this.bracket.name());
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerBlock(this, PMPItemWallBracket.class, this.bracket.name());
		OreDictionary.registerOre(this.bracket.oreDictName, this);
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
	
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.DOWN;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int metaData, EntityLivingBase placer) {
		IBlockState state = getDefaultState();
		if (canAttachToBlock(worldIn, pos.offset(facing.getOpposite()), facing)) {
			return state.withProperty(FACING, facing.getOpposite());
		}
		return state;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return (worldIn.isAirBlock(pos)) && ((canAttachToBlock(worldIn, pos.north(), EnumFacing.SOUTH)) || (canAttachToBlock(worldIn, pos.south(), EnumFacing.NORTH)) || (canAttachToBlock(worldIn, pos.east(), EnumFacing.WEST)) || (canAttachToBlock(worldIn, pos.west(), EnumFacing.EAST)));
	}
	
	private boolean canBlockStay(World worldIn, BlockPos pos) {
		EnumFacing facing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
		return canAttachToBlock(worldIn, pos.offset(facing), facing.getOpposite());
	}
	
	private boolean canAttachToBlock(World worldIn, BlockPos pos, EnumFacing facing) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return (block.isBlockSolid(worldIn, pos, facing)) && (block.isSideSolid(worldIn.getBlockState(pos), worldIn, pos, facing));
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (!canBlockStay(worldIn, pos)) {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		switch (facing) {
		case SOUTH: 
			return AABB_S;
		case NORTH: 
			return AABB_N;
		case EAST: 
			return AABB_E;
		case WEST: 
			return AABB_W;
		default:
			break;
		}
		return Block.FULL_BLOCK_AABB;
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return getBoundingBox(state, worldIn, pos).offset(pos);
	}
}

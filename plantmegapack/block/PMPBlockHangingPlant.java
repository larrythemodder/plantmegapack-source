package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockWall;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPSettings;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPTab;

public class PMPBlockHangingPlant
	extends Block
	implements IGrowable
{
	public static final PropertyEnum<Segment> SEGMENT = PropertyEnum.create("segment", Segment.class);
	private PMPHangingPlant hangingPlant;
	
	public PMPBlockHangingPlant(PMPHangingPlant hangingPlant) {
		super(Material.PLANTS);
		this.hangingPlant = hangingPlant;
		setDefaultState(this.blockState.getBaseState().withProperty(SEGMENT, Segment.top));
		setCreativeTab(PlantMegaPack.tabItem);
		setUnlocalizedName(this.hangingPlant.name());
		setSoundType(SoundType.PLANT);
		GameRegistry.registerBlock(this, this.hangingPlant.name());
		OreDictionary.registerOre(this.hangingPlant.oreDictName, this);
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SEGMENT });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SEGMENT, Segment.byMetadata(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((Segment)state.getValue(SEGMENT)).getMetadata();
	}
	
	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	public boolean isSideSolid(IBlockState baseState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return side == EnumFacing.UP;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return (isValidConnectionBlock(worldIn, pos.up())) && (worldIn.isAirBlock(pos)) && (worldIn.isAirBlock(pos.down()));
	}
	
	private boolean canBlockStay(World worldIn, BlockPos pos) {
		Segment segment = (Segment)worldIn.getBlockState(pos).getValue(SEGMENT);
		if (segment == Segment.top) {
			return (isValidConnectionBlock(worldIn, pos.up())) && (worldIn.getBlockState(pos.down()).getBlock() == this);
		}
		IBlockState stateAbove = worldIn.getBlockState(pos.up());
		if (stateAbove.getBlock() == this) {
			segment = (Segment)stateAbove.getValue(SEGMENT);
			return (segment == Segment.middle) || (segment == Segment.top);
		}
		return false;
	}
	
	private boolean isValidConnectionBlock(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (block.isSideSolid(worldIn.getBlockState(pos), worldIn, pos, EnumFacing.DOWN)) {
			return true;
		}
		if (((block instanceof BlockFence)) || ((block instanceof BlockWall))) {
			return true;
		}
		return false;
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		worldIn.setBlockState(pos.down(), getDefaultState().withProperty(SEGMENT, Segment.bottom), 3);
		return getStateFromMeta(meta);
	}
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, 0);
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (!canBlockStay(worldIn, pos)) {
			if ((!Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) && (worldIn.getBlockState(pos).getValue(SEGMENT) == Segment.top)) {
				dropBlockAsItem(worldIn, pos, getDefaultState(), 0);
			}
			destroyPlantFromHereDownwards(worldIn, pos);
		} else {
			IBlockState stateBelow = worldIn.getBlockState(pos.down());
			if (stateBelow.getBlock() != this) {
				worldIn.setBlockState(pos, getDefaultState().withProperty(SEGMENT, Segment.bottom), 3);
			}
		}
	}
	
	private void destroyPlantFromHereDownwards(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		while (block == this) {
			worldIn.setBlockToAir(pos);
			pos = pos.down();
			block = worldIn.getBlockState(pos).getBlock();
		}
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if (worldIn.getBlockState(pos).getValue(SEGMENT) == Segment.top) {
			return (PMPBlockCore.countPlantHeightFromTop(worldIn, pos, this) < PlantMegaPack.settings.plantHangingMaxHeight) && (canGrowDown(worldIn, pos));
		}
		return false;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return canGrow(worldIn, pos, state, true);
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		BlockPos posCur = pos;
		Block block = worldIn.getBlockState(posCur).getBlock();
		while (block == this) {
			posCur = posCur.down();
			block = worldIn.getBlockState(posCur).getBlock();
		}
		worldIn.setBlockState(posCur, getDefaultState().withProperty(SEGMENT, Segment.bottom), 3);
		posCur = posCur.up();
		if (worldIn.getBlockState(posCur).getValue(SEGMENT) == Segment.bottom) {
			worldIn.setBlockState(posCur, getDefaultState().withProperty(SEGMENT, Segment.middle), 3);
		}
	}
	
	private boolean canGrowDown(World worldIn, BlockPos pos) {
		BlockPos posCur = pos;
		Block block = worldIn.getBlockState(posCur).getBlock();
		while (block == this) {
			posCur = posCur.down();
			block = worldIn.getBlockState(posCur).getBlock();
		}
		return block.getMaterial(worldIn.getBlockState(posCur)) == Material.AIR;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return Block.FULL_BLOCK_AABB;
	}
	
	public static enum Segment
		implements IStringSerializable
	{
		top(0, "_hgpt"),	middle(1, "_hgpm"),	bottom(2, "_hgpb");
		
		private static final Segment[] META_LOOKUP;
		private final int meta;
		public final String modelName;
		
		private Segment(int meta, String modelName) {
			this.meta = meta;
			this.modelName = modelName;
		}
		
		public String getName() {
			return name();
		}
		
		public int getMetadata() {
			return this.meta;
		}
		
		public static Segment byMetadata(int meta) {
			if ((meta < 0) || (meta >= META_LOOKUP.length)) {
				meta = 0;
			}
			return META_LOOKUP[meta];
		}
		
		static
		{
			META_LOOKUP = new Segment[values().length];
			for (Segment hangingPlantSegment : values()) {
				META_LOOKUP[hangingPlantSegment.getMetadata()] = hangingPlantSegment;
			}
		}
	}
}

package plantmegapack.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPTreeFruitType;

public class PMPBlockTreeFruit
	extends BlockDirectional
	implements IGrowable
{
	private static int MAX_GROWTH_INDEX = 3;
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, MAX_GROWTH_INDEX);
	protected static final AxisAlignedBB AABB_BERRIES = new AxisAlignedBB(0.2D, 0.5D, 0.2D, 0.8D, 1.0D, 0.8D);
	protected static final AxisAlignedBB AABB_FRUIT = new AxisAlignedBB(0.2D, 0.5D, 0.2D, 0.8D, 1.0D, 0.8D);
	private PMPSapling sapling;
	
	public PMPBlockTreeFruit(PMPSapling sapling) {
		super(Material.PLANTS);
		setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)).withProperty(BlockDirectional.FACING, EnumFacing.NORTH));
		setTickRandomly(true);
		this.sapling = sapling;
		setSoundType(SoundType.PLANT);
		setUnlocalizedName(getUnlocalizedNameModified());
		setCreativeTab((CreativeTabs)null);
		GameRegistry.registerBlock(this, getUnlocalizedNameModified());
		OreDictionary.registerOre(getUnlocalizedNameModified(), this);
	}
	
	public String getUnlocalizedNameModified() {
		return "treeFruit" + this.sapling.food.name().substring(4);
	}
	
	public PMPFood getFoodItem() {
		return this.sapling.food;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE, BlockDirectional.FACING });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(AGE, Integer.valueOf((meta & 0xF) >> 2)).withProperty(BlockDirectional.FACING, EnumFacing.getHorizontal(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((EnumFacing)state.getValue(BlockDirectional.FACING)).getHorizontalIndex();
		i |= ((Integer)state.getValue(AGE)).intValue() << 2;
		
		return i >= 0 ? i : 0;
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		if (!canBlockStay(worldIn, pos, state)) {
			dropBlock(worldIn, pos, state);
		} else if (worldIn.rand.nextInt(4) == 0) {
			int i = ((Integer)state.getValue(AGE)).intValue();
			if (i < MAX_GROWTH_INDEX) {
				worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(i + 1)), 2);
			}
		}
	}
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		EnumFacing enumfacing = EnumFacing.fromAngle(placer.rotationYaw);
		worldIn.setBlockState(pos, state.withProperty(BlockDirectional.FACING, enumfacing), 2);
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		int direction = MathHelper.floor_double(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 0x3;
		EnumFacing enumFacing = EnumFacing.getHorizontal(direction);
		return getDefaultState().withProperty(BlockDirectional.FACING, enumFacing).withProperty(AGE, Integer.valueOf(0));
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if (!canBlockStay(worldIn, pos, state)) {
			dropBlock(worldIn, pos, state);
		}
	}
	
	private boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState stateBlockAbove = worldIn.getBlockState(pos.up());
		if (stateBlockAbove == null) {
			return false;
		}
		Block blockAbove = stateBlockAbove.getBlock();
		if ((blockAbove == null) || (!blockAbove.isWood(worldIn, pos.up()))) {
			return false;
		}
		return true;
	}
	
	private void dropBlock(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
		dropBlockAsItem(worldIn, pos, state, 0);
	}
	
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
		super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
	}
	
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> dropped = new ArrayList();
		int j = ((Integer)state.getValue(AGE)).intValue();
		byte b0 = 1;
		if (j >= MAX_GROWTH_INDEX) {
			b0 = 3;
		}
		for (int k = 0; k < b0; k++) {
			dropped.add(new ItemStack(PlantMegaPack.items.getFoodItem(this.sapling.food), 1, 0));
		}
		return dropped;
	}
	
	@SideOnly(Side.CLIENT)
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
		return new ItemStack(PlantMegaPack.items.getFoodItem(this.sapling.food), 1, 0);
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return ((Integer)state.getValue(AGE)).intValue() < MAX_GROWTH_INDEX;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, state.withProperty(AGE, Integer.valueOf(((Integer)state.getValue(AGE)).intValue() + 1)), 2);
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
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (this.sapling.treeFruitType == PMPTreeFruitType.berries) {
			return AABB_BERRIES;
		}
		return AABB_FRUIT;
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return getBoundingBox(blockState, worldIn, pos);
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return getSelectedBoundingBox(state, worldIn, pos).offset(pos);
	}
}

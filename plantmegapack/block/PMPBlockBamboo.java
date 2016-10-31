package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
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
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSoilType;
import net.minecraftforge.oredict.OreDictionary;

public class PMPBlockBamboo extends PMPBlockBase implements IGrowable {
	public static final PropertyInteger SEGMENT = PropertyInteger.create("segment", 0, 4);
	protected static final AxisAlignedBB AABB_ALL = new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
	
	public PMPBlockBamboo(PMPDataPlant plantData) {
		super(plantData);
		setDefaultState(getDefaultState().withProperty(SEGMENT, Integer.valueOf(Integer.valueOf(0).intValue())));
		setSoundType(SoundType.WOOD);
		setHarvestLevel("axe", 0);
		OreDictionary.registerOre("bamboo");
		OreDictionary.registerOre("materialBamboo");
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { SEGMENT });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SEGMENT, Integer.valueOf(Integer.valueOf(meta).intValue()));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(SEGMENT)).intValue();
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if (worldIn.getBlockState(pos.down()).getBlock() == this) {
			return false;
		}
		return PMPBlockCore.countPlantHeightFromBottom(worldIn, pos, this) < PlantMegaPack.settings.plantBambooMaxHeight;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		int newHeight = PMPBlockCore.countPlantHeightFromBottom(worldIn, pos, this) + 1;
		int segment = -1;
		for (int y = 0; y < newHeight; y++) {
			segment++;
			if (segment > 3) {
				segment = 3;
			}
			if (y == newHeight - 1) {
				segment = 4;
			}
			worldIn.setBlockState(pos.up(y), getDefaultState().withProperty(SEGMENT, Integer.valueOf(Integer.valueOf(segment).intValue())), 3);
		}
	}
	
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return false;
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return getStateFromMeta(4);
	}
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
		BlockPos posAbove = pos.offset(EnumFacing.UP);
		IBlockState iblockstate = worldIn.getBlockState(posAbove);
		Block block = iblockstate.getBlock();
		if ((block == this) && (PlantMegaPack.settings.realismGravity))
		{
			block.dropBlockAsItem(worldIn, posAbove, iblockstate, 0);
			worldIn.setBlockState(posAbove, Blocks.AIR.getDefaultState(), 3);
		}
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if ((state.getBlock() == this) && (worldIn.isAirBlock(pos.up())) && (((Integer)state.getValue(SEGMENT)).intValue() != 4)) {
			worldIn.setBlockState(pos, state.withProperty(SEGMENT, Integer.valueOf(Integer.valueOf(4).intValue())), 3);
		}
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (rand.nextInt(8) == 0) {
			if (canGrow(worldIn, pos, state, !worldIn.isRemote)) {
				grow(worldIn, rand, pos, state);
			}
		}
		checkAndDropBlock(worldIn, pos, state);
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (!PlantMegaPack.settings.realismGravity) {
			return true;
		}
		BlockPos posBelow = pos.down();
		Block soil = worldIn.getBlockState(posBelow).getBlock();
		if (state.getBlock() != this) {
			return canPlaceBlockOn(soil);
		}
		if (soil == this) {
			return true;
		}
		return super.canBlockStay(worldIn, pos, state);
	}
	
	private boolean canPlaceBlockOn(Block soil) {
		if ((this.plantData.attributes == PMPPlant.bambooTimorBlack) && (PMPPlantSoilType.isMycelium(soil, soil.getDefaultState()))) {
			return true;
		}
		return PMPPlantSoilType.canPlantGrowOnBlock(soil, soil.getDefaultState(), this.plantData.attributes.category.soilType);
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return PMPBlockCore.getPlantAABB(AABB_ALL, pos);
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return state.getBoundingBox(worldIn, pos);
	}
}

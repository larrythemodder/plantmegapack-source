package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCropDrops;

public class PMPBlockCropDouble
	extends BlockCrops
	implements IPMPPlant
{
	public static final PropertyEnum<PMPBlockHalf> HALF = PropertyEnum.create("half", PMPBlockHalf.class);
	private PMPDataPlant plantData;
	protected static final AxisAlignedBB[] AABB_UPPER = { new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D), new AxisAlignedBB(0.15D, -1.0D, 0.15D, 0.85D, 0.5D, 0.85D), new AxisAlignedBB(0.15D, -1.0D, 0.15D, 0.85D, 0.85D, 0.85D) };
	protected static final AxisAlignedBB[] AABB_LOWER = { new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.25D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.5D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 1.0D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 1.5D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 1.85D, 0.85D) };
	
	public PMPBlockCropDouble(PMPDataPlant plantData) {
		this.plantData = plantData;
		setSoundType(SoundType.PLANT);
		PMPBlockCore.initPlantBlock(this, this.plantData, PMPItemPlant.class);
		setDefaultState(getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)).withProperty(HALF, PMPBlockHalf.lower));
	}
	
	public PMPDataPlant getPlantData() {
		return this.plantData;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockCrops.AGE, HALF });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(meta & 0x7)).withProperty(HALF, PMPBlockHalf.byMetadata(meta & 0x8));
	}
	
	public int getMetaFromState(IBlockState state) {
		int meta = ((Integer)state.getValue(BlockCrops.AGE)).intValue();
		meta |= ((PMPBlockHalf)state.getValue(HALF)).getMetadata() << 3;
		return meta;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		IBlockState stateBelow = worldIn.getBlockState(pos.down());
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.upper) {
			if ((stateBelow.getBlock() == this) && ((PMPBlockHalf)stateBelow.getValue(HALF) == PMPBlockHalf.lower)) {
				return true;
			}
			return false;
		}
		return super.canBlockStay(worldIn, pos, state);
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
		if (position == PMPBlockHalf.lower) {
			if (worldIn.getBlockState(pos.up()).getBlock() == this) {
				worldIn.setBlockToAir(pos.up());
			}
		} else if (worldIn.getBlockState(pos.down()).getBlock() == this) {
			worldIn.setBlockToAir(pos.down());
		}
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.lower) && (worldIn.getLightFromNeighbors(pos.up()) >= 9)) {
			if (((Integer)state.getValue(BlockCrops.AGE)).intValue() < 7) {
				float f = getGrowthChance(this, worldIn, pos);
				if (rand.nextInt((int)(25.0F / f) + 1) == 0) {
					growPlant(worldIn, pos, state, 1);
				}
			}
		}
		checkAndDropBlock(worldIn, pos, state);
	}
	
	private void growPlant(World worldIn, BlockPos pos, IBlockState state, int growAmount) {
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.lower) {
			int age = ((Integer)state.getValue(BlockCrops.AGE)).intValue() + growAmount;
			if (age > 7) {
				age = 7;
			}
			if (((age > 2) && (worldIn.isAirBlock(pos.up()))) || (worldIn.getBlockState(pos.up()).getBlock() == this)) {
				worldIn.setBlockState(pos.up(), getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(age)).withProperty(HALF, PMPBlockHalf.upper), 2);
			} else {
				age = 2;
			}
			worldIn.setBlockState(pos, state.withProperty(BlockCrops.AGE, Integer.valueOf(age)).withProperty(HALF, PMPBlockHalf.lower), 2);
		} else {
			growPlant(worldIn, pos.down(), worldIn.getBlockState(pos.down()), growAmount);
		}
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		growPlant(worldIn, pos, state, MathHelper.getRandomIntegerInRange(worldIn.rand, 2, 5));
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		int age = ((Integer)state.getValue(BlockCrops.AGE)).intValue();
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.lower) {
			if (age < 2) {
				return true;
			}
			if ((age == 2) && (worldIn.isAirBlock(pos.up()))) {
				return true;
			}
			if (worldIn.getBlockState(pos.up()).getBlock() != this) {
				return false;
			}
		}
		return age < 7;
	}
	
	protected Item getSeed() {
		return PlantMegaPack.plantDrops.getSeedItem(this.plantData.attributes.name());
	}
	
	protected Item getCrop() {
		return PlantMegaPack.plantDrops.getFoodItem(this.plantData.attributes.name());
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return PlantMegaPack.settings.realismCropCentered ? Block.EnumOffsetType.NONE : Block.EnumOffsetType.XZ;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		int stage = PMPHelper.getCropStageFromAge(((Integer)worldIn.getBlockState(pos).getValue(BlockCrops.AGE)).intValue());
		AxisAlignedBB aabb;
		if ((PMPBlockHalf)state.getValue(HALF) == PMPBlockHalf.upper) {
			aabb = AABB_UPPER[stage];
		} else {
			aabb = AABB_LOWER[stage];
		}
		return PMPBlockCore.getCropAABB(aabb, pos);
	}
}

package plantmegapack.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Plane;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCropDrops;

public class PMPBlockClimbing extends PMPBlockBase implements IPMPPlant, IGrowable, IPlantable {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB AABB_S = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_W = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
	protected static final AxisAlignedBB AABB_N = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
	protected static final AxisAlignedBB AABB_E = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	
	public PMPBlockClimbing(PMPDataPlant plantData) {
		super(plantData);
		setDefaultState(getDefaultState().withProperty(AGE, Integer.valueOf(0)).withProperty(FACING, EnumFacing.NORTH));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { AGE, FACING });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing enumfacing = EnumFacing.getHorizontal(meta &= 0xFFFFFFF3);
		return getDefaultState().withProperty(FACING, enumfacing).withProperty(AGE, Integer.valueOf((meta | 0xC) >> 2));
	}
	
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
		i |= ((Integer)state.getValue(AGE)).intValue() << 2;
		return i;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		Block soil = worldIn.getBlockState(pos.down()).getBlock();
		if (soil == this) {
			return true;
		}
		return super.canBlockStay(worldIn, pos, state);
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		if (worldIn.getBlockState(pos.down()).getBlock() == this) {
			return false;
		}
		return canContinueGrowing(worldIn, pos, state);
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		Block soil = worldIn.getBlockState(pos.down()).getBlock();
		if ((soil == null) || (soil == this)) {
			return false;
		}
		return true;
	}
	
	public boolean isLadder(IBlockState state, IBlockAccess worldIn, BlockPos pos, EntityLivingBase entity) {
		return true;
	}
	
	private boolean canContinueGrowing(World worldIn, BlockPos pos, IBlockState state) {
		BlockPos posTarget = new BlockPos(pos);
		int totalHeight = 1;
		while (worldIn.getBlockState(posTarget.up()).getBlock() == this) {
			posTarget = posTarget.up();
			totalHeight++;
		}
		int topBlockGrowth = ((Integer)worldIn.getBlockState(posTarget).getValue(AGE)).intValue();
		if (totalHeight == PlantMegaPack.settings.plantClimbingMaxHeight) {
			return topBlockGrowth < 3;
		}
		if (topBlockGrowth < 3) {
			return true;
		}
		posTarget = posTarget.up();
		if (!worldIn.isAirBlock(posTarget)) {
			return false;
		}
		EnumFacing facing = (EnumFacing)state.getValue(FACING);
		posTarget = posTarget.offset(facing);
		return worldIn.getBlockState(posTarget).isSideSolid(worldIn, posTarget, facing.getOpposite());
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		if (worldIn.getBlockState(pos).getBlock() != this) {
			return;
		}
		BlockPos posTarget = new BlockPos(pos);
		
		int totalHeight = 1;
		while (worldIn.getBlockState(posTarget.up()).getBlock() == this) {
			posTarget = posTarget.up();
			totalHeight++;
		}
		IBlockState blockStateTarget = worldIn.getBlockState(posTarget);
		int curGrowth = ((Integer)blockStateTarget.getValue(AGE)).intValue();
		if (totalHeight == PlantMegaPack.settings.plantClimbingMaxHeight) {
			if (curGrowth < 3) {
				curGrowth++;
				worldIn.setBlockState(posTarget, state.withProperty(AGE, Integer.valueOf(curGrowth)), 2);
				return;
			}
			return;
		}
		EnumFacing attached = (EnumFacing)state.getValue(FACING);
		if (curGrowth < 3) {
			curGrowth++;
			worldIn.setBlockState(posTarget, state.withProperty(AGE, Integer.valueOf(curGrowth)), 2);
			return;
		}
		posTarget = posTarget.up();
		if ((worldIn.isAirBlock(posTarget)) && (worldIn.isSideSolid(posTarget.offset(attached), attached.getOpposite(), false))) {
			worldIn.setBlockState(posTarget, getDefaultState().withProperty(FACING, attached).withProperty(AGE, Integer.valueOf(0)), 2);
			return;
		}
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return (direction == EnumFacing.UP) && (plantable == this) && (state.getBlock() == this) && (((Integer)state.getValue(AGE)).intValue() == 3);
	}
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if ((!worldIn.isRemote) && (!canBlockStay(worldIn, pos, state))) {
			dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockToAir(pos);
		}
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(worldIn, pos, state, random);
		if ((worldIn.getBlockState(pos).getBlock() != this) || (worldIn.getBlockState(pos.down()).getBlock() == this)) {
			return;
		}
		if ((random.nextInt(5) == 0) && (canContinueGrowing(worldIn, pos, state))) {
			grow(worldIn, random, pos, state);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}
	
	public List<ItemStack> getDrops(IBlockAccess worldIn, BlockPos pos, IBlockState state, int fortune) {
		List<ItemStack> ret = new ArrayList();
		Random rand = (worldIn instanceof World) ? ((World)worldIn).rand : RANDOM;
		ret.add(new ItemStack(getItemDropped(state, rand, fortune), 1, 0));
		if (((Integer)state.getValue(AGE)).intValue() == 3) {
			ret.add(new ItemStack(PlantMegaPack.plantDrops.getFoodItem(this.plantData.attributes.name()), 1, 0));
		}
		return ret;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		switch (((EnumFacing)state.getValue(FACING)).getHorizontalIndex()) {
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

package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPBlockSubmerged
	extends Block
	implements IPMPPlant, IPlantable
{
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
	protected PMPDataPlant plantData;
	
	public PMPBlockSubmerged(PMPDataPlant plantData) {
		super(Material.WATER);
		this.plantData = plantData;
		setSoundType(SoundType.PLANT);
		PMPBlockCore.initPlantBlock(this, this.plantData, PMPItemPlant.class);
		setLightOpacity(3);
		setDefaultState(getDefaultState().withProperty(LEVEL, Integer.valueOf(15)));
	}
	
	public PMPDataPlant getPlantData() {
		return this.plantData;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LEVEL });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(LEVEL)).intValue();
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if ((worldIn.getBlockState(pos).getBlock().getMaterial(worldIn.getBlockState(pos)) != Material.WATER) || (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.WATER)) {
			return false;
		}
		return PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(pos.down()).getBlock(), worldIn.getBlockState(pos.down()), this.plantData.attributes.category.soilType);
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.setBlockState(pos, Blocks.WATER.getDefaultState(), 2);
	}
	
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}
	
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return PlantMegaPack.settings.realismPlantCentered ? Block.EnumOffsetType.NONE : Block.EnumOffsetType.XZ;
	}
	
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		return Block.FULL_BLOCK_AABB;
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		if (this.plantData.attributes.category == PMPPlantCategory.cora) {
			return Block.FULL_BLOCK_AABB;
		}
		return Block.NULL_AABB;
	}
}

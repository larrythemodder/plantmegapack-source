package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPItems;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;
import plantmegapack.object.PMPFood;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantCropDrops;

public class PMPBlockCrop
	extends BlockCrops
	implements IPMPPlant
{
	protected PMPDataPlant plantData;
	protected static final AxisAlignedBB[] AABB_AGE = { new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.125D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.25D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.375D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.5D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.625D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.75D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.875D, 0.85D), new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 1.0D, 0.85D) };
	
	public PMPBlockCrop(PMPDataPlant plantData) {
		this.plantData = plantData;
		setSoundType(SoundType.PLANT);
		PMPBlockCore.initPlantBlock(this, this.plantData, PMPItemPlant.class);
	}
	
	public PMPDataPlant getPlantData() {
		return this.plantData;
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this) {
			IBlockState soil = worldIn.getBlockState(pos.down());
			return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), EnumFacing.UP, this);
		}
		return canPlaceBlockOn(worldIn.getBlockState(pos.down()));
	}
	
	protected boolean canPlaceBlockOn(IBlockState state) {
		return state.getBlock() == Blocks.FARMLAND;
	}
	
	protected Item getSeed() {
		if (this.plantData.attributes.category == PMPPlantCategory.crpa) {
			if (this.plantData.attributes.name().matches("cropRice")) {
				return PlantMegaPack.items.getFoodItem(PMPFood.foodRice);
			}
			if (this.plantData.attributes.name().matches("cropWildRice")) {
				return PlantMegaPack.items.getFoodItem(PMPFood.foodWildRice);
			}
			return Item.getItemFromBlock(this);
		}
		if ((this.plantData.attributes.name().matches("cropLentil")) || (this.plantData.attributes.name().matches("cropPeanut"))) {
			return getCrop();
		}
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
		return PMPBlockCore.getCropAABB(AABB_AGE[((Integer)state.getValue(getAgeProperty())).intValue()], pos);
	}
}

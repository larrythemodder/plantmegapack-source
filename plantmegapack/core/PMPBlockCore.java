package plantmegapack.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;

public abstract class PMPBlockCore
{
	public static void initPlantBlock(Block block, PMPDataPlant plantData, Class<? extends ItemBlock> itemClass) {
		if ((block != null) && (plantData != null) && (itemClass != null)) {
			block.setTickRandomly(plantData.attributes.category.tickRandomly);
			block.setUnlocalizedName(plantData.attributes.name());
			block.setCreativeTab(plantData.attributes.category.creativeTab);
			block.setHardness(plantData.attributes.hardness);
			GameRegistry.registerBlock(block, itemClass, plantData.attributes.name());
			OreDictionary.registerOre(plantData.attributes.oreDictName, block);
		} else {
			PlantMegaPack.instance.logOutput("Error initializing block");
		}
	}
	
	public static AxisAlignedBB getCropAABB(AxisAlignedBB baseAABB, BlockPos pos) {
		if (PlantMegaPack.settings.realismCropCentered) {
			return baseAABB;
		}
		long l = pos.getX() * 3129871 ^ pos.getZ() * 116129781L;
		l = l * l * 42317861L + l * 11L;
		return baseAABB.offset(((float)(l >> 16 & 0xF) / 15.0F - 0.5D) * 0.5D, 0.0D, ((float)(l >> 24 & 0xF) / 15.0F - 0.5D) * 0.5D);
	}
	
	public static AxisAlignedBB getPlantAABB(AxisAlignedBB baseAABB, BlockPos pos) {
		if (PlantMegaPack.settings.realismPlantCentered) {
			return baseAABB;
		}
		long l = pos.getX() * 3129871 ^ pos.getZ() * 116129781L;
		l = l * l * 42317861L + l * 11L;
		return baseAABB.offset(((float)(l >> 16 & 0xF) / 15.0F - 0.5D) * 0.5D, 0.0D, ((float)(l >> 24 & 0xF) / 15.0F - 0.5D) * 0.5D);
	}
	
	public static int countPlantHeightFromBottom(World worldIn, BlockPos posBottom, Block block) {
		BlockPos pos = posBottom;
		int height = 0;
		do
		{
			pos = pos.offset(EnumFacing.UP);
			height++;
		} while (worldIn.getBlockState(pos).getBlock() == block);
		return height;
	}
	
	public static int countPlantHeightFromTop(World worldIn, BlockPos posTop, Block block) {
		BlockPos pos = posTop;
		int height = 0;
		do
		{
			pos = pos.down();
			height++;
		} while (worldIn.getBlockState(pos).getBlock() == block);
		return height;
	}
	
	public static boolean isFullWaterBlock(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		Block block = state.getBlock();
		if (block.getMaterial(state) != Material.WATER) {
			return false;
		}
		return ((Integer)state.getValue(BlockLiquid.LEVEL)).intValue() == 0;
	}
}

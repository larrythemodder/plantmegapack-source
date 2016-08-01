package plantmegapack.core;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import plantmegapack.object.PMPColor;

public abstract class PMPHelper
{
	public static void addCraftingDyeTooltip(List list, int color) {
		list.add("§8" + I18n.translateToLocal("gui.crafting") + ": " + PMPColor.getColorFromID(color).getLocalizedDyeNameFormatted() + "§r");
	}
	
	public static void addCraftingItemTooltip(List list, String key) {
		list.add("§8" + I18n.translateToLocal("gui.crafting") + ": §9" + I18n.translateToLocal(key) + "§r");
	}
	
	public static void addSmeltingItemTooltip(List list, String key) {
		list.add("§8" + I18n.translateToLocal("gui.smelting") + ": §9" + I18n.translateToLocal(key) + "§r");
	}
	
	public static void addCraftingBlockTooltip(List list, Block block) {
		list.add("§8" + I18n.translateToLocal("gui.crafting") + ": §9" + I18n.translateToLocal(new StringBuilder().append(block.getUnlocalizedName()).append(".name").toString()) + "§r");
	}
	
	public static void addCraftingItemTooltip(List list, Item item) {
		list.add("§8" + I18n.translateToLocal("gui.crafting") + ": §9" + I18n.translateToLocal(new StringBuilder().append(item.getUnlocalizedName()).append(".name").toString()) + "§r");
	}
	
	public static boolean isValidPosition(BlockPos pos) {
		return (pos.getX() >= -30000000) && (pos.getZ() >= -30000000) && (pos.getX() < 30000000) && (pos.getZ() < 30000000) && (pos.getY() >= 0) && (pos.getY() < 256);
	}
	
	public static boolean isValidLeavesBlock(Block block) {
		return block.getMaterial(block.getDefaultState()) == Material.LEAVES;
	}
	
	public static boolean isValidPlantBlock(Block block) {
		Material material = block.getMaterial(block.getDefaultState());
		return (material == Material.CACTUS) || (material == Material.PLANTS) || (material == Material.VINE) || ((block instanceof BlockBush)) || ((block instanceof BlockCactus)) || ((block instanceof BlockReed)) || ((block instanceof BlockVine));
	}
	
	public static void spawnParticles(World worldIn, BlockPos pos, Random random, boolean heightAdjust, EnumParticleTypes particleType) {
		double d0 = random.nextGaussian() * 0.02D;
		double d1 = random.nextGaussian() * 0.02D;
		double d2 = random.nextGaussian() * 0.02D;
		double heightAdjustment = heightAdjust ? 1.0F : 0.05F;
		worldIn.spawnParticle(particleType, pos.getX() + random.nextFloat(), pos.getY() + heightAdjustment + random.nextFloat() * 1.0D, pos.getZ() + random.nextFloat(), d0, d1, d2, new int[0]);
	}
	
	public static String correctMojangItemName(String badName, Item item, int meta) {
		if (badName.matches("wheat_seeds")) {
			return "seeds_wheat";
		}
		if (badName.matches("melon_seeds")) {
			return "seeds_melon";
		}
		if (badName.matches("poisonous_potato")) {
			return "potato_poisonous";
		}
		if (badName.matches("pumpkin_seeds")) {
			return "seeds_pumpkin";
		}
		if (item == Items.DYE) {
			badName = "dye_powder_" + EnumDyeColor.byDyeDamage(meta).getUnlocalizedName();
			if (badName.matches("dye_powder_lightBlue")) {
				badName = "dye_powder_light_blue";
			}
		}
		return badName;
	}
	
	public static boolean isFilenameValid(String fileName) {
		File file = new File(fileName);
		try
		{
			file.getCanonicalPath();
			return true;
		}
		catch (IOException e) {}
		return false;
	}
	
	public static boolean createDirectory(String path) {
		File pmpConfigDir = new File(path);
		if (!pmpConfigDir.exists()) {
			try
			{
				pmpConfigDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static void deleteOutputDirectory(File directory) {
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			if ((files != null) && (files.length > 0)) {
				for (File aFile : files) {
					deleteOutputDirectory(aFile);
				}
			}
			directory.delete();
		} else {
			directory.delete();
		}
	}
	
	public static int getCropStageFromAge(int age) {
		switch (age) {
		case 0: 
			return 0;
		case 1: 
		case 2: 
			return 1;
		case 3: 
		case 4: 
			return 2;
		case 5: 
		case 6: 
			return 3;
		case 7: 
			return 4;
		}
		return 0;
	}
}

package plantmegapack.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPSettings;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPTab;
import plantmegapack.worldgen.feature.PMPGenPowder;

public class PMPItemPowder
	extends Item
{
	private PMPPowder powder;
	
	public PMPItemPowder(PMPPowder powder) {
		this.powder = powder;
		setUnlocalizedName(this.powder.name());
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		setMaxStackSize(1);
		setMaximumUses();
		GameRegistry.registerItem(this, this.powder.name());
		OreDictionary.registerOre(this.powder.oreDictName, this);
	}
	
	private void setMaximumUses() {
		int setting = 10;
		if (this.powder == PMPPowder.powderConditioner) {
			setting = PlantMegaPack.settings.powderConditionerUses;
		} else if (this.powder == PMPPowder.powderDefoliant) {
			setting = PlantMegaPack.settings.powderDefoliantUses;
		} else if (this.powder == PMPPowder.powderFertilizer) {
			setting = PlantMegaPack.settings.powderFertilizerUses;
		}
		setMaxDamage(setting - 1);
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) == Material.WATER) {
			return EnumActionResult.FAIL;
		}
		boolean powderApplied = false;
		if (this.powder == PMPPowder.powderConditioner) {
			powderApplied = PMPGenPowder.applyPowder(worldIn, pos, PMPPowder.powderConditioner, false);
		} else if (this.powder == PMPPowder.powderDefoliant) {
			powderApplied = PMPGenPowder.applyPowder(worldIn, pos, PMPPowder.powderDefoliant, false);
		} else if (this.powder == PMPPowder.powderFertilizer) {
			powderApplied = PMPGenPowder.applyPowder(worldIn, pos, PMPPowder.powderFertilizer, false);
		}
		if (powderApplied) {
			stack.damageItem(1, playerIn);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		int strength = 0;
		int radius = 0;
		if (this.powder == PMPPowder.powderConditioner) {
			strength = PlantMegaPack.settings.powderConditionerStrength;
			radius = PlantMegaPack.settings.powderConditionerRadius;
		} else if (this.powder == PMPPowder.powderDefoliant) {
			strength = PlantMegaPack.settings.powderDefoliantStrength;
			radius = PlantMegaPack.settings.powderDefoliantRadius;
		} else if (this.powder == PMPPowder.powderFertilizer) {
			strength = PlantMegaPack.settings.powderFertilizerStrength;
			radius = PlantMegaPack.settings.powderFertilizerRadius;
		}
		list.add("§7" + I18n.translateToLocal("gui.usesRemaining") + String.format(": §a%d§r", new Object[] { Integer.valueOf(itemStack.getMaxDamage() - itemStack.getItemDamage() + 1) }));
		list.add("§8" + I18n.translateToLocal("gui.strength") + String.format(": §9%d", new Object[] { Integer.valueOf(strength) }) + "%§r");
		list.add("§8" + I18n.translateToLocal("gui.radius") + String.format(": §9%d§r", new Object[] { Integer.valueOf(radius) }));
	}
}

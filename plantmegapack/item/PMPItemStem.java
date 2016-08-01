package plantmegapack.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPStem;
import plantmegapack.object.PMPTab;

public class PMPItemStem
	extends Item
{
	private PMPStem stem;
	
	public PMPItemStem(PMPStem stem) {
		this.stem = stem;
		setUnlocalizedName(this.stem.name());
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerItem(this, this.stem.name());
		OreDictionary.registerOre(this.stem.oreDictName, this);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if (this.stem == PMPStem.stemHard) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getPowderItem(PMPPowder.powderConditioner));
		} else if (this.stem == PMPStem.stemSoft) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getPowderItem(PMPPowder.powderFertilizer));
		} else if (this.stem == PMPStem.stemCactus) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getSalveItem(PMPSalve.salveStrength));
		}
	}
}

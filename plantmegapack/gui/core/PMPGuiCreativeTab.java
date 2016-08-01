package plantmegapack.gui.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPTab;

public class PMPGuiCreativeTab
	extends CreativeTabs
{
	private PMPTab tab;
	
	public PMPGuiCreativeTab(PMPTab tab) {
		super(CreativeTabs.getNextID(), tab.name());
		this.tab = tab;
	}
	
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		Item retItem = null;
		if (this.tab == PMPTab.aqua) {
			retItem = Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("freshwaterAmazonSword"));
		} else if (this.tab == PMPTab.crop) {
			retItem = Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("cropTomato"));
		} else if (this.tab == PMPTab.flow) {
			retItem = Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("flowerPurpleConeflower"));
		} else if (this.tab == PMPTab.item) {
			retItem = PlantMegaPack.items.getPowderItem(PMPPowder.powderFertilizer);
		} else if (this.tab == PMPTab.land) {
			retItem = Item.getItemFromBlock(PlantMegaPack.blocks.getPlantBlockByName("shrubBoxwood"));
		}
		return retItem == null ? Item.getItemFromBlock(Blocks.DEADBUSH) : retItem;
	}
	
	@SideOnly(Side.CLIENT)
	public String getTranslatedTabLabel() {
		return "tab." + getTabLabel();
	}
}

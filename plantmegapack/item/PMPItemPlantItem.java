package plantmegapack.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPTab;

public class PMPItemPlantItem
	extends Item
{
	private PMPPlantItem plantItem;
	
	public PMPItemPlantItem(PMPPlantItem plantItem) {
		this.plantItem = plantItem;
		setUnlocalizedName(this.plantItem.name());
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerItem(this, this.plantItem.name());
		OreDictionary.registerOre(this.plantItem.oreDictName, this);
	}
}

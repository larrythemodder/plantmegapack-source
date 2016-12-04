package plantmegapack.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPTab;

public class PMPItemRootMedicinal
	extends Item
{
	public PMPItemRootMedicinal(String name) {
		setUnlocalizedName(name);
		setCreativeTab(PlantMegaPack.tabItem);
		setMaxDamage(0);
		setHasSubtypes(true);
		GameRegistry.registerItem(this, name);
		OreDictionary.registerOre(name, this);
		OreDictionary.registerOre("rootMedicinal", this);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		for (PMPRootMedicinal root : PMPRootMedicinal.values()) {
			subItems.add(new ItemStack(itemIn, 1, root.ID));
		}
	}
}

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
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerItem(this, this.stem.name());
		OreDictionary.registerOre(this.stem.oreDictName, this);
	}
}

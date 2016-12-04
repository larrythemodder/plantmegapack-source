package plantmegapack.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPTab;

public class PMPItemFragment
	extends Item
{
	private PMPFragment coralFragment;
	
	public PMPItemFragment(PMPFragment coralFragment) {
		this.coralFragment = coralFragment;
		setUnlocalizedName(this.coralFragment.name());
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerItem(this, this.coralFragment.name());
		OreDictionary.registerOre(this.coralFragment.oreDictName, this);
	}
}

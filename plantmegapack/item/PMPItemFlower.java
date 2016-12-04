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
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPTab;

public class PMPItemFlower
	extends Item
{
	private PMPFlower flower;
	
	public PMPItemFlower(PMPFlower flower) {
		this.flower = flower;
		setUnlocalizedName(this.flower.name());
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerItem(this, this.flower.name());
		OreDictionary.registerOre(this.flower.oreDictName, this);
	}
}

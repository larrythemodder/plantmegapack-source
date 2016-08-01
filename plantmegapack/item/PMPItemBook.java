package plantmegapack.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPSettings;
import plantmegapack.object.PMPTab;

public class PMPItemBook
	extends Item
{
	public PMPItemBook(String name) {
		setMaxStackSize(1);
		setUnlocalizedName(name);
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerItem(this, name);
		OreDictionary.registerOre(name, this);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.openGui(PlantMegaPack.instance, 1, worldIn, 0, 0, 0);
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if (PlantMegaPack.settings.bookSaveProgress) {
			String text1 = I18n.translateToLocal("gui.plantGuide1");
			String text2 = I18n.translateToLocal("gui.plantGuide2");
			int pctCollected = Math.round(PlantMegaPack.bookRegistry.getPlantsCollected() * 100 / PlantMegaPack.bookRegistry.getPlantsTotal());
			list.add(String.format("§a%d§7 %s§a %d§7 %s (%d%%)§r", new Object[] { Integer.valueOf(PlantMegaPack.bookRegistry.getPlantsCollected()), text1, Integer.valueOf(PlantMegaPack.bookRegistry.getPlantsTotal()), text2, Integer.valueOf(pctCollected) }));
		}
		list.add("§8" + I18n.translateToLocal("gui.plantGuide3") + "§r");
	}
}

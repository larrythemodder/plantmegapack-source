package plantmegapack.item;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPSettings;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPTab;

public class PMPItemSalve
	extends Item
{
	private PMPSalve salve;
	
	public PMPItemSalve(PMPSalve salve) {
		this.salve = salve;
		setUnlocalizedName(this.salve.name());
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerItem(this, this.salve.name());
		OreDictionary.registerOre(this.salve.name(), this);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			int salveSetting = getSalveSetting();
			if (this.salve == PMPSalve.salveHealth) {
				if (playerIn.shouldHeal()) {
					float maxHealth = playerIn.getMaxHealth();
					float healAmount = maxHealth / 100.0F * salveSetting;
					playerIn.setHealth(playerIn.getHealth() + healAmount);
				} else {
					new ActionResult(EnumActionResult.PASS, itemStackIn);
				}
			} else {
				playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(this.salve.potionID), salveSetting * 20, 1));
			}
			itemStackIn.stackSize -= 1;
		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if (this.salve == PMPSalve.salveHealth) {
			list.add(/*"§8" +*/ I18n.translateToLocal("gui.effect") + ": " /*§9"*/ + String.format("%d", new Object[] { Integer.valueOf(getSalveSetting()) }) + I18n.translateToLocal("gui.healthHealed") /*+ "§r"*/);
		} else {
			list.add(/*"§8" +*/ I18n.translateToLocal("gui.effect") + ": " /*§9"*/ + I18n.translateToLocal("gui.duration") + String.format(" %d ", new Object[] { Integer.valueOf(getSalveSetting()) }) + I18n.translateToLocal("gui.seconds") /*+ "§r"*/);
		}
	}
	
	private int getSalveSetting() {
		switch (this.salve) {
		case salveFireResist: 
			return PlantMegaPack.settings.salveFireResist;
		case salveHealth: 
			return PlantMegaPack.settings.salveHealing;
		case salveNightVision: 
			return PlantMegaPack.settings.salveNightVision;
		case salveStrength: 
			return PlantMegaPack.settings.salveStrength;
		case salveSwiftness: 
			return PlantMegaPack.settings.salveSwiftness;
		case salveWaterBreathing: 
			return PlantMegaPack.settings.salveWaterBreathing;
		}
		return 30;
	}
}

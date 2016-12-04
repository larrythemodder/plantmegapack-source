package plantmegapack.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockSapling;
import plantmegapack.core.PMPSettings;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPLogType;
import plantmegapack.object.PMPSapling;
import plantmegapack.plant.PMPPlantConStat;

public class PMPItemSapling
	extends ItemBlock
{
	private PMPBlockSapling blockSapling = null;
	
	public PMPItemSapling(Block block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName().substring(5));
		this.blockSapling = ((PMPBlockSapling)block);
		setMaxDamage(0);
	}
	
	public int getMetadata(int damageValue) {
		return damageValue;
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if (this.blockSapling == null) {
			return;
		}
		if (PlantMegaPack.settings.tooltipLatinName) {
			list.add(new String(/*"§7§o" +*/ I18n.translateToLocal(String.format("%s.snam", new Object[] { this.blockSapling.getUnlocalizedName() })) /*+ "§r"*/));
		}
		PMPSapling sapling = this.blockSapling.getSapling();
		list.add(new String(new StringBuilder()/*.append("§8")*/.append(I18n.translateToLocal("gui.logType")).append(": "/*§a"*/).toString()) + I18n.translateToLocal(sapling.logType.getLocalizedName()) /*+ "§r"*/);
		String trunkSizeKey = String.format("gui.trunk%d", new Object[] { Integer.valueOf(sapling.trunkSize) });
		list.add(new String(new StringBuilder()/*.append("§8")*/.append(I18n.translateToLocal("gui.trunkSize")).append(": "/*§2"*/).toString()) + I18n.translateToLocal(trunkSizeKey) /*+ "§r"*/);
		if (PlantMegaPack.settings.tooltipConservation) {
			list.add(/*"§8" +*/ I18n.translateToLocal("gui.conservation") + ": " + PMPPlantConStat.getStatusByID(sapling.conservationStatus).getLocalizedNameFormatted());
		}
		if (sapling.isFruitTree()) {
			list.add(new String(new StringBuilder()/*.append("§8")*/.append(I18n.translateToLocal("gui.fruit")).append(": "/*§6"*/).toString()) + sapling.food.getLocalizedName() /*+ "§r"*/);
		}
	}
}

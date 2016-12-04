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
}

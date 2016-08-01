package plantmegapack.block;

import net.minecraft.item.ItemStack;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodSlabDouble
	extends PMPBlockWoodSlab
{
	public PMPBlockWoodSlabDouble(PMPWood wood) {
		super(wood);
	}
	
	public PMPBlockWoodSlabDouble(PMPBamboo bamboo) {
		super(bamboo);
	}
	
	public boolean isDouble() {
		return true;
	}
	
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return null;
	}
}

package plantmegapack.block;

import net.minecraft.item.ItemStack;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodSlabHalf
	extends PMPBlockWoodSlab
{
	public PMPBlockWoodSlabHalf(PMPWood wood) {
		super(wood);
	}
	
	public PMPBlockWoodSlabHalf(PMPBamboo bamboo) {
		super(bamboo);
	}
	
	public boolean isDouble() {
		return false;
	}
	
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return null;
	}
}

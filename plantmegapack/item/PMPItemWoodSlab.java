package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import plantmegapack.block.PMPBlockWoodSlabDouble;
import plantmegapack.block.PMPBlockWoodSlabHalf;

public class PMPItemWoodSlab
	extends ItemSlab
{
	public PMPItemWoodSlab(Block block, PMPBlockWoodSlabHalf singleSlab, PMPBlockWoodSlabDouble doubleSlab) {
		super(block, singleSlab, doubleSlab);
	}
}

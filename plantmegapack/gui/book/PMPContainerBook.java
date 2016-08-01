package plantmegapack.gui.book;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PMPContainerBook
	extends Container
{
	public PMPContainerBook(World worldIn, BlockPos pos, EntityPlayer player) {}
	
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
}

package plantmegapack.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.block.PMPBlockLeaves;

public class PMPItemLeaves
	extends ItemBlock
{
	private final PMPBlockLeaves leavesBlock;
	
	public PMPItemLeaves(Block block) {
		super(block);
		this.leavesBlock = ((PMPBlockLeaves)block);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack stack) {
		String name = this.leavesBlock.getVariantName(stack.getMetadata());
		if (name != null) {
			return "tile.leaves_" + name;
		}
		return "gui.error";
	}
	
	public int getMetadata(int damage) {
		return damage | 0x4;
	}
	
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int renderPass) {
		return this.leavesBlock.getRenderColor(stack.getMetadata());
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		this.leavesBlock.getSubBlocks(itemIn, tab, subItems);
	}
}

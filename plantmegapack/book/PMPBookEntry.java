package plantmegapack.book;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PMPBookEntry
{
	public Block block;
	public int blockMeta;
	public boolean coloredTexture;
	public String commonName;
	public String latinName;
	public String description;
	public String textureName;
	public int mapID;
	public int conStat;
	private boolean discovered;
	public ArrayList<ItemStack> items = new ArrayList();
	
	public PMPBookEntry(Block block, int blockMeta, boolean coloredTexture, String commonName, String latinName, String description, String textureName, int mapID, int conservationStatus) {
		this.block = block;
		this.blockMeta = blockMeta;
		this.coloredTexture = coloredTexture;
		this.commonName = commonName;
		this.latinName = latinName;
		this.description = description;
		this.textureName = textureName;
		this.mapID = mapID;
		this.conStat = conservationStatus;
		this.discovered = false;
	}
	
	public void addEntryItem(ItemStack stack) {
		this.items.add(stack);
	}
	
	public String getGuiKey() {
		return String.format("%s:%d", new Object[] { this.block.getUnlocalizedName().substring(5), Integer.valueOf(this.blockMeta) });
	}
	
	public boolean isDiscovered() {
		return this.discovered;
	}
	
	public void resetDiscovered() {
		this.discovered = false;
	}
	
	public void setDiscovered() {
		this.discovered = true;
	}
}

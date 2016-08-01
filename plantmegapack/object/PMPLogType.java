package plantmegapack.object;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;

public enum PMPLogType {
	acacia("log2", 4),
	birch("log", 2),
	darkOak("log2", 5),
	jungle("log", 3),
	oak("log", 0),
	spruce("log", 1),
	woodfruitgray("woodfruitgray", -1);
	
	public final String logBlockName;
	public final int logMeta;
	
	private PMPLogType(String logBlockName, int logMeta) {
		this.logBlockName = logBlockName;
		this.logMeta = logMeta;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("log." + name());
	}
	
	public Block getLogBlock() {
		if (this.logBlockName.matches("log")) {
			return Blocks.LOG;
		}
		if (this.logBlockName.matches("log2")) {
			return Blocks.LOG2;
		}
		return PlantMegaPack.blocks.getWoodBlockFromName(name());
	}
}

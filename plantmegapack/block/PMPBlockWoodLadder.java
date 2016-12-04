package plantmegapack.block;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.SoundType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodLadder
	extends BlockLadder
{
	public PMPBlockWoodLadder(PMPWood wood) {
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodLadder(PMPBamboo bamboo) {
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Ladder";
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		setUnlocalizedName(blockName + suffix);
		setHardness(0.4F);
		setSoundType(SoundType.WOOD);
		GameRegistry.registerBlock(this, blockName + suffix);
		OreDictionary.registerOre(oreDictName + suffix, this);
		OreDictionary.registerOre("ladderWood", this);
	}
}

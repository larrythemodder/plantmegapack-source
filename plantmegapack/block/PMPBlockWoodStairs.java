package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodStairs
	extends BlockStairs
{
	public PMPBlockWoodStairs(PMPWood wood) {
		super(Blocks.OAK_STAIRS.getDefaultState());
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodStairs(PMPBamboo bamboo) {
		super(Blocks.OAK_STAIRS.getDefaultState());
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Stairs";
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(blockName + suffix);
		setHardness(1.2F);
		this.useNeighborBrightness = true;
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerBlock(this, blockName + suffix);
		OreDictionary.registerOre(oreDictName + suffix, this);
		OreDictionary.registerOre("stairWood");
	}
}

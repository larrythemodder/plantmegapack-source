package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodPlanks
	extends Block
{
	public PMPBlockWoodPlanks(PMPWood wood) {
		super(Material.WOOD);
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodPlanks(PMPBamboo bamboo) {
		super(Material.WOOD);
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Planks";
		setHardness(1.2F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(blockName + suffix);
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerBlock(this, blockName + suffix);
		OreDictionary.registerOre(blockName + suffix, this);
		OreDictionary.registerOre("plankWood");
	}
}

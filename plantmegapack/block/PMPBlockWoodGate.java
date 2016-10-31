package plantmegapack.block;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodGate
	extends BlockFenceGate
{
	public PMPBlockWoodGate(PMPWood wood) {
		super(EnumType.OAK);
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodGate(PMPBamboo bamboo) {
		super(EnumType.OAK);
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Gate";
		setUnlocalizedName(blockName + suffix);
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		setHardness(1.2F);
		setSoundType(SoundType.WOOD);
		GameRegistry.registerBlock(this, blockName + suffix);
		OreDictionary.registerOre(oreDictName + suffix, this);
		OreDictionary.registerOre("fencegateWood");
	}
}

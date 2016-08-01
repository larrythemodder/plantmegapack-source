package plantmegapack.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.object.PMPSeed;

public class PMPItemSeed
	extends ItemSeeds
{
	private PMPSeed seed;
	
	public PMPItemSeed(PMPSeed seed) {
		super(PlantMegaPack.blocks.getPlantBlockByName(seed.name().replace("seed", "crop")), Blocks.FARMLAND);
		this.seed = seed;
		setUnlocalizedName(this.seed.name());
		setCreativeTab(CreativeTabs.MATERIALS);
		GameRegistry.registerItem(this, this.seed.name());
		OreDictionary.registerOre(this.seed.oreDictName, this);
	}
	
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Crop;
	}
}

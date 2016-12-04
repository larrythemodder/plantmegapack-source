package plantmegapack.block;

import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodFence
	extends BlockFence
{
	public PMPBlockWoodFence(PMPWood wood) {
		super(Material.WOOD, EnumType.OAK.getMapColor());
		init(wood.name(), wood.oreDictName);
		setSoundType(SoundType.WOOD);
	}
	
	public PMPBlockWoodFence(PMPBamboo bamboo) {
		super(Material.WOOD, EnumType.OAK.getMapColor());
		init(bamboo.name(), bamboo.oreDictName);
		setSoundType(SoundType.WOOD);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Fence";
		setUnlocalizedName(blockName + suffix);
		setCreativeTab(PlantMegaPack.tabItem);
		setHardness(1.2F);
		GameRegistry.registerBlock(this, blockName + suffix);
		OreDictionary.registerOre(oreDictName + suffix, this);
		OreDictionary.registerOre("fenceWood", this);
	}
	
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
}

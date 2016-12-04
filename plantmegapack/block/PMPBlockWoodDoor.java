package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoor.EnumDoorHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.item.PMPItemWoodDoor;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodDoor
	extends BlockDoor
{
	public PMPBlockWoodDoor(PMPWood wood) {
		super(Material.WOOD);
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodDoor(PMPBamboo bamboo) {
		super(Material.WOOD);
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = "Door";
		setHardness(1.2F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(blockName + suffix);
		setCreativeTab(PlantMegaPack.tabItem);
		GameRegistry.registerBlock(this, PMPItemWoodDoor.class, blockName + suffix);
		OreDictionary.registerOre(oreDictName + suffix, this);
	}
	
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(getDoorItem(), 1, 0);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(BlockDoor.HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : getDoorItem();
	}
	
	private Item getDoorItem() {
		return Item.getItemFromBlock(this);
	}
}

package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public abstract class PMPBlockWoodSlab
	extends BlockSlab
{
	public static final PropertyBool VARIANT = PropertyBool.create("variant");
	private static final int HALF_META_BIT = 8;
	private boolean bambooBlock;
	
	public PMPBlockWoodSlab(PMPWood wood) {
		super(Material.WOOD);
		this.bambooBlock = false;
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodSlab(PMPBamboo bamboo) {
		super(Material.WOOD);
		this.bambooBlock = true;
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		String suffix = isDouble() ? "DoubleSlab" : "Slab";
		setUnlocalizedName(blockName + suffix);
		this.useNeighborBrightness = (!isDouble());
		setHardness(1.2F);
		setResistance(5.0F);
		setSoundType(SoundType.WOOD);
		OreDictionary.registerOre("slabWood", this);
		setDefaultState(getDefaultState().withProperty(VARIANT, Boolean.valueOf(false)).withProperty(BlockSlab.HALF, isDouble() ? BlockSlab.EnumBlockHalf.TOP : BlockSlab.EnumBlockHalf.BOTTOM));
		if (!isDouble()) {
			setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		}
	}
	
	public IBlockState getStateFromMeta(int meta) {
		IBlockState blockState = getDefaultState();
		blockState = blockState.withProperty(VARIANT, Boolean.valueOf(false));
		if (!isDouble()) {
			BlockSlab.EnumBlockHalf value = BlockSlab.EnumBlockHalf.BOTTOM;
			if ((meta & 0x8) != 0) {
				value = BlockSlab.EnumBlockHalf.TOP;
			}
			blockState = blockState.withProperty(BlockSlab.HALF, value);
		}
		return blockState;
	}
	
	public int getMetaFromState(IBlockState state) {
		if (isDouble()) {
			return 0;
		}
		if ((BlockSlab.EnumBlockHalf)state.getValue(BlockSlab.HALF) == BlockSlab.EnumBlockHalf.TOP) {
			return 8;
		}
		return 0;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT, BlockSlab.HALF });
	}
	
	public final Item getItemDropped(IBlockState blockState, Random random, int unused) {
		return getSlabItem(getUnlocalizedName().substring(5));
	}
	
	@SideOnly(Side.CLIENT)
	public final ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(getSlabItem(getUnlocalizedName().substring(5)), 1, 0);
	}
	
	private Item getSlabItem(String blockName) {
		String prefix = "Slab";
		if (this.bambooBlock) {
			return Item.getItemFromBlock(PlantMegaPack.blocks.getBambooSlab(blockName + prefix));
		}
		return Item.getItemFromBlock(PlantMegaPack.blocks.getWoodSlab(blockName + prefix));
	}
	
	public String getUnlocalizedName(int meta) {
		return getUnlocalizedName();
	}
	
	public IProperty getVariantProperty() {
		return VARIANT;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		if (isDouble()) {
			return super.shouldSideBeRendered(blockState, worldIn, pos, side);
		}
		if ((side != EnumFacing.UP) && (side != EnumFacing.DOWN) && (!super.shouldSideBeRendered(blockState, worldIn, pos, side))) {
			return false;
		}
		return super.shouldSideBeRendered(blockState, worldIn, pos, side);
	}
	
	@SideOnly(Side.CLIENT)
	private boolean isSlabBlock(Block blockIn) {
		return (blockIn != null) && ((blockIn instanceof PMPBlockWoodSlab));
	}
}

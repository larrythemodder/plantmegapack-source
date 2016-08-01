package plantmegapack.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPItems;
import plantmegapack.item.PMPItemLeaves;
import plantmegapack.object.PMPLeaves;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPTab;

public class PMPBlockLeaves
	extends BlockLeaves
{
	public static final PropertyInteger VARIANT = PropertyInteger.create("variant", 0, 3);
	private PMPLeaves leaves;
	
	public PMPBlockLeaves(PMPLeaves leaves) {
		this.leaves = leaves;
		setUnlocalizedName(this.leaves.name());
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Integer.valueOf(0)).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(true)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)));
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerBlock(this, PMPItemLeaves.class, this.leaves.name());
		OreDictionary.registerOre(this.leaves.oreDictName, this);
	}
	
	@SideOnly(Side.CLIENT)
	public void init() {
		setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
	}
	
	public String getVariantName(int itemDamage) {
		PMPSapling sapling = getSaplingFromVariant(itemDamage);
		if (sapling != null) {
			return sapling.name().toLowerCase().substring(7);
		}
		return null;
	}
	
	private PMPSapling getSaplingFromVariant(int meta) {
		for (PMPSapling sapling : PMPSapling.values()) {
			if ((sapling.leaves == this.leaves) && (sapling.leafMeta == meta)) {
				return sapling;
			}
		}
		return null;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { BlockLeaves.CHECK_DECAY, BlockLeaves.DECAYABLE, VARIANT });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, Integer.valueOf(meta & 0x3)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf((meta & 0x4) == 0)).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf((meta & 0x8) > 0));
	}
	
	public int getMetaFromState(IBlockState state) {
		byte b0 = 0;
		int i = b0 | ((Integer)state.getValue(VARIANT)).intValue();
		if (!((Boolean)state.getValue(BlockLeaves.DECAYABLE)).booleanValue()) {
			i |= 0x4;
		}
		if (((Boolean)state.getValue(BlockLeaves.CHECK_DECAY)).booleanValue()) {
			i |= 0x8;
		}
		return i;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (PMPSapling sapling : PMPSapling.values()) {
			if (sapling.leaves == this.leaves) {
				list.add(new ItemStack(itemIn, 1, sapling.leafMeta));
			}
		}
	}
	
	protected ItemStack createStackedBlock(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1, ((Integer)state.getValue(VARIANT)).intValue());
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(PlantMegaPack.blocks.getSapling(getSaplingFromVariant(((Integer)state.getValue(VARIANT)).intValue())));
	}
	
	protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
		if (worldIn.rand.nextInt(chance) == 0) {
			for (PMPSapling sapling : PMPSapling.values()) {
				if ((sapling.leaves == this.leaves) && (sapling.leafMeta == ((Integer)state.getValue(VARIANT)).intValue())) {
					if (sapling.food != null) {
						Block.spawnAsEntity(worldIn, pos, new ItemStack(PlantMegaPack.items.getFoodItem(sapling.food), 1, 0));
					}
				}
			}
		}
	}
	
	public int damageDropped(IBlockState state) {
		return ((Integer)state.getValue(VARIANT)).intValue();
	}
	
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	protected int getSaplingDropChance(IBlockState state) {
		return super.getSaplingDropChance(state);
	}
	
	public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
		if ((!worldIn.isRemote) && (player.getHeldItemMainhand() != null) && (player.getHeldItemMainhand().getItem() == Items.SHEARS)) {
			Block.spawnAsEntity(worldIn, pos, new ItemStack(Item.getItemFromBlock(this), 1, ((Integer)state.getValue(VARIANT)).intValue()));
		} else {
			super.harvestBlock(worldIn, player, pos, state, te, stack);
		}
	}
	
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		IBlockState state = world.getBlockState(pos);
		return new ArrayList(Arrays.asList(new ItemStack[] { new ItemStack(this, 1, ((Integer)state.getValue(VARIANT)).intValue()) }));
	}
	
	@SideOnly(Side.CLIENT)
	public IBlockColor getBlockColor() {
		return/*new*/ new IBlockColor() {
			public int colorMultiplier(IBlockState state, IBlockAccess world, BlockPos pos, int tintIndex) {
				PMPBlockLeaves leavesBlock = (PMPBlockLeaves)world.getBlockState(pos).getBlock();
				if (leavesBlock != null) {
					return leavesBlock.getRenderColor(((Integer)state.getValue(PMPBlockLeaves.VARIANT)).intValue());
				}
				return 16777215;
			}
		};
	}
	
	@SideOnly(Side.CLIENT)
	public IItemColor getItemColor() {
		return/*new*/ new IItemColor() {
			public int getColorFromItemstack(ItemStack stack, int tintIndex) {
				PMPBlockLeaves leavesBlock = (PMPBlockLeaves)Block.getBlockFromItem(stack.getItem());
				if (leavesBlock != null) {
					return leavesBlock.getRenderColor(stack.getMetadata());
				}
				return 16777215;
			}
		};
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int variantID) {
		return getSaplingFromVariant(variantID).renderColor;
	}
}

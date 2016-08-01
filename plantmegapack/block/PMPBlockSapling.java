package plantmegapack.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.data.PMPDataTree;
import plantmegapack.item.PMPItemSapling;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPTab;
import plantmegapack.tree.PMPTree;

public class PMPBlockSapling
	extends BlockBush
	implements IGrowable
{
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	private PMPDataTree treeData;
	private PMPTree tree;
	
	public PMPBlockSapling(PMPDataTree treeData, PMPTree tree) {
		this.treeData = treeData;
		this.tree = tree;
		setUnlocalizedName(this.treeData.sapling.name());
		setDefaultState(this.blockState.getBaseState().withProperty(STAGE, Integer.valueOf(0)));
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		setSoundType(SoundType.PLANT);
		GameRegistry.registerBlock(this, PMPItemSapling.class, this.treeData.sapling.name());
		OreDictionary.registerOre(this.treeData.sapling.oreDictName, this);
	}
	
	public PMPTree getTree() {
		return this.tree;
	}
	
	public PMPDataTree getTreeData() {
		return this.treeData;
	}
	
	public PMPSapling getSapling() {
		return this.treeData.sapling;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { STAGE });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		if ((meta < 0) || (meta > 1)) {
			meta = 0;
		}
		return getDefaultState().withProperty(STAGE, Integer.valueOf(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((Integer)state.getValue(STAGE)).intValue();
	}
	
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			super.updateTick(worldIn, pos, state, rand);
			if ((worldIn.getLightFromNeighbors(pos.up()) >= 9) && (rand.nextInt(7) == 0)) {
				grow(worldIn, pos, state, rand);
			}
		}
	}
	
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return true;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return worldIn.rand.nextFloat() < 0.45D;
	}
	
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		grow(worldIn, pos, state, rand);
	}
	
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		return EnumPlantType.Plains;
	}
	
	protected void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (((Integer)state.getValue(STAGE)).intValue() == 0) {
			worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
		} else {
			generateTree(worldIn, pos, state, rand);
		}
	}
	
	private void generateTree(World worldIn, BlockPos pos, IBlockState state, Random random) {
		int i = 0;int j = 0;
		boolean canGenerate = false;
		IBlockState stateAir = Blocks.AIR.getDefaultState();
		if (this.treeData.sapling.trunkSize == 2) {
			label143:
			for (i = 0; i >= -1; i--) {
				for (j = 0; j >= -1; j--) {
					if ((isSameBlockAt(worldIn, pos.add(i, 0, j))) && (isSameBlockAt(worldIn, pos.add(i + 1, 0, j))) && (isSameBlockAt(worldIn, pos.add(i, 0, j + 1))) && (isSameBlockAt(worldIn, pos.add(i + 1, 0, j + 1)))) {
						canGenerate = true;
						break label143;
					}
				}
			}
			if (!canGenerate) {
				return;
			}
			worldIn.setBlockState(pos.add(i, 0, j), stateAir, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j), stateAir, 4);
			worldIn.setBlockState(pos.add(i, 0, j + 1), stateAir, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j + 1), stateAir, 4);
			this.tree.generate(worldIn, random, pos.add(i, 0, j));
			return;
		}
		worldIn.setBlockState(pos, stateAir, 4);
		this.tree.generate(worldIn, random, pos);
	}
	
	private boolean isSameBlockAt(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		return state.getBlock() == this;
	}
}

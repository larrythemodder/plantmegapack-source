package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.object.PMPPlanter;
import plantmegapack.object.PMPPlanterType;
import plantmegapack.object.PMPTab;

public class PMPBlockPlanter
	extends Block
{
	private PMPPlanter planter;
	public static final PropertyBool NORTH = PropertyBool.create("north");
	public static final PropertyBool EAST = PropertyBool.create("east");
	public static final PropertyBool SOUTH = PropertyBool.create("south");
	public static final PropertyBool WEST = PropertyBool.create("west");
	
	public PMPBlockPlanter(PMPPlanter planter) {
		super(planter.planterType.material);
		this.planter = planter;
		setDefaultState(this.blockState.getBaseState().withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false)).withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false)));
		setUnlocalizedName(this.planter.name());
		setCreativeTab(PlantMegaPack.tabItem);
		setTickRandomly(false);
		if (this.planter.planterType.material == Material.IRON) {
			setHardness(1.75F);
			setResistance(8.0F);
			setSoundType(SoundType.METAL);
		} else if (this.planter.planterType.material == Material.ROCK) {
			setHardness(1.5F);
			setResistance(7.0F);
			setSoundType(SoundType.STONE);
		} else if (this.planter.planterType.material == Material.WOOD) {
			setHardness(1.25F);
			setResistance(6.0F);
			setSoundType(SoundType.WOOD);
		} else if (this.planter.planterType.material == Material.CLAY) {
			setHardness(1.0F);
			setResistance(5.0F);
			setSoundType(SoundType.STONE);
		} else {
			setHardness(1.0F);
			setResistance(4.0F);
			setSoundType(SoundType.STONE);
		}
		this.useNeighborBrightness = true;
		this.enableStats = false;
		GameRegistry.registerBlock(this, this.planter.name());
		OreDictionary.registerOre(this.planter.oreDictName, this);
	}
	
	public boolean isVisuallyOpaque() {
		return true;
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { NORTH, EAST, WEST, SOUTH });
	}
	
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return state.withProperty(NORTH, Boolean.valueOf(canConnectTo(worldIn, pos.north()))).withProperty(EAST, Boolean.valueOf(canConnectTo(worldIn, pos.east()))).withProperty(SOUTH, Boolean.valueOf(canConnectTo(worldIn, pos.south()))).withProperty(WEST, Boolean.valueOf(canConnectTo(worldIn, pos.west())));
	}
	
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
	
	public boolean canSustainPlant(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing direction, IPlantable plantable) {
		return (direction == EnumFacing.UP) && (plantable.getPlantType(worldIn, pos) != EnumPlantType.Water);
	}
	
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return this.planter.planterType != PMPPlanterType.squareMetal;
	}
	
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	public boolean isFullCube(IBlockState state) {
		return this.planter.planterType != PMPPlanterType.squareMetal;
	}
	
	public boolean isFertile(World worldIn, BlockPos pos) {
		return true;
	}
	
	private boolean canConnectTo(IBlockAccess worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		return block == this;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return true;
	}
}

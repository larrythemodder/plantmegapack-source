package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockLog.EnumAxis;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.object.PMPBamboo;
import plantmegapack.object.PMPTab;
import plantmegapack.object.PMPWood;

public class PMPBlockWoodLog extends BlockLog {
	public static final PropertyEnum<BlockLog.EnumAxis> LOG_AXIS = PropertyEnum.create("axis", BlockLog.EnumAxis.class);
	private boolean bambooBlock;
	
	public PMPBlockWoodLog(PMPWood wood) {
		this.bambooBlock = false;
		init(wood.name(), wood.oreDictName);
	}
	
	public PMPBlockWoodLog(PMPBamboo bamboo) {
		this.bambooBlock = true;
		init(bamboo.name(), bamboo.oreDictName);
	}
	
	private void init(String blockName, String oreDictName) {
		setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, BlockLog.EnumAxis.Y));
		setHardness(1.2F);
		setSoundType(SoundType.WOOD);
		setUnlocalizedName(blockName + "Block");
		setCreativeTab(PlantMegaPack.creativeTabs.getTab(PMPTab.item));
		GameRegistry.registerBlock(this, blockName + "Block");
		OreDictionary.registerOre(oreDictName + "Log", this);
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(EnumFacing.getFront(meta).getAxis()));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal();
	}
	
	public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(LOG_AXIS, BlockLog.EnumAxis.fromFacingAxis(facing.getAxis()));
	}
	
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return !this.bambooBlock;
	}
}

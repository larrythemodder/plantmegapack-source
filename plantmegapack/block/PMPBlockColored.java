package plantmegapack.block;

import java.util.List;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.object.PMPColor;

public class PMPBlockColored
	extends PMPBlockBase
{
	public static final PropertyEnum<PMPColor> VARIANT = PropertyEnum.create("variant", PMPColor.class);
	
	public PMPBlockColored(PMPDataPlant plantData) {
		super(plantData);
		setDefaultState(getDefaultState().withProperty(VARIANT, PMPColor.red));
	}
	
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { VARIANT });
	}
	
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, PMPColor.getColorFromID(meta));
	}
	
	public int getMetaFromState(IBlockState state) {
		return ((PMPColor)state.getValue(VARIANT)).ID;
	}
	
	public int damageDropped(IBlockState state) {
		return ((PMPColor)state.getValue(VARIANT)).ID;
	}
	
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World worldIn, BlockPos pos, EntityPlayer player) {
		return new ItemStack(this, 1, ((PMPColor)worldIn.getBlockState(pos).getValue(VARIANT)).ID);
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (PMPColor color : PMPColor.values()) {
			subItems.add(new ItemStack(itemIn, 1, color.ID));
		}
	}
}

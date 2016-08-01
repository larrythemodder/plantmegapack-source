package plantmegapack.block;

import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.core.IPMPPlant;
import plantmegapack.data.PMPDataPlant;

public class PMPBlockGroundcover
	extends PMPBlockBase
	implements IPMPPlant
{
	protected static final AxisAlignedBB AABB_FLAT = new AxisAlignedBB(0.05D, 0.0D, 0.05D, 0.95D, 0.0625D, 0.95D);
	
	public PMPBlockGroundcover(PMPDataPlant plantData) {
		super(plantData);
	}
	
	@SideOnly(Side.CLIENT)
	public EnumOffsetType getOffsetType() {
		return EnumOffsetType.NONE;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return AABB_FLAT;
	}
}

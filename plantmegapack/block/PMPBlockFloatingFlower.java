package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;

public class PMPBlockFloatingFlower
	extends PMPBlockColored
	implements IPMPPlant
{
	protected static final AxisAlignedBB AABB_FLOWER = new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.45D, 0.85D);
	
	public PMPBlockFloatingFlower(PMPDataPlant plantData) {
		super(plantData);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		return (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.AIR) && (PMPBlockCore.isFullWaterBlock(worldIn, pos.down()));
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this) {
			return PMPBlockCore.isFullWaterBlock(worldIn, pos.down());
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return Block.EnumOffsetType.NONE;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB_FLOWER;
	}
}

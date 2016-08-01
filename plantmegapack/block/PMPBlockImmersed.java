package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPBlockImmersed
	extends PMPBlockBase
	implements IPMPPlant
{
	public PMPBlockImmersed(PMPDataPlant plantData) {
		super(plantData);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		Block blockSoil = worldIn.getBlockState(pos.down(2)).getBlock();
		return (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.AIR) && (PMPBlockCore.isFullWaterBlock(worldIn, pos.down())) && (PMPPlantSoilType.canPlantGrowOnBlock(blockSoil, worldIn.getBlockState(pos.down(2)), PMPPlantSoilType.aqua));
	}
}

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

public class PMPBlockImmersedDouble
	extends PMPBlockDouble
	implements IPMPPlant
{
	public PMPBlockImmersedDouble(PMPDataPlant plantData) {
		super(plantData);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		Block blockSoil = worldIn.getBlockState(pos.down(2)).getBlock();
		return (blockAbove.getMaterial(worldIn.getBlockState(pos.up())) == Material.AIR) && (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.AIR) && (PMPBlockCore.isFullWaterBlock(worldIn, pos.down())) && (PMPPlantSoilType.canPlantGrowOnBlock(blockSoil, worldIn.getBlockState(pos.down(2)), PMPPlantSoilType.aqua));
	}
}

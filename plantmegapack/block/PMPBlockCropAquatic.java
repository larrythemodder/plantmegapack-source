package plantmegapack.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPBlockCropAquatic
	extends PMPBlockCrop
{
	public PMPBlockCropAquatic(PMPDataPlant plantData) {
		super(plantData);
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		Block blockSpawn = worldIn.getBlockState(pos).getBlock();
		Block blockSoil = worldIn.getBlockState(pos.down(2)).getBlock();
		return (blockSpawn.getMaterial(worldIn.getBlockState(pos)) == Material.AIR) && (PMPBlockCore.isFullWaterBlock(worldIn, pos.down())) && (PMPPlantSoilType.canPlantGrowOnBlock(blockSoil, worldIn.getBlockState(pos.down(2)), PMPPlantSoilType.aqua));
	}
	
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if (state.getBlock() == this) {
			return canPlaceBlockOn(worldIn.getBlockState(pos.down()));
		}
		return super.canBlockStay(worldIn, pos, state);
	}
	
	protected boolean canPlaceBlockOn(IBlockState state) {
		return state.getBlock().getMaterial(state) == Material.WATER;
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, pos.getX() + 0.5F, pos.getY() + 0.45F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
		worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, pos.getX() + 0.5F, pos.getY() + 0.55F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	protected Item getSeed() {
		return Item.getItemFromBlock(this);
	}
}

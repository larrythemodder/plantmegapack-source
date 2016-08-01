package plantmegapack.worldgen;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import plantmegapack.PlantMegaPack;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.habitat.PMPBiomeType;
import plantmegapack.habitat.PMPHabitat;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPWorldgenSpawn
{
	public static final int BASE_SPAWN_HEIGHT = 64;
	public static final int CHUNK_SIZE = 16;
	public static final int MAX_CHUNK_PASSES = 64;
	public Biome biome;
	public PMPBiomeType biomeType;
	public PMPDataBiome biomeData;
	public PMPHabitat habitat;
	public BlockPos spawnPos;
	public EnumFacing spawnFacing;
	public boolean isTreeSpawn;
	
	public PMPWorldgenSpawn() {
		resetBiomeData();
		resetSpawnData();
	}
	
	private void resetBiomeData() {
		this.biome = null;
		this.biomeType = null;
		this.biomeData = null;
	}
	
	private void resetSpawnData() {
		this.habitat = null;
		this.spawnPos = null;
		this.spawnFacing = null;
		this.isTreeSpawn = false;
	}
	
	public boolean initBiomeInfo(World worldIn, BlockPos posInitial) {
		this.biome = worldIn.getBiomeForCoordsBody(posInitial);
		if (this.biome == null) {
			return false;
		}
		this.biomeType = PMPBiomeType.getBiomeType(this.biome);
		if (this.biomeType == null) {
			return false;
		}
		this.biomeData = PlantMegaPack.worldgenProfile.getBiomeData(this.biomeType);
		if (this.biomeData == null) {
			return false;
		}
		return true;
	}
	
	public boolean initSpawnLocation(World worldIn, BlockPos posInitial) {
		this.spawnPos = worldIn.getTopSolidOrLiquidBlock(posInitial);
		this.habitat = getHabitatFromPosition(worldIn, this.spawnPos, this.biomeType);
		if (this.habitat == null) {
			return false;
		}
		if ((this.habitat == PMPHabitat.frfl) || (this.habitat == PMPHabitat.mafl) || (this.habitat == PMPHabitat.frmr) || (this.habitat == PMPHabitat.mamr)) {
			this.spawnPos = this.spawnPos.up(1);
		}
		return true;
	}
	
	public PMPHabitat getHabitatFromPosition(World worldIn, BlockPos pos, PMPBiomeType biomeType) {
		if (biomeType == null) {
			return null;
		}
		Block blockSoil = worldIn.getBlockState(this.spawnPos.down()).getBlock();
		Block blockSpawn = worldIn.getBlockState(this.spawnPos).getBlock();
		Block blockAbove = worldIn.getBlockState(this.spawnPos.up()).getBlock();
		
		this.isTreeSpawn = false;
		if (blockSpawn.getMaterial(worldIn.getBlockState(this.spawnPos)) == Material.WATER) {
			if (blockAbove.getMaterial(worldIn.getBlockState(this.spawnPos.up())) == Material.WATER) {
				return biomeType == PMPBiomeType.ocea ? PMPHabitat.madp : PMPHabitat.frdp;
			}
			if (blockAbove.getMaterial(worldIn.getBlockState(this.spawnPos.up())) == Material.AIR) {
				if (PMPWorldgenHelper.isSurroundedByWater(worldIn, pos)) {
					return biomeType == PMPBiomeType.ocea ? PMPHabitat.mafl : PMPHabitat.frfl;
				}
				if (!PMPPlantSoilType.canPlantGrowOnBlock(blockSoil, worldIn.getBlockState(this.spawnPos.down()), PMPPlantSoilType.aqua)) {
					return null;
				}
				return biomeType == PMPBiomeType.ocea ? PMPHabitat.mamr : PMPHabitat.frmr;
			}
			return null;
		}
		if (blockSpawn.getMaterial(worldIn.getBlockState(this.spawnPos)) == Material.LEAVES) {
			if ((blockSoil.isWood(worldIn, this.spawnPos.down())) || (blockSoil.getMaterial(worldIn.getBlockState(this.spawnPos.down())) == Material.LEAVES)) {
				return null;
			}
		}
		if (blockSoil.isWood(worldIn, this.spawnPos.down())) {
			if (blockSpawn.getMaterial(worldIn.getBlockState(this.spawnPos)) == Material.LEAVES) {
				EnumFacing direction = adjustForAdjacentSoilBlock(worldIn);
				if (direction != null) {
					this.spawnPos = this.spawnPos.offset(direction);
					if (PMPWorldgenHelper.isBlockReplaceable(worldIn, this.spawnPos)) {
						if (!worldIn.canBlockSeeSky(pos)) {
							return PMPHabitat.shad;
						}
						return PMPHabitat.open;
					}
					return null;
				}
				return null;
			}
			return null;
		}
		if (blockSpawn.getMaterial(worldIn.getBlockState(this.spawnPos)) == Material.PLANTS) {
			if (blockAbove.getMaterial(worldIn.getBlockState(this.spawnPos.up())) != Material.AIR) {
				return null;
			}
		}
		if (!worldIn.canBlockSeeSky(pos)) {
			return PMPHabitat.shad;
		}
		this.isTreeSpawn = true;
		if (PMPWorldgenHelper.isAdjacentToBlockMaterialDiagonal(worldIn, pos.down(), Material.WATER)) {
			return PMPHabitat.wedg;
		}
		if (PMPWorldgenHelper.isGroundSloped(worldIn, pos, 2)) {
			return PMPHabitat.slop;
		}
		if ((this.biome != null) && (PMPWorldgenHelper.isAdjacentToDifferentBiome(this.biome, worldIn, pos, 2))) {
			return PMPHabitat.bedg;
		}
		return PMPHabitat.open;
	}
	
	private EnumFacing adjustForAdjacentSoilBlock(World worldIn) {
		BlockPos posGround = this.spawnPos.down();
		if (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(posGround.north()).getBlock(), worldIn.getBlockState(posGround.north()), PMPPlantSoilType.norm)) {
			return EnumFacing.NORTH;
		}
		if (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(posGround.south()).getBlock(), worldIn.getBlockState(posGround.south()), PMPPlantSoilType.norm)) {
			return EnumFacing.SOUTH;
		}
		if (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(posGround.west()).getBlock(), worldIn.getBlockState(posGround.west()), PMPPlantSoilType.norm)) {
			return EnumFacing.WEST;
		}
		if (PMPPlantSoilType.canPlantGrowOnBlock(worldIn.getBlockState(posGround.east()).getBlock(), worldIn.getBlockState(posGround.east()), PMPPlantSoilType.norm)) {
			return EnumFacing.EAST;
		}
		return null;
	}
	
	public boolean initEpiphyteSpawnLocation(World worldIn, Random random, BlockPos posInitial) {
		this.habitat = null;
		this.spawnPos = worldIn.getTopSolidOrLiquidBlock(posInitial);
		this.habitat = locateEpiphyteSpawnLocation(worldIn, random, this.spawnPos);
		return this.habitat == PMPHabitat.epip;
	}
	
	private PMPHabitat locateEpiphyteSpawnLocation(World worldIn, Random random, BlockPos posInitial) {
		BlockPos posFind = new BlockPos(posInitial);
		EnumFacing facing = null;
		
		int count = 0;
		while ((facing == null) && (count < 3)) {
			facing = PMPWorldgenHelper.isAdjacentToLog(worldIn, posFind);
			if (facing == null) {
				posFind = posInitial.offset(EnumFacing.SOUTH);
				Material material = worldIn.getBlockState(posFind).getBlock().getMaterial(worldIn.getBlockState(posFind));
				if ((material == Material.AIR) || (material == Material.VINE)) {
					facing = PMPWorldgenHelper.isAdjacentToLog(worldIn, posFind);
				}
			}
			if (facing == null) {
				posFind = posInitial.offset(EnumFacing.EAST);
				Material material = worldIn.getBlockState(posFind).getBlock().getMaterial(worldIn.getBlockState(posFind));
				if ((material == Material.AIR) || (material == Material.VINE)) {
					facing = PMPWorldgenHelper.isAdjacentToLog(worldIn, posFind);
				}
			}
			if (facing == null) {
				posFind = posInitial.offset(EnumFacing.WEST);
				Material material = worldIn.getBlockState(posFind).getBlock().getMaterial(worldIn.getBlockState(posFind));
				if ((material == Material.AIR) || (material == Material.VINE)) {
					facing = PMPWorldgenHelper.isAdjacentToLog(worldIn, posFind);
				}
			}
			if (facing == null) {
				posFind = posInitial.offset(EnumFacing.NORTH);
				Material material = worldIn.getBlockState(posFind).getBlock().getMaterial(worldIn.getBlockState(posFind));
				if ((material == Material.AIR) || (material == Material.VINE)) {
					facing = PMPWorldgenHelper.isAdjacentToLog(worldIn, posFind);
				}
			}
			if (facing == null) {
				count++;
				posFind = new BlockPos(posInitial.up(count));
			}
		}
		if (facing == null) {
			return null;
		}
		ArrayList<BlockPos> locations = new ArrayList();
		BlockPos posCur = new BlockPos(posFind);
		locations.add(posCur);
		boolean keepGoing;
		do
		{
			posCur = posCur.up();
			keepGoing = worldIn.getBlockState(posCur.offset(facing)).getBlock().isWood(worldIn, posCur.offset(facing));
			if ((keepGoing) && (PMPWorldgenHelper.canEpiphyteSpawnAtBlock(worldIn.getBlockState(posCur).getBlock(), worldIn.getBlockState(posCur.offset(facing))))) {
				locations.add(posCur);
			}
		} while (keepGoing);
		this.spawnPos = ((BlockPos)locations.get(random.nextInt(locations.size())));
		this.spawnFacing = facing;
		return PMPHabitat.epip;
	}
	
	public boolean initVineSpawnLocation(World worldIn, Random random, BlockPos posInitial) {
		this.habitat = null;
		this.spawnPos = getTopBlockIncludeLeaves(worldIn, posInitial).down();
		Block blockBelow = worldIn.getBlockState(this.spawnPos.down()).getBlock();
		while (blockBelow.getMaterial(worldIn.getBlockState(this.spawnPos.down())) == Material.LEAVES) {
			this.spawnPos = this.spawnPos.down();
			blockBelow = worldIn.getBlockState(this.spawnPos.down()).getBlock();
		}
		if (worldIn.getBlockState(this.spawnPos).getBlock().getMaterial(worldIn.getBlockState(this.spawnPos.down())) != Material.LEAVES) {
			return false;
		}
		EnumFacing facing = locateVineSpawnLocation(worldIn, this.spawnPos);
		if (facing == null) {
			return false;
		}
		this.spawnPos = this.spawnPos.offset(facing);
		this.spawnFacing = facing.getOpposite();
		this.habitat = PMPHabitat.vine;
		return true;
	}
	
	private EnumFacing locateVineSpawnLocation(World worldIn, BlockPos posInitial) {
		if (PMPWorldgenHelper.canVineSpawnAtBlock(worldIn, posInitial.south())) {
			return EnumFacing.SOUTH;
		}
		if (PMPWorldgenHelper.canVineSpawnAtBlock(worldIn, posInitial.east())) {
			return EnumFacing.EAST;
		}
		if (PMPWorldgenHelper.canVineSpawnAtBlock(worldIn, posInitial.west())) {
			return EnumFacing.WEST;
		}
		if (PMPWorldgenHelper.canVineSpawnAtBlock(worldIn, posInitial.north())) {
			return EnumFacing.NORTH;
		}
		return null;
	}
	
	private BlockPos getTopBlockIncludeLeaves(World worldIn, BlockPos pos) {
		Chunk chunk = worldIn.getChunkFromBlockCoords(pos);
		BlockPos blockpos1;
		for (BlockPos blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1) {
			blockpos1 = blockpos.down();
			Block block = chunk.getBlockState(blockpos1).getBlock();
			if (block.getMaterial(worldIn.getBlockState(blockpos1)).blocksMovement()) {
				return blockpos;
			}
		}
		return null;
	}
}

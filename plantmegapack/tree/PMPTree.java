package plantmegapack.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockLeaves;
import plantmegapack.core.PMPBlocks;
import plantmegapack.object.PMPLogType;
import plantmegapack.object.PMPSapling;

public abstract class PMPTree
{
	private PMPSapling sapling;
	protected int height;
	protected boolean canGrowOnSand;
	protected int spawnRadius;
	
	public PMPTree(PMPSapling sapling) {
		this.sapling = sapling;
		this.height = 6;
		this.canGrowOnSand = false;
		this.spawnRadius = 2;
	}
	
	public String getUnlocalizedName() {
		return this.sapling.name();
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("tile." + this.sapling.name() + ".name");
	}
	
	public String getLatinName() {
		return I18n.translateToLocal("tile." + this.sapling.name() + ".snam");
	}
	
	public boolean isFruitTree() {
		return this.sapling.isFruitTree();
	}
	
	public PMPSapling getSapling() {
		return this.sapling;
	}
	
	public int getSpawnRadius() {
		return this.spawnRadius;
	}
	
	public boolean canGrowOnSand() {
		return this.canGrowOnSand;
	}
	
	public abstract void generate(World paramWorld, Random paramRandom, BlockPos paramBlockPos);
	
	protected void generateFruitTree(World worldIn, Random random, BlockPos pos) {
		generateTrunk(worldIn, pos);
		
		BlockPos posStart = pos.offset(EnumFacing.UP, this.height - 1);
		
		IBlockState woodBlockState = getWoodBlockState();
		BlockPos posTemp = posStart.offset(EnumFacing.NORTH, 1);
		worldIn.setBlockState(posTemp, woodBlockState, 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.NORTH);
		posTemp = posStart.offset(EnumFacing.SOUTH, 1);
		worldIn.setBlockState(posTemp, woodBlockState, 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.SOUTH);
		posTemp = posStart.offset(EnumFacing.EAST, 1);
		worldIn.setBlockState(posTemp, woodBlockState, 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.EAST);
		posTemp = posStart.offset(EnumFacing.WEST, 1);
		worldIn.setBlockState(posTemp, woodBlockState, 3);
		spawnFruit(worldIn, posTemp.down(), EnumFacing.WEST);
		
		generateRandomLeafLayer(worldIn, random, posStart, 2, 1, 0, 92);
		generateRandomLeafLayer(worldIn, random, posStart.offset(EnumFacing.UP, 1), 2, 1, 0, 92);
		generateLeafLayer(worldIn, random, posStart.offset(EnumFacing.UP, 2), 1, 1, 0);
	}
	
	protected void spawnFruit(World worldIn, BlockPos pos, EnumFacing facing) {
		if (this.sapling.treeFruitType == null) {
			return;
		}
		Block block = PlantMegaPack.blocks.getTreeFruit(this.sapling);
		if (block != null) {
			IBlockState state = block.getDefaultState();
			if (state != null) {
				state = state.withProperty(BlockDirectional.FACING, facing.getOpposite());
				worldIn.setBlockState(pos, state, 3);
			}
		}
	}
	
	protected void generateTrunk(World worldIn, BlockPos pos) {
		for (int x = 0; x < this.height; x++) {
			worldIn.setBlockState(pos.offset(EnumFacing.UP, x), getWoodBlockState(), 3);
		}
	}
	
	protected void generateDoubleTrunk(World worldIn, BlockPos pos) {
		BlockPos posStart = pos;
		IBlockState woodBlockState = getWoodBlockState();
		for (int i = 0; i < this.height; i++) {
			worldIn.setBlockState(posStart, woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 1).offset(EnumFacing.SOUTH, 1), woodBlockState, 3);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
	}
	
	protected void generateQuadTrunk(World worldIn, BlockPos pos) {
		BlockPos posStart = pos;
		IBlockState woodBlockState = getWoodBlockState();
		for (int i = 0; i < this.height; i++) {
			worldIn.setBlockState(posStart, woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.NORTH, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.NORTH, 1).offset(EnumFacing.EAST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.WEST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.EAST, 2), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.WEST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 1), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 1).offset(EnumFacing.EAST, 2), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 2), woodBlockState, 3);
			worldIn.setBlockState(posStart.offset(EnumFacing.SOUTH, 2).offset(EnumFacing.EAST, 1), woodBlockState, 3);
			posStart = posStart.offset(EnumFacing.UP, 1);
		}
	}
	
	protected BlockPos offsetToBranchStartLocation(World worldIn, Random random, EnumFacing direction, BlockPos posStart) {
		BlockPos posNew = posStart;
		switch (direction) {
		case NORTH: 
			posNew = posStart.offset(EnumFacing.NORTH, 1);
			if (random.nextInt(100) < 50) {
				posNew = posNew.offset(EnumFacing.EAST, 1);
			}
			break;
		case SOUTH: 
			posNew = posStart.offset(EnumFacing.SOUTH, 2);
			if (random.nextInt(100) < 50) {
				posNew = posNew.offset(EnumFacing.EAST, 1);
			}
			break;
		case EAST: 
			posNew = posStart.offset(EnumFacing.EAST, 2);
			if (random.nextInt(100) < 50) {
				posNew = posNew.offset(EnumFacing.SOUTH, 1);
			}
			break;
		case WEST: 
			posNew = posStart.offset(EnumFacing.WEST, 1);
			if (random.nextInt(100) < 50) {
				posNew = posNew.offset(EnumFacing.SOUTH, 1);
			}
			break;
		default:
			break;
		}
		return posNew;
	}
	
	protected void generateAngledBranch(World worldIn, BlockPos pos, EnumFacing direction, int nodes) {
		for (int i = 1; i <= nodes; i++) {
			worldIn.setBlockState(pos.offset(EnumFacing.UP, i).offset(direction, i), getWoodBlockState(), 3);
		}
	}
	
	protected void generateAngledBranchWithLeaves(World worldIn, BlockPos pos, EnumFacing direction, int nodes) {
		for (int i = 1; i <= nodes; i++) {
			BlockPos posStart = pos.offset(EnumFacing.UP, i).offset(direction, i);
			worldIn.setBlockState(posStart, getWoodBlockState(), 3);
			spawnLeafBlock(worldIn, posStart.offset(EnumFacing.NORTH, 1));
			spawnLeafBlock(worldIn, posStart.offset(EnumFacing.SOUTH, 1));
			spawnLeafBlock(worldIn, posStart.offset(EnumFacing.EAST, 1));
			spawnLeafBlock(worldIn, posStart.offset(EnumFacing.WEST, 1));
		}
	}
	
	protected void generateAngledBranchWithLeavesSegment(World worldIn, BlockPos pos) {
		worldIn.setBlockState(pos, getWoodBlockState(), 3);
		spawnLeafBlock(worldIn, pos.offset(EnumFacing.NORTH, 1));
		spawnLeafBlock(worldIn, pos.offset(EnumFacing.SOUTH, 1));
		spawnLeafBlock(worldIn, pos.offset(EnumFacing.EAST, 1));
		spawnLeafBlock(worldIn, pos.offset(EnumFacing.WEST, 1));
	}
	
	protected void generateAngledBranchLeaves(World worldIn, BlockPos pos, EnumFacing direction, int nodes, boolean outerTipLeaf) {
		BlockPos posStart = pos.offset(direction, 1).offset(EnumFacing.UP, 1);
		for (int i = 0; i < nodes; i++) {
			spawnLeafBlock(worldIn, posStart.down());
			spawnLeafBlock(worldIn, posStart.up());
			spawnLeafBlock(worldIn, posStart.offset(getSideDirection90(direction), 1));
			spawnLeafBlock(worldIn, posStart.offset(getSideDirection270(direction), 1));
			posStart = posStart.offset(direction, 1).up();
		}
		spawnLeafBlock(worldIn, posStart);
		spawnLeafBlock(worldIn, posStart.down());
		if (outerTipLeaf) {
			posStart = posStart.offset(direction, 1);
			spawnLeafBlock(worldIn, posStart);
		}
	}
	
	protected void generateBranchWithLeavesSegmented(World worldIn, Random random, BlockPos pos, EnumFacing direction, int segments, int segmentHeight, int pctLeafGen) {
		BlockPos posStart = pos;
		for (int i = 0; i < segments; i++) {
			for (int j = 0; j < segmentHeight; j++) {
				worldIn.setBlockState(posStart, getWoodBlockState(), 3);
				if (random.nextInt(100) < pctLeafGen) {
					spawnLeafBlock(worldIn, posStart.offset(EnumFacing.NORTH, 1));
				}
				if (random.nextInt(100) < pctLeafGen) {
					spawnLeafBlock(worldIn, posStart.offset(EnumFacing.SOUTH, 1));
				}
				if (random.nextInt(100) < pctLeafGen) {
					spawnLeafBlock(worldIn, posStart.offset(EnumFacing.EAST, 1));
				}
				if (random.nextInt(100) < pctLeafGen) {
					spawnLeafBlock(worldIn, posStart.offset(EnumFacing.WEST, 1));
				}
				posStart = posStart.offset(EnumFacing.UP, 1);
			}
			posStart = posStart.offset(direction, 1);
		}
		spawnLeafBlock(worldIn, posStart.offset(direction.getOpposite(), 1));
	}
	
	protected void generateDiagonalBranch(World worldIn, BlockPos pos, EnumFacing direction1, EnumFacing direction2, int nodes) {
		for (int x = 1; x <= nodes; x++) {
			worldIn.setBlockState(pos.offset(EnumFacing.UP, x).offset(direction1, x).offset(direction2, x), getWoodBlockState(), 3);
		}
	}
	
	protected void generateDiagonalBranchLeaves(World worldIn, BlockPos pos, EnumFacing direction1, EnumFacing direction2, int nodes, boolean outerTipLeaf) {
		BlockPos posStart = pos.offset(direction1, 1).offset(direction2, 1).offset(EnumFacing.UP, 1);
		for (int i = 0; i < nodes; i++) {
			spawnLeafBlock(worldIn, posStart.down());
			spawnLeafBlock(worldIn, posStart.up());
			spawnLeafBlock(worldIn, posStart.north());
			spawnLeafBlock(worldIn, posStart.east());
			spawnLeafBlock(worldIn, posStart.south());
			spawnLeafBlock(worldIn, posStart.west());
			posStart = posStart.offset(direction1, 1).offset(direction2, 1).up();
		}
		if (outerTipLeaf) {
			spawnLeafBlock(worldIn, posStart);
		}
	}
	
	protected void generateLeafLayer(World worldIn, Random random, BlockPos pos, int radius, int cornerCut, int cornerCutMissPct) {
		for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
			for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
				boolean canGenerate = true;
				if (((z == pos.getZ() - radius) || (z == pos.getZ() + radius)) && ((x < pos.getX() - radius + cornerCut) || (x > pos.getX() + radius - cornerCut))) {
					if (1 + random.nextInt(99) > cornerCutMissPct) {
						canGenerate = false;
					}
				}
				if (((x == pos.getX() - radius) || (x == pos.getX() + radius)) && ((z < pos.getZ() - radius + cornerCut) || (z > pos.getZ() + radius - cornerCut))) {
					if (1 + random.nextInt(99) > cornerCutMissPct) {
						canGenerate = false;
					}
				}
				if (canGenerate) {
					BlockPos newPos = new BlockPos(x, pos.getY(), z);
					spawnLeafBlock(worldIn, newPos);
				}
			}
		}
	}
	
	protected void generateRandomLeafLayer(World worldIn, Random random, BlockPos pos, int radius, int cornerCut, int cornerCutMissPct, int pctChanceGenerate) {
		for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
			for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
				boolean canGenerate = true;
				if (((z == pos.getZ() - radius) || (z == pos.getZ() + radius)) && ((x < pos.getX() - radius + cornerCut) || (x > pos.getX() + radius - cornerCut))) {
					if (1 + random.nextInt(99) > cornerCutMissPct) {
						canGenerate = false;
					}
				}
				if (((x == pos.getX() - radius) || (x == pos.getX() + radius)) && ((z < pos.getZ() - radius + cornerCut) || (z > pos.getZ() + radius - cornerCut))) {
					if (1 + random.nextInt(99) > cornerCutMissPct) {
						canGenerate = false;
					}
				}
				if ((canGenerate) && (random.nextInt(100) < pctChanceGenerate)) {
					BlockPos newPos = new BlockPos(x, pos.getY(), z);
					spawnLeafBlock(worldIn, newPos);
				}
			}
		}
	}
	
	protected void generateCircularLeafLayer(World worldIn, Random random, BlockPos pos, int radius, int missPct) {
		for (int z = -radius; z <= radius; z++) {
			for (int x = -radius; x <= radius; x++) {
				if (x * x + z * z <= radius * radius + radius * 0.8F) {
					if (1 + random.nextInt(100) > missPct) {
						spawnLeafBlock(worldIn, pos.getX() + x, pos.getY(), pos.getZ() + z);
					}
				}
			}
		}
	}
	
	protected void generateSquareLeafLayer(World worldIn, Random random, BlockPos pos, int radius, int missPct) {
		for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
			for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
				if (1 + random.nextInt(100) > missPct) {
					BlockPos newPos = new BlockPos(x, pos.getY(), z);
					spawnLeafBlock(worldIn, newPos);
				}
			}
		}
	}
	
	protected void generatePalmLeaf(World worldIn, BlockPos pos, EnumFacing direction) {
		spawnLeafBlock(worldIn, pos);
		spawnLeafBlock(worldIn, pos.offset(direction, 1));
		spawnLeafBlock(worldIn, pos.offset(direction, 1).offset(EnumFacing.DOWN, 1));
		spawnLeafBlock(worldIn, pos.offset(direction, 2).offset(EnumFacing.DOWN, 1));
	}
	
	protected void generateUprightPalmLeaf(World worldIn, BlockPos pos, EnumFacing direction) {
		spawnLeafBlock(worldIn, pos);
		spawnLeafBlock(worldIn, pos.offset(direction, 1));
		spawnLeafBlock(worldIn, pos.offset(direction, 1).offset(EnumFacing.UP, 1));
		spawnLeafBlock(worldIn, pos.offset(direction, 2).offset(EnumFacing.UP, 1));
	}
	
	protected void spawnLeafBlock(World worldIn, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		spawnLeafBlock(worldIn, pos);
	}
	
	protected void spawnLeafBlock(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos)) {
			worldIn.setBlockState(pos, getLeafBlockState(), 3);
		}
	}
	
	protected IBlockState getLeafBlockState() {
		return PlantMegaPack.blocks.getLeavesBlock(this.sapling.leaves).getDefaultState().withProperty(PMPBlockLeaves.VARIANT, Integer.valueOf(this.sapling.leafMeta)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(true)).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
	}
	
	protected IBlockState getWoodBlockState() {
		if (this.sapling.logType.logBlockName.matches("log")) {
			return Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, EnumType.byMetadata(this.sapling.getLogMeta()));
		}
		if (this.sapling.logType.logBlockName.matches("log2")) {
			return Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, EnumType.byMetadata(this.sapling.getLogMeta()));
		}
		return PlantMegaPack.blocks.getWoodBlockFromName(this.sapling.logType.logBlockName + "Block").getDefaultState();
	}
	
	protected EnumFacing getSideDirection90(EnumFacing direction) {
		switch (direction) {
		case NORTH: 
			return EnumFacing.EAST;
		case EAST: 
			return EnumFacing.SOUTH;
		case SOUTH: 
			return EnumFacing.WEST;
		case WEST: 
			return EnumFacing.NORTH;
		default:
			break;
		}
		return EnumFacing.NORTH;
	}
	
	protected EnumFacing getSideDirection270(EnumFacing direction) {
		switch (direction) {
		case NORTH: 
			return EnumFacing.WEST;
		case EAST: 
			return EnumFacing.NORTH;
		case SOUTH: 
			return EnumFacing.EAST;
		case WEST: 
			return EnumFacing.SOUTH;
		default:
			break;
		}
		return EnumFacing.NORTH;
	}
	
	protected EnumFacing getRandomDirection(Random random) {
		int i = 1 + random.nextInt(100);
		if (i < 25) {
			return EnumFacing.NORTH;
		}
		if (i < 50) {
			return EnumFacing.EAST;
		}
		if (i < 75) {
			return EnumFacing.SOUTH;
		}
		return EnumFacing.WEST;
	}
}

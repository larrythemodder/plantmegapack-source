package plantmegapack.gui.core;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockDoublePlant.EnumBlockHalf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockDouble;
import plantmegapack.block.PMPBlockHalf;
import plantmegapack.book.PMPBookEntry;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.core.PMPHelper;
import plantmegapack.gui.book.PMPContainerBook;
import plantmegapack.gui.book.PMPGuiBook;

public class PMPGuiHandler
	implements IGuiHandler
{
	public static final int PMP_GUI_PLANT_FIELD_GUIDE = 1;
	
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 1) {
			return new PMPContainerBook(world, new BlockPos(x, y, z), player);
		}
		return null;
	}
	
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 1) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
				BlockPos posTarget = mc.objectMouseOver.getBlockPos();
				if ((posTarget != null) && (PMPHelper.isValidPosition(posTarget))) {
					IBlockState state = world.getBlockState(posTarget);
					Block block = state.getBlock();
					if (((block instanceof BlockDoublePlant)) && (world.getBlockState(posTarget).getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER)) {
						state = world.getBlockState(posTarget.down());
						block = state.getBlock();
					} else if (((block instanceof PMPBlockDouble)) && (world.getBlockState(posTarget).getValue(PMPBlockDouble.HALF) == PMPBlockHalf.upper)) {
						state = world.getBlockState(posTarget.down());
						block = state.getBlock();
					}
					PMPBookEntry entry = PlantMegaPack.bookRegistry.getRegisteredPlant(block, block.getMetaFromState(state));
					if (entry != null) {
						PlantMegaPack.bookRegistry.onRightClickPlant(world, posTarget, player, entry);
						return new PMPGuiBook(player, entry);
					}
				}
			}
		}
		return null;
	}
}

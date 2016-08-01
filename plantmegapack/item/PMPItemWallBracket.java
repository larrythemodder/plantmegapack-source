package plantmegapack.item;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PMPItemWallBracket
	extends ItemBlock
{
	public PMPItemWallBracket(Block block) {
		super(block);
		setUnlocalizedName(block.getUnlocalizedName().substring(5));
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		Block block = iblockstate.getBlock();
		if (stack.stackSize == 0) {
			return EnumActionResult.FAIL;
		}
		if (!playerIn.canPlayerEdit(pos, facing, stack)) {
			return EnumActionResult.FAIL;
		}
		if ((pos.getY() == 255) && (this.block.getMaterial(iblockstate).isSolid())) {
			return EnumActionResult.FAIL;
		}
		BlockPos posTarget;
		switch (facing) {
		case NORTH: 
			posTarget = pos.north();
			break;
		case SOUTH: 
			posTarget = pos.south();
			break;
		case WEST: 
			posTarget = pos.west();
			break;
		case EAST: 
			posTarget = pos.east();
			break;
		default: 
			return EnumActionResult.FAIL;
		}
		if (block.canPlaceBlockAt(worldIn, posTarget)) {
			int i = getMetadata(stack.getMetadata());
			IBlockState iblockstate1 = this.block.onBlockPlaced(worldIn, posTarget, facing, hitX, hitY, hitZ, i, playerIn);
			worldIn.setBlockState(posTarget, iblockstate1, 3);
			SoundType soundtype = this.block.getSoundType();
			worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
			stack.stackSize -= 1;
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}

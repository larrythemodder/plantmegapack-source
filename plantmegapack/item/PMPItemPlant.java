package plantmegapack.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.block.PMPBlockClimbing;
import plantmegapack.block.PMPBlockColored;
import plantmegapack.block.PMPBlockDouble;
import plantmegapack.block.PMPBlockHalf;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPHangingPlant;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPStem;
import plantmegapack.object.PMPTab;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantConStat;
import plantmegapack.plant.PMPPlantCropDrops;

public class PMPItemPlant extends ItemBlock {
	IPMPPlant plantBlock;
	
	public PMPItemPlant(Block block) {
		super(block);
		this.plantBlock = ((IPMPPlant)block);
		setUnlocalizedName(this.plantBlock.getPlantData().attributes.name());
		PMPDataPlant plantData = this.plantBlock.getPlantData();
		if ((plantData.attributes.blockType == PMPPlantBlockType.fflo) || (plantData.attributes.blockType == PMPPlantBlockType.flow)) {
			setMaxDamage(0);
			setHasSubtypes(true);
		}
	}
	
	public int getMetadata(int damageValue) {
		return damageValue;
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
		if (getHasSubtypes()) {
			((PMPBlockColored)this.block).getSubBlocks(itemIn, tab, subItems);
			return;
		}
		super.getSubItems(itemIn, tab, subItems);
	}
	
	public String getUnlocalizedName(ItemStack itemStack) {
		PMPDataPlant plantData = this.plantBlock.getPlantData();
		if ((plantData.attributes.blockType == PMPPlantBlockType.fflo) || (plantData.attributes.blockType == PMPPlantBlockType.flow)) {
			return "tile." + plantData.attributes.name() + "_" + PMPColor.getColorFromID(itemStack.getMetadata()).name();
		}
		return super.getUnlocalizedName(itemStack);
	}
	
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
		PMPPlantCategory category = this.plantBlock.getPlantData().attributes.category;
		if ((category == PMPPlantCategory.crpa) || (category == PMPPlantCategory.floa) || (category == PMPPlantCategory.imme)) {
			RayTraceResult movingobjectposition = rayTrace(worldIn, playerIn, false);//getMovingObjectPositionFromPlayer(worldIn, playerIn, true);
			if (movingobjectposition == null) {
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			BlockPos blockPos = movingobjectposition.getBlockPos().up();
			if (!this.block.canPlaceBlockAt(worldIn, blockPos)) {
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
				if (category == PMPPlantCategory.floa) {
					if (getHasSubtypes()) {
						worldIn.setBlockState(blockPos, this.block.getDefaultState().withProperty(PMPBlockColored.VARIANT, PMPColor.getColorFromID(getMetadata(itemStackIn))), 2);
					} else {
						worldIn.setBlockState(blockPos, this.block.getDefaultState(), 2);
					}
				} else if (category == PMPPlantCategory.crpa) {
					worldIn.setBlockState(blockPos, this.block.getDefaultState().withProperty(BlockCrops.AGE, Integer.valueOf(0)), 2);
				} else if (this.plantBlock.getPlantData().attributes.blockType == PMPPlantBlockType.immd) {
					worldIn.setBlockState(blockPos, this.block.getDefaultState().withProperty(PMPBlockDouble.HALF, PMPBlockHalf.lower), 2);
					worldIn.setBlockState(blockPos.up(), this.block.getDefaultState().withProperty(PMPBlockDouble.HALF, PMPBlockHalf.upper), 2);
				} else {
					worldIn.setBlockState(blockPos, this.block.getDefaultState(), 2);
				}
				if (!playerIn.capabilities.isCreativeMode) {
					itemStackIn.stackSize -= 1;
				}
				worldIn.playSound(playerIn, blockPos, this.block.getSoundType().getBreakSound(), SoundCategory.BLOCKS, (this.block.getSoundType().getVolume() + 1.0F) / 2.0F, this.block.getSoundType().getPitch() * 0.8F);
				
				worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, blockPos.getX() + 0.5F, blockPos.getY() + 0.45F, blockPos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, blockPos.getX() + 0.5F, blockPos.getY() + 0.45F, blockPos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
	
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		PMPPlantCategory category = this.plantBlock.getPlantData().attributes.category;
		if (category == PMPPlantCategory.clim) {
			if ((facing == EnumFacing.UP) || (facing == EnumFacing.DOWN)) {
				return EnumActionResult.FAIL;
			}
			BlockPos posTarget = pos.offset(facing);
			boolean canPlace = false;
			canPlace = worldIn.isSideSolid(pos, facing, false);
			if ((canPlace) && (playerIn.canPlayerEdit(posTarget, facing, stack)) && (this.block.canPlaceBlockAt(worldIn, posTarget))) {
				worldIn.setBlockState(posTarget, this.block.getDefaultState().withProperty(PMPBlockClimbing.FACING, facing.getOpposite()), 3);
				worldIn.playSound(playerIn, posTarget, this.block.getSoundType().getBreakSound(), SoundCategory.BLOCKS, (this.block.getSoundType().getVolume() + 1.0F) / 2.0F, this.block.getSoundType().getPitch() * 0.8F);
				stack.stackSize -= 1;
				return EnumActionResult.SUCCESS;
			}
			return EnumActionResult.FAIL;
		}
		return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		PMPPlant plant = this.plantBlock.getPlantData().attributes;
		if ((PlantMegaPack.settings.tooltipLatinName) && (plant.category != PMPPlantCategory.grou)) {
			list.add(String.format("§7§o%s§r", new Object[] { plant.getLatinName() }));
		}
		if (PlantMegaPack.settings.tooltipCategory) {
			list.add(String.format("§%s%s§r", new Object[] { Character.valueOf(plant.category.colorCode), plant.category.getLocalizedName() }));
		}
		if (PlantMegaPack.settings.tooltipConservation) {
			list.add("§8" + I18n.translateToLocal("gui.conservation") + ": " + PMPPlantConStat.getStatusByID(plant.conservationStatus).getLocalizedNameFormatted());
		}
		if (PlantMegaPack.settings.tooltipDrops) {
			if (plant.category.creativeTab == PMPTab.aqua) {
				if (plant.category == PMPPlantCategory.cora) {
					if (plant.flowerID >= 0) {
						list.add("§8" + I18n.translateToLocal("gui.crafting") + ": " + PMPColor.getColorFromID(plant.flowerID).getLocalizedNameFormatted() + " §9" + I18n.translateToLocal("gui.coralFragment") + "§r");
					}
				}
			} else
			{
				if ((plant.flowerID >= 0) || (PMPPlant.hasColorVariants(plant))) {
					PMPColor color = PMPColor.getColorFromID(PMPPlant.hasColorVariants(plant) ? itemStack.getMetadata() : plant.flowerID);
					list.add("§8" + I18n.translateToLocal("gui.flower") + ": " + color.getLocalizedNameFormatted());
				}
				if (plant.stemID >= 0) {
					PMPStem stem = PMPStem.getStemFromID(PMPPlant.hasColorVariants(plant) ? 1 : plant.stemID);
					list.add("§8" + I18n.translateToLocal("gui.stem") + ": " + stem.getLocalizedNameFormatted());
				}
				if (plant.rootID >= 0) {
					PMPRootMedicinal root = PMPRootMedicinal.getRootFromID(plant.rootID);
					list.add("§8" + I18n.translateToLocal("gui.root") + ": " + root.getLocalizedNameFormatted());
				}
				if ((plant.category == PMPPlantCategory.fung) && (!plant.isPoisonPlant())) {
					PMPHelper.addCraftingItemTooltip(list, Items.MUSHROOM_STEW);
				}
			}
			PMPItemFood foodDrop = PlantMegaPack.plantDrops.getFoodItem(plant.name());
			if (foodDrop != null) {
				list.add("§8" + I18n.translateToLocal("gui.food") + ": §a" + foodDrop.getLocalizedName() + "§r");
			}
		}
		if (PlantMegaPack.settings.tooltipCrafting) {
			addUniquePlantTooltips(list, plant);
		}
		addWarningTooltips(list, plant);
	}
	
	private void addUniquePlantTooltips(List list, PMPPlant plant) {
		if ((plant.flowerID >= 0) && ((plant.category == PMPPlantCategory.clim) || (plant.category == PMPPlantCategory.vine))) {
			PMPHelper.addCraftingItemTooltip(list, Item.getItemFromBlock(PlantMegaPack.blocks.getHangingPlant(PMPHangingPlant.getHangingPlantFromFlowerID(plant.flowerID).name())));
		}
		if ((plant.isPoisonPlant()) && (plant.category == PMPPlantCategory.fung)) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getPowderItem(PMPPowder.powderDefoliant));
		}
		if (plant.blockType == PMPPlantBlockType.seaw) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed));
		}
		if ((plant.category == PMPPlantCategory.clim) || (plant.category == PMPPlantCategory.vine)) {
			if ((plant == PMPPlant.climbingBlushingPhilodendron) || (plant == PMPPlant.climbingBridalCreeper) || (plant == PMPPlant.climbingJapaneseCreeper)) {
				PMPHelper.addCraftingItemTooltip(list, "gui.hangingPlant");
			} else if ((plant == PMPPlant.vineBlueSkyflower) || (plant == PMPPlant.vineJapaneseIvy) || (plant == PMPPlant.vineMadeiraVine) || (plant == PMPPlant.vineSilverveinCreeper) || (plant == PMPPlant.vineSwedishIvy) || (plant == PMPPlant.vineVariegatedPersianIvy)) {
				PMPHelper.addCraftingItemTooltip(list, "gui.hangingPlant");
			}
		}
		if (plant == PMPPlant.forestWolfsFootClubmoss) {
			PMPHelper.addCraftingItemTooltip(list, Items.GUNPOWDER);
		} else if (plant == PMPPlant.groundcoverTwig) {
			PMPHelper.addCraftingItemTooltip(list, Items.STICK);
		} else if (plant == PMPPlant.vineSpanishMoss) {
			PMPHelper.addCraftingItemTooltip(list, Items.STRING);
		} else if (plant == PMPPlant.wetlandsCattails) {
			PMPHelper.addCraftingItemTooltip(list, PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike));
		}
	}
	
	private void addWarningTooltips(List list, PMPPlant plant) {
		if (plant.isPoisonPlant()) {
			list.add("§8" + I18n.translateToLocal("gui.warning") + ": §c" + I18n.translateToLocal("gui.poison") + "§r");
		}
	}
}

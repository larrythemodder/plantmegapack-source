package plantmegapack.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Block.EnumOffsetType;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlockCore;
import plantmegapack.core.PMPItems;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.item.PMPItemPlant;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPStem;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantSoilType;

public class PMPBlockBase extends BlockBush implements IPMPPlant, IPlantable, IShearable {
	protected PMPDataPlant plantData;
	protected static final AxisAlignedBB AABB_ALL = new AxisAlignedBB(0.15D, 0.0D, 0.15D, 0.85D, 0.85D, 0.85D);
	
	public PMPBlockBase(PMPDataPlant plantData) {
		super(plantData.attributes.category == PMPPlantCategory.cact ? Material.CACTUS : Material.PLANTS);
		this.plantData = plantData;
		setSoundType(SoundType.PLANT);
		PMPBlockCore.initPlantBlock(this, this.plantData, PMPItemPlant.class);
	}
	
	public PMPDataPlant getPlantData() {
		return this.plantData;
	}
	
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock().getMaterial(worldIn.getBlockState(pos)) == Material.WATER) {
			return false;
		}
		if (this.plantData.attributes.isDoubleTallPlant()) {
			if (worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.AIR) {
				return false;
			}
		} else if (this.plantData.attributes.isTripleTallPlant()) {
			if ((worldIn.getBlockState(pos.up()).getBlock().getMaterial(worldIn.getBlockState(pos.up())) != Material.AIR) || (worldIn.getBlockState(pos.up(2)).getBlock().getMaterial(worldIn.getBlockState(pos.up(2))) != Material.AIR)) {
				return false;
			}
		}
		return super.canPlaceBlockAt(worldIn, pos);
	}
	
	protected boolean canSustainBush(IBlockState state) {
		return PMPPlantSoilType.canPlantGrowOnBlock(state.getBlock(), state, this.plantData.attributes.category.soilType);
	}
	
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		if (this.plantData.attributes.isAquaticPlant()) {
			if (this.plantData.attributes.isSubmergedPlant()) {
				worldIn.spawnParticle(EnumParticleTypes.WATER_BUBBLE, pos.getX() + 0.5F, pos.getY() + 0.45F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.WATER_BUBBLE, pos.getX() + 0.5F, pos.getY() + 0.55F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
			} else {
				worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, pos.getX() + 0.5F, pos.getY() + 0.45F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
				worldIn.spawnParticle(EnumParticleTypes.WATER_SPLASH, pos.getX() + 0.5F, pos.getY() + 0.55F, pos.getZ() + 0.5F, 0.0D, 0.0D, 0.0D, new int[0]);
			}
		}
		super.onBlockDestroyedByPlayer(worldIn, pos, state);
	}
	
	public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
		if ((this.plantData.attributes.category == PMPPlantCategory.beac) || (this.plantData.attributes.category == PMPPlantCategory.cact) || (this.plantData.attributes.category == PMPPlantCategory.dese) || (this.plantData.attributes.category == PMPPlantCategory.mesa)) {
			return EnumPlantType.Desert;
		}
		return EnumPlantType.Plains;
	}
	
	public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
		return getDefaultState();
	}
	
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}
	
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		if (((entity instanceof EntityPlayer)) || ((entity instanceof EntityPlayerMP))) {
			if ((PlantMegaPack.settings.realismPoison) && (this.plantData.attributes.isPoisonPlant())) {
				((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionById(19), 5));
			}
			if ((PlantMegaPack.settings.realismThorns) && (this.plantData.attributes.isThornsPlant())) {
				((EntityLivingBase)entity).attackEntityFrom(DamageSource.cactus, 1.0F);
			}
		}
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if (canPlantBeBrokenIntoParts()) {
			return null;
		}
		return super.getItemDropped(state, rand, fortune);
	}
	
	public int quantityDropped(Random random) {
		if (canPlantBeBrokenIntoParts()) {
			return 0;
		}
		return super.quantityDropped(random);
	}
	
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return canPlantBeBrokenIntoParts();
	}
	
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess worldIn, BlockPos pos, int fortune) {
		List<ItemStack> ret = new ArrayList();
		return ret;
	}
	
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		if (canPlantBeBrokenIntoParts()) {
			List<ItemStack> ret = new ArrayList();
			return ret;
		}
		return super.getDrops(world, pos, state, fortune);
	}
	
	public void harvestBlock(World worldIn, EntityPlayer playerIn, BlockPos pos, IBlockState state, TileEntity te, ItemStack itemStack) {
		playerIn.addExhaustion(0.025F);
		PMPPlant plant = this.plantData.attributes;
		
		ItemStack playerItemStack = playerIn.getHeldItemMainhand();
		if ((playerItemStack != null) && (canPlantBeBrokenIntoParts()) && ((playerItemStack.getItem() instanceof ItemShears))) {
			if ((this.plantData.attributes.flowerID >= 0) || (PMPPlant.hasColorVariants(plant))) {
				EntityItem plantPart;
				if (PMPPlant.hasColorVariants(plant)) {
					int meta = ((PMPColor)state.getValue(PMPBlockColored.VARIANT)).ID;
					plantPart = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(PlantMegaPack.items.getFlowerItem(PMPFlower.getFlowerFromID(meta)), 1, 0));
				} else {
					plantPart = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(PlantMegaPack.items.getFlowerItem(PMPFlower.getFlowerFromID(this.plantData.attributes.flowerID)), 1, 0));
				}
				worldIn.spawnEntityInWorld(plantPart);
			}
			if (this.plantData.attributes.stemID >= 0) {
				EntityItem plantPart = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(PlantMegaPack.items.getStemItem(PMPStem.getStemFromID(this.plantData.attributes.stemID)), 1, 0));
				worldIn.spawnEntityInWorld(plantPart);
			}
			if (this.plantData.attributes.rootID >= 0) {
				EntityItem plantPart = new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(PlantMegaPack.items.getMedicinalRootItem(), 1, this.plantData.attributes.rootID));
				worldIn.spawnEntityInWorld(plantPart);
			}
		} else {
			this.harvesters.set(playerIn);
			ItemStack plantStack = new ItemStack(Item.getItemFromBlock(this));
			int meta = PMPPlant.hasColorVariants(plant) ? ((PMPColor)state.getValue(PMPBlockColored.VARIANT)).ID : 0;
			plantStack = new ItemStack(Item.getItemFromBlock(this), 1, meta);
			spawnAsEntity(worldIn, pos, plantStack);
			this.harvesters.set(null);
		}
	}
	
	private boolean canPlantBeBrokenIntoParts() {
		PMPPlant plant = this.plantData.attributes;
		return (PMPPlant.hasColorVariants(plant)) || (plant.flowerID >= 0) || (plant.stemID >= 0) || (plant.rootID >= 0);
	}
	
	@SideOnly(Side.CLIENT)
	public Block.EnumOffsetType getOffsetType() {
		return PlantMegaPack.settings.realismPlantCentered ? Block.EnumOffsetType.NONE : Block.EnumOffsetType.XZ;
	}
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return PMPBlockCore.getPlantAABB(AABB_ALL, pos);
	}
	
	public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes.category == PMPPlantCategory.cact) {
			return state.getBoundingBox(worldIn, pos);
		}
		return super.getSelectedBoundingBox(state, worldIn, pos);
	}
	
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
		if (this.plantData.attributes.category == PMPPlantCategory.cact) {
			return state.getBoundingBox(worldIn, pos).offset(pos);
		}
		return super.getCollisionBoundingBox(state, worldIn, pos);
	}
}

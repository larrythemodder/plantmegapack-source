package plantmegapack.book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.IPMPPlant;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPHelper;
import plantmegapack.core.PMPItems;
import plantmegapack.core.PMPReference;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataPlant;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.item.PMPItemFlower;
import plantmegapack.item.PMPItemFood;
import plantmegapack.item.PMPItemFragment;
import plantmegapack.item.PMPItemPowder;
import plantmegapack.item.PMPItemRootMedicinal;
import plantmegapack.item.PMPItemSalve;
import plantmegapack.item.PMPItemSeed;
import plantmegapack.item.PMPItemStem;
import plantmegapack.object.PMPColor;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPSapling;
import plantmegapack.object.PMPStem;
import plantmegapack.plant.PMPPlant;
import plantmegapack.plant.PMPPlantBlockType;
import plantmegapack.plant.PMPPlantCategory;
import plantmegapack.plant.PMPPlantCropDrops;
import plantmegapack.plant.PMPPlantVanilla;

public class PMPBookRegistry
{
	private ArrayList<PMPBookEntry> plants = new ArrayList();
	private int plantsDiscovered;
	private int plantsTotal;
	private Map<String, Boolean> items = new HashMap();
	
	public void init() {
		this.plantsDiscovered = 0;
		this.plantsTotal = 0;
		initPMP();
		initMC();
		resetItemsCollected();
		Collections.sort(this.plants, new PMPBookEntrySorter());
		PMPReference.addToRegisteredBookEntries(this.plantsTotal);
	}
	
	private void initPMP() {
		for (PMPPlant plant : PMPPlant.values()) {
			Block block = PlantMegaPack.blocks.getPlantBlockByName(plant.name());
			PMPDataPlant plantData = ((IPMPPlant)block).getPlantData();
			String prefix = "tile." + plant.name();
			if (PMPPlant.hasColorVariants(plant)) {
				for (PMPColor color : PMPColor.values()) {
					String colorName = color.name();
					String textureName = String.format("%s:%s_%s", new Object[] { "plantmegapack", plant.name(), colorName });
					addPlant(block, color.ID, false, I18n.translateToLocal(prefix + "_" + colorName + ".name"), I18n.translateToLocal(prefix + ".snam"), I18n.translateToLocal(prefix + ".desc"), textureName, plantData.attributes.mapID, plantData.attributes.conservationStatus);
					addPMPPlantItems(block, color.ID, plant);
				}
			} else
			{
				String textureName;
				if (plant.blockType.isTallPlant()) {
					textureName = String.format("%s:%s", new Object[] { "plantmegapack", plant.name() });
				} else {
					textureName = String.format("%s:%s%d", new Object[] { "plantmegapack", plant.name(), Integer.valueOf(plantData.attributes.blockType.itemModelIndex) });
				}
				addPlant(block, -1, false, I18n.translateToLocal(prefix + ".name"), I18n.translateToLocal(prefix + ".snam"), I18n.translateToLocal(prefix + ".desc"), textureName, plantData.attributes.mapID, plantData.attributes.conservationStatus);
				addPMPPlantItems(block, -1, plant);
			}
		}
		for (PMPSapling sapling : PMPSapling.values()) {
			Block block = PlantMegaPack.blocks.getSapling(sapling);
			String prefix = block.getUnlocalizedName();
			String textureName = String.format("%s:%s", new Object[] { "plantmegapack", sapling.name() });
			addPlant(block, 0, false, I18n.translateToLocal(prefix + ".name"), I18n.translateToLocal(prefix + ".snam"), I18n.translateToLocal(prefix + ".desc"), textureName, sapling.mapID, sapling.conservationStatus);
			if (sapling.isFruitTree()) {
				addPlantItem(block, 0, PlantMegaPack.items.getFoodItem(sapling.food), 0);
			}
		}
	}
	
	private void initMC() {
		for (PMPPlantVanilla plant : PMPPlantVanilla.values()) {
			String textureName = String.format("Minecraft:%s", new Object[] { plant.texture });
			addPlant(plant.block, plant.meta, plant.coloredTexture, plant.getName(), plant.getLatinName(), plant.getDescription(), textureName, plant.mapID, plant.conservationStatus);
			if (plant.item != null) {
				addPlantItem(plant.block, plant.meta, plant.item, 0);
			}
			if (plant.dyeMeta >= 0) {
				addPlantItem(plant.block, plant.meta, Items.DYE, plant.dyeMeta);
			}
		}
		addPlantItem(Blocks.MELON_BLOCK, -1, Items.MELON, 0);
		addPlantItem(Blocks.POTATOES, -1, Items.POISONOUS_POTATO, 0);
		
		addPlantItem(Blocks.WHEAT, -1, Items.WHEAT, 0);
	}
	
	private void resetItemsCollected() {
		for (PMPFlower flower : PMPFlower.values()) {
			this.items.put(PlantMegaPack.items.getFlowerItem(flower).getUnlocalizedName(), Boolean.valueOf(false));
		}
		for (PMPStem stem : PMPStem.values()) {
			this.items.put(PlantMegaPack.items.getStemItem(stem).getUnlocalizedName(), Boolean.valueOf(false));
		}
		for (PMPRootMedicinal root : PMPRootMedicinal.values()) {
			this.items.put(String.format("%s:%d", new Object[] { PlantMegaPack.items.getMedicinalRootItem().getUnlocalizedName(), Integer.valueOf(root.ID) }), Boolean.valueOf(false));
		}
		for (PMPSalve salve : PMPSalve.values()) {
			this.items.put(PlantMegaPack.items.getSalveItem(salve).getUnlocalizedName(), Boolean.valueOf(false));
		}
		for (PMPPowder powder : PMPPowder.values()) {
			this.items.put(PlantMegaPack.items.getPowderItem(powder).getUnlocalizedName(), Boolean.valueOf(false));
		}
		for (PMPFragment fragment : PMPFragment.values()) {
			this.items.put(PlantMegaPack.items.getCoralFragmentItem(fragment).getUnlocalizedName(), Boolean.valueOf(false));
		}
	}
	
	@SuppressWarnings("unused")
	public boolean addPlant(Block block, int blockMeta, boolean coloredTexture, String commonName, String latinName, String desc, String texture, int mapID, int conStat) {
		if (!validateBlockAndMeta(block, blockMeta)) {
			return false;
		}
		PMPBookEntry newEntry = new PMPBookEntry(block, blockMeta, coloredTexture, commonName, latinName, desc, texture, mapID, conStat);
		if (newEntry == null) {
			PlantMegaPack.instance.logOutput("Plant Registry Error: unable to create new entry");
			return false;
		}
		this.plants.add(newEntry);
		this.plantsTotal += 1;
		return true;
	}
	
	@SuppressWarnings("unused")
	public boolean addPlantItem(Block block, int blockMeta, Item item, int itemMeta) {
		PMPBookEntry entry = getRegisteredPlant(block, blockMeta);
		if (entry == null) {
			PlantMegaPack.instance.logOutput(String.format("Plant Registry Error: block/meta has not been registered: %s:%d", new Object[] { block.getUnlocalizedName().substring(5), Integer.valueOf(blockMeta) }));
			return false;
		}
		ItemStack itemStack = new ItemStack(item, 1, itemMeta);
		if (itemStack == null) {
			PlantMegaPack.instance.logOutput("Plant Registry Error: unable to create itemstack for plant item");
			return false;
		}
		entry.addEntryItem(itemStack);
		PMPReference.addToRegisteredBookEntryItems(1);
		return true;
	}
	
	public PMPBookEntry getRegisteredPlant(Block block, int blockMeta) {
		if (!validateBlockAndMeta(block, blockMeta)) {
			return null;
		}
		Iterator<PMPBookEntry> iterator = this.plants.iterator();
		while (iterator.hasNext()) {
			PMPBookEntry entry = (PMPBookEntry)iterator.next();
			if ((entry.block == block) && ((entry.blockMeta == blockMeta) || (entry.blockMeta == -1))) {
				return entry;
			}
		}
		return null;
	}
	
	private boolean validateBlockAndMeta(Block block, int blockMeta) {
		if (block == null) {
			PlantMegaPack.instance.logOutput("Plant Registry Error: block is null");
			return false;
		}
		if ((blockMeta < -1) || (blockMeta > 15)) {
			PlantMegaPack.instance.logOutput("Plant Registry Error: block meta value is out of range");
			return false;
		}
		return true;
	}
	
	public void populateGuiList(PMPGuiListbox list) {
		Iterator iterator = this.plants.iterator();
		while (iterator.hasNext()) {
			PMPBookEntry entry = (PMPBookEntry)iterator.next();
			if ((PlantMegaPack.settings.bookShowAllPlants) || (entry.isDiscovered())) {
				list.addListboxEntry(entry.getGuiKey(), entry.commonName);
			}
		}
	}
	
	public PMPBookEntry getEntryFromGuiKey(String key) {
		Iterator<PMPBookEntry> iterator = this.plants.iterator();
		String[] optionLine = key.split(":");
		String blockName = optionLine[0];
		int blockMeta = Integer.parseInt(optionLine[1]);
		while (iterator.hasNext()) {
			PMPBookEntry entry = (PMPBookEntry)iterator.next();
			if ((entry.block.getUnlocalizedName().substring(5).matches(blockName)) && (entry.blockMeta == blockMeta)) {
				return entry;
			}
		}
		return null;
	}
	
	private void addPMPPlantItems(Block block, int meta, PMPPlant plant) {
		if (plant.category == PMPPlantCategory.flwm) {
			addPlantItem(block, meta, PlantMegaPack.items.getFlowerItem(PMPFlower.getFlowerFromID(meta)), meta);
		} else if (plant.flowerID >= 0) {
			if (plant.category == PMPPlantCategory.cora) {
				addPlantItem(block, -1, PlantMegaPack.items.getCoralFragmentItem(PMPFragment.getFragmentFromID(plant.flowerID)), plant.flowerID);
			} else {
				addPlantItem(block, -1, PlantMegaPack.items.getFlowerItem(PMPFlower.getFlowerFromID(plant.flowerID)), plant.flowerID);
			}
		}
		if ((plant.category == PMPPlantCategory.flwm) && (plant.stemID >= 0)) {
			addPlantItem(block, meta, PlantMegaPack.items.getStemItem(PMPStem.getStemFromID(plant.stemID)), plant.stemID);
		} else if (plant.stemID >= 0) {
			addPlantItem(block, -1, PlantMegaPack.items.getStemItem(PMPStem.getStemFromID(plant.stemID)), plant.stemID);
		}
		if (plant.rootID >= 0) {
			addPlantItem(block, -1, PlantMegaPack.items.getMedicinalRootItem(), plant.rootID);
		}
		PMPItemSeed seed = PlantMegaPack.plantDrops.getSeedItem(plant.name());
		if (seed != null) {
			addPlantItem(block, -1, seed, 0);
		}
		PMPItemFood food = PlantMegaPack.plantDrops.getFoodItem(plant.name());
		if (food != null) {
			addPlantItem(block, -1, food, 0);
		}
		if (plant.name().matches("forestWolfsFootClubmoss")) {
			addPlantItem(block, -1, Items.GUNPOWDER, 0);
		} else if (plant.name().matches("groundcoverTwig")) {
			addPlantItem(block, -1, Items.STICK, 0);
		} else if (plant.name().matches("vineSpanishMoss")) {
			addPlantItem(block, -1, Items.STRING, 0);
		} else if ((plant.name().matches("saltwaterKelpGiantGRN")) || (plant.name().matches("saltwaterKelpGiantYEL"))) {
			addPlantItem(block, -1, PlantMegaPack.items.getFoodItem(PMPFood.foodWrapSeaweed), 0);
		} else if (plant.name().matches("wetlandsCattails")) {
			addPlantItem(block, -1, PlantMegaPack.items.getPlantItem(PMPPlantItem.cattailSpike), 0);
		}
	}
	
	public int getPlantsCollected() {
		return this.plantsDiscovered;
	}
	
	public int getPlantsTotal() {
		return this.plantsTotal;
	}
	
	public void resetPlantsCollected() {
		Iterator<PMPBookEntry> iterator = this.plants.iterator();
		while (iterator.hasNext()) {
			PMPBookEntry entry = (PMPBookEntry)iterator.next();
			if (entry != null) {
				entry.resetDiscovered();
			}
		}
		this.plantsDiscovered = 0;
	}
	
	public boolean isItemCollected(String key) {
		return (this.items.containsKey(key)) && (((Boolean)this.items.get(key)).booleanValue() == true);
	}
	
	public void onRightClickPlant(World worldIn, BlockPos pos, EntityPlayer playerIn, PMPBookEntry entry) {
		if ((worldIn != null) && (pos != null) && (playerIn != null) && (entry != null)) {
			if (!entry.isDiscovered()) {
				entry.setDiscovered();
				this.plantsDiscovered += 1;
				if (PlantMegaPack.settings.bookChatMessage) {
					displayNewPlantChatMessage(playerIn, entry.commonName);
				}
				if (PlantMegaPack.settings.bookDropXPOnDiscovery) {
					PMPHelper.spawnParticles(worldIn, pos, worldIn.rand, false, EnumParticleTypes.VILLAGER_HAPPY);
					playerIn.addExperience(1);
				}
				if (PlantMegaPack.settings.debugMode) {
					PlantMegaPack.instance.logOutput(String.format("Updated statistics file for %s", new Object[] { entry.commonName }));
				}
			}
		}
	}
	
	public void onPickupItem(EntityPlayer player, EntityItem item) {
		ItemStack stack = item.getEntityItem();
		Item pickupItem = stack.getItem();
		if (pickupItem == Item.getItemFromBlock(Blocks.STONE)) {
			return;
		}
		String key = pickupItem.getUnlocalizedName();
		String message = I18n.translateToLocal(key + ".name");
		if (key.matches(PlantMegaPack.items.getMedicinalRootItem().getUnlocalizedName())) {
			key = String.format("%s:%d", new Object[] { pickupItem.getUnlocalizedName(), Integer.valueOf(stack.getMetadata()) });
			message = message + ": " + PMPRootMedicinal.getRootFromID(stack.getMetadata()).getLocalizedName();
		}
		if ((this.items.containsKey(key)) && (!((Boolean)this.items.get(key)).booleanValue())) {
			this.items.put(key, Boolean.valueOf(true));
			displayNewItemChatMessage(player, message);
		}
	}
	
	public void onCraftItem(EntityPlayer player, ItemStack itemStack) {
		String key = itemStack.getItem().getUnlocalizedName();
		String message = I18n.translateToLocal(key + ".name");
		if ((this.items.containsKey(key)) && (!((Boolean)this.items.get(key)).booleanValue())) {
			this.items.put(key, Boolean.valueOf(true));
			displayCraftedNewItemChatMessage(player, message);
		}
	}
	
	public void onPlayerLoad(String dataDirectory, String playerUUID) {
		resetPlantsCollected();
		resetItemsCollected();
		String path = dataDirectory = dataDirectory + "//pmp_" + playerUUID + ".stat";
		File statFile = new File(path);
		try
		{
			if (!statFile.exists()) {
				onPlayerSave(dataDirectory, playerUUID);
				return;
			}
			BufferedReader bufferedreader = new BufferedReader(new FileReader(statFile));
			String line = "";
			while ((line = bufferedreader.readLine()) != null) {
				String[] optionLine = line.split("=");
				if (optionLine[0].startsWith("item")) {
					this.items.put(optionLine[0], Boolean.valueOf(optionLine[1].equals("true")));
				} else {
					PMPBookEntry entry = getEntryFromGuiKey(optionLine[0]);
					if (entry != null) {
						if (optionLine[1].equals("true")) {
							entry.setDiscovered();
							this.plantsDiscovered += 1;
						}
					}
				}
			}
			bufferedreader.close();
		}
		catch (IOException e) {
			if (PlantMegaPack.settings.debugMode) {
				PlantMegaPack.instance.logOutput("Error handling statistics world load event");
			}
			return;
		}
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput("Statistics loaded");
		}
	}
	
	public void onPlayerSave(String dataDirectory, String playerUUID) {
		String path = dataDirectory = dataDirectory + "//pmp_" + playerUUID + ".stat";
		File statFile = new File(path);
		try
		{
			if (!statFile.exists()) {
				statFile.createNewFile();
			}
			PrintWriter printwriter = new PrintWriter(new FileWriter(statFile));
			Iterator<PMPBookEntry> iterator = this.plants.iterator();
			while (iterator.hasNext()) {
				PMPBookEntry entry = (PMPBookEntry)iterator.next();
				if (entry != null) {
					printwriter.println(entry.getGuiKey() + "=" + entry.isDiscovered());
				}
			}
			for (Map.Entry<String, Boolean> item : this.items.entrySet()) {
				printwriter.println((String)item.getKey() + "=" + item.getValue());
			}
			printwriter.close();
		}
		catch (IOException e) {
			return;
		}
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput("Statistics saved");
		}
	}
	
	private void displayCraftedNewItemChatMessage(EntityPlayer player, String itemName) {
		displayDiscoverChatMessage(player, itemName, "chatMsg.newCraftedItem");
	}
	
	private void displayNewItemChatMessage(EntityPlayer player, String itemName) {
		displayDiscoverChatMessage(player, itemName, "chatMsg.newItem");
	}
	
	private void displayNewPlantChatMessage(EntityPlayer player, String plantName) {
		displayDiscoverChatMessage(player, plantName, "chatMsg.newPlant");
	}
	
	private void displayDiscoverChatMessage(EntityPlayer player, String objectName, String messageKey) {
		if ((player != null) && (!objectName.isEmpty())) {
			TextComponentString message = new TextComponentString(player.getDisplayNameString() + " ");
			TextComponentTranslation body = new TextComponentTranslation(messageKey, new Object[0]);
			TextComponentString sep1 = new TextComponentString(" [");
			TextComponentTranslation item = new TextComponentTranslation(objectName, new Object[0]);
			TextComponentString sep2 = new TextComponentString("]");
			sep1.getStyle().setColor(TextFormatting.GREEN);
			item.getStyle().setColor(TextFormatting.GREEN);
			sep2.getStyle().setColor(TextFormatting.GREEN);
			message.appendSibling(body);
			message.appendSibling(sep1);
			message.appendSibling(item);
			message.appendSibling(sep2);
			player.addChatComponentMessage(message);
		}
	}
}

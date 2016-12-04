package plantmegapack.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.gui.core.PMPGuiHandler;
import plantmegapack.plant.PMPPlantCropDrops;
import plantmegapack.worldgen.PMPWorldGenerator;
import plantmegapack.worldgen.PMPWorldgenProfile;

public class PMPProxyCommon
{
	public void preInit() {
		PlantMegaPack.settings = new PMPSettings(PlantMegaPack.configPathRoot);
		//PlantMegaPack.creativeTabs = new PMPCreativeTab();
		PlantMegaPack.blocks = new PMPBlocks();
		PlantMegaPack.items = new PMPItems();
		PlantMegaPack.plantDrops = new PMPPlantCropDrops();
		PlantMegaPack.recipes = new PMPRecipes();
		PlantMegaPack.worldGenerator = new PMPWorldGenerator();
		PlantMegaPack.worldgenProfile = new PMPWorldgenProfile(PlantMegaPack.settings.startupProfile);
		PlantMegaPack.integration = new PMPIntegration();
		//PlantMegaPack.bookRegistry = new PMPBookRegistry();
	}
	
	public void init() {
		PlantMegaPack.worldGenerator.init();
		PlantMegaPack.worldgenProfile.init();
		//PlantMegaPack.bookRegistry.init();
		PlantMegaPack.recipes.init();
		PMPChestItems.initChestItems();
		PlantMegaPack.integration.init();
		
		MinecraftForge.EVENT_BUS.register(PlantMegaPack.eventHandlers);
		NetworkRegistry.INSTANCE.registerGuiHandler(PlantMegaPack.instance, new PMPGuiHandler());
		GameRegistry.registerWorldGenerator(PlantMegaPack.worldGenerator, 1);
	}
	
	public void postInit() {
		PlantMegaPack.worldGenerator.postInit();
	}
}

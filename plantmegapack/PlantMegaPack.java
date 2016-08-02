package plantmegapack;

import java.io.File;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import plantmegapack.book.PMPBookRegistry;
import plantmegapack.core.PMPBlocks;
import plantmegapack.core.PMPCreativeTab;
import plantmegapack.core.PMPEventHandler;
import plantmegapack.core.PMPIntegration;
import plantmegapack.core.PMPItems;
import plantmegapack.core.PMPProxyCommon;
import plantmegapack.core.PMPRecipes;
import plantmegapack.core.PMPSettings;
import plantmegapack.plant.PMPPlantCropDrops;
import plantmegapack.worldgen.PMPWorldGenerator;
import plantmegapack.worldgen.PMPWorldgenProfile;

@Mod(modid="plantmegapack", name="Plant Mega Pack", version="1.10.2 PORT1", canBeDeactivated=false, guiFactory="plantmegapack.gui.core.PMPGuiFactory", acceptedMinecraftVersions="[1.10.2]")
public class PlantMegaPack {
	@Mod.Instance("plantmegapack")
	public static PlantMegaPack instance;
	@SidedProxy(clientSide="plantmegapack.core.PMPProxyClient", serverSide="plantmegapack.core.PMPProxyServer")
	public static PMPProxyCommon proxy;
	private static final Logger logger = LogManager.getLogger("plantmegapack");
	public static String configPathRoot;
	public static PMPSettings settings;
	public static PMPEventHandler eventHandlers;
	public static PMPBlocks blocks;
	public static PMPItems items;
	public static PMPPlantCropDrops plantDrops;
	public static PMPCreativeTab creativeTabs;
	public static PMPRecipes recipes;
	public static PMPIntegration integration;
	public static PMPWorldGenerator worldGenerator;
	public static PMPWorldgenProfile worldgenProfile;
	public static PMPBookRegistry bookRegistry;
	
	public PlantMegaPack() {
		eventHandlers = new PMPEventHandler();
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configPathRoot = new String(event.getModConfigurationDirectory().getPath() + "//" + "plantmegapack" + "//");
		proxy.preInit();
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
	}
	
	public void logOutput(String line) {
		logger.info(line);
	}
}

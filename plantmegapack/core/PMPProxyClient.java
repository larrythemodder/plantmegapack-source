package plantmegapack.core;

import plantmegapack.PlantMegaPack;
import plantmegapack.json.PMPJsonGenerator;

public class PMPProxyClient
	extends PMPProxyCommon
{
	public void preInit() {
		super.preInit();
		PlantMegaPack.blocks.preInit();
		PMPJsonGenerator.generateJsonFiles(PlantMegaPack.configPathRoot);
	}
	
	public void init() {
		PlantMegaPack.blocks.initClient();
		PlantMegaPack.items.initClient();
		super.init();
	}
	
	public void postInit() {
		super.postInit();
		PMPReference.outputModStatisticsToConsole();
	}
}

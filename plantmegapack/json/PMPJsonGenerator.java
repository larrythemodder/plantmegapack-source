package plantmegapack.json;

import java.io.File;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPHelper;

public abstract class PMPJsonGenerator
{
	public static void generateJsonFiles(String rootPath) {
		File outputDir = new File(rootPath + "json");
		if (outputDir.exists()) {
			PMPHelper.deleteOutputDirectory(outputDir);
			PlantMegaPack.instance.logOutput("Developer .json files deleted");
		}
	}
	
	private static void checkOutputDirectories(String rootPath) {
		File outputDir = new File(rootPath + "json");
		if (!outputDir.exists()) {
			try
			{
				outputDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		outputDir = new File(rootPath + "json/blockstates");
		if (!outputDir.exists()) {
			try
			{
				outputDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		outputDir = new File(rootPath + "json/models");
		if (!outputDir.exists()) {
			try
			{
				outputDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		outputDir = new File(rootPath + "json/models/block");
		if (!outputDir.exists()) {
			try
			{
				outputDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		outputDir = new File(rootPath + "json/models/item");
		if (!outputDir.exists()) {
			try
			{
				outputDir.mkdir();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

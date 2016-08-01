package plantmegapack.worldgen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.core.PMPSettings;
import plantmegapack.data.PMPDataBiome;
import plantmegapack.gui.control.PMPGuiListbox;
import plantmegapack.habitat.PMPBiomeType;

public class PMPWorldgenProfile
{
	private String activeProfile;
	private Map<String, PMPDataBiome> biomeData = new HashMap(PMPBiomeType.values().length);
	
	public PMPWorldgenProfile(String startProfile) {
		this.activeProfile = startProfile;
	}
	
	public void init() {
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			PMPDataBiome biomeData = new PMPDataBiome(biomeType);
			if (biomeData != null) {
				this.biomeData.put(biomeType.name(), biomeData);
			}
		}
		if (isActiveProfileDefault()) {
			applyDefaultProfile();
		} else {
			loadProfile(this.activeProfile, true);
		}
	}
	
	public boolean isActiveProfileDefault() {
		return this.activeProfile.matches(PlantMegaPack.settings.getDefaultProfileName());
	}
	
	public String getActiveProfile() {
		return this.activeProfile;
	}
	
	public void setActiveProfileName(String newName) {
		this.activeProfile = newName;
	}
	
	public PMPDataBiome getBiomeData(PMPBiomeType biomeType) {
		return (PMPDataBiome)this.biomeData.get(biomeType.name());
	}
	
	public PMPDataBiome getBiomeData(String biomeTypeName) {
		return (PMPDataBiome)this.biomeData.get(biomeTypeName);
	}
	
	public void applyDefaultProfile() {
		this.activeProfile = PlantMegaPack.settings.getDefaultProfileName();
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			PMPDataBiome biomeData = (PMPDataBiome)this.biomeData.get(biomeType.name());
			if (biomeData != null) {
				biomeData.applyDefaultSettings();
				biomeData.applyHabitatDefaultsAll();
			}
		}
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput(String.format("Default profile is active (%d spawns)", new Object[] { Long.valueOf(getTotalProfileSpawnCount()) }));
		}
	}
	
	public void clearCurrentProfile() {
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput("Clear current profile");
		}
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			PMPDataBiome biomeData = (PMPDataBiome)this.biomeData.get(biomeType.name());
			if (biomeData != null) {
				biomeData.clearAll();
			}
		}
	}
	
	public boolean loadProfile(String profileName, boolean startup) {
		if (profileName.matches(PlantMegaPack.settings.getDefaultProfileName())) {
			this.activeProfile = PlantMegaPack.settings.getDefaultProfileName();
			applyDefaultProfile();
			return true;
		}
		File profileFile = new File(String.format("%sprofiles//%s.pmp", new Object[] { PlantMegaPack.configPathRoot, profileName }));
		if (!profileFile.exists()) {
			PlantMegaPack.instance.logOutput(String.format("Cannot load non-existent worldgen profile '%s' - using default profile", new Object[] { profileName }));
			if (startup) {
				PlantMegaPack.settings.applyDefaultStartupProfileName();
				PlantMegaPack.settings.saveOptions();
			}
			this.activeProfile = PlantMegaPack.settings.getDefaultProfileName();
			applyDefaultProfile();
			return false;
		}
		clearCurrentProfile();
		try
		{
			BufferedReader bufferedreader = new BufferedReader(new FileReader(profileFile));
			if (bufferedreader != null) {
				String line = "";
				while ((line = bufferedreader.readLine()) != null) {
					if ((line.length() > 0) && (!line.startsWith("#"))) {
						String[] dataLine = line.split("=");
						PMPDataBiome biomeData = (PMPDataBiome)this.biomeData.get(dataLine[0]);
						if (biomeData != null) {
							biomeData.loadSettingsDataLine(dataLine[1]);
						}
					}
				}
				bufferedreader.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			PlantMegaPack.instance.logOutput(String.format("Error loading worldgen profile '%s' - using default profile", new Object[] { profileName }));
			clearCurrentProfile();
			applyDefaultProfile();
			return false;
		}
		this.activeProfile = profileName;
		PlantMegaPack.instance.logOutput(String.format("Worldgen profile '%s' loaded and active (%d spawns)", new Object[] { profileName, Long.valueOf(getTotalProfileSpawnCount()) }));
		return true;
	}
	
	public boolean saveActiveProfile() {
		if (isActiveProfileDefault()) {
			return true;
		}
		if ((this.activeProfile == null) || (this.activeProfile.isEmpty())) {
			return false;
		}
		File profileFile = new File(String.format("%sprofiles//%s.pmp", new Object[] { PlantMegaPack.configPathRoot, this.activeProfile }));
		try
		{
			if (!profileFile.exists()) {
				profileFile.createNewFile();
				profileFile.setWritable(true);
			}
			PrintWriter printwriter = new PrintWriter(new FileWriter(profileFile));
			if (printwriter != null) {
				printwriter.println("# Plant Mega Pack Worldgen Profile");
				for (PMPBiomeType biomeType : PMPBiomeType.values()) {
					PMPDataBiome biomeData = (PMPDataBiome)this.biomeData.get(biomeType.name());
					if (biomeData != null) {
						biomeData.saveSettings(printwriter);
					}
				}
				printwriter.close();
			}
		}
		catch (Exception e) {
			PlantMegaPack.instance.logOutput(String.format("Error saving worldgen profile '%s'", new Object[] { this.activeProfile }));
			return false;
		}
		if (PlantMegaPack.settings.debugMode) {
			PlantMegaPack.instance.logOutput(String.format("Worldgen profile '%s' saved", new Object[] { this.activeProfile }));
		}
		return true;
	}
	
	public boolean deleteProfile(String profile) {
		File profileFile = new File(String.format("%sprofiles//%s.pmp", new Object[] { PlantMegaPack.configPathRoot, profile }));
		try
		{
			if ((profileFile.exists()) && (profileFile.delete())) {
				PlantMegaPack.instance.logOutput(String.format("Profile '%s' deleted", new Object[] { profile }));
				if (profile.matches(PlantMegaPack.settings.startupProfile)) {
					PlantMegaPack.settings.startupProfile = PlantMegaPack.settings.getDefaultProfileName();
				}
				if (profile.matches(this.activeProfile)) {
					this.activeProfile = PlantMegaPack.settings.getDefaultProfileName();
				}
				return true;
			}
		}
		catch (Exception e) {
			PlantMegaPack.instance.logOutput(String.format("Error deleting profile '%s'", new Object[] { profile }));
		}
		return false;
	}
	
	public long getTotalProfileSpawnCount() {
		long count = 0L;
		for (PMPBiomeType biomeType : PMPBiomeType.values()) {
			PMPDataBiome biomeData = (PMPDataBiome)this.biomeData.get(biomeType.name());
			if (biomeData != null) {
				count += biomeData.getTotalBiomeSpawnCount();
			} else
			{
				PlantMegaPack.instance.logOutput("Error counting profile spawns");
				return 0L;
			}
		}
		return count;
	}
	
	public void populateGuiListWithProfiles(PMPGuiListbox list) {
		list.addListboxEntry(PlantMegaPack.settings.getDefaultProfileName(), formatGuiListProfileLine(PlantMegaPack.settings.getDefaultProfileName()));
		File profileDirectory = new File(String.format("%sprofiles//", new Object[] { PlantMegaPack.configPathRoot }));
		File[] files = profileDirectory.listFiles();
		if (files == null) {
			return;
		}
		for (File file : files) {
			if ((file != null) && (!file.isDirectory()) && (file.getAbsolutePath().endsWith(".pmp"))) {
				String fileName = file.getName();
				fileName = fileName.substring(0, fileName.lastIndexOf("."));
				list.addListboxEntry(fileName, formatGuiListProfileLine(fileName));
			}
		}
	}
	
	private String formatGuiListProfileLine(String profileName) {
		boolean active = profileName.matches(PlantMegaPack.worldgenProfile.getActiveProfile());
		boolean startup = profileName.matches(PlantMegaPack.settings.startupProfile);
		return String.format("%s%s%s", new Object[] { profileName, active ? " §e(" + I18n.translateToLocal("gui.active") + ")§r" : "", startup ? " §a(" + I18n.translateToLocal("gui.startup") + ")§r" : "" });
	}
}

package plantmegapack.plant;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.text.translation.I18n;
import plantmegapack.PlantMegaPack;
import plantmegapack.object.PMPTab;

public enum PMPPlantCategory {
	bamb('2', PlantMegaPack.tabLand, PMPPlantSoilType.beac, true),
	beac('e', PlantMegaPack.tabLand, PMPPlantSoilType.beac, false),
	berr('a', PlantMegaPack.tabCrop, PMPPlantSoilType.norm, true),
	cact('2', PlantMegaPack.tabLand, PMPPlantSoilType.sand, true),
	clim('6', PlantMegaPack.tabLand, PMPPlantSoilType.norm, true),
	cora('e', PlantMegaPack.tabAqua, PMPPlantSoilType.aqua, false),
	crop('6', PlantMegaPack.tabCrop, PMPPlantSoilType.norm, true),
	crpa('b', PlantMegaPack.tabCrop, PMPPlantSoilType.aqua, true),
	dese('e', PlantMegaPack.tabLand, PMPPlantSoilType.sand, false),
	epip('5', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	fern('a', PlantMegaPack.tabLand, PMPPlantSoilType.norm, true),
	floa('b', PlantMegaPack.tabAqua, PMPPlantSoilType.wate, false),
	flwm('c', PlantMegaPack.tabFlow, PMPPlantSoilType.norm, false),
	flws('c', PlantMegaPack.tabFlow, PMPPlantSoilType.norm, false),
	fore('2', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	fres('b', PlantMegaPack.tabAqua, PMPPlantSoilType.aqua, false),
	fung('6', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	gras('a', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	grou('2', PlantMegaPack.tabLand, PMPPlantSoilType.grou, false),
	imme('d', PlantMegaPack.tabAqua, PMPPlantSoilType.aqua, false),
	jung('2', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	lgpl('e', PlantMegaPack.tabLand, PMPPlantSoilType.beac, false),
	mesa('4', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	moun('d', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	plai('a', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	salt('9', PlantMegaPack.tabAqua, PMPPlantSoilType.aqua, false),
	sava('e', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	shru('2', PlantMegaPack.tabLand, PMPPlantSoilType.norm, false),
	vine('a', PlantMegaPack.tabLand, PMPPlantSoilType.norm, true),
	wetl('3', PlantMegaPack.tabLand, PMPPlantSoilType.wetl, false);
	
	public final char colorCode;
	public final CreativeTabs creativeTab;
	public final PMPPlantSoilType soilType;
	public final boolean tickRandomly;
	
	private PMPPlantCategory(char colorCode, CreativeTabs creativeTab, PMPPlantSoilType soilType, boolean tickRandomly) {
		this.colorCode = colorCode;
		this.creativeTab = creativeTab;
		this.soilType = soilType;
		this.tickRandomly = tickRandomly;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("cat." + name());
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("%s", new Object[] { Character.valueOf(this.colorCode) }) + I18n.translateToLocal(new StringBuilder().append("cat.").append(name()).toString());
	}
}

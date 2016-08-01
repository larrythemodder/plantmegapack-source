package plantmegapack.plant;

import net.minecraft.util.text.translation.I18n;
import plantmegapack.object.PMPTab;

public enum PMPPlantCategory {
	bamb('2', PMPTab.land, PMPPlantSoilType.beac, true),
	beac('e', PMPTab.land, PMPPlantSoilType.beac, false),
	berr('a', PMPTab.crop, PMPPlantSoilType.norm, true),
	cact('2', PMPTab.land, PMPPlantSoilType.sand, true),
	clim('6', PMPTab.land, PMPPlantSoilType.norm, true),
	cora('e', PMPTab.aqua, PMPPlantSoilType.aqua, false),
	crop('6', PMPTab.crop, PMPPlantSoilType.norm, true),
	crpa('b', PMPTab.crop, PMPPlantSoilType.aqua, true),
	dese('e', PMPTab.land, PMPPlantSoilType.sand, false),
	epip('5', PMPTab.land, PMPPlantSoilType.norm, false),
	fern('a', PMPTab.land, PMPPlantSoilType.norm, true),
	floa('b', PMPTab.aqua, PMPPlantSoilType.wate, false),
	flwm('c', PMPTab.flow, PMPPlantSoilType.norm, false),
	flws('c', PMPTab.flow, PMPPlantSoilType.norm, false),
	fore('2', PMPTab.land, PMPPlantSoilType.norm, false),
	fres('b', PMPTab.aqua, PMPPlantSoilType.aqua, false),
	fung('6', PMPTab.land, PMPPlantSoilType.norm, false),
	gras('a', PMPTab.land, PMPPlantSoilType.norm, false),
	grou('2', PMPTab.land, PMPPlantSoilType.grou, false),
	imme('d', PMPTab.aqua, PMPPlantSoilType.aqua, false),
	jung('2', PMPTab.land, PMPPlantSoilType.norm, false),
	lgpl('e', PMPTab.land, PMPPlantSoilType.beac, false),
	mesa('4', PMPTab.land, PMPPlantSoilType.norm, false),
	moun('d', PMPTab.land, PMPPlantSoilType.norm, false),
	plai('a', PMPTab.land, PMPPlantSoilType.norm, false),
	salt('9', PMPTab.aqua, PMPPlantSoilType.aqua, false),
	sava('e', PMPTab.land, PMPPlantSoilType.norm, false),
	shru('2', PMPTab.land, PMPPlantSoilType.norm, false),
	vine('a', PMPTab.land, PMPPlantSoilType.norm, true),
	wetl('3', PMPTab.land, PMPPlantSoilType.wetl, false);
	
	public final char colorCode;
	public final PMPTab creativeTab;
	public final PMPPlantSoilType soilType;
	public final boolean tickRandomly;
	
	private PMPPlantCategory(char colorCode, PMPTab creativeTab, PMPPlantSoilType soilType, boolean tickRandomly) {
		this.colorCode = colorCode;
		this.creativeTab = creativeTab;
		this.soilType = soilType;
		this.tickRandomly = tickRandomly;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("cat." + name());
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s", new Object[] { Character.valueOf(this.colorCode) }) + I18n.translateToLocal(new StringBuilder().append("cat.").append(name()).toString()) + "§r";
	}
}

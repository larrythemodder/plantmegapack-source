package plantmegapack.plant;

import net.minecraft.util.text.translation.I18n;

public enum PMPPlantConStat {
	ne(0, '7'),
	dd(1, '7'),
	lc(2, '2'),
	nt(3, 'b'),
	vu(4, 'e'),
	en(5, '6'),
	ce(6, 'c'),
	ew(7, '5'),
	ex(8, '0');
	
	private static final PMPPlantConStat[] ID_LOOKUP;
	private final int ID;
	private final char formatColor;
	
	private PMPPlantConStat(int ID, char formatColor) {
		this.ID = ID;
		this.formatColor = formatColor;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public char getFormatColor() {
		return this.formatColor;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("conStat." + name() + ".name");
	}
	
	public String getLocalizedNameFormatted() {
		return "§" + this.formatColor + I18n.translateToLocal(new StringBuilder().append("conStat.").append(name()).append(".name").toString()) + "§r";
	}
	
	public String getLocalizedDescription() {
		return I18n.translateToLocal("conStat." + name() + ".desc");
	}
	
	public static PMPPlantConStat getStatusByID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPPlantConStat[values().length];
		
		PMPPlantConStat[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPPlantConStat var3 = var0[var2];
			ID_LOOKUP[var3.getID()] = var3;
		}
	}
}

package plantmegapack.object;

import net.minecraft.util.text.translation.I18n;

public enum PMPStem
{
	stemHard(0, "stemHard", '6'),	stemSoft(1, "stemSoft", 'a'),	stemCactus(2, "stemCactus", '2');
	
	private static final PMPStem[] ID_LOOKUP;
	public final int ID;
	public final String oreDictName;
	public final char formatCode;
	
	private PMPStem(int ID, String oreDictName, char formatCode) {
		this.ID = ID;
		this.oreDictName = oreDictName;
		this.formatCode = formatCode;
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("item." + name() + ".name");
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s", new Object[] { Character.valueOf(this.formatCode) }) + getLocalizedName() + "§r";
	}
	
	public static PMPStem getStemFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPStem[values().length];
		
		PMPStem[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPStem var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}

package plantmegapack.object;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.translation.I18n;

public enum PMPColor
	implements IStringSerializable
{
	red(0, '4', 1),	orange(1, '6', 14),	yellow(2, 'e', 11),	green(3, 'a', 10),	cyan(4, '3', 6),	blue(5, 'b', 12),	purple(6, '5', 5),	pink(7, 'c', 9),	white(8, 'f', 7);
	
	private static final PMPColor[] ID_LOOKUP;
	public final int ID;
	public final char formatCode;
	public final int dyeID;
	
	private PMPColor(int ID, char formatCode, int dyeID) {
		this.ID = ID;
		this.formatCode = formatCode;
		this.dyeID = dyeID;
	}
	
	public String getName() {
		return name();
	}
	
	public String getLocalizedName() {
		return I18n.translateToLocal("color." + name());
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s", new Object[] { Character.valueOf(this.formatCode) }) + I18n.translateToLocal(new StringBuilder().append("color.").append(name()).toString()) + "§r";
	}
	
	public String getLocalizedDyeNameFormatted() {
		EnumDyeColor dyeColor = EnumDyeColor.byDyeDamage(this.dyeID);
		char finalCode = this.formatCode == 'f' ? '7' : this.formatCode;
		return String.format("§%s", new Object[] { Character.valueOf(finalCode) }) + I18n.translateToLocal(String.format("item.dyePowder.%s.name", new Object[] { dyeColor.getUnlocalizedName() })) + "§r";
	}
	
	public static PMPColor getColorFromID(int ID) {
		if ((ID < 0) || (ID >= ID_LOOKUP.length)) {
			ID = 0;
		}
		return ID_LOOKUP[ID];
	}
	
	static
	{
		ID_LOOKUP = new PMPColor[values().length];
		
		PMPColor[] var0 = values();
		int var1 = var0.length;
		for (int var2 = 0; var2 < var1; var2++) {
			PMPColor var3 = var0[var2];
			ID_LOOKUP[var3.ID] = var3;
		}
	}
}

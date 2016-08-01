package plantmegapack.habitat;

import net.minecraft.util.text.translation.I18n;

public enum PMPHabitatType {
	fres('b'),
	land('a'),
	mari('9'),
	tree('2');
	
	public final char formatCode;
	
	private PMPHabitatType(char formatCode) {
		this.formatCode = formatCode;
	}
	
	public String getLocalizedNameFormatted() {
		return String.format("§%s%s§r", new Object[] { Character.valueOf(this.formatCode), I18n.translateToLocal("habitatType." + name()) });
	}
}

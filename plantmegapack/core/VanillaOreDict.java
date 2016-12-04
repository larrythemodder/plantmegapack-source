package plantmegapack.core;

import net.minecraft.init.Items;
import net.minecraftforge.oredict.OreDictionary;

public class VanillaOreDict {
	public static void init() {
		OreDictionary.registerOre("cropApple", Items.APPLE);
		OreDictionary.registerOre("cropCarrot", Items.CARROT);
	}
}

package plantmegapack.object;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum PMPFoodType
{
	berr(0),	bott(2),	bowl(1),	fdrn(0),	flav(0),	food(0),	frui(0),	seed(0),	tea(0),	vege(0);
	
	public final int returnObject;
	
	private PMPFoodType(int returnObject) {
		this.returnObject = returnObject;
	}
	
	public Item getReturnObject() {
		switch (this.returnObject) {
		case 1: 
			return Items.BOWL;
		case 2: 
			return Items.GLASS_BOTTLE;
		}
		return null;
	}
}

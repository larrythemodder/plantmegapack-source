package plantmegapack.core;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import plantmegapack.item.PMPItemBook;
import plantmegapack.item.PMPItemFlower;
import plantmegapack.item.PMPItemFood;
import plantmegapack.item.PMPItemFragment;
import plantmegapack.item.PMPItemPlantItem;
import plantmegapack.item.PMPItemPowder;
import plantmegapack.item.PMPItemRootMedicinal;
import plantmegapack.item.PMPItemSalve;
import plantmegapack.item.PMPItemSeed;
import plantmegapack.item.PMPItemStem;
import plantmegapack.object.PMPFlower;
import plantmegapack.object.PMPFood;
import plantmegapack.object.PMPFragment;
import plantmegapack.object.PMPPlantItem;
import plantmegapack.object.PMPPowder;
import plantmegapack.object.PMPRootMedicinal;
import plantmegapack.object.PMPSalve;
import plantmegapack.object.PMPSeed;
import plantmegapack.object.PMPStem;

public class PMPItems
{
	//private PMPItemBook bookPlantGuide;
	private PMPItemRootMedicinal rootMedicinal;
	private Map<String, PMPItemFragment> coralFragments = new HashMap(PMPFragment.values().length);
	private Map<String, PMPItemFood> food = new HashMap(PMPFood.values().length);
	private Map<String, PMPItemFlower> flowers = new HashMap(PMPFlower.values().length);
	private Map<String, PMPItemPlantItem> plantItems = new HashMap(PMPPlantItem.values().length);
	private Map<String, PMPItemPowder> powders = new HashMap(PMPPowder.values().length);
	private Map<String, PMPItemSalve> salves = new HashMap(PMPSalve.values().length);
	private Map<String, PMPItemSeed> seeds = new HashMap(PMPSeed.values().length);
	private Map<String, PMPItemStem> stems = new HashMap(PMPStem.values().length);
	
	public PMPItems() {
		//this.bookPlantGuide = new PMPItemBook("bookPlantGuide");
		for (PMPPowder powder : PMPPowder.values()) {
			this.powders.put(powder.name(), new PMPItemPowder(powder));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPSalve salve : PMPSalve.values()) {
			this.salves.put(salve.name(), new PMPItemSalve(salve));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPFlower flower : PMPFlower.values()) {
			this.flowers.put(flower.name(), new PMPItemFlower(flower));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPStem stem : PMPStem.values()) {
			this.stems.put(stem.name(), new PMPItemStem(stem));
			PMPReference.addToRegisteredItems(1);
		}
		this.rootMedicinal = new PMPItemRootMedicinal("rootMedicinal");
		for (PMPPlantItem plantItem : PMPPlantItem.values()) {
			this.plantItems.put(plantItem.name(), new PMPItemPlantItem(plantItem));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPFragment coralFragment : PMPFragment.values()) {
			this.coralFragments.put(coralFragment.name(), new PMPItemFragment(coralFragment));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPFood food : PMPFood.values()) {
			this.food.put(food.name(), new PMPItemFood(food));
			PMPReference.addToRegisteredItems(1);
		}
		for (PMPSeed seed : PMPSeed.values()) {
			this.seeds.put(seed.name(), new PMPItemSeed(seed));
			PMPReference.addToRegisteredItems(1);
		}
	}
	
	public void initClient() {
		RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
		if (renderItem == null) {
			return;
		}
		//renderItem.getItemModelMesher().register(this.bookPlantGuide, 0, new ModelResourceLocation("plantmegapack:bookPlantGuide", "inventory"));
		for (PMPPowder powder : PMPPowder.values()) {
			renderItem.getItemModelMesher().register((Item)this.powders.get(powder.name()), 0, new ModelResourceLocation("plantmegapack:" + powder.name(), "inventory"));
		}
		for (PMPSalve salve : PMPSalve.values()) {
			renderItem.getItemModelMesher().register((Item)this.salves.get(salve.name()), 0, new ModelResourceLocation("plantmegapack:" + salve.name(), "inventory"));
		}
		for (PMPFlower flower : PMPFlower.values()) {
			renderItem.getItemModelMesher().register((Item)this.flowers.get(flower.name()), 0, new ModelResourceLocation("plantmegapack:" + flower.name(), "inventory"));
		}
		for (PMPStem stem : PMPStem.values()) {
			renderItem.getItemModelMesher().register((Item)this.stems.get(stem.name()), 0, new ModelResourceLocation("plantmegapack:" + stem.name(), "inventory"));
		}
		for (PMPRootMedicinal root : PMPRootMedicinal.values()) {
			renderItem.getItemModelMesher().register(this.rootMedicinal, root.ID, new ModelResourceLocation("plantmegapack:rootMedicinal", "inventory"));
		}
		for (PMPPlantItem plantItem : PMPPlantItem.values()) {
			renderItem.getItemModelMesher().register((Item)this.plantItems.get(plantItem.name()), 0, new ModelResourceLocation("plantmegapack:" + plantItem.name(), "inventory"));
		}
		for (PMPFragment coralFragment : PMPFragment.values()) {
			renderItem.getItemModelMesher().register((Item)this.coralFragments.get(coralFragment.name()), 0, new ModelResourceLocation("plantmegapack:" + coralFragment.name(), "inventory"));
		}
		for (PMPFood food : PMPFood.values()) {
			renderItem.getItemModelMesher().register((Item)this.food.get(food.name()), 0, new ModelResourceLocation("plantmegapack:" + food.name(), "inventory"));
		}
		for (PMPSeed seed : PMPSeed.values()) {
			renderItem.getItemModelMesher().register((Item)this.seeds.get(seed.name()), 0, new ModelResourceLocation("plantmegapack:" + seed.name(), "inventory"));
		}
	}
	
	/*public PMPItemBook getBookItem() {
		return this.bookPlantGuide;
	}*/
	
	public PMPItemFragment getCoralFragmentItem(PMPFragment item) {
		return (PMPItemFragment)this.coralFragments.get(item.name());
	}
	
	public PMPItemFood getFoodItem(PMPFood item) {
		return (PMPItemFood)this.food.get(item.name());
	}
	
	public PMPItemFlower getFlowerItem(PMPFlower item) {
		return (PMPItemFlower)this.flowers.get(item.name());
	}
	
	public PMPItemPlantItem getPlantItem(PMPPlantItem item) {
		return (PMPItemPlantItem)this.plantItems.get(item.name());
	}
	
	public PMPItemPowder getPowderItem(PMPPowder item) {
		return (PMPItemPowder)this.powders.get(item.name());
	}
	
	public PMPItemRootMedicinal getMedicinalRootItem() {
		return this.rootMedicinal;
	}
	
	public PMPItemSalve getSalveItem(PMPSalve item) {
		return (PMPItemSalve)this.salves.get(item.name());
	}
	
	public PMPItemSeed getSeedItem(PMPSeed item) {
		return (PMPItemSeed)this.seeds.get(item.name());
	}
	
	public PMPItemStem getStemItem(PMPStem item) {
		return (PMPItemStem)this.stems.get(item.name());
	}
}

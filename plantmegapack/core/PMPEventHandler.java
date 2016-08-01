package plantmegapack.core;

import java.io.File;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
import plantmegapack.PlantMegaPack;
import plantmegapack.book.PMPBookRegistry;

public class PMPEventHandler
{
	@SubscribeEvent(receiveCanceled=false)
	public void onItemCrafted(ItemCraftedEvent event) {
		if (PlantMegaPack.settings.bookSaveProgress) {
			//PlantMegaPack.bookRegistry.onCraftItem(event.player, event.crafting);
		}
	}
	
	@SubscribeEvent(receiveCanceled=false)
	public void onPickupItem(EntityItemPickupEvent event) {
		if (PlantMegaPack.settings.bookSaveProgress) {
			//PlantMegaPack.bookRegistry.onPickupItem(event.getEntityPlayer(), event.getItem());
		}
	}
	
	@SubscribeEvent(receiveCanceled=false)
	public void onPlayerLoad(LoadFromFile event) {
		if (PlantMegaPack.settings.bookSaveProgress) {
			//PlantMegaPack.bookRegistry.onPlayerLoad(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
		}
	}
	
	@SubscribeEvent(receiveCanceled=false)
	public void onPlayerSave(SaveToFile event) {
		if (PlantMegaPack.settings.bookSaveProgress) {
			//PlantMegaPack.bookRegistry.onPlayerSave(event.getPlayerDirectory().getPath(), event.getPlayerUUID());
		}
	}
}

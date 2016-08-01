package plantmegapack.gui.core;

import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionCategoryElement;
import net.minecraftforge.fml.client.IModGuiFactory.RuntimeOptionGuiHandler;
import plantmegapack.gui.PMPGuiMainMenu;

public class PMPGuiFactory
	implements IModGuiFactory
{
	public void initialize(Minecraft minecraftInstance) {}
	
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return PMPGuiMainMenu.class;
	}
	
	public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
	
	public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
		return null;
	}
}

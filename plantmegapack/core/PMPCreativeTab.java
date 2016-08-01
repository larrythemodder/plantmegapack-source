package plantmegapack.core;

import java.util.HashMap;
import plantmegapack.gui.core.PMPGuiCreativeTab;
import plantmegapack.object.PMPTab;

public class PMPCreativeTab
{
	private HashMap<String, PMPGuiCreativeTab> tabs = new HashMap(PMPTab.values().length);
	
	public PMPCreativeTab() {
		for (PMPTab tab : PMPTab.values()) {
			this.tabs.put(tab.name(), new PMPGuiCreativeTab(tab));
		}
	}
	
	public PMPGuiCreativeTab getTab(PMPTab tab) {
		return (PMPGuiCreativeTab)this.tabs.get(tab.name());
	}
}

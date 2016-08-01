package plantmegapack.gui.control;

public class PMPGuiListboxState
{
	public float amountScrolled;
	public float scrollMultiplier;
	public int selectedElement;
	
	public PMPGuiListboxState() {
		resetState();
	}
	
	public PMPGuiListboxState(PMPGuiListboxState sourceState) {
		saveState(sourceState);
	}
	
	public void resetState() {
		this.amountScrolled = 0.0F;
		this.scrollMultiplier = 0.0F;
		this.selectedElement = 0;
	}
	
	public void saveState(PMPGuiListboxState sourceState) {
		this.amountScrolled = sourceState.amountScrolled;
		this.scrollMultiplier = sourceState.scrollMultiplier;
		this.selectedElement = sourceState.selectedElement;
	}
}

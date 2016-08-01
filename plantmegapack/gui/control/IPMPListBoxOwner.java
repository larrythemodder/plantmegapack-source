package plantmegapack.gui.control;

public abstract interface IPMPListBoxOwner
{
	public abstract int getHeight();
	
	public abstract void listItemSelected(PMPGuiListbox paramPMPGuiListbox, int paramInt);
	
	public abstract void listItemDoubleClicked(PMPGuiListbox paramPMPGuiListbox, int paramInt);
}

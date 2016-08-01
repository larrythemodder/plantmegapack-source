package plantmegapack.gui.control;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import plantmegapack.gui.PMPGuiBase;

@SideOnly(Side.CLIENT)
public class PMPGuiSlider
	extends PMPGuiButton
{
	PMPGuiBase parent;
	private String name;
	private String labelPrefix;
	private String labelSuffix;
	private float sliderValue;
	public boolean mouseButtonDown;
	private float valueMin;
	private float valueMax;
	private float valueStep;
	private PMPGuiSliderMode sliderMode;
	private boolean notifyParentOnChange;
	
	public PMPGuiSlider(PMPGuiBase parent, int id, int xPosition, int yPosition, int width, int height, String name, int valueMin, int valueInitial, int valueMax, int valueStep) {
		super(id, PMPGuiButtonMode.NORMAL, xPosition, yPosition, width, height, name);
		this.parent = parent;
		this.name = name;
		this.labelPrefix = "";
		this.labelSuffix = "";
		this.sliderMode = PMPGuiSliderMode.normal;
		this.valueStep = valueStep;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		this.sliderValue = normalizeValue(valueInitial);
		this.notifyParentOnChange = false;
		updateDisplayString();
	}
	
	public int getHoverState(boolean state) {
		return 0;
	}
	
	protected void mouseDragged(Minecraft mc, int xPos, int yPos) {
		if (this.visible) {
			if (this.mouseButtonDown) {
				this.sliderValue = ((xPos - (this.xPosition + 4)) / (this.width - 8));
				if (this.sliderValue < 0.0F) {
					this.sliderValue = 0.0F;
				}
				if (this.sliderValue > 1.0F) {
					this.sliderValue = 1.0F;
				}
				this.sliderValue = normalizeValue(getIntValue());
				updateDisplayString();
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
			drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
			if (this.notifyParentOnChange) {
				this.parent.onSliderUpdate(this, getIntValue());
			}
		}
	}
	
	public boolean mousePressed(Minecraft mc, int xPos, int yPos) {
		if (super.mousePressed(mc, xPos, yPos)) {
			this.sliderValue = ((xPos - (this.xPosition + 4)) / (this.width - 8));
			if (this.sliderValue < 0.0F) {
				this.sliderValue = 0.0F;
			}
			if (this.sliderValue > 1.0F) {
				this.sliderValue = 1.0F;
			}
			updateDisplayString();
			this.mouseButtonDown = true;
			return true;
		}
		return false;
	}
	
	public void mouseReleased(int xPos, int yPos) {
		this.mouseButtonDown = false;
	}
	
	private float normalizeValue(float value) {
		return MathHelper.clamp_float((snapToStepClamp(value) - this.valueMin) / (this.valueMax - this.valueMin), 0.0F, 1.0F);
	}
	
	private float denormalizeValue(float value) {
		return snapToStepClamp(this.valueMin + (this.valueMax - this.valueMin) * MathHelper.clamp_float(value, 0.0F, 1.0F));
	}
	
	private float snapToStepClamp(float value) {
		value = snapToStep(value);
		return MathHelper.clamp_float(value, this.valueMin, this.valueMax);
	}
	
	private float snapToStep(float value) {
		if (this.valueStep > 0.0F) {
			value = this.valueStep * Math.round(value / this.valueStep);
		}
		return value;
	}
	
	public int getIntValue() {
		return (int)denormalizeValue(this.sliderValue);
	}
	
	public void setIntValue(int value) {
		if ((value < this.valueMin) || (value > this.valueMax)) {
			return;
		}
		this.sliderValue = normalizeValue(value);
	}
	
	public void setLabelPrefix(String prefix) {
		this.labelPrefix = prefix;
	}
	
	public void setLabelSuffix(String suffix) {
		this.labelSuffix = suffix;
	}
	
	public void setSliderMode(PMPGuiSliderMode mode) {
		if (mode == null) {
			return;
		}
		this.sliderMode = mode;
	}
	
	public void setNotifyParentOnUpdate(boolean notify) {
		this.notifyParentOnChange = notify;
	}
	
	public void updateDisplayString() {
		this.displayString = (this.labelPrefix.isEmpty() ? "" : this.labelPrefix);
		if (this.sliderMode == PMPGuiSliderMode.normal) {
			this.displayString = (this.displayString + I18n.translateToLocal(this.name) + ": ");
			this.displayString += (this.valueStep > 0.0F ? I18n.translateToLocal("options.off") : getIntValue() > 0 ? String.valueOf(getIntValue()) : String.valueOf(getIntValue()));
			if (!this.labelSuffix.isEmpty()) {
				this.displayString += this.labelSuffix;
			}
		} else if (this.sliderMode == PMPGuiSliderMode.percentage) {
			this.displayString = (this.displayString + I18n.translateToLocal(this.name) + ": ");
			this.displayString += (this.valueStep > 0.0F ? I18n.translateToLocal("options.off") : getIntValue() > 0 ? String.valueOf(getIntValue()) + "%" : String.valueOf(getIntValue()));
		}
	}
}

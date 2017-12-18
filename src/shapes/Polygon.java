package shapes;

import java.awt.Color;

public abstract class Polygon extends Shape {

	protected ControlPixel leftControl;
	protected ControlPixel rightControl;
	protected ControlPixel upControl;
	protected ControlPixel downControl;
	protected ControlPixel centerControl;
	
	protected Polygon(String name, Color c) {
		super(c, name);
	}
	
	@Override
	public ControlPixel getCenterControl() {
		return centerControl;
	}
	
	@Override
	public ControlPixel getDownControl() {
		return downControl;
	}
	
	@Override
	public ControlPixel getUpControl() {
		return upControl;
	}
	
	@Override
	public ControlPixel getLeftControl() {
		return leftControl;
	}
	
	@Override
	public ControlPixel getRightControl() {
		return rightControl;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}

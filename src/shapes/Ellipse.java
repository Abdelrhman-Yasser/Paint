/*
 * 
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

/**
 * The Class Ellipse.
 */
public class Ellipse extends Shape {

	private ControlPixel leftControl;
	private ControlPixel rightControl;
	private ControlPixel upControl;
	private ControlPixel downControl;
	private ControlPixel centerControl;
	protected double centerx;
	protected double centery;
	private double width;
	private double height;
	private Ellipse2D.Double ellipse;
	private static String name = "Ellipse";

	/**
	 * Instantiates a new ellipse.
	 *
	 * @param centerx the x coordinate of center of ellipse.
	 * @param centery the y coordinate of center of ellipse.
	 * @param endx the x coordinate of a point on the ellipse.
	 * @param endy the y coordinate of a point on the ellipse.
	 * @param c the color of ellipse.
	 */
	public Ellipse(double centerx, double centery, double endx, double endy, Color c) {
		super(c, name);
		define(centerx, centery, endx, endy, c);
	}
	
	protected Ellipse(String name, double centerx, double centery, double endx, double endy, Color c) {
		super(c, name);
		define(centerx, centery, endx, endy, c);
	}
	
	private void define(double centerx, double centery, double endx, double endy, Color c) {
		this.centerx = new Double(centerx);
		this.centery = new Double(centery);
		this.width = new Double(Math.abs(centerx - endx));
		this.height = new Double(Math.abs(centery - endy));
		this.ellipse = new Ellipse2D.Double(centerx - width / 2, centery - height / 2, width, height);
		this.centerControl = new ControlPixel(centerx, centery);
		this.leftControl = new ControlPixel(centerx - width / 2, centery);
		this.rightControl = new ControlPixel(centerx + width / 2, centery);
		this.downControl = new ControlPixel(centerx, centery + height / 2);
		this.upControl = new ControlPixel(centerx, centery - height / 2);
	}

	@Override
	public boolean contains(Point e) {
		return this.ellipse.contains(e);
	}

	@Override
	public IShape drag(double endx, double endy) {
		return new Ellipse(endx, endy,
				endx + this.width, endy + this.height, this.getColor());
	}

	@Override
	public IShape changeWidthLeft(double endx) {
		return new Ellipse(this.centerx, this.centery, endx, this.centery + this.height, this.color);
	}	

	@Override
	public IShape changeWidthRight(double endx) {
		return changeWidthLeft(endx);
	}

	@Override
	public IShape changeWidthDown(double endy) {
		return new Ellipse(this.centerx, this.centery, this.centerx + this.width, endy, this.color);
	}

	@Override
	public IShape changeWidthUp(double endy) {
		return changeWidthDown(endy);
	}
	
	@Override
	public IShape addState() {
		return new Ellipse(this.centerx, this.centery,
				this.width + this.centerx, this.height  + this.centery,
				this.getColor());
	}

	@Override
	public void draw(Graphics2D g) {
		Color old = new Color(g.getColor().getRGB());
		if (!this.color.equals(Color.white)) {
			g.setColor(this.color);
			g.fill(ellipse);
		}
		g.setColor(Color.black);
		g.draw(ellipse);
		if (this.isSelected()) {
			g.setColor(Color.black);
			this.centerControl.draw(g);
			this.leftControl.draw(g);
			this.rightControl.draw(g);
			this.upControl.draw(g);
			this.downControl.draw(g);
		}
		g.setColor(old);
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
		return super.toString() + String.format("%s %s %s %s", centerx, centery, width, height);
	}
	
}

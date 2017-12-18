/*
 * 
 */
package shapes;

import java.awt.Color;

/**
 * The Class Circle.
 */
public class Circle extends Ellipse {

	private double radius;
	private static String name = "Circle";

	/**
	 * Instantiates a new circle.
	 *
	 * @param centerx the x coordinate of center of circle
	 * @param centery the y coordinate of center of circle
	 * @param radius the radius
	 * @param c the c
	 */
	public Circle(double centerx, double centery, double radius, Color c) {
		super(name, centerx, centery, radius + centerx, radius + centery, c);
		this.radius = new Double(radius);
	}
	
	public Circle(double centerx, double centery, double pointx, double pointy, Color c) {
		this(centerx, centery,
				Math.sqrt(Math.pow(centerx - pointx, 2) + Math.pow(centery - pointy, 2)),
				c);
	}
	
	@Override
	public IShape changeWidthLeft(double endx) {
		return new Circle(this.centerx, this.centery, Math.abs(endx - this.centerx), this.color);
	}

	@Override
	public IShape changeWidthRight(double endx) {
		return changeWidthLeft(endx);
	}
	
	@Override
	public IShape changeWidthDown(double endy) {
		return new Circle(this.centerx, this.centery, Math.abs(endy - this.centery), this.color);
	}

	@Override
	public IShape changeWidthUp(double endy) {
		return changeWidthDown(endy);
	}
	
	@Override
	public IShape drag(double endx, double endy) {
		return new Circle(endx, endy, this.radius, this.color);
	}

	@Override
	public IShape addState() {
		return new Circle(this.centerx, this.centery, this.radius, this.color);
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("%s", radius);
	}
	
}
/*
 * 
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

/**
 * The Class Rectangle.
 */
public class Rectangle extends Polygon {

	protected double centerx;
	protected double centery;
	private double width;
	private double height;
	private Rectangle2D.Double rec;
	private static String name = "Rectangle";
	
	public Rectangle(double point1x, double point1y, double point2x,
			double point2y, Color c) {
		super(name, c);
		define(point1x, point1y, point2x, point2y, c);
	}
	
	protected Rectangle(String name, double point1x, double point1y, double point2x,
			double point2y, Color c) {
		super(name, c);
		define(point1x, point1y, point2x, point2y, c);
	}

	private void define(double point1x, double point1y, double point2x,
			double point2y, Color c) {
		this.centerx = point1x;
		this.centery = point1y;
		this.width = Math.abs(point1x - point2x) * 2;
		this.height = Math.abs(point1y - point2y) * 2;
		this.rec = new Rectangle2D.Double(point1x - width / 2, point1y - height / 2, width, height);
		this.centerControl = new ControlPixel(point1x, point1y);
		this.leftControl = new ControlPixel(point1x - width / 2, point1y);
		this.rightControl = new ControlPixel(point1x + width / 2, point1y);
		this.downControl = new ControlPixel(point1x, point1y + height / 2);
		this.upControl = new ControlPixel(point1x, point1y - height / 2);
	}
	

	@Override
	public IShape changeWidthLeft(double endx) {
		double newWidth = Math.abs(this.rightControl.getPoint().getX() - endx);
		return new Rectangle(endx + newWidth / 2, this.centery, endx, this.centery  + this.height / 2, this.getColor());
	}

	@Override
	public IShape changeWidthRight(double endx) {
		double newWidth = Math.abs(this.leftControl.getPoint().getX() - endx);
		return new Rectangle(endx - newWidth / 2, this.centery, endx, this.centery  + this.height / 2, this.getColor());
	}

	@Override
	public IShape changeWidthDown(double endy) {
		double newHeight = Math.abs(this.upControl.getPoint().getY() - endy);
		return new Rectangle(this.centerx, endy - newHeight / 2, this.centerx + this.width / 2, endy, this.getColor());
	}

	@Override
	public IShape changeWidthUp(double endy) {
		double newHeight = Math.abs(this.upControl.getPoint().getY() - endy);
		return new Rectangle(this.centerx, endy + newHeight / 2, this.centerx + this.width / 2, endy, this.getColor());
	}
	
	@Override
	public IShape drag(double endx, double endy) {
		return new Rectangle(endx, endy, endx + this.width / 2,  endy + this.height / 2, this.getColor());
	}
	
	@Override
	public boolean contains(Point e) {
		return this.rec.contains(e);
	}

	@Override
	public void draw(Graphics2D g) {
		Color old = new Color(g.getColor().getRGB());
		if (!this.getColor().equals(Color.white)) {
			g.setColor(this.getColor());
			g.fill(rec);
		}
		g.setColor(Color.black);
		g.draw(rec);
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
	public IShape addState() {
		return new Rectangle(this.centerx, this.centery, this.centerx + width / 2, this.centery + height / 2, this.getColor());
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("%s %s %s %s", centerx, centery,
				width, height);
	}
}
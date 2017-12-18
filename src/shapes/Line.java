/*
 * 
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

//  initialize line shape.
/**
 * The Class Line.
 */
public class Line extends Shape {

	protected double point1x;
	protected double point1y;
	protected double point2x;
	protected double point2y;
	private ControlPixel point1Control;
	private ControlPixel point2Control;
	private ControlPixel centerControl;
	private Line2D.Double line;
	private static String name = "Line";
	
	/**
	 * Instantiates a new line.
	 *
	 * @param point1x the x coordinate of p1 of line.
	 * @param point1y the y coordinate of p1 of line.
	 * @param point2x the x coordinate of p2 of line.
	 * @param point2y the y coordinate of p2 of line.
	 * @param c the color of line.
	 */
	public Line(double point1x, double point1y, double point2x, double point2y, Color c) {
		super(c, name);
		this.point1x = new Double(point1x);
		this.point1y = new Double(point1y);
		this.point2x = new Double(point2x);
		this.point2y = new Double(point2y);
		this.line = new Line2D.Double(point1x, point1y, point2x, point2y);
		this.point1Control = new ControlPixel(point1x, point1y);
		this.point2Control = new ControlPixel(point2x, point2y);
		this.centerControl = new ControlPixel((point1x + point2x) / 2, (point2y + point1y) / 2);
	}

	@Override
	public boolean contains(Point e) {
		int len = 6;
		Rectangle2D.Double inter = new Rectangle2D.Double(e.x, e.y, len, len);
		return inter.intersectsLine(this.line);
	}

	@Override
	public IShape drag(double endx, double endy) {
		double dx = endx - this.centerControl.getPoint().getX(), dy = endy - this.centerControl.getPoint().getY();
		return new Line(this.point1x + dx, this.point1y + dy, this.point2x + dx,
				this.point2y + dy, this.getColor());
	}

	@Override
	public IShape movePoint1(double endx, double endy) {
		return new Line(endx, endy, this.point2x, this.point2y, this.getColor());
	}
	
	@Override
	public IShape movePoint2(double endx, double endy) {
		return new Line(this.point1x, this.point1y, endx, endy, this.getColor());
	}

	@Override
	public IShape addState() {
	 return new Line(this.point1x, this.point1y,
				this.point2x, this.point2y, this.getColor());
	};

	@Override
	public void draw(Graphics2D g) {
		Color old = new Color(g.getColor().getRGB());
		if (this.isSelected()) {
			g.setColor(Color.black);
			this.point1Control.draw(g);
			this.point2Control.draw(g);
			this.centerControl.draw(g);
		}
		g.setColor(this.getColor());
		g.draw(this.line);
		g.setColor(old);
	}
	
	@Override
	public ControlPixel getCenterControl() {
		return centerControl;
	}
	
	@Override
	public ControlPixel getPoint1Control() {
		return point1Control;
	}
	
	@Override
	public ControlPixel getPoint2Control() {
		return point2Control;
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("%s %s %s %s", point1x, point1y,
				point2x, point2y);
	}
}

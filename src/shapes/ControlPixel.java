package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

//initialize a control pixel.
/**
 * The Class Contorol_Pixel.
 */
public class ControlPixel {

	private Rectangle2D.Double point;

	/**
	 * Instantiates a new control pixel.
	 *
	 * @param x The place of the point in x-axis
	 * @param y The place of the point in y-axis
	 */
	public ControlPixel(double x, double y) {
		int pixelLength = 10;
		this.point = new Rectangle2D.Double(x, y, pixelLength, pixelLength);
	}

	/**
	 * Pointed.
	 *
	 * @param p the point of pixel
	 * @return true, if successful
	 */
	public boolean pointed(Point p) {
		return this.point.contains(p);
	}

	/**
	 * Gets the point.
	 *
	 * @return the point
	 */
	public Rectangle2D.Double getPoint() {
		return point;
	}

	/**
	 * Draw.
	 *
	 * @param g the graphics object
	 */
	public void draw(Graphics2D g) {
		Color old = new Color(g.getColor().getRGB());
		g.setColor(Color.black);
		g.draw(point);
		g.fill(point);
		g.setColor(old);
	}

}
package shapes;

import java.awt.Color;
  
/**
 * The Class Square.
 */
public class Square extends Rectangle {

	private double sidelength;
	private static String name = "Square";

	/**
	 * Instantiates a new Square.
	 *
	 * @param centerx the x coordinate of center of square.
	 * @param centery the y coordinate of center of square.
	 * @param sideLength the side length of square.
	 * @param c the color of square.
	 */
	public Square(double centerx, double centery, double sideLength, Color c) {
		super(name, centerx, centery, sideLength + centerx, sideLength + centery, c);
		this.sidelength = new Double(sideLength);
	}
	
	public Square(double point1x, double point1y, double point2x,
			double point2y, Color c) {
		this(point1x, point1y,
				Math.sqrt(Math.pow(point1x - point2x, 2) + Math.pow(point1y - point2y, 2)),
				c);
	}

	@Override
	public IShape drag(double endx, double endy) {
		return new Square(endx, endy, this.sidelength, this.color);
	}

	@Override
	public IShape changeWidthLeft(double endx) {
		return new Square(this.centerx, this.centery, Math.abs(endx - this.centerx), this.color);
	}
	
	@Override
	public IShape changeWidthRight(double endx) {
		return changeWidthLeft(endx);
	}
	
	@Override
	public IShape changeWidthDown(double endy) {
		return new Square(this.centerx, this.centery, Math.abs(endy - this.centery), this.color);
	}
	
	@Override
	public IShape changeWidthUp(double endy) {
		return changeWidthDown(endy);
	}

	@Override
	public IShape addState() {
		return new Square(this.centerx, this.centery, this.sidelength, this.color);
	}
	
	@Override
	public String toString() {
		return super.toString() + String.format("%s", sidelength);
	}
}
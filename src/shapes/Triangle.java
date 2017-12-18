/*
 * 
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * The Class MTriangle.
 */
public class Triangle extends Polygon {
	
    private double point1x;
    private double point1y;
    private double point2x;
    private double point2y;
    private double stepx;    
    private Triangle2D triangle;
	private static String name = "Triangle";

    /**
     * Instantiates a new m triangle.
     *
     * @param point1x the x coordinate of the first point
     * @param point1y the y coordinate of the first point
     * @param point2x the x coordinate of the second point
     * @param point2y the y coordinate of the second point
     * @param color the color
     */
    public Triangle(double point1x, double point1y, double point2x, double point2y, Color color) {
    	super(name, color);
        this.point1x = point1x;
        this.point1y = point1y;
        this.point2x = point2x;
        this.point2y = point2y;
    	this.stepx = ((int)point2x - (int)point1x) * 2;
        this.triangle = new Triangle2D(point1x, point1y, point2x, point2y);
        this.centerControl = new ControlPixel(point2x, (point1y + point2y)/2);
        this.leftControl = new ControlPixel(point1x, (point1y + point2y)/2);
        this.rightControl = new ControlPixel(point1x + stepx, (point1y + point2y)/2);
        this.downControl = new ControlPixel(point2x, point1y);
        this.upControl = new ControlPixel(point2x, point2y);
    }

    @Override
    public boolean contains(Point e) {
        return this.triangle.contains(e);
    }

    @Override
	public IShape drag(double endx, double endy) {
		double stepx = endx - this.point2x;
		double stepy = endy - (this.point1y + this.point2y) / 2;
		return new Triangle(this.point1x + stepx, this.point1y + stepy, this.point2x + stepx,
				this.point2y + stepy, this.color);
	}

	@Override
	public IShape changeWidthLeft(double endx) {
		if (this.stepx < 0) {
			return new Triangle(this.point1x, this.point1y, (this.point1x + endx) / 2,
					this.point2y,this.color);
		} else {
			return new Triangle(endx, this.point1y, this.point2x + (endx - this.point1x) / 2,
					this.point2y, this.color);
		}
	}

	@Override
	public IShape changeWidthRight(double endx) {
		if (this.stepx < 0) {
			return new Triangle(endx, this.point1y, this.point2x + (endx - this.point1x) / 2,
					this.point2y, this.color);
		} else {
			return new Triangle(this.point1x, this.point1y, (this.point1x + endx) / 2,
					this.point2y, this.color);
		}
	}

	@Override
	public IShape changeWidthDown(double endy) {
		return new Triangle(this.point1x, endy, this.point2x, this.point2y, this.color);
	}
	
	@Override
	public IShape changeWidthUp(double endy) {
		return new Triangle(this.point1x, this.point1y, this.point2x, endy, this.color);
	}

	@Override
	public IShape addState() {
		return new Triangle(this.point1x, this.point1y, this.point2x, this.point2y, this.color);
	}

    @Override    
    public void draw(Graphics2D g){
    	Color old = g.getColor();
    	if (!this.color.equals(Color.white)) {
            g.setColor(this.color);
            triangle.fill(g);
        }
        g.setColor(Color.black);
        triangle.draw(g);
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
	public String toString() {
		return super.toString() + String.format("%s %s %s %s", point1x, point1y, point2x, point2y);
	}
}
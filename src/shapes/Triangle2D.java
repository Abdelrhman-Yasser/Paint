
package shapes;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * The Class Triangle2D.
 */
public class Triangle2D {

    private double point1x;
    private double point1y;
    private double point2x;
    private double point2y;
    
    private int stepx;
    
    private Line2D.Double line1;
    
    private Line2D.Double line2;
    
    private Line2D.Double line3;

	/**
	 * Instantiates a new triangle 2 D.
	 *
	 * @param point1x the point 1 x
	 * @param point1y the point 1 y
	 * @param point2x the point 2 x
	 * @param point2y the point 2 y
	 */
	public Triangle2D(double point1x, double point1y, double point2x, double point2y) {
        this.point1x = point1x;
        this.point1y = point1y;
        this.point2x = point2x;
        this.point2y = point2y;
    	this.stepx = ((int)point2x - (int)point1x) * 2;
    	this.line1 = new Line2D.Double((int)point1x,(int)point1y,(int)point1x + stepx,(int)point1y);
    	this.line2 = new Line2D.Double((int)point1x,(int)point1y,(int)point2x,(int)point2y);
    	this.line3 = new Line2D.Double((int)point1x + stepx,(int)point1y,(int)point2x,(int)point2y);
	}
	
	/**
	 * Contains.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	public boolean contains (Point e) {
		Rectangle2D.Double inter = new Rectangle2D.Double(e.x, e.y, 6, 6);
		if (stepx >= 0) {
			for (int i = (int)this.point1x; i <= (int)this.point1x + stepx; i++) {
				Line2D.Double temp = new Line2D.Double(i, this.point1y, this.point2x, this.point2y);
				if (inter.intersectsLine(temp)) {
					return true;
				}
			}
		} else {
			for (int i = (int)this.point1x + stepx; i <= (int)this.point1x; i++) {
				Line2D.Double temp = new Line2D.Double(i, this.point1y, this.point2x, this.point2y);
				if (inter.intersectsLine(temp)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Draw.
	 *
	 * @param g the g
	 */
	public void  draw (Graphics2D g) {
		g.draw(line1);
		g.draw(line2);
		g.draw(line3);
	}

	/**
	 * Fill.
	 *
	 * @param g the g
	 */
	public void  fill (Graphics2D g) {
		if (stepx >= 0) {
			for (int i = (int) this.point1x; i <= (int)this.point1x + stepx; i++) {
				g.drawLine(i, (int)this.point1y, (int)this.point2x, (int)this.point2y);
			}
		} else {
			for (int i = (int) this.point1x + stepx; i <= (int)this.point1x; i++) {
				g.drawLine(i, (int)this.point1y, (int)this.point2x, (int)this.point2y);
			}
		}		
	}
}

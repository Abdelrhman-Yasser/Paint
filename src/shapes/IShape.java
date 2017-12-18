package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;


public interface IShape {
	/**
	 * Checks if the shape is selected.
	 * @return true, if is selected
	 */
	boolean isSelected();

	/**
	 * select the shape
	 */
	void select();
	
	/**
	 * deSelect the shape
	 */	
	void deSelect();

	/**
	 * Increase or decrease the horizontal length of the shape to the left.
	 * @param endx The place of the new point in x-axis
	 * @return the new shape after modifications
	 */
	IShape changeWidthLeft(double endx);
	
	/**
	 * Increase or decrease the horizontal length of the shape to the right.
	 * @param endx The place of the new point in x-axis
	 * @return the new shape after modifications
	 */
	IShape changeWidthRight(double endx);
	
	/**
	 * Increase or decrease the vertical length of the shape downwards.
	 * @param endy The place of the new point in y-axis
	 * @return the new shape after modifications
	 */
	IShape changeWidthDown(double endy);
    
	/**
	 * Increase or decrease the vertical length of the shape upwards.
	 * @param endy The place of the new point in y-axis
	 * @return the new shape after modifications
	 */
	IShape changeWidthUp(double endy);
	
	/**
	 * Move Point 1
	 * @param endx the new place of point 1 in x-axis
	 * @param endy the new place of point 1 in y-axis
	 * @return the new shape after modifications
	 */
	IShape movePoint1(double endx , double endy);
	
	/**
	 * Move Point 2.
	 * @param endx the new place of point 2 in x-axis
	 * @param endy the new place of point 2 in y-axis
	 * @return the new shape after modifications
	 */
	IShape movePoint2(double endx , double endy);

	/**
	 * Check if the shape contains point e.
	 *
	 * @param e A point
	 * @return true, if the point is found inside the shape
	 */
	boolean contains(Point e);
	
    /**
     * Drag.
     * @param endx The place of the new point in x-axis
     * @param endy The place of the new point in y-axis
     * @return the new shape after modifications
     */
    IShape drag(double endx , double endy);	
	
	/**
	 * Draw the shape.
	 * @param g the graphics object
	 */
	void draw(Graphics2D g);

	/**
	 * Sets the color of the shape to color parameter.
	 * @param color
	 */
	void setColor(Color color);

	/**
	 * 
	 * @return the color of the Shape
	 */
	Color getColor();

	/**
	 * 
	 * @return the name of the Shape
	 */
	String getName();
	
	
	/**
	 * Used in undo and redo
	 * @return a copy of the state of the shape
	 */
    IShape addState();

	/**
	 * get control pixels.
	 * */
    ControlPixel getUpControl();
    ControlPixel getDownControl();
    ControlPixel getLeftControl();
    ControlPixel getRightControl();
    ControlPixel getCenterControl();
    ControlPixel getPoint1Control();
    ControlPixel getPoint2Control();
}

package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * The Class Shape.
 */
public abstract class Shape implements IShape {
	
	private boolean selected;
	protected Color color;
	private String name;
	
	protected Shape (Color c, String name) {
		this.name = name;
		this.selected = false;
		this.color = new Color(c.getRGB());
	}
	
	@Override
	public boolean isSelected() {
		return this.selected;
	}

	@Override
	public void select() {
		this.selected = true;
	}

	@Override
	public void deSelect() {
		this.selected = false;
	}

	@Override
	public void setColor(Color color) {
		this.color = new Color(color.getRGB());
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return name + color.toString();
	}

	@Override
	public IShape changeWidthLeft(double endx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape changeWidthRight(double endx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape changeWidthDown(double endy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape changeWidthUp(double endy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape movePoint1(double endx, double endy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IShape movePoint2(double endx, double endy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Point e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IShape drag(double endx, double endy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IShape addState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getUpControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getDownControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getLeftControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getRightControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getCenterControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getPoint1Control() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlPixel getPoint2Control() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*
	@Override
	public IShape changeWidthLeft(double endx) {
		return null;
	}

	@Override
	public IShape changeWidthRight(double endx) {
		return null;
	}

	@Override
	public IShape changeWidthDown(double endy) {
		return null;
	}

	@Override
	public IShape changeWidthUp(double endy) {
		return null;
	}

	@Override
	public IShape movePoint1(double endx, double endy) {
		return null;
	}

	@Override
	public IShape movePoint2(double endx, double endy) {
		return null;
	}

	@Override
	public boolean contains(Point e) {
		return false;
	}

	@Override
	public IShape drag(double endx, double endy) {
		return null;
	}

	@Override
	public void draw(Graphics2D g) {
	}
	
	@Override
	public IShape addState() {
		return null;
	}
	*/
	
//	/** The name. */
//	private String name;
//	
//	/** The selected. */
//	private boolean selected = false;
//	
//	/**
//	 * Checks if the shape is selected.
//	 * @return true, if is selected
//	 */
//	public boolean isSelected() {
//		return this.selected;
//	}
//	
//	/**
//	 * Sets the selected.
//	 *
//	 * @param selected the new selected
//	 */
//	public void setSelected(boolean selected) {
//		this.selected = new Boolean(selected);
//	}
//
//	/**
//	 * Instantiates a new mshape.
//	 */
//	public ShapeImp(double point1x, double point1y, double point2x, double point2y, Color c) {
//	
//	}
//	
//	/**
//	 * Gets the l control.
//	 *
//	 * @return the l control
//	 */
//	public ControlPixel getlControl() {
//		return null;
//	}
//
//	/**
//	 * Gets the r control.
//	 *
//	 * @return the r control
//	 */
//	public ControlPixel getrControl() {
//		return null;
//	}
//
//	/**
//	 * Gets the u control.
//	 *
//	 * @return the u control
//	 */
//	public ControlPixel getuControl() {
//		return null;
//	}
//
//	/**
//	 * Gets the d control.
//	 *
//	 * @return the d control
//	 */
//	public ControlPixel getdControl() {
//		return null;
//	}
//
//	/**
//	 * Gets the c control.
//	 *
//	 * @return the c control
//	 */
//	public ControlPixel getcControl() {
//		return null;
//	}
//
//	/**
//	 * Gets the name.
//	 *
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * Sets the name.
//	 *
//	 * @param name the new name
//	 */
//	public void setName(String name) {
//		this.name = new String(name);
//	}
//
//	/**
//	 * Gets the controls.
//	 *
//	 * @return the controls
//	 */
//	public ArrayList<ControlPixel> getControls() {
//		return new ArrayList<ControlPixel>();
//	}
//	
//	/**
//	 * Shift L.
//	 *
//	 * @param endx the endx
//	 * @param gui the gui
//	 */
//	public void shiftL(double endx, GUI gui){
//	}
//	
//	/**
//	 * Shift R.
//	 *
//	 * @param endx the endx
//	 * @param gui the gui
//	 */
//	public void shiftR(double endx, GUI gui){
//	}
//	
//	/**
//	 * Shift D.
//	 *
//	 * @param endy the endy
//	 * @param gui the gui
//	 */
//	public void shiftD(double endy,  GUI gui){
//	}
//    
//	/**
//	 * Shift U.
//	 *
//	 * @param endy the endy
//	 * @param gui the gui
//	 */
//	public void shiftU(double endy , GUI gui){
//	}
//	
//	/**
//	 * Shift P 1.
//	 *
//	 * @param endx the endx
//	 * @param endy the endy
//	 * @param gui the gui
//	 */
//	// for line only
//	public void shiftP1(double endx , double endy , GUI gui){
//	}
//	
//	/**
//	 * Shift P 2.
//	 *
//	 * @param endx the endx
//	 * @param endy the endy
//	 * @param gui the gui
//	 */
//	// for line only
//	public void shiftP2(double endx , double endy , GUI gui){
//	}
//	
//    /**
//     * Drag.
//     *
//     * @param endx the endx
//     * @param endy the endy
//     * @param gui the gui
//     */
//    public void drag(double endx , double endy , GUI gui){
//	}
//    
//    /**
//     * Addstate.
//     *
//     * @param grid the grid
//     */
//    public void addstate(ArrayList<ShapeImp> grid){
//    }
//
//	/**
//	 * Contains.
//	 *
//	 * @param e the e
//	 * @return true, if successful
//	 */
//	public boolean contains(Point e) {
//		return true;
//	}
//
//	/**
//	 * Draw.
//	 *
//	 * @param g the g
//	 */
//	public void draw(Graphics2D g) {
//	}
//
//	/**
//	 * Fill.
//	 *
//	 * @param color the color
//	 */
//	public void fill(Color color) {
//	}
//
//	/**
//	 * Gets the color.
//	 *
//	 * @return the color
//	 */
//	public Color getColor() {
//		return null;
//	}
//	
//	
//	/**
//	 * Gets the control 1.
//	 *
//	 * @return the control 1
//	 */
//	public ControlPixel getControl1() {
//		return null;
//	}
//
//	
//	/**
//	 * Gets the control 2.
//	 *
//	 * @return the control 2
//	 */
//	public ControlPixel getControl2(){
//		return null;
//	}
}

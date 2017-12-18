
package state;

import java.util.ArrayList;

import shapes.IShape;

//it generates a state from current paint state which helps in saving, loading, undo and redo. 
/**
 * The Class State.
 */
public class State {

	private ArrayList<IShape> grid;

	public State(ArrayList<IShape> shapes) {
		grid = new ArrayList<IShape> ();
		for (IShape iShape : shapes) {
			grid.add(iShape.addState());
		}
	}

	/**
	 * @return the grid
	 */
	public ArrayList<IShape> getGrid() {
		return grid;
	}
	
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (IShape iShape : grid) {
			stringBuilder.append(iShape.toString());
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}

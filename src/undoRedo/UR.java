/*
 * 
 */
package undoRedo;

import java.util.ArrayList;

import shapes.IShape;
import state.State;

/**
 * The Class undo/redo main core.
 */
public class UR {

	private static UR instance;
	private ArrayList<State> states;
	private int currentIndex = -1;
	
	private UR() {
		this.states = new ArrayList<State>();
		this.states.add(new State(new ArrayList<IShape>()));
		currentIndex++;
	}

	public static UR getInstance() {
		if (instance == null) {
			instance = new UR();
		}
		return instance;
	}
	
	/**
	 * Adds the state : add one more state to window application data base.
	 *
	 * @param currentGrid the current grid
	 */
	public void addState(State currentGrid) {
		for(int i = this.states.size() - 1; i > currentIndex ; i--) {
			this.states.remove(i);
			
		}
		this.states.add(currentGrid);
		this.currentIndex++;
	}

	/**
	 * Current : get to current state.
	 *
	 * @return the state
	 */
	public State current() {
		return this.states.get(currentIndex);
	}

	/**
	 * Undo : get to previous state.
	 *
	 * @return the state
	 */
	public State undo() {
		this.currentIndex--;
		return this.states.get(currentIndex);
	}

	/**
	 * Can undo : state if can user undo or not.
	 *
	 * @return true, if successful
	 */
	public boolean canUndo() {
		return this.currentIndex > 0;
	}

	/**
	 * Can redo : : state if can user redo or not.
	 *
	 * @return true, if successful
	 */
	public boolean canRedo() {
		return this.currentIndex < this.states.size() - 1;
	}

	/**
	 * Redo : get to next state.
	 *
	 * @return the state
	 */
	public State redo() {
		this.currentIndex++;
		return this.states.get(currentIndex);
	}

}

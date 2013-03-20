package maze.elems;

import maze.cli.invalidSize;

public class Sword extends GameElem {

	private boolean picked_up = false;

	public Sword(int[] pos) throws invalidSize {
		super(pos);
	}
	/*
	 * 'getters'
	 */
	public boolean isPickedUp() {
		return picked_up;
	}

	/*
	 * a espada foi apanhada
	 */
	public void pickUp() {
		picked_up = true;
	}

	/*
	 * a espada foi largada
	 */
	public void putDown() {
		picked_up = false;
	}

	public char getCurrCharacter() {
		if (picked_up)
			return ' ';
		else
			return 'E';
	}
}

package maze.elems;

import maze.exceptions.*;

public class Sword extends GameElem {

	private boolean picked_up = false;

	private static int[] default_sword_pos = { 8, 1 };

	public Sword() throws invalidSize {
		super(default_sword_pos);
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
	public void putDown(int pos[]) {
		this.setHorizPos(pos[1]);
		this.setVertPos(pos[0]);
		picked_up = false;
	}

	public char getCurrCharacter() {
		if (picked_up)
			return ' ';
		else
			return 'E';
	}
}

package maze.elems;

import maze.cli.invalidSize;

public class MazeExit extends GameElem {

	private boolean is_open;

	public MazeExit(int[] pos) throws invalidSize {
		super(pos);
		is_open = false;
	}

	public boolean isOpen() {
		return is_open;
	}

	public void Open() {
		is_open = true;
	}

	public char getCurrCharacter() {
			return 'S';
	}
}

package maze.elems;

import maze.exceptions.*;

public class MazeExit extends GameElem {

	private boolean is_open;

	private static int[] default_exit_pos = { 5, 9 };

	public MazeExit() throws invalidSize {
		super(default_exit_pos);
		is_open = false;
	}

	public boolean isOpen() {
		return is_open;
	}

	public void Open() {
		is_open = true;
	}

	public char getCurrCharacter() {
		if (is_open)
			return 'S';
		else
			return ' ';
	}
}

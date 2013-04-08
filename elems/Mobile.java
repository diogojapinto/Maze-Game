package maze.elems;

import maze.exceptions.*;
import maze.elems.GameElem;
import maze.logic.Maze;
import maze.logic.Movement;
import maze.logic.mazebuilder.*;

public abstract class Mobile extends GameElem {

	protected boolean alive = true;

	public Mobile(int[] pos) throws invalidSize {
		super(pos);
	}

	public boolean move(int dir, Maze maze) {
		int[] next_pos = pos.clone();

		switch (dir) {
		case Movement.UP:
			next_pos[0]--;
			break;
		case Movement.LEFT:
			next_pos[1]--;
			break;
		case Movement.DOWN:
			next_pos[0]++;
			break;
		case Movement.RIGHT:
			next_pos[1]++;
			break;
		default:
			return false;
		}

		char maze_elem = 0;
		try {
			maze_elem = maze.getPosElem(next_pos);
		} catch (invalidSize e) {
			System.err.println("Invalid size");
		}
		if (maze_elem == MazeBuilder.PATH) {
			pos = next_pos.clone();
			return true;
		}
		return false;
	}

	public abstract char getCurrCharacter();

	public boolean isAlive() {
		return alive;
	}

	public void die() {
		alive = false;
	}
}

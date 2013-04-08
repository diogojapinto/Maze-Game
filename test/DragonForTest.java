package maze.test;

import java.util.Random;

import maze.elems.Dragon;
import maze.elems.Mobile;
import maze.exceptions.invalidSize;
import maze.logic.Maze;
import maze.logic.Movement;
import maze.logic.mazebuilder.MazeBuilder;

public class DragonForTest extends Dragon {

	public DragonForTest() throws invalidSize {
		super();
	}

	public boolean move(int dir, Maze maze) {
		if (!isAsleep()) {
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
		return false;
	}
}

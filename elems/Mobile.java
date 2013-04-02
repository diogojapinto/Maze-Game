package maze.elems;

import maze.exceptions.*;
import maze.elems.GameElem;
import maze.logic.Maze;
import maze.logic.mazebuilder.*;

public abstract class Mobile extends GameElem {

	// constantes usadas no movimento das personagens
	// colisões são processadas previamente pelo motor de jogo
	public static final int UP = 1;
	public static final int LEFT = 2;
	public static final int DOWN = 3;
	public static final int RIGHT = 4;
	protected boolean alive = true;

	public Mobile(int[] pos) throws invalidSize {
		super(pos);
	}

	public void move(int dir, Maze maze) {
		int[] next_pos = pos.clone();

		switch (dir) {
		case UP:
			next_pos[0]--;
			break;
		case LEFT:
			next_pos[1]--;
			break;
		case DOWN:
			next_pos[0]++;
			break;
		case RIGHT:
			next_pos[1]++;
			break;
		default:
			return;
		}

		char maze_elem = 0;
		try {
			maze_elem = maze.getPosElem(next_pos);
		} catch (invalidSize e) {
			System.err.println("Invalid size");
		}
		if (maze_elem == MazeBuilder.PATH) {
			pos = next_pos.clone();
		}
	}

	public abstract char getCurrCharacter();

	public boolean isAlive() {
		return alive;
	}

	public void die() {
		alive = false;
	}
}

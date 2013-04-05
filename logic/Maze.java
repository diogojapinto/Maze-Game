package maze.logic;

import maze.cli.CLIFunctions;
import maze.elems.GameElem;
import maze.elems.MazeExit;
import maze.exceptions.invalidSize;
import maze.logic.mazebuilder.DefaultBuilder;
import maze.logic.mazebuilder.MazeBuilder;
import maze.logic.mazebuilder.RandomBuilder;

public class Maze {
	MazeBuilder builder;

	private char[][] maze;

	private static Maze instance = null;

	public static Maze getInstance() {
		if (instance == null)
			instance = new Maze();
		return instance;
	}

	public void setAsRandom() {
		builder = new RandomBuilder();
	}

	public void setAsDefault() {
		builder = new DefaultBuilder();
	}

	public void buildMaze(int maze_size, MazeExit exit, GameElem[] elems) {
		while (true) {
			maze = builder.createMaze(maze_size, exit);
			try {
				if (builder.placeElements(maze_size, this, elems))
					break;
			} catch (invalidSize e) {
			}
		}
	}

	public void openExit(MazeExit exit) {
		exit.Open();
		maze[exit.getVertPos()][exit.getHorizPos()] = MazeBuilder.PATH;
	}

	public char[][] getMaze() {
		char[][] ret = maze.clone();
		for (int i = 0; i < maze.length; i++) {
			ret[i] = maze[i].clone();
		}
		return ret;
	}

	public char getPosElem(int pos[]) throws invalidSize {
		if (pos.length != 2 || pos[0] >= maze.length || pos[1] >= maze.length
				|| pos[0] < 0 || pos[1] < 0) {
			invalidSize e = new invalidSize();
			throw e;
		}

		if (maze[pos[0]][pos[1]] != MazeBuilder.WALL
				&& maze[pos[0]][pos[1]] != MazeBuilder.PATH)
			return MazeBuilder.OTHER;
		else
			return maze[pos[0]][pos[1]];
	}

	public int getMazeSize() {
		return maze.length;
	}
}

package maze.logic.mazebuilder;

import maze.exceptions.*;
import maze.elems.GameElem;
import maze.elems.MazeExit;
import maze.logic.Maze;

public abstract class MazeBuilder {
	public static final char WALL = 'X';
	public static final char PATH = ' ';
	public static final char OTHER = 'O';

	public abstract char[][] createMaze(int size, MazeExit exit);

	/*
	 * Coloca os elementos recebidos num array. Parte do pressuposto de que o
	 * heroi e' o ultimo elemento
	 */
	public abstract boolean placeElements(int size, Maze maze, GameElem[] elems)
			throws invalidSize;

}

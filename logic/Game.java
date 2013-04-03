package maze.logic;

import maze.elems.Eagle;
import maze.elems.Hero;
import maze.elems.MazeExit;
import maze.elems.Sword;

public class Game {
	private static Game instance = null;

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}
	
	private Hero hero;
	private Eagle eagle;
	private Sword sword;
	private MazeExit exit;
}

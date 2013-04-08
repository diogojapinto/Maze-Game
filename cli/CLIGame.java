package maze.cli;

import java.util.LinkedList;

import maze.elems.Dragon;
import maze.elems.Eagle;
import maze.elems.GameElem;
import maze.elems.Hero;
import maze.elems.MazeExit;
import maze.elems.Sword;
import maze.exceptions.invalidSize;
import maze.logic.Game;
import maze.logic.Movement;

public class CLIGame {
	public static void main(String[] args) {

		Game.getInstance().initializeMain();

		int maze_generation_type = CLIFunctions.getInstance().askTypeMaze();

		if (maze_generation_type == CLIFunctions.RANDOM) {
			int maze_size = CLIFunctions.getInstance().askMazeSize();

			int nr_dragons = CLIFunctions.getInstance().receiveNrDragons(
					maze_size);
			Game.getInstance().initializeDragons(nr_dragons);
			boolean type_dragons = CLIFunctions.getInstance().askTypeDragon();
			Game.getInstance().setTypeDragons(type_dragons);
			Game.getInstance().buildRandomMaze(maze_size);
		} else {
			Game.getInstance().initializeDragons(1);
			boolean type_dragons = CLIFunctions.getInstance().askTypeDragon();
			Game.getInstance().setTypeDragons(type_dragons);
			Game.getInstance().buildDefaultMaze();
		}

		char[][] maze = Game.getInstance().getFilledMaze();
		CLIFunctions.getInstance().printMaze(maze);

		do {
			int move = CLIFunctions.getInstance().receiveCmdHero();
			LinkedList<String> messages = Game.getInstance()
					.moveHeroEagle(move);
			CLIFunctions.getInstance().printStrings(messages);
			messages = Game.getInstance().moveDragons();
			CLIFunctions.getInstance().printStrings(messages);
			maze = Game.getInstance().getFilledMaze();
			CLIFunctions.getInstance().printMaze(maze);
		} while (Game.getInstance().checkGameEnds());

		System.out.println(Game.getInstance().gameEnding());
	}
}

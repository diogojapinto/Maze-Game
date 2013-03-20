package maze.logic;

import java.util.Scanner;

import javax.annotation.PreDestroy;

import maze.cli.CLIMaze;
import maze.cli.invalidSize;
import maze.elems.*;

public class Game {

	public static void main(String[] args) {
		int[] default_dragon_pos = { 3, 1 };
		int[] default_hero_pos = { 1, 1 };
		int[] default_sword_pos = { 8, 1 };
		int[] default_exit_pos = { 5, 9 };

		Hero hero;
		Eagle eagle;
		Sword sword;
		MazeExit exit;

		Maze maze = new Maze();
		while (true) {
			try {
				exit = new MazeExit(default_exit_pos);
				break;
			} catch (Exception e) {

			}
		}
		CLIMaze cli = new CLIMaze();

		int maze_size = 0;
		int maze_generation_type = cli.askTypeMaze();

		Dragon[] dragons;
		int nr_dragons;
		if (maze_generation_type == CLIMaze.RANDOM) {
			maze_size = cli.setMazeSize();
			nr_dragons = cli.receiveNrDragons(maze_size);
			dragons = new Dragon[nr_dragons];
		} else {
			dragons = new Dragon[1];
		}

		while (true) {
			try {
				for (int i = 0; i < dragons.length; i++) {
					dragons[i] = new Dragon(default_dragon_pos);
				}
				hero = new Hero(default_hero_pos);
				sword = new Sword(default_sword_pos);
				eagle = new Eagle(default_hero_pos);

				break;
			} catch (Exception e) {
				System.out.println("Error in default initial positions.");
			}
		}

		if (cli.askTypeDragon()) {
			for (Dragon d : dragons) {
				d.setAsMovable();
			}
		} else {
			for (Dragon d : dragons) {
				d.setAsFixed();
			}
		}
		int other_elems = 4;
		GameElem[] game_elements = new GameElem[dragons.length + other_elems];
		for (int i = 0; i < game_elements.length; i++) {
			// coloca os elementos que nao sao dragoes nas ultimas posições
			if (i == game_elements.length - 1) {
				game_elements[i] = exit;
			} else if (i == game_elements.length - 2) {
				game_elements[i] = eagle;
			} else if (i == game_elements.length - 3) {
				game_elements[i] = hero;
			} else if (i == game_elements.length - 4) {
				game_elements[i] = sword;
			} else {
				game_elements[i] = dragons[i];
			}
		}

		if (maze_generation_type == CLIMaze.RANDOM) {
			while (true) {
				maze.createMaze(maze_size, exit);
				try {
					if (maze.placeElements(game_elements))
						break;
				} catch (invalidSize e) {
				}
			}
		}
		cli.printMaze(maze, game_elements);

		do {
			int move = cli.receiveCmdHero();
			hero.move(move, maze);
			// funcao move a aguia com o heroi
			if (!sword.isPickedUp()) {
				if (hero.reachedSword(sword)) {
					if (hero.hasSword()) {
						maze.openExit(exit);
					}
				}
			}
			if (hero.isEagleThrown()) {
				if (eagle.hasSword()) {
					eagle.retrieveSword(sword);
				} else {
					eagle.fetchSword(sword);
				}
			}
			if (eagle.isWithHero() && eagle.isAlive())
				eagle.move(move, maze);

			if (eagle.getHorizPos() == hero.getHorizPos()
					&& eagle.getVertPos() == hero.getVertPos()
					&& eagle.hasSwordAvailable()) {
				hero.catchEagle(eagle);
				maze.openExit(exit);
			}

			for (Dragon d : dragons) {
				hero.checkConfrontation(d);
				eagle.checkConfrontation(d, sword);
				if (d.isAlive() && d.isMovable()) {
					Movement.moveRandom(d, maze);
					d.isAtSword(sword);
				}
				hero.checkConfrontation(d);
				eagle.checkConfrontation(d, sword);
			}
			hero.reachedExit(exit);
			cli.printMaze(maze, game_elements);
		} while (hero.isAlive() && !hero.isAtExit());
		if (hero.isAtExit())
			// usar mensagens em cliMaze (criar classe Messages) e usar para
			// aguia, matar dragao...
			System.out.println("Victory!!!");
		else if (!hero.isAlive())
			System.out.println("Your dead");

	}
}

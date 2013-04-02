package maze.cli;

import maze.elems.Dragon;
import maze.elems.Eagle;
import maze.elems.GameElem;
import maze.elems.Hero;
import maze.elems.MazeExit;
import maze.elems.Sword;
import maze.exceptions.invalidSize;
import maze.logic.Movement;

public class CLIGame {
	public static void main(String[] args) {

		Hero hero = new Hero();
		Eagle eagle = new Eagle(hero.getPos());
		Sword sword = new Sword();
		MazeExit exit = new MazeExit();


		int maze_size = 0;
		int maze_generation_type = CLIMaze.getInstance().askTypeMaze();

		Dragon[] dragons;
		int nr_dragons;
		if (maze_generation_type == CLIMaze.RANDOM) {
			maze_size = CLIMaze.getInstance().setMazeSize();
			nr_dragons = CLIMaze.getInstance().receiveNrDragons(maze_size);
			dragons = new Dragon[nr_dragons];
		} else {
			dragons = new Dragon[1];
		}

		for (int i = 0; i < dragons.length; i++) {
			dragons[i] = new Dragon();
		}

		if (CLIMaze.getInstance().askTypeDragon()) {
			for (Dragon d : dragons) {
				d.setAsMovable();
			}
		} else {
			for (Dragon d : dragons) {
				d.setAsFixed();
			}
		}
		
		// variavel contem o numero de personagens usadas que nao sao dragoes
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
			
		}
		CLIMaze.getInstance().printMaze(maze, game_elements);

		do {
			int move = CLIMaze.getInstance().receiveCmdHero();
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
			CLIMaze.getInstance().printMaze(maze, game_elements);
		} while (hero.isAlive() && !hero.isAtExit());
		if (hero.isAtExit())
			// usar mensagens em cliMaze (criar classe Messages) e usar para
			// aguia, matar dragao...
			System.out.println("Victory!!!");
		else if (!hero.isAlive())
			System.out.println("Your dead");

	}
}

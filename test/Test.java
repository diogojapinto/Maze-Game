package maze.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
//import org.junit.Test;
import maze.elems.*;
import maze.logic.Maze;
import maze.cli.invalidSize;

public class Test {

	/*
	 * testa os movimentos do heroi
	 */

	@org.junit.Test
	public void testSimpleMovement() {
		int[] default_hero_pos = { 1, 1 };
		Hero hero;
		Maze maze;
		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}
		//assertFalse(hero.move(1, maze));
		//assertFalse(hero.move(2, maze));
		//assertTrue(hero.move(3, maze));
		//assertTrue(hero.move(4, maze));
	}

	/*
	 * testa se o heroi na tiver a espada e colidir com o dragao
	 */
	public void killHero() {
		int[] default_hero_pos = { 1, 1 };
		int[] default_dragon_pos = { 3, 1 };
		Maze maze;
		Hero hero;
		Dragon dragon;

		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				dragon = new Dragon(default_dragon_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}

		hero.move(3, maze);

		// assertTrue(hero.checkconfrontation(dragon));
	}

	/*
	 * testa se o heroi apanha a espada
	 */
	public void catchSword() {
		int[] default_hero_pos = { 1, 1 };
		int[] default_sword_pos = { 2, 1 };
		Hero hero;
		Sword sword;
		Maze maze;

		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				sword = new Sword(default_sword_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}

		hero.move(3, maze);

		assertTrue(hero.reachedSword(sword));
	}

	/*
	 * testa se o heroi tiver a espada e colidir com o dragao o mata
	 */
	public void killDragon() {
		int[] default_hero_pos = { 1, 1 };
		int[] default_dragon_pos = { 3, 1 };
		Maze maze;
		Hero hero;
		Dragon dragon;

		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				dragon = new Dragon(default_dragon_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}

		hero.foundSword();
		hero.move(3, maze);

		// assertTrue(hero.checkConfrontation(dragon));
	}

	/*
	 * testa se o heroi consegue acabar o jogo sem ter a espada
	 */
	public void reachExitWithoutSword() {
		int[] default_hero_pos = { 1, 1 };
		int[] default_exit_pos = { 2, 1 };
		Hero hero;
		MazeExit exit;
		Maze maze;

		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				exit = new MazeExit(default_exit_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}

		hero.move(3, maze);
		// assertTrue(hero.reachedExit(exit));
	}

	/*
	 * testa se o heroi consegue acabar o jogo tendo a espada e tendo morto
	 * todos os dragoes existentes
	 */
	public void reachLegitExit() {
		int[] default_hero_pos = { 1, 1 };
		int[] default_dragon_pos = { 3, 1 };
		int[] default_exit_pos = { 4, 1 };
		Maze maze;
		Hero hero;
		Dragon dragon;
		MazeExit exit;

		while (true) {
			try {
				hero = new Hero(default_hero_pos);
				dragon = new Dragon(default_dragon_pos);
				exit = new MazeExit(default_exit_pos);
				maze = new Maze();
				break;
			} catch (Exception e) {
			}
		}

		hero.move(3, maze);
		hero.foundSword();
		hero.move(3, maze);
		hero.move(3, maze);
		// assertTrue(hero.reachedExit(exit));

	}
}
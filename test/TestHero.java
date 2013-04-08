package maze.test;

import org.junit.Test;
import static org.junit.Assert.*;

import maze.cli.CLIFunctions;
import maze.elems.*;
import maze.test.GameForTest;
import maze.logic.Maze;
import maze.logic.Movement;

public class TestHero {

	/**
	 * testa os movimentos do heroi para posicao de caminho
	 */
	@Test
	public void testMoveWhiteSpace() {

		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(0);
		GameForTest.getInstance().buildDefaultMaze();

		// criar array com movimentacoes
		int[] movement = { Movement.UP, Movement.LEFT, Movement.DOWN,
				Movement.RIGHT };

		// criar variavel com a posicao do heroi em cada iteracao
		int[] init_pos = { 5, 4 };

		// criar array com posições a verificar
		int[][] expected_pos = { { 4, 4 }, { 5, 3 }, { 6, 4 }, { 5, 5 } };

		for (int i = 0; i < movement.length; i++) {
			GameForTest.getInstance().setHeroPos(init_pos);
			assertTrue(GameForTest.getInstance().moveHero(movement[i]));
			assertArrayEquals(expected_pos[i], GameForTest.getInstance()
					.getHeroPos());
		}
	}

	/**
	 * testa os movimentos do heroi para posicao de parede
	 */
	@Test
	public void testMoveWallSpace() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(0);
		GameForTest.getInstance().buildDefaultMaze();

		// criar array com movimentações
		int[] movement = { Movement.LEFT, Movement.RIGHT, Movement.UP,
				Movement.DOWN };

		// criar arrays com as posicoes iniciais do heroi em cada iteracao
		int[][] init_pos = { { 2, 1 }, { 1, 2 } };

		// criar array com posições a verificar
		int[][] expected_pos = { { 2, 1 }, { 2, 1 }, { 1, 2 }, { 1, 2 } };

		for (int i = 0; i < movement.length; i++) {
			GameForTest.getInstance().setHeroPos(init_pos[i / 2]);
			assertFalse(GameForTest.getInstance().moveHero(movement[i]));
			assertArrayEquals(expected_pos[i], GameForTest.getInstance()
					.getHeroPos());
		}
	}

	/**
	 * testa se o heroi na tiver a espada e colidir com o dragao
	 */
	@Test
	public void pickUpSword() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(0);
		GameForTest.getInstance().buildDefaultMaze();

		// movimento que o heroi tem de fazer
		int movement = Movement.DOWN;

		// numero de movimentos
		int nr_moves = 7;

		// criar array com posições a verificar
		int[][] expected_pos = { { 2, 1 }, { 2, 1 }, { 1, 2 }, { 1, 2 } };

		for (int i = 1; i <= nr_moves; i++) {
			GameForTest.getInstance().moveHeroEagle(movement);
			if (i == nr_moves) {
				assertTrue(GameForTest.getInstance().heroHasSword());
			} else {
				assertFalse(GameForTest.getInstance().heroHasSword());
			}
		}
	}

	/**
	 * testa se o heroi sem espada morre com o contacto com o dragao
	 */
	@Test
	public void testKillHero() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(false);
		GameForTest.getInstance().buildDefaultMaze();

		// movimento que o heroi tem de fazer
		int movement = Movement.DOWN;

		// confirma que o heroi inicialmente esta vivo
		assertTrue(GameForTest.getInstance().heroIsAlive());
		// move o heroi
		GameForTest.getInstance().moveHeroEagle(movement);
		// chama a funcao que verifica o confronto
		GameForTest.getInstance().moveDragons();
		// confirma que o heroi morreu
		assertFalse(GameForTest.getInstance().heroIsAlive());
	}

	/**
	 * testa se o heroi tiver a espada e colidir com o dragao o mata
	 */
	@Test
	public void testKillDragon() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(false);
		GameForTest.getInstance().buildDefaultMaze();

		// movimento que o heroi tem de fazer
		int movement = Movement.DOWN;

		// coloca a espada entre o heroi e o dragao
		int[] sword_pos = { 2, 1 };
		GameForTest.getInstance().setSwordPos(sword_pos);

		// na funcao moveHeroEagle que se move o heroi e se verifica se este
		// apanhou a espada
		// logo isto acontece antes de verificar confronto com o dragao

		// confirma que o heroi inicialmente esta vivo
		assertTrue(GameForTest.getInstance().heroIsAlive());
		// move o heroi
		GameForTest.getInstance().moveHeroEagle(movement);
		// chama a funcao que verifica o confronto
		GameForTest.getInstance().moveDragons();
		// confirma que o ataque teve sucesso
		assertTrue(GameForTest.getInstance().heroIsAlive());
		assertTrue(GameForTest.getInstance().heroHasSword());
		assertTrue(GameForTest.getInstance().areDragonsDead());
	}

	/**
	 * testa se o heroi consegue acabar o jogo sem ter a espada
	 */
	@Test
	public void testReachExitWithSword() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(false);
		GameForTest.getInstance().buildDefaultMaze();

		// array com o movimento que o heroi tem de fazer
		int[] movement = { Movement.RIGHT, Movement.RIGHT, Movement.RIGHT,
				Movement.DOWN, Movement.DOWN, Movement.DOWN, Movement.DOWN,
				Movement.LEFT, Movement.LEFT, Movement.LEFT, Movement.DOWN,
				Movement.DOWN, Movement.DOWN, Movement.UP, Movement.UP,
				Movement.UP, Movement.UP, Movement.DOWN, Movement.RIGHT,
				Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.RIGHT,
				Movement.DOWN, Movement.DOWN, Movement.DOWN, Movement.RIGHT,
				Movement.RIGHT, Movement.UP, Movement.UP, Movement.UP,
				Movement.RIGHT };

		for (int m : movement) {
			GameForTest.getInstance().moveHeroEagle(m);
			GameForTest.getInstance().moveDragons();
		}
		assertTrue(GameForTest.getInstance().isHeroAtExit());
	}

	/**
	 * testa se o heroi consegue acabar o jogo apanhar a espada (e nao matar o
	 * dragao
	 */
	@Test
	public void testReachExitWithoutSword() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(false);
		GameForTest.getInstance().buildDefaultMaze();

		// array com o movimento que o heroi tem de fazer
		int[] movement = { Movement.RIGHT, Movement.RIGHT, Movement.RIGHT,
				Movement.RIGHT, Movement.RIGHT, Movement.RIGHT, Movement.RIGHT,
				Movement.DOWN, Movement.DOWN, Movement.DOWN, Movement.DOWN,
				Movement.RIGHT };

		for (int m : movement) {
			GameForTest.getInstance().moveHeroEagle(m);
			GameForTest.getInstance().moveDragons();
		}
		assertFalse(GameForTest.getInstance().isHeroAtExit());
	}
}
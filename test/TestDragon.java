package maze.test;

import static org.junit.Assert.*;
import maze.elems.*;
import maze.logic.Maze;
import maze.logic.Movement;
import maze.exceptions.*;

import org.junit.Test;

public class TestDragon {

	/*
	 * testa os movimentos do dragao
	 */
	@Test
	public void testDragonSimpleMovement() {

		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(true);
		GameForTest.getInstance().buildDefaultMaze();

		// criar array com movimentacoes
		int[] movement = { Movement.UP, Movement.LEFT, Movement.DOWN,
				Movement.RIGHT };

		// criar variavel com a posicao do dragao em cada iteracao
		int[] init_pos = { 5, 4 };

		// criar array com posições a verificar
		int[][] expected_pos = { { 4, 4 }, { 5, 3 }, { 6, 4 }, { 5, 5 } };

		for (int i = 0; i < movement.length; i++) {
			GameForTest.getInstance().setFirstDragonPos(init_pos);
			assertTrue(GameForTest.getInstance().moveFirstDragon(movement[i]));
			assertArrayEquals(expected_pos[i], GameForTest.getInstance()
					.getFirstDragonPos());
		}

		// testa agora movimentos contra as paredes
		movement = new int[] { Movement.LEFT, Movement.RIGHT, Movement.UP,
				Movement.DOWN };

		// criar arrays com as posicoes iniciais do heroi em cada iteracao
		int[][] init_pos2 = new int[][] { { 2, 1 }, { 1, 2 } };

		// criar array com posições a verificar
		int[][] expected_pos2 = { { 2, 1 }, { 2, 1 }, { 1, 2 }, { 1, 2 } };

		for (int i = 0; i < movement.length; i++) {
			GameForTest.getInstance().setFirstDragonPos(init_pos2[i / 2]);
			assertFalse(GameForTest.getInstance().moveFirstDragon(movement[i]));
			assertArrayEquals(expected_pos2[i], GameForTest.getInstance()
					.getFirstDragonPos());
		}
	}

	/**
	 * confirma se o dragao dorme
	 */
	@Test
	public void dragonSleeps() {
		// iniciar modulos do jogo necessarios
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(1);
		GameForTest.getInstance().setTypeDragons(true);
		GameForTest.getInstance().buildDefaultMaze();

		int movement = Movement.DOWN;

		GameForTest.getInstance().setDragonsAsleep();
		assertFalse(GameForTest.getInstance().moveFirstDragon(movement));
		assertTrue(GameForTest.getInstance().areDragonsAsleep());
	}

	/**
	 * testa se ha multiplos dragoes
	 */
	@Test
	public void MultipleDragons() {
		int nr_dragons = 8;
		GameForTest.getInstance().initializeMain();
		GameForTest.getInstance().initializeDragons(nr_dragons);
		GameForTest.getInstance().setTypeDragons(true);
		GameForTest.getInstance().buildDefaultMaze();
		
		assertEquals(nr_dragons, GameForTest.getInstance().getNrDragons());
	}
}

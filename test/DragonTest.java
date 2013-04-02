package maze.test;

import static org.junit.Assert.*;
import maze.elems.*;
import maze.logic.Maze;
import maze.exceptions.*;

import org.junit.Test;

public class DragonTest {

	@Test
	/*
	 * testa os movimentos do dragao
	 */
	public void DragonSimpleMovement() {
		int[] default_dragon_pos = { 1, 1};
		Dragon dragon;
		MazeBuilder maze;
		
		while(true)
		{
			try{
				dragon = new Dragon(default_dragon_pos);
				maze = new MazeBuilder();
				break;
			} catch(Exception e){
			}
		}
		
		//assertFalse(dragon.move(1,maze));
		//assertFalse(dragon.move(2,maze));
		//assertTrue(dragon.move(3,maze));
		//assertTrue(dragon.move(4,maze));
	}
	
	/*
	 * ve se o dragao dorme
	 */
	public void DragonAsleep(){
		int[] default_dragon_pos = { 1, 1};
		Dragon dragon;
		
		while(true)
		{
			try{
				dragon = new Dragon(default_dragon_pos);
				break;
			} catch(Exception e){
			}
		}
		
		dragon.sleep();
		
		assertEquals("Resultado: ", 'd', dragon.getCurrCharacter());
	}

	/*
	 * testa se ha multiplos dragoes
	 */
	public void MultipleDragons(){
		
	}
}

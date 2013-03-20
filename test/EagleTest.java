package maze.test;
/*
import static org.junit.Assert.*;

import maze.elems.Dragon;
import maze.elems.Eagle;
import maze.elems.Hero;
import maze.elems.Sword;
import maze.logic.Maze;

import org.junit.Test;

public class EagleTest {

	@Test
	public void testEagleHasSword() {
		Eagle eagle;
		int[] default_eagle_pos = { 1, 1};
		
		while(true)
		{
			try{
				eagle = new Eagle(default_eagle_pos);
				break;
			} catch(Exception e){
			}
		}
		
		eagle.foundSword();
		//assertTrue(eagle.foundSword());
		assertEquals('W', eagle.getCurrCharacter());
	}
	
	public void testFetchSword() {
		Eagle eagle;
		Sword sword;
		Maze maze;
		int[] default_eagle_pos = { 1, 1};
		int[] default_sword_pos = { 5, 3};
		
		while(true)
		{
			try{
				eagle = new Eagle(default_eagle_pos);
				sword = new Sword(default_sword_pos);
				maze = new Maze();
				break;
			} catch(Exception e){
			}
		}
		
		eagle.fetchSword(sword);
		//assertTrue(eagle.foundSword());
	}
	
	public void testRetrieveSword() {
		Eagle eagle;
		Sword sword;
		Maze maze;
		Hero hero;
		int[] default_eagle_pos = { 1, 1};
		int[] default_hero_pos = { 1, 1};
		int[] default_sword_pos = { 5, 3};
		
		while(true)
		{
			try{
				eagle = new Eagle(default_eagle_pos);
				sword = new Sword(default_sword_pos);
				hero = new Hero(default_hero_pos);
				maze = new Maze();
				break;
			} catch(Exception e){
			}
		}
		eagle.fetchSword(sword);
		//assertTrue(eagle.foundSword());
		eagle.retrieveSword(sword);
		hero.catchEagle(eagle);
		assertEquals('A', hero.getCurrCharacter());
	}
	
	

}
*/
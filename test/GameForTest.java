package maze.test;

import maze.elems.Dragon;
import maze.logic.Game;

/**
 * Classe que extende a classe Game, acrescentando funções necessárias
 * (nomeadadamente getters e setters) necessárias para os testes
 */
public class GameForTest extends Game {

	private static GameForTest instance = null;

	public static GameForTest getInstance() {
		if (instance == null)
			instance = new GameForTest();
		return instance;
	}

	public boolean initializeDragons(int size) {
		dragons = new Dragon[size];
		try {
			for (int i = 0; i < dragons.length; i++) {
				dragons[i] = new DragonForTest();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean moveHero(int movement) {
		return hero.move(movement, maze);
	}

	public int[] getHeroPos() {
		return hero.getPos();
	}

	public void setHeroPos(int[] pos) {
		hero.setVertPos(pos[0]);
		hero.setHorizPos(pos[1]);
	}

	public boolean heroHasSword() {
		return hero.hasSword();
	}

	public boolean heroIsAlive() {
		return hero.isAlive();
	}

	public void setSwordPos(int[] pos) {
		sword.setVertPos(pos[0]);
		sword.setHorizPos(pos[1]);
	}

	public boolean areDragonsDead() {
		for (Dragon d : dragons) {
			if (d.isAlive())
				return false;
		}
		return true;
	}

	public boolean isHeroAtExit() {
		return hero.isAtExit();
	}

	public void setFirstDragonPos(int[] pos) {
		dragons[0].setVertPos(pos[0]);
		dragons[0].setHorizPos(pos[1]);
	}

	public boolean moveFirstDragon(int movement) {
		return dragons[0].move(movement, maze);
	}

	public int[] getFirstDragonPos() {
		return dragons[0].getPos();
	}

	public void setDragonsAsleep() {
		for (Dragon d : dragons) {
			d.sleep();
		}
	}

	public void setDragonsWaked() {
		for (Dragon d : dragons) {
			d.wakeUp();
		}
	}

	public boolean areDragonsAsleep() {
		for (Dragon d : dragons) {
			if (!d.isAsleep())
				return false;
		}
		return true;
	}

	public int getNrDragons() {
		if (dragons != null)
			return dragons.length;
		else
			return 0;
	}
}

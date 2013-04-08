package maze.logic;

import java.io.Serializable;
import java.util.LinkedList;

import maze.elems.Dragon;
import maze.elems.Eagle;
import maze.elems.GameElem;
import maze.elems.Hero;
import maze.elems.MazeExit;
import maze.elems.Mobile;
import maze.elems.Sword;
import maze.exceptions.invalidSize;

public class Game implements Serializable {

	// conjunto de mensagens predefinidas
	private final String mess_killed_dragon = "You slayed a dragon!";
	private final String mess_killed_hero = "You were killed!";
	private final String mess_win_game = "You survived the maze.\nCongratulations :D!";
	private final String mess_killed_eagle = "Your eagle was killed!";
	private final String mess_hero_picked_sword = "You picked up the sword.\nRun to the exit!";
	private final String mess_eagle_retrieving_sword = "Your eagle is retrieving the sword.\nGo to where you launched it!";

	private static Game instance = null;

	public static Game getInstance() {
		if (instance == null)
			instance = new Game();
		return instance;
	}

	protected Hero hero;
	protected Eagle eagle;
	protected Sword sword;
	protected MazeExit exit;
	protected Dragon[] dragons;

	// vetor que contem todos os elementos de jogo
	protected GameElem[] elems;

	protected Maze maze;

	private NextMazeDefinitions next_definitions;

	public Game() {
		next_definitions = new NextMazeDefinitions();
	}

	public boolean initializeMain() {

		try {
			hero = new Hero();
			eagle = new Eagle(hero.getPos());
			sword = new Sword();
			exit = new MazeExit();
			maze = new Maze();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public int getMazeSize() {
		return maze.getMazeSize();
	}

	public boolean initializeDragons(int size) {
		dragons = new Dragon[size];
		try {
			for (int i = 0; i < dragons.length; i++) {
				dragons[i] = new Dragon();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void buildRandomMaze(int size) {
		maze.setAsRandom();
		updateElemsArray();
		maze.buildMaze(size, exit, elems);
	}

	public void buildDefaultMaze() {
		maze.setAsDefault();
		updateElemsArray();
		maze.buildMaze(0, exit, elems);
	}

	private void updateElemsArray() {
		int nr_elems = dragons.length + 4;
		elems = new GameElem[nr_elems];

		for (int i = 0; i < elems.length; i++) {
			// coloca os elementos que nao sao dragoes nas ultimas posições
			if (i == elems.length - 1) {
				elems[i] = exit;
			} else if (i == elems.length - 2) {
				elems[i] = eagle;
			} else if (i == elems.length - 3) {
				elems[i] = hero;
			} else if (i == elems.length - 4) {
				elems[i] = sword;
			} else {
				elems[i] = dragons[i];
			}
		}
	}

	public void setTypeDragons(boolean is_movable) {
		if (is_movable) {
			for (Dragon d : dragons) {
				d.setAsMovable();
			}
		} else {
			for (Dragon d : dragons) {
				d.setAsFixed();
			}
		}
	}

	public char[][] getFilledMaze() {
		char[][] maze_tmp = maze.getMaze();
		for (GameElem e : elems) {
			if (e instanceof Mobile) {
				if (((Mobile) e).isAlive()) {
					char elem_char = e.getCurrCharacter();
					if (elem_char != ' ') {
						maze_tmp[e.getVertPos()][e.getHorizPos()] = elem_char;
					}
				}
			} else {
				char elem_char = e.getCurrCharacter();
				if (elem_char != ' ') {
					maze_tmp[e.getVertPos()][e.getHorizPos()] = elem_char;
				}
			}
		}
		return maze_tmp;
	}

	public LinkedList<String> moveHeroEagle(int movement) {
		LinkedList<String> ret = new LinkedList<String>();
		hero.move(movement, maze);
		// funcao move a aguia com o heroi
		if (!sword.isPickedUp()) {
			if (hero.reachedSword(sword)) {
				if (hero.hasSword()) {
					maze.openExit(exit);
				}
				ret.push(mess_hero_picked_sword);
			}
		}
		if (eagle.isAlive()) {
			if (hero.isEagleThrown()) {
				if (eagle.hasSword()) {
					ret.push(mess_eagle_retrieving_sword);
					eagle.retrieveSword(sword);
				} else {
					eagle.fetchSword(sword);
				}
			}
			if (eagle.isWithHero() && eagle.isAlive())
				eagle.move(movement, maze);

			if (eagle.getHorizPos() == hero.getHorizPos()
					&& eagle.getVertPos() == hero.getVertPos()
					&& eagle.hasSwordAvailable()) {
				hero.catchEagle(eagle);
				maze.openExit(exit);
			}
		}
		hero.reachedExit(exit);
		return ret;
	}

	public LinkedList<String> moveDragons() {
		LinkedList<String> ret = new LinkedList<String>();
		for (Dragon d : dragons) {
			if (hero.checkConfrontation(d))
				ret.push(mess_killed_dragon);
			if (eagle.checkConfrontation(d, sword))
				ret.push(mess_killed_eagle);
			if (d.isAlive() && d.isMovable()) {
				Movement.getInstance().moveRandom(d, maze);
				d.isAtSword(sword);
			}
			if (hero.checkConfrontation(d))
				ret.push(mess_killed_dragon);
			if (eagle.checkConfrontation(d, sword))
				ret.push(mess_killed_eagle);
		}
		return ret;
	}

	public boolean checkGameEnds() {
		if (hero.isAlive() && !hero.isAtExit())
			return true;
		return false;
	}

	public String gameEnding() {
		if (hero.isAtExit())
			return mess_win_game;
		else
			// if (!hero.isAlive())
			return mess_killed_hero;
	}

	public void newGame() {
		initializeMain();
		initializeDragons(next_definitions.getNrDragons());
		setTypeDragons(next_definitions.areDragonsMovable());
		if (next_definitions.isMazeDefault())
			buildDefaultMaze();
		else
			buildRandomMaze(next_definitions.getMazeSize());
	}

	public boolean setNextMazeDefault() {
		next_definitions.setMazeAsDefault(true);
		return true;
	}

	public boolean setNextMazeRandom(int size) {
		next_definitions.setMazeAsDefault(false);
		next_definitions.setMazeSize(size);
		return true;
	}

	public boolean setNextDragonsFixed() {
		next_definitions.setDragonsAsMovable(false);
		return true;
	}

	public boolean setNextDragonsMovable() {
		next_definitions.setDragonsAsMovable(true);
		return true;
	}

	public boolean setNextNrDragons(int nr) {
		if (nr < 0 || nr > Math.pow((next_definitions.getMazeSize() / 5), 2.0))
			return false;
		else {
			next_definitions.setNrDragons(nr);
			return true;
		}
	}

	public int getMaxNrDragons() {
		return (int) Math.pow((next_definitions.getMazeSize() / 5), 2.0);
	}

	public static int getMaxNrDragons(int i) {
		return (int) Math.pow((i / 5), 2.0);
	}
}

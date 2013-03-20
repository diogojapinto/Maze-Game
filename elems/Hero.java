package maze.elems;

import maze.cli.invalidSize;
import maze.logic.Maze;

public class Hero extends Mobile {

	public static final int EAGLE = 5;

	private boolean found_exit = false;
	private boolean has_sword = false;
	private boolean throwed_eagle = false;

	public Hero(int[] pos) throws invalidSize {
		super(pos);
	}

	public boolean isAtExit() {
		return found_exit;
	}

	public boolean hasSword() {
		return has_sword;
	}

	/*
	 * encontrou a saída
	 */
	public void foundExit() {
		found_exit = true;
	}

	/*
	 * encontrou a espada
	 */
	public void foundSword() {
		has_sword = true;
	}

	/*
	 * verifica se o heroi e o dragao se confrontaram
	 */
	public void checkConfrontation(Dragon d) {
		if (!d.isAlive())
			return;

		if ((getVertPos() == d.getVertPos() - 1 && getHorizPos() == d
				.getHorizPos())
				|| (getVertPos() == d.getVertPos() + 1 && getHorizPos() == d
						.getHorizPos())
				|| (getHorizPos() == d.getHorizPos() - 1 && getVertPos() == d
						.getVertPos())
				|| (getHorizPos() == d.getHorizPos() + 1 && getVertPos() == d
						.getVertPos())) {
			if (!d.isAsleep() && !this.hasSword()) {
				this.die();
			}
			if (has_sword) {
				d.die();
			}

			// caso o dragao estiver a dormir e o heroi nao tiver a espada, nada
			// acontece
		}
	}

	public boolean reachedSword(Sword s) {

		if (!isAlive())
			return false;
		if (getVertPos() == s.getVertPos() && getHorizPos() == s.getHorizPos()) {
			if (!s.isPickedUp()) {
				this.foundSword();
				s.pickUp();
				return true;
			}
		}
		return false;
	}

	public void reachedExit(MazeExit exit) {
		if (!isAlive())
			return;

		if (getVertPos() == exit.getVertPos()
				&& getHorizPos() == exit.getHorizPos()) {

			if (has_sword)
				this.foundExit();
		}
	}

	public char getCurrCharacter() {
		if (!alive)
			return ' ';
		else if (has_sword)
			return 'A';
		else
			return 'H';
	}

	public void move(int dir, Maze maze) {
		if (dir == EAGLE && !this.hasSword()) {
			this.throwEagle();
		} else
			super.move(dir, maze);
	}

	public boolean isEagleThrown() {
		return throwed_eagle;
	}

	public void throwEagle() {
		throwed_eagle = true;
	}

	public void catchEagle(Eagle eagle) {
		throwed_eagle = false;
		if (eagle.hasSword()) {
			this.foundSword();
			eagle.setAsUsed();
		}
	}
}

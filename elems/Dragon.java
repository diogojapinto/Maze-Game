package maze.elems;

import java.util.Random;

import maze.cli.invalidSize;
import maze.logic.Maze;

public class Dragon extends Mobile {

	// guarda estados do dragao
	private boolean has_sword = false;
	private boolean asleep = false;
	private boolean movable = true;

	public Dragon(int[] pos) throws invalidSize {
		super(pos);
	}

	/*
	 * 'getters'
	 */
	public boolean hasSword() {
		return has_sword;
	}

	public boolean isAsleep() {
		return asleep;
	}

	/*
	 * encontrou a espada
	 */
	public void foundSword() {
		has_sword = true;
	}

	public void dropSword() {
		has_sword = false;
	}

	/*
	 * dragao adormeceu
	 */
	public void sleep() {
		asleep = true;
	}

	public void wakeUp() {
		asleep = false;
	}

	public void isAtSword(Sword s) {
		if (!isAlive())
			return;

		if (this.getVertPos() == s.getVertPos()
				&& this.getHorizPos() == s.getHorizPos()) {
			if (!s.isPickedUp()) {
				this.foundSword();
				s.pickUp();
			}
		} else if ((this.getVertPos() != s.getVertPos() || this.getHorizPos() != s
				.getHorizPos()) && hasSword()) {
			this.dropSword();
			s.putDown();
		}
	}

	public char getCurrCharacter() {
		char res;

		if (!alive) {
			res = ' ';
		} else if (asleep && !has_sword) {
			res = 'd';
		} else if (!asleep && has_sword) {
			res = 'F';
		} else if (asleep && has_sword) {
			res = 'f';
		} else {
			res = 'D';
		}

		return res;
	}

	public void move(int dir, Maze maze) {
		if (this.isMovable()) {
			Random r = new Random();
			int sleep = r.nextInt(3) + 1;
			if (sleep == 3) {
				this.sleep();
			} else {
				this.wakeUp();
				super.move(dir, maze);
			}
		}
	}
	
	public void setAsMovable() {
		movable = true;
	}
	
	public void setAsFixed() {
		movable = false;
	}
	
	public boolean isMovable() {
		return movable;
	}
}

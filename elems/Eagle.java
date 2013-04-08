package maze.elems;

import maze.exceptions.*;
import maze.logic.Movement;

public class Eagle extends Mobile {

	private boolean alive = true;
	private boolean has_sword = false;
	private boolean flying = false;
	private boolean is_with_hero = true;
	private boolean returning = false;
	private int[] hero_pos = new int[2];

	public Eagle(int[] pos) throws invalidSize {
		super(pos);
	}

	public boolean isAlive() {
		return alive;
	}

	public void die(Sword s) {
		alive = false;
		if (this.hasSword()) {
			has_sword = false;
			s.putDown(this.getPos());
		}
	}

	public boolean hasSword() {
		return has_sword;
	}

	/*
	 * coloca a aguia em posse da espada
	 */
	public void foundSword() {
		has_sword = true;
	}

	/*
	 * verifica confronto entre a aguia e um dragao
	 */
	public boolean checkConfrontation(Dragon d, Sword s) {
		if (!d.isAlive())
			return false;

		if ((getVertPos() == d.getVertPos() - 1 && getHorizPos() == d
				.getHorizPos())
				|| (getVertPos() == d.getVertPos() + 1 && getHorizPos() == d
						.getHorizPos())
				|| (getHorizPos() == d.getHorizPos() - 1 && getVertPos() == d
						.getVertPos())
				|| (getHorizPos() == d.getHorizPos() + 1 && getVertPos() == d
						.getVertPos())) {
			if (!d.isAsleep() && !this.isFlying() && !this.isWithHero()) {
				this.die(s);
				return true;
			}
		}
		return false;
	}

	/*
	 * coloca a aguia a ir buscar a espada
	 */

	public void fetchSword(Sword s) {
		if (is_with_hero && !s.isPickedUp() && alive) {
			flying = true;
			is_with_hero = false;
			hero_pos = this.pos.clone();
			returning = false;
			return;
		} else if (!returning) {
			int[] inc_pos = Movement.getInstance().moveRect(
					hero_pos[0] - s.getVertPos(),
					hero_pos[1] - s.getHorizPos(), this.getVertPos(),
					this.getHorizPos(), s.getVertPos(), s.getHorizPos());

			this.setVertPos(inc_pos[0] + this.getVertPos());
			this.setHorizPos(inc_pos[1] + this.getHorizPos());
			if (this.getHorizPos() == s.getHorizPos()
					&& this.getVertPos() == s.getVertPos()) {
				returning = true;
				flying = false;
				has_sword = true;
				s.pickUp();
			}
		}
	}

	public void retrieveSword(Sword s) {
		if (returning && has_sword) {
		}
		flying = true;
		int[] inc_pos = Movement.getInstance()
				.moveRect(s.getVertPos() - hero_pos[0],
						s.getHorizPos() - hero_pos[1], this.getVertPos(),
						this.getHorizPos(), hero_pos[0], hero_pos[1]);

		this.setVertPos(inc_pos[0] + this.getVertPos());
		this.setHorizPos(inc_pos[1] + this.getHorizPos());
		if (this.getHorizPos() == hero_pos[1]
				&& this.getVertPos() == hero_pos[0]) {
			returning = false;
			flying = false;
			has_sword = true;
		}
	}

	public boolean isFlying() {
		return flying;
	}

	public boolean isReturning() {
		return returning;
	}

	public char getCurrCharacter() {
		if (is_with_hero || !alive) {
			return ' ';
		} else if (hasSword()) {
			if (flying) {
				return 'W';
			} else {
				return 'w';
			}
		} else {
			return 'B';
		}
	}

	public boolean hasSwordAvailable() {
		return !flying && has_sword && alive;
	}

	public boolean isWithHero() {
		return is_with_hero;
	}

	public void setAsUsed() {
		alive = false;
		has_sword = false;
		flying = false;
		is_with_hero = false;
		returning = false;
	}
}

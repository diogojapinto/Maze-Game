package maze.logic;

import java.util.Random;
import maze.elems.Mobile;

public class Movement {
	static int pos_counter = 0;
	
	private static Movement instance = null;
	
	public static Movement getInstance() {
		if (instance == null)
			instance = new Movement();
		return instance;
	}

	public void moveRandom(Mobile elem, Maze m) {
		Random r = new Random();
		int dir = r.nextInt(4) + 1;
		elem.move(dir, m);
	}

	public int[] moveRect(int delta_x, int delta_y, int x_act,
			int y_act, int x_fin, int y_fin) {
		int[] new_pos = new int[2];
		if (x_fin - x_act == 0 && y_fin - y_act == 0) {
			return new_pos;
		} else if (x_fin - x_act == 0) {
			if (delta_y < 0) {
				new_pos[1] = 1;
			} else {
				new_pos[1] = -1;
			}
		} else if (y_fin - y_act == 0) {
			if (delta_x < 0) {
				new_pos[0] = 1;
			} else {
				new_pos[0] = -1;
			}
		} else if (Math.abs(delta_x) > Math.abs(delta_y)) {
			if (pos_counter <= (Math.abs(delta_x)) / (Math.abs(delta_y))) {
				pos_counter++;
				if (delta_x < 0) {
					new_pos[0] = 1;
				} else {
					new_pos[0] = -1;
				}
			} else {
				pos_counter = 0;
				if (delta_y < 0) {
					new_pos[1] = 1;
				} else {
					new_pos[1] = -1;
				}
			}
		} else {
			if (pos_counter <= (Math.abs(delta_y)) / (Math.abs(delta_x))) {
				pos_counter++;
				if (delta_y < 0) {
					new_pos[1] = 1;
				} else {
					new_pos[1] = -1;
				}
			} else {
				pos_counter = 0;
				if (delta_x < 0) {
					new_pos[0] = 1;
				} else {
					new_pos[0] = -1;
				}
			}
		}
		return new_pos;
	}
}

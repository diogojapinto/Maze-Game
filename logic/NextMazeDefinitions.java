package maze.logic;

public class NextMazeDefinitions {

	private boolean is_maze_default;
	private boolean are_dragons_movable;
	private int maze_size;
	private int nr_dragons;

	public NextMazeDefinitions() {
		is_maze_default = true;
		are_dragons_movable = true;
		maze_size = 5;
		nr_dragons = 1;
	}

	/**
	 * @return the is_maze_default
	 */
	public boolean isMazeDefault() {
		return is_maze_default;
	}

	/**
	 * @param is_maze_default
	 *            the is_maze_default to set
	 */
	public void setMazeAsDefault(boolean is_maze_default) {
		this.is_maze_default = is_maze_default;
	}

	/**
	 * @return the are_dragons_fixed
	 */
	public boolean areDragonsMovable() {
		return are_dragons_movable;
	}

	/**
	 * @param are_dragons_fixed
	 *            the are_dragons_fixed to set
	 */
	public void setDragonsAsMovable(boolean are_dragons_movable) {
		this.are_dragons_movable = are_dragons_movable;
	}

	/**
	 * @return the maze_size
	 */
	public int getMazeSize() {
		return maze_size;
	}

	/**
	 * @param maze_size
	 *            the maze_size to set
	 */
	public void setMazeSize(int maze_size) {
		this.maze_size = maze_size;
	}

	/**
	 * @return the nr_dragons
	 */
	public int getNrDragons() {
		return nr_dragons;
	}

	/**
	 * @param nr_dragons
	 *            the nr_dragons to set
	 */
	public void setNrDragons(int nr_dragons) {
		this.nr_dragons = nr_dragons;
	}
}

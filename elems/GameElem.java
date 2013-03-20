package maze.elems;

import maze.cli.invalidSize;

// introduzir metodos final

public abstract class GameElem {
	/*
	 * array com posi��es na matriz 'maze': pos[0] = vertical, sentido de cima
	 * pos[1] = horizontal, sentido da esquerda para a direita
	 */
	protected int[] pos;

	public GameElem(int[] pos) throws invalidSize {
		if (pos.length != 2) {
			invalidSize e = new invalidSize();
			throw e;
		}
		this.pos = pos.clone();
	}

	/*
	 * retorna a posi��o
	 */
	public int[] getPos() {
		return pos.clone();
	}

	/*
	 * retorna a posicao vertical
	 */
	public int getVertPos() {
		return pos[0];
	}

	/*
	 * retorna a posicao horizontal
	 */
	public int getHorizPos() {
		return pos[1];
	}

	/*
	 * altera a posicao vertical
	 */
	public void setVertPos(int v_pos) {
		if (v_pos >= 0)
			pos[0] = v_pos;
	}

	/*
	 * altera a posicao horizontal
	 */
	public void setHorizPos(int h_pos) {
		if (h_pos >= 0)
			pos[1] = h_pos;
	}

	public abstract char getCurrCharacter();
}

package maze.logic;

import java.util.Random;
import java.util.Stack;

import maze.cli.invalidSize;
import maze.elems.Dragon;
import maze.elems.Eagle;
import maze.elems.GameElem;
import maze.elems.Hero;
import maze.elems.MazeExit;

public class Maze {
	public static final char WALL = 'X';
	public static final char PATH = ' ';
	public static final char OTHER = 'O';

	/*
	 * campo que aloja o labirinto, sendo iniciado com o predefinido (aula 1)
	 */
	private char[][] maze = {
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
			{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
			{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

	public void createMaze(int size, MazeExit exit) {
		maze = new char[size][size];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				maze[i][j] = WALL;
			}
		}

		int[] actual_pos = new int[2]; // vetor com a posicao onde estamos neste
										// momento

		Random r = new Random();
		int x = r.nextInt(size - 2) + 1;
		int y = r.nextInt(2) + 1;
		int z = r.nextInt(2) + 1;
		switch (y) {
		case 1:
			switch (z) {
			case 1:
				maze[x][0] = ' ';
				exit.setVertPos(x);
				exit.setHorizPos(0);
				actual_pos[0] = exit.getVertPos();
				actual_pos[1] = exit.getHorizPos() + 1;
				break;
			case 2:
				maze[x][size - 1] = ' ';
				exit.setVertPos(x);
				exit.setHorizPos(size - 1);
				actual_pos[0] = exit.getVertPos();
				actual_pos[1] = exit.getHorizPos() - 1;
				break;
			default:
				break;
			}
			break;
		case 2:
			switch (z) {
			case 1:
				maze[0][x] = ' ';
				exit.setVertPos(0);
				exit.setHorizPos(x);
				actual_pos[0] = exit.getVertPos() + 1;
				actual_pos[1] = exit.getHorizPos();
				break;
			case 2:
				maze[size - 1][x] = PATH;
				exit.setVertPos(size - 1);
				exit.setHorizPos(x);
				actual_pos[0] = exit.getVertPos() - 1;
				actual_pos[1] = exit.getHorizPos();
				break;
			default:
				break;
			}
			break;
		}

		// coloca uma parede inicialmente na saida
		maze[exit.getVertPos()][exit.getHorizPos()] = WALL;

		int n_tries = 20; // numero de tentativas de arranjar uma nova posicao
		Stack<int[]> paths = new Stack<int[]>(); // stack onde ficam as posicoes

		maze[actual_pos[0]][actual_pos[1]] = PATH; // preenche quadrado
													// adjacente
													// a branco

		paths.push(actual_pos.clone());
		outer_loop: do {
			int i = 0;
			while (true) {
				int a = r.nextInt(4) + 1;

				switch (a) {
				case 1: // se for para a esquerda, ele verifica se nao esta nos
						// cantos, se ha uma parede e as condicoes dos quadrados
						// 2x2 brancos e a condicao diagonal
					if (((actual_pos[0] != 0) && (actual_pos[0] != size - 1)
							&& (actual_pos[1] - 1 != 0) && (actual_pos[1] - 1 != size - 1))
							&& maze[actual_pos[0]][actual_pos[1] - 1] != ' ') {
						int[] tmp = new int[2];
						tmp[0] = actual_pos[0];
						tmp[1] = actual_pos[1] - 1;
						if (checkWhiteSquare(tmp) && checkDiagSquare(tmp)) {
							actual_pos[1] = actual_pos[1] - 1;
							maze[actual_pos[0]][actual_pos[1]] = ' ';
							paths.push(actual_pos.clone());
						}
					}
					break;
				case 2: // se for para baixo, ele verifica se nao est� nos
						// cantos,
						// se � uma parede e as condi��es dos quadrados 2x2
						// brancos e a condi��o diagonal
					if (((actual_pos[0] + 1 != 0)
							&& (actual_pos[0] + 1 != size - 1)
							&& (actual_pos[1] != 0) && (actual_pos[1] != size - 1))
							&& maze[actual_pos[0] + 1][actual_pos[1]] != ' ') {
						int[] tmp = new int[2];
						tmp[0] = actual_pos[0] + 1;
						tmp[1] = actual_pos[1];
						if (checkWhiteSquare(tmp) && checkDiagSquare(tmp)) {
							actual_pos[0] = actual_pos[0] + 1;
							maze[actual_pos[0]][actual_pos[1]] = ' ';
							paths.push(actual_pos.clone());
						}
					}
					break;
				case 3: // se for para a direita, ele verifica se nao esta nos
						// cantos, se nao e uma parede e as condicoes dos
						// quadrados 2x2 brancos e a condicao diagonal
					if (((actual_pos[0] != 0) && (actual_pos[0] != size - 1)
							&& (actual_pos[1] + 1 != 0) && (actual_pos[1] + 1 != size - 1))
							&& maze[actual_pos[0]][actual_pos[1] + 1] != ' ') {
						int[] tmp = new int[2];
						tmp[0] = actual_pos[0];
						tmp[1] = actual_pos[1] + 1;
						if (checkWhiteSquare(tmp) && checkDiagSquare(tmp)) {
							actual_pos[1] = actual_pos[1] + 1;
							maze[actual_pos[0]][actual_pos[1]] = ' ';
							paths.push(actual_pos.clone());
						}
					}
					break;
				case 4: // se for para cima, ele verifica se nao esta nos
						// cantos, se nao e uma parede e as condicoes dos
						// quadrados 2x2 brancos e a condicao diagonal
					if (((actual_pos[0] - 1 != 0)
							&& (actual_pos[0] - 1 != size - 1)
							&& (actual_pos[1] != 0) && (actual_pos[1] != size - 1))
							&& maze[actual_pos[0] - 1][actual_pos[1]] != ' ') {
						int[] tmp = new int[2];
						tmp[0] = actual_pos[0] - 1;
						tmp[1] = actual_pos[1];
						if (checkWhiteSquare(tmp) && checkDiagSquare(tmp)) {
							actual_pos[0] = actual_pos[0] - 1;
							maze[actual_pos[0]][actual_pos[1]] = ' ';
							paths.push(actual_pos.clone());
						}
						break;
					}
				default:
					break;
				}
				i++;
				if (i >= n_tries) {
					if (paths.isEmpty()) {
						createMaze(size, exit);
						break outer_loop;
					} else {
						actual_pos = paths.pop();
						break;
					}
				}
			}
		} while (!checkWallSquare());
	}

	/*
	 * Coloca os elementos recebidos num array. Parte do pressuposto de que o
	 * heroi e' o ultimo elemento
	 */
	public boolean placeElements(GameElem[] elems) throws invalidSize {
		int n_tries = 200; // numero de tentativas de arranjar uma nova posicao
		Random r = new Random();
		for (int counter = 0; counter < elems.length; counter++) {
			// verificar que o heroi fica a pelo menos duas casas de distancia
			// do dragao
			if (elems[counter] instanceof Hero) {
				int a = 0;
				while (true) {
					elems[counter].setVertPos(r.nextInt(maze.length - 2) + 1);
					elems[counter].setHorizPos(r.nextInt(maze.length - 2) + 1);
					if (this.getPosElem(elems[counter].getPos()) == PATH) {
						boolean checked_all = true;
						// percorre a procura de dragoes
						for (GameElem d : elems) {
							if (d instanceof Dragon) {
								if (elems[counter].getVertPos() <= d
										.getVertPos() + 2
										&& elems[counter].getVertPos() >= d
												.getVertPos() - 2
										&& elems[counter].getHorizPos() <= d
												.getHorizPos() + 2
										&& elems[counter].getHorizPos() >= d
												.getHorizPos() - 2) {
									checked_all = false;
									break;
								}
							}
						}
						if (checked_all) {
							break;
						}
						a++;
						if (a > n_tries) {
							return false;
						}
					}
				}
			} else if (elems[counter] instanceof Eagle) {
				for (GameElem h : elems) {
					// se for a aguia, procura o heroi e copia deste as suas
					// posicoes
					if (h instanceof Hero) {
						elems[counter].setHorizPos(h.getHorizPos());
						elems[counter].setVertPos(h.getVertPos());
						break;
					}
				}
			} else if (elems[counter] instanceof MazeExit) {
				// nao fazer nada
			} else {
				while (true) {
					int i = r.nextInt(maze.length - 1) + 1;
					int j = r.nextInt(maze[i].length - 1) + 1;
					if (maze[i][j] == ' ') {
						elems[counter].setVertPos(i);
						elems[counter].setHorizPos(j);
						break;
					}
				}
			}
			// percorre elementos antecessores ao atual (que já foram
			// posicionados)
			boolean valid_pos = true;
			if (!(elems[counter] instanceof Eagle)) {
				for (int a = 0; a < counter; a++) {
					if (elems[a].getHorizPos() == elems[counter].getHorizPos()
							&& elems[a].getVertPos() == elems[counter]
									.getVertPos()) {
						valid_pos = false;
					}
				}
			}
			if (!valid_pos) {
				counter--;
			}
		}
		return true;
	}

	// desenhar caminho a partir de quadrado adjacente 'a saida

	// verificacoes durante o desenho:
	// quadrados 2x2 com paredes e passagem diagonais
	private boolean checkDiagSquare(int pos[]) {
		// aX
		// X_
		if (maze[pos[0]][pos[1] + 1] == 'X' && maze[pos[0] + 1][pos[1]] == 'X'
				&& maze[pos[0] + 1][pos[1] + 1] == ' ')
			return false;
		// Xa
		// _X
		else if (maze[pos[0]][pos[1] - 1] == 'X'
				&& maze[pos[0] + 1][pos[1]] == 'X'
				&& maze[pos[0] + 1][pos[1] - 1] == ' ')
			return false;
		// X_
		// aX
		else if (maze[pos[0]][pos[1] + 1] == 'X'
				&& maze[pos[0] - 1][pos[1]] == 'X'
				&& maze[pos[0] - 1][pos[1] + 1] == ' ')
			return false;
		// _X
		// Xa
		else if (maze[pos[0]][pos[1] - 1] == 'X'
				&& maze[pos[0] - 1][pos[1]] == 'X'
				&& maze[pos[0] - 1][pos[1] - 1] == ' ')
			return false;
		return true;
	}

	// quadrados 2x2 so com passagem
	private boolean checkWhiteSquare(int pos[]) {
		if (
		// a _
		// _ _
		(maze[pos[0]][pos[1] + 1] == ' ' && (maze[pos[0] + 1][pos[1]] == ' ') && (maze[pos[0] + 1][pos[1] + 1] == ' '))
				// _ _
				// a _
				|| (maze[pos[0]][pos[1] + 1] == ' '
						&& (maze[pos[0] - 1][pos[1]] == ' ') && (maze[pos[0] - 1][pos[1] + 1] == ' '))
				// _ _
				// _ a
				|| (maze[pos[0]][pos[1] - 1] == ' '
						&& (maze[pos[0] - 1][pos[1]] == ' ') && (maze[pos[0] - 1][pos[1] - 1] == ' '))
				// _ a
				// _ _
				|| (maze[pos[0]][pos[1] - 1] == ' '
						&& (maze[pos[0] + 1][pos[1]] == ' ') && (maze[pos[0] + 1][pos[1] - 1] == ' ')))
			return false;
		else
			return true;
	}

	// verificacao final
	// quadrados 3x3 so com parede
	private boolean checkWallSquare() {
		for (int i = 0; i < maze.length - 2; i++) {
			for (int j = 0; j < maze[i].length - 2; j++) {
				if (maze[i][j] == 'X' && maze[i][j + 1] == 'X'
						&& maze[i][j + 2] == 'X' && maze[i + 1][j] == 'X'
						&& maze[i + 1][j + 1] == 'X'
						&& maze[i + 1][j + 2] == 'X' && maze[i + 2][j] == 'X'
						&& maze[i + 2][j + 1] == 'X'
						&& maze[i + 2][j + 2] == 'X')
					return false;
			}
		}
		return true;
	}

	public char getPosElem(int pos[]) throws invalidSize {
		if (pos.length != 2 || pos[0] >= maze.length || pos[1] >= maze.length
				|| pos[0] < 0 || pos[1] < 0) {
			invalidSize e = new invalidSize();
			throw e;
		}

		if (maze[pos[0]][pos[1]] != WALL && maze[pos[0]][pos[1]] != PATH)
			return OTHER;
		else
			return maze[pos[0]][pos[1]];
	}

	public char[][] getMaze() {
		char[][] ret = maze.clone();
		for (int i = 0; i < maze.length; i++) {
			ret[i] = maze[i].clone();
		}
		return ret;
	}

	public void openExit(MazeExit exit) {
		maze[exit.getVertPos()][exit.getHorizPos()] = PATH;
	}
}

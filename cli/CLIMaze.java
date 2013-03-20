package maze.cli;

import java.util.Scanner;

import maze.elems.GameElem;
import maze.elems.Hero;
import maze.elems.Mobile;
import maze.elems.Sword;
import maze.logic.Maze;

public class CLIMaze {

	public static final int PREDEFINED = 2;
	public static final int RANDOM = 1;

	public static final int FIXED_DRAGON = 2;
	public static final int MOVABLE_DRAGON = 1;

	@SuppressWarnings("unused")
	private int[] findCharacter(char p, char[][] maze) {
		int[] res = new int[2];
		outer_loop: for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == p) {
					res[0] = i;
					res[1] = j;
					break outer_loop;
				}
			}
		}
		return res;
	}

	/*
	 * Pergunta ao utilizador qual o labirinto a usar: predefinido ou aleat�rio
	 */
	public int askTypeMaze() {
		Scanner s = new Scanner(System.in);
		int answer;
		while (true) {
			try {
				System.out.println("Qual o tipo de labirinto:");
				System.out.println("1 - Labirinto Automatico");
				System.out.println("2 - Labirinto predefinido");
				answer = s.nextInt();
				if ((answer != PREDEFINED) && (answer != RANDOM)) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.out.println("Invalid input!");
				s.nextLine();
				s.reset();
			}
		}
		return answer;
	}

	public boolean askTypeDragon() {
		Scanner s = new Scanner(System.in);
		int answer;
		while (true) {
			try {
				System.out.println("Qual o tipo de dragao:");
				System.out.println("1 - Dragao habilitado de movimento");
				System.out.println("2 - Dragao fixo");
				answer = s.nextInt();
				if ((answer != FIXED_DRAGON) && (answer != MOVABLE_DRAGON)) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				System.out.println("Invalid input!");
				s.nextLine();
				s.reset();
			}
		}
		switch (answer) {
		case MOVABLE_DRAGON:
			return true;
		case FIXED_DRAGON:
			return false;
		default:
			return true;
		}
	}

	public int receiveNrDragons(int maze_size) {
		int nr_dragons;
		while (true) {
			try {
				System.out.println("Quantos dragoes?");
				Scanner s = new Scanner(System.in);
				nr_dragons = s.nextInt();
				// dependendo do tamanho do labirinto, permite ate
				// (maze_size / 5)
				if (nr_dragons < 1
						|| nr_dragons > Math.pow((maze_size / 5), 2.0))
					throw new invalidSize();
				break;
			} catch (Exception e) {
				System.out.println("Numero de dragoes invalido.");
				System.out
						.println("O numero deve ser entre 1 e (tamanho_do_labirinto/5)^2.");
			}
		}
		return nr_dragons;
	}

	public int receiveCmdHero() {
		System.out.println("Para onde quer mover?");
		System.out.println("(A)Esquerda");
		System.out.println("(S)Baixo");
		System.out.println("(D)Direita");
		System.out.println("(W)Cima");
		System.out.println("(L)Enviar aguia");
		Scanner s = new Scanner(System.in);
		String str = s.nextLine().toUpperCase();

		switch (str) {
		case "A":
			return (int) Mobile.LEFT;
		case "S":
			return (int) Mobile.DOWN;
		case "D":
			return (int) Mobile.RIGHT;
		case "W":
			return (int) Mobile.UP;
		case "L":
			return (int) Hero.EAGLE;
		default:
			return 0;
		}
	}

	public int setMazeSize() {
		Scanner s = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Qual o tamanho do labirinto desejado?");
				int i = s.nextInt();
				if (i < 5 || i > 500)
					throw new invalidSize();
				return i;
			} catch (Exception e) {
				System.out.println("Invalid input");
				s.nextLine();
				s.reset();
			}
		}
	}

	public boolean drawGameElem(GameElem elem, char[][] maze) {
		char elem_char = elem.getCurrCharacter();
		if (elem_char != ' ') {
			maze[elem.getVertPos()][elem.getHorizPos()] = elem_char;
		}
		return true;
	}

	public void printMaze(Maze maze, GameElem[] elems) {
		char[][] maze_tmp = maze.getMaze();
		for (GameElem e : elems) {
			if (e instanceof Mobile) {
				if (((Mobile) e).isAlive()) {
					drawGameElem(e, maze_tmp);
				}
			} else {
				drawGameElem(e, maze_tmp);
			}
		}
		System.out.println();
		for (char[] s : maze_tmp) {
			for (char c : s) {
				System.out.print(c);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}

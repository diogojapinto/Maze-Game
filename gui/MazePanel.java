package maze.gui;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import maze.logic.Game;

public class MazePanel extends JPanel {

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (GameFrame.hasGameStarted() == 1) {
			char[][] maze = Game.getInstance().getFilledMaze();
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze.length; j++) {
					g.drawImage(
							ImageFetcher.getInstance().fetchImage(maze[i][j]),
							j * 30, i * 30, (j + 1) * 30, (i + 1) * 30, 0, 0,
							30, 30, this);
				}
			}
		} else
			g.drawImage(ImageFetcher.getInstance().fetchImage('N'), 55, 150,
					this);
	}
}

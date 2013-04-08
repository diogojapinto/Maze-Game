package maze.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import maze.cli.CLIFunctions;
import maze.logic.Game;
import maze.logic.Movement;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {

	private final int DEFAULT_SIZE = 300;

	private JPanel contentPane;
	private static int game_started = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameFrame frame = new GameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameFrame() {
		setTitle("Bowser's Maze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final MazePanel mazePanel = new MazePanel();
		final JScrollPane scrollPane = new JScrollPane(mazePanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mazePanel.setSize(DEFAULT_SIZE, DEFAULT_SIZE);
		mazePanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				switch (code) {
				case KeyEvent.VK_LEFT:
					Game.getInstance().moveHeroEagle(Movement.LEFT);
					break;
				case KeyEvent.VK_DOWN:
					Game.getInstance().moveHeroEagle(Movement.DOWN);
					break;
				case KeyEvent.VK_RIGHT:
					Game.getInstance().moveHeroEagle(Movement.RIGHT);
					break;
				case KeyEvent.VK_UP:
					Game.getInstance().moveHeroEagle(Movement.UP);
					break;
				default:
				}
				Game.getInstance().moveDragons();
				if (!Game.getInstance().checkGameEnds()) {
					System.exit(0);
				}
				repaint();
			}
		});
		mazePanel.setBackground(Color.white);
		mazePanel.setLayout(new BorderLayout(0, 0));

		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				mazePanel.requestFocus();
			}

			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		contentPane.add(scrollPane, BorderLayout.CENTER);

		final JPanel optionsPane = new JPanel();
		contentPane.add(optionsPane, BorderLayout.SOUTH);
		optionsPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton btnNewGame = new JButton("New");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game_started = 1;
				Game.getInstance().newGame();
				Dimension d = new Dimension();
				d.setSize(Game.getInstance().getMazeSize() * 30, Game
						.getInstance().getMazeSize() * 30);
				mazePanel.setPreferredSize(d);
				scrollPane.setPreferredSize(d);
				scrollPane.setMinimumSize(d);
				scrollPane.revalidate();
				mazePanel.repaint();
				scrollPane.repaint();
				contentPane.setPreferredSize(d);
				contentPane.revalidate();
				contentPane.repaint();
				mazePanel.requestFocus();
			}
		});
		optionsPane.add(btnNewGame);

		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OptionsDialog options = new OptionsDialog();
				options.setLocationRelativeTo(contentPane);
				options.setVisible(true);
				options.setAlwaysOnTop(true);
			}
		});
		optionsPane.add(btnOptions);

		JButton btnSave = new JButton("Save");
		optionsPane.add(btnSave);

		JButton btnLoad = new JButton("Load");
		optionsPane.add(btnLoad);

		JButton btnCustomMaze = new JButton("Custom Maze");
		optionsPane.add(btnCustomMaze);

		final JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnExit.setVisible(false);
				System.exit(0);
			}
		});
		optionsPane.add(btnExit);
	}

	public static int hasGameStarted() {
		return game_started;
	}
}

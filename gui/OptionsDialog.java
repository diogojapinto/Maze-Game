package maze.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import maze.exceptions.*;
import maze.logic.Game;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class OptionsDialog extends JDialog {
	
	private final int MIN_MAZE_SIZE = 5;
	private final int MAX_MAZE_SIZE = 250;

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldMazeSize;
	private JTextField textFieldNrDragons;
	final JRadioButton rdbtnPredefinido;
	final JRadioButton rdbtnGeradoAleatoriamente;
	final JRadioButton rdbtnCriarOPrprio;
	final JLabel lblNrDragons;

	/**
	 * Create the dialog.
	 */
	public OptionsDialog() {
		setResizable(false);
		setAlwaysOnTop(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 2, 0, 0));

		final JPanel panel1 = new JPanel();
		panel1.setToolTipText("Determina se o modo de geração do labirinto");
		final JPanel panel2 = new JPanel();
		panel2.setToolTipText("Determina as dimensões do labirinto (o que influencia o número máximo de dragões).");
		FlowLayout flowLayout = (FlowLayout) panel1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel1);
		{
			JLabel lblEscolhaOTipo = new JLabel("Escolha o tipo de labirinto:");
			panel1.add(lblEscolhaOTipo);
		}

		lblNrDragons = new JLabel();

		rdbtnPredefinido = new JRadioButton("Predefinido");
		rdbtnGeradoAleatoriamente = new JRadioButton("Gerado aleatoriamente");
		rdbtnCriarOPrprio = new JRadioButton("Criar o próprio labirinto");

		rdbtnPredefinido.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnPredefinido.isSelected()
						&& rdbtnGeradoAleatoriamente.isSelected()) {
					rdbtnGeradoAleatoriamente.setSelected(false);
					panel2.setVisible(false);
				}

				if (rdbtnPredefinido.isSelected()
						&& rdbtnCriarOPrprio.isSelected()) {
					rdbtnCriarOPrprio.setSelected(false);
					panel2.setVisible(false);
				}
				if (rdbtnPredefinido.isSelected()) {
					updateMaxNrDragons();
					panel2.setVisible(false);
				}
				if (!rdbtnPredefinido.isSelected()
						&& !rdbtnGeradoAleatoriamente.isSelected()
						&& !rdbtnCriarOPrprio.isSelected()) {
					rdbtnPredefinido.setSelected(true);
				}
			}
		});
		rdbtnGeradoAleatoriamente.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnPredefinido.isSelected()
						&& rdbtnGeradoAleatoriamente.isSelected()) {
					rdbtnPredefinido.setSelected(false);
				}
				if (rdbtnCriarOPrprio.isSelected()
						&& rdbtnGeradoAleatoriamente.isSelected()) {
					rdbtnCriarOPrprio.setSelected(false);
				}
				if (rdbtnGeradoAleatoriamente.isSelected()) {
					updateMaxNrDragons();
					panel2.setVisible(true);
				}
				if (!rdbtnPredefinido.isSelected()
						&& !rdbtnGeradoAleatoriamente.isSelected()
						&& !rdbtnCriarOPrprio.isSelected()) {
					rdbtnGeradoAleatoriamente.setSelected(true);
				}
			}
		});
		rdbtnCriarOPrprio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnPredefinido.isSelected()
						&& rdbtnCriarOPrprio.isSelected()) {
					rdbtnPredefinido.setSelected(false);
				}
				if (rdbtnCriarOPrprio.isSelected()
						&& rdbtnGeradoAleatoriamente.isSelected()) {
					rdbtnGeradoAleatoriamente.setSelected(false);
				}
				if (rdbtnCriarOPrprio.isSelected()) {
					updateMaxNrDragons();
					panel2.setVisible(true);
				}
				if (!rdbtnPredefinido.isSelected()
						&& !rdbtnGeradoAleatoriamente.isSelected()
						&& !rdbtnCriarOPrprio.isSelected()) {
					rdbtnCriarOPrprio.setSelected(true);
				}
			}
		});
		panel1.add(rdbtnPredefinido);
		panel1.add(rdbtnGeradoAleatoriamente);
		panel1.add(rdbtnCriarOPrprio);

		JPanel panel3 = new JPanel();
		panel3.setToolTipText("Determina o número de dragões em jogo.");
		FlowLayout fl_panel3 = new FlowLayout(FlowLayout.LEFT, 5, 5);
		fl_panel3.setAlignOnBaseline(true);
		panel3.setLayout(fl_panel3);

		JLabel lblQuantidadeDeDrages = new JLabel("Quantidade de dragões");
		panel3.add(lblQuantidadeDeDrages);

		textFieldNrDragons = new JTextField();
		textFieldNrDragons.setHorizontalAlignment(SwingConstants.LEFT);
		panel3.add(textFieldNrDragons);
		textFieldNrDragons.setColumns(10);

		flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel2);
		contentPanel.add(panel3);

		panel3.add(lblNrDragons);

		panel3.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { lblQuantidadeDeDrages, textFieldNrDragons,
						lblNrDragons }));

		JLabel lblEscolha = new JLabel("Tam. do labirinto: (n x n)");
		lblEscolha.setHorizontalAlignment(SwingConstants.LEFT);
		panel2.add(lblEscolha);

		textFieldMazeSize = new JTextField("");
		textFieldMazeSize.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMazeSize.setSize(180, 22);
		textFieldMazeSize.getDocument().addDocumentListener(
				new DocumentListener() {
					@Override
					public void removeUpdate(DocumentEvent e) {
						updateMaxNrDragons();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						updateMaxNrDragons();
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
						updateMaxNrDragons();
					}
				});
		panel2.add(textFieldMazeSize);
		textFieldMazeSize.setColumns(10);
		
		JLabel lblminMx = new JLabel("(min: 5; máx: 250)");
		panel2.add(lblminMx);

		JPanel panel4 = new JPanel();
		panel4.setToolTipText("Determina se os dragões são móveis ou não.");
		flowLayout = (FlowLayout) panel4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel4);

		JLabel lblEscolhaOTipo_1 = new JLabel("Escolha o tipo de dragões:");
		panel4.add(lblEscolhaOTipo_1);

		final JRadioButton rdbtnFixos = new JRadioButton("Fixos");
		final JRadioButton rdbtnHabilitadosDeMovimento = new JRadioButton(
				"Habilitados de movimento");
		rdbtnFixos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFixos.isSelected()
						&& rdbtnHabilitadosDeMovimento.isSelected()) {
					rdbtnHabilitadosDeMovimento.setSelected(false);
				}
				if (!rdbtnFixos.isSelected()
						&& !rdbtnHabilitadosDeMovimento.isSelected()) {
					rdbtnFixos.setSelected(true);
				}
			}
		});
		rdbtnHabilitadosDeMovimento.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnFixos.isSelected()
						&& rdbtnHabilitadosDeMovimento.isSelected()) {
					rdbtnFixos.setSelected(false);
				}
				if (!rdbtnFixos.isSelected()
						&& !rdbtnHabilitadosDeMovimento.isSelected()) {
					rdbtnHabilitadosDeMovimento.setSelected(true);
				}
			}
		});

		panel4.add(rdbtnFixos);
		panel4.add(rdbtnHabilitadosDeMovimento);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					// verifica se os campos estão preenchidos corretamente
					try {
						if (!checkMazeSize()) {
							throw new invalidSize();
						}
						if (!checkNrDragons()) {
							throw new invalidNrDragons();
						}
					} catch (invalidSize e) {
						JOptionPane.showMessageDialog(contentPanel,
								"Dimensões do labirinto inválidas\n"
										+ "(min: 5; máx: 250)", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return;
					} catch (invalidNrDragons e) {
						JOptionPane.showMessageDialog(contentPanel,
								"Número de dragões inválido", "Erro",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (rdbtnPredefinido.isSelected()) {
						Game.getInstance().setNextMazeDefault();
					} else if (rdbtnGeradoAleatoriamente.isSelected()) {
						int size = Integer.parseInt(textFieldMazeSize.getText());
						Game.getInstance().setNextMazeRandom(size);
					} else if (rdbtnCriarOPrprio.isSelected()) {
						// TODO
					}
					int nr_dragons = Integer.parseInt(textFieldNrDragons
							.getText());
					Game.getInstance().setNextNrDragons(nr_dragons);

					if (rdbtnFixos.isSelected()) {
						Game.getInstance().setNextDragonsFixed();
					} else if (rdbtnHabilitadosDeMovimento.isSelected()) {
						Game.getInstance().setNextDragonsMovable();
					}

					int choice = JOptionPane.showConfirmDialog(contentPanel,
							"Tem a certeza que pretende gravar as alterações?",
							"Gravar alterações", JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						JOptionPane
								.showMessageDialog(
										contentPanel,
										"Alterações gravadas.\n"
												+ "Clique em \"New\" para começar iniciar um jogo com estas opções.",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE );
						dispose();
					}
				}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			final JButton cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int choice = JOptionPane
							.showConfirmDialog(
									contentPanel,
									"Tem a certeza que pretende ignorar as alterações?",
									"Ignorar alterações",
									JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
						dispose();
					}
				}
			});
			cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		rdbtnPredefinido.setSelected(true);
		rdbtnHabilitadosDeMovimento.setSelected(true);
	}

	private boolean checkMazeSize() {
		if (rdbtnPredefinido.isSelected())
			return true;
		else {
			int value;
			try {
				value = Integer.parseInt(textFieldMazeSize.getText());
			} catch (NumberFormatException e) {
				return false;
			}
			if (value < MIN_MAZE_SIZE || value > MAX_MAZE_SIZE)
				return false;
			else
				return true;
		}
	}

	private boolean checkNrDragons() {
		int maze_size, nr_dragons;
		try {
			if (rdbtnPredefinido.isSelected()) {
				maze_size = 5;
			} else {
				maze_size = Integer.parseInt(textFieldMazeSize.getText());
			}
			nr_dragons = Integer.parseInt(textFieldNrDragons.getText());
		} catch (NumberFormatException e) {
			return false;
		}
		if (nr_dragons < 0 || nr_dragons > Game.getMaxNrDragons(maze_size))
			return false;
		else
			return true;
	}

	private void updateMaxNrDragons() {
		int value = 0;
		if (rdbtnPredefinido.isSelected()) {
			value = 5;
		} else {
			try {
				value = Integer.parseInt(textFieldMazeSize.getText());
				if (value < MIN_MAZE_SIZE || value > MAX_MAZE_SIZE)
					throw new invalidSize();
			} catch (NumberFormatException exeption) {
				lblNrDragons.setText("Dimensão não especificada");
				return;
			} catch (invalidSize exeption) {
				lblNrDragons.setText("Dimensão do labirinto inválida");
				return;
			}
		}
		lblNrDragons.setText("(máx: "
				+ Integer.toString(Game.getMaxNrDragons(value)) + ")");
	}
}

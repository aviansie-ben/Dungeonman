package com.bendude56.dungeonman.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.Random;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.EntityStats;
import com.bendude56.dungeonman.entity.EntityPlayer.PlayerRace;

/**
 * Benjamin C. Thomas
 * Computer Science 30
 * 2012/13 Semester 2
 * Centennial High School
 *
 * @author Benjamin C. Thomas
 */

public class NewGameFrame extends JFrame {
	private static final long	serialVersionUID	= 0L;

	private GameFrame parent;

	private JPanel	contentPane;
	private final ButtonGroup difficultyButtonGroup = new ButtonGroup();
	private JRadioButton veryEasyButton;
	private JRadioButton easyButton;
	private JRadioButton mediumButton;
	private JRadioButton hardButton;
	private JRadioButton veryHardButton;
	private JRadioButton reallyjoelsDadButton;
	private JTextField textField;
	private JTable table;
	
	private EntityStats stats;
	private PlayerRace race = PlayerRace.HUMAN;

	/**
	 * Create the frame.
	 */
	public NewGameFrame(GameFrame parent) {
		this.parent = parent;
		
		setResizable(false);
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setBounds(6, 364, 173, 150);
		difficultyPanel.setBorder(new TitledBorder(null, "Difficulty", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
		contentPane.add(difficultyPanel);
		difficultyPanel.setLayout(null);
		
		veryEasyButton = new JRadioButton("Very Easy (Wuss)");
		difficultyButtonGroup.add(veryEasyButton);
		veryEasyButton.setBounds(16, 33, 139, 18);
		difficultyPanel.add(veryEasyButton);
		
		easyButton = new JRadioButton("Easy");
		difficultyButtonGroup.add(easyButton);
		easyButton.setBounds(16, 50, 139, 18);
		difficultyPanel.add(easyButton);
		
		mediumButton = new JRadioButton("Medium");
		mediumButton.setSelected(true);
		difficultyButtonGroup.add(mediumButton);
		mediumButton.setBounds(16, 67, 139, 18);
		difficultyPanel.add(mediumButton);
		
		hardButton = new JRadioButton("Hard");
		difficultyButtonGroup.add(hardButton);
		hardButton.setBounds(16, 84, 139, 18);
		difficultyPanel.add(hardButton);
		
		veryHardButton = new JRadioButton("Very Hard");
		difficultyButtonGroup.add(veryHardButton);
		veryHardButton.setBounds(16, 101, 139, 18);
		difficultyPanel.add(veryHardButton);
		
		reallyjoelsDadButton = new JRadioButton("(Secret Difficulty)");
		reallyjoelsDadButton.setEnabled(false);
		difficultyButtonGroup.add(reallyjoelsDadButton);
		reallyjoelsDadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(NewGameFrame.this, "I know you might think this is a good idea, but it's really not!\nTrust me: There is no need to prove yourself!", "Reallyjoel's Dad", JOptionPane.WARNING_MESSAGE);
			}
		});
		reallyjoelsDadButton.setBounds(16, 118, 139, 18);
		difficultyPanel.add(reallyjoelsDadButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int difficulty = 0;
				// String difficultyName;
				// String[] rules;
				
				if (veryEasyButton.isSelected()) {
					difficulty = 0;
					/*difficultyName = "VERY EASY";
					rules = new String[] {
						"Potions have only positive effects",
						
					};*/
				} else if (easyButton.isSelected()) {
					difficulty = 1;
					// difficultyName = "EASY";
				} else if (mediumButton.isSelected()) {
					difficulty = 2;
					// difficultyName = "MEDIUM";
				} else if (hardButton.isSelected()) {
					difficulty = 3;
					// difficultyName = "HARD";
				} else if (veryHardButton.isSelected()) {
					difficulty = 4;
					// difficultyName = "VERY HARD";
				} else if (reallyjoelsDadButton.isSelected()) {
					difficulty = 5;
					/*difficultyName = "REALLYJOEL'S DAD";
					rules = new String[] {
						"All healing items kill you",
						"Trying to move into walls is death",
						"No saving allowed",
						"Enemies are ridiculously overpowered",
						"All attacks do 1 damage (Except enemy attacks)"
					};*/
				} else {
					return;
				}
				
				GameInstance.createNewGame(difficulty, stats);
				
				NewGameFrame.this.parent.windowMenu.setEnabled(true);
				NewGameFrame.this.parent.gamePanel.repaint();
				NewGameFrame.this.parent.openDefaultWindows();
				
				NewGameFrame.this.dispose();
			}
		});
		btnOk.setBounds(270, 486, 90, 28);
		contentPane.add(btnOk);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Character", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
		panel.setBounds(6, 6, 354, 131);
		contentPane.add(panel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(28, 33, 53, 16);
		panel.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(93, 27, 239, 28);
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (textField.getText().equalsIgnoreCase("Reallyjoel's Dad")) {
					reallyjoelsDadButton.setEnabled(true);
					reallyjoelsDadButton.setText("Reallyjoel's Dad");
				}
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (textField.getText().equalsIgnoreCase("Reallyjoel's Dad")) {
					reallyjoelsDadButton.setEnabled(true);
					reallyjoelsDadButton.setText("Reallyjoel's Dad");
				}
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				if (textField.getText().equalsIgnoreCase("Reallyjoel's Dad")) {
					reallyjoelsDadButton.setEnabled(true);
					reallyjoelsDadButton.setText("Reallyjoel's Dad");
				}
			}
			
		});
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(28, 61, 53, 16);
		panel.add(lblGender);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Male", "Female"}));
		comboBox.setBounds(93, 56, 75, 26);
		panel.add(comboBox);
		
		JLabel lblRace = new JLabel("Race:");
		lblRace.setBounds(28, 89, 53, 16);
		panel.add(lblRace);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Human"}));
		comboBox_1.setBounds(93, 84, 119, 26);
		panel.add(comboBox_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Stats", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
		panel_1.setBounds(6, 136, 354, 196);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 35, 293, 111);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Magic", new Integer(0), new Integer(5)},
				{"Strength", new Integer(0), new Integer(5)},
				{"Defense", new Integer(0), new Integer(5)},
				{"Agility", new Integer(0), new Integer(5)},
				{"Endurance", new Integer(0), new Integer(5)},
				{"Intelligence", new Integer(0), new Integer(5)},
			},
			new String[] {
				"Stat", "Rolled", "Bonus"
			}
		) {
			private static final long	serialVersionUID	= 0L;
			Class<?>[] columnTypes = new Class<?>[] {
				String.class, Integer.class, Integer.class
			};
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		table.setBorder(null);
		
		JButton btnReroll = new JButton("Reroll");
		btnReroll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rerollStats();
			}
		});
		btnReroll.setBounds(237, 149, 90, 28);
		panel_1.add(btnReroll);
		
		rerollStats();
	}
	
	public void rerollStats() {
		Random r = new Random();
		EntityStats rolled = new EntityStats(3 + r.nextInt(8), 3 + r.nextInt(8), 3 + r.nextInt(8), 3 + r.nextInt(8), 3 + r.nextInt(8), 3 + r.nextInt(8), 0);
		
		table.getModel().setValueAt(rolled.getMagic(), 0, 1);
		table.getModel().setValueAt(rolled.getStrength(), 1, 1);
		table.getModel().setValueAt(rolled.getDefense(), 2, 1);
		table.getModel().setValueAt(rolled.getAgility(), 3, 1);
		table.getModel().setValueAt(rolled.getEndurance(), 4, 1);
		table.getModel().setValueAt(rolled.getIntelligence(), 5, 1);
		
		stats = new EntityStats(race.bonusStats.getMagic() + rolled.getMagic(),
				race.bonusStats.getStrength() + rolled.getStrength(),
				race.bonusStats.getDefense() + rolled.getDefense(),
				race.bonusStats.getAgility() + rolled.getAgility(),
				race.bonusStats.getEndurance() + rolled.getEndurance(),
				race.bonusStats.getIntelligence() + rolled.getIntelligence(),
				race.bonusStats.getBaseMaxHp());
	}
}

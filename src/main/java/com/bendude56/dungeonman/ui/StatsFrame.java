package com.bendude56.dungeonman.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.EntityPlayer;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Benjamin C. Thomas
 */

public class StatsFrame extends JFrame {
	private static final long	serialVersionUID	= 0L;
	
	private JPanel	contentPane;
	private JLabel hpValueLabel;
	private JLabel magicValueLabel;
	private JLabel strengthValueLabel;
	private JLabel defenseValueLabel;
	private JLabel agilityValueLabel;
	private JLabel enduranceValueLabel;
	private JLabel intelligenceValueLabel;

	/**
	 * Create the frame.
	 */
	public StatsFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				GameFrame.activeFrame.activeStats = null;
			}
		});
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Player Stats");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 201, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(7, 0, 0, 0));
		
		JPanel hpPanel = new JPanel();
		FlowLayout fl_hpPanel = (FlowLayout) hpPanel.getLayout();
		fl_hpPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(hpPanel);
		
		JLabel hpLabel = new JLabel("HP:");
		hpPanel.add(hpLabel);
		
		hpValueLabel = new JLabel("0/0");
		hpValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		hpPanel.add(hpValueLabel);
		
		JPanel magicPanel = new JPanel();
		FlowLayout fl_magicPanel = (FlowLayout) magicPanel.getLayout();
		fl_magicPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(magicPanel);
		
		JLabel magicLabel = new JLabel("Magic:");
		magicPanel.add(magicLabel);
		
		magicValueLabel = new JLabel("0");
		magicValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		magicPanel.add(magicValueLabel);
		
		JPanel strengthPanel = new JPanel();
		FlowLayout fl_strengthPanel = (FlowLayout) strengthPanel.getLayout();
		fl_strengthPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(strengthPanel);
		
		JLabel strengthLabel = new JLabel("Strength:");
		strengthPanel.add(strengthLabel);
		
		strengthValueLabel = new JLabel("0");
		strengthValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		strengthPanel.add(strengthValueLabel);
		
		JPanel defensePanel = new JPanel();
		FlowLayout fl_defensePanel = (FlowLayout) defensePanel.getLayout();
		fl_defensePanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(defensePanel);
		
		JLabel defenseLabel = new JLabel("Defense:");
		defensePanel.add(defenseLabel);
		
		defenseValueLabel = new JLabel("0");
		defenseValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		defensePanel.add(defenseValueLabel);
		
		JPanel agilityPanel = new JPanel();
		FlowLayout fl_agilityPanel = (FlowLayout) agilityPanel.getLayout();
		fl_agilityPanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(agilityPanel);
		
		JLabel agilityLabel = new JLabel("Agility:");
		agilityPanel.add(agilityLabel);
		
		agilityValueLabel = new JLabel("0");
		agilityValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		agilityPanel.add(agilityValueLabel);
		
		JPanel endurancePanel = new JPanel();
		FlowLayout fl_endurancePanel = (FlowLayout) endurancePanel.getLayout();
		fl_endurancePanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(endurancePanel);
		
		JLabel enduranceLabel = new JLabel("Endurance:");
		endurancePanel.add(enduranceLabel);
		
		enduranceValueLabel = new JLabel("0");
		enduranceValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		endurancePanel.add(enduranceValueLabel);
		
		JPanel intelligencePanel = new JPanel();
		FlowLayout fl_intelligencePanel = (FlowLayout) intelligencePanel.getLayout();
		fl_intelligencePanel.setAlignment(FlowLayout.LEFT);
		contentPane.add(intelligencePanel);
		
		JLabel intelligenceLabel = new JLabel("Intelligence:");
		intelligencePanel.add(intelligenceLabel);
		
		intelligenceValueLabel = new JLabel("0");
		intelligenceValueLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		intelligencePanel.add(intelligenceValueLabel);
		
		doUpdate();
	}
	
	public void doUpdate() {
		EntityPlayer p = GameInstance.getActiveInstance().getPlayerEntity();
		
		hpValueLabel.setText(p.getHp() + "/" + p.getMaxHp());
		magicValueLabel.setText(p.getStats().getMagic() + "");
		strengthValueLabel.setText(p.getStats().getStrength() + "");
		defenseValueLabel.setText(p.getStats().getDefense() + "");
		agilityValueLabel.setText(p.getStats().getAgility() + "");
		enduranceValueLabel.setText(p.getStats().getEndurance() + "");
		intelligenceValueLabel.setText(p.getStats().getIntelligence() + "");
	}
}

package com.bendude56.dungeonman.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.bendude56.dungeonman.gfx.GraphicsPanel;

public class GameFrame extends JFrame {
	private static final long	serialVersionUID	= 1L;
	
	private static GameFrame activeFrame;
	
	public JMenuBar mainMenu;
	
	public JMenu fileMenu;
	public JMenuItem newMenuButton;
	public JMenuItem loadMenuButton;
	public JMenuItem saveMenuButton;
	public JMenuItem saveAsMenuButton;
	public JMenuItem exitMenuButton;
	
	public JMenu windowMenu;
	public JMenuItem windowInventoryButton;
	public JMenuItem windowStatsButton;
	public JMenuItem windowTextLogButton;
	
	public GraphicsPanel gamePanel;
	
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeAndWait(new Runnable() {
			public void run() {
				try {
					activeFrame = new GameFrame();
					activeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		activeFrame.mainLoop();
	}
	
	public GameFrame() {
		initComponents();
	}
	
	private void initComponents() {
		this.setTitle("Dungeonman v0.1.0");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 600, 500);
		
		initMenus();
		
		gamePanel = new GraphicsPanel();
		this.add(gamePanel);
	}
	
	private void initMenus() {
		mainMenu = new JMenuBar();
		this.setJMenuBar(mainMenu);
		
		initFileMenu();
		initWindowMenu();
	}
	
	private void initFileMenu() {
		fileMenu = new JMenu("File");
		mainMenu.add(fileMenu);
		
		newMenuButton = new JMenuItem("New Game");
		fileMenu.add(newMenuButton);
		
		loadMenuButton = new JMenuItem("Load...");
		fileMenu.add(loadMenuButton);
		
		fileMenu.addSeparator();
		
		saveMenuButton = new JMenuItem("Save");
		saveMenuButton.setEnabled(false);
		fileMenu.add(saveMenuButton);
		
		saveAsMenuButton = new JMenuItem("Save As...");
		saveAsMenuButton.setEnabled(false);
		fileMenu.add(saveAsMenuButton);
		
		fileMenu.addSeparator();
		
		exitMenuButton = new JMenuItem("Exit");
		fileMenu.add(exitMenuButton);
		
		newMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NewGameFrame f = new NewGameFrame(GameFrame.this);
							f.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		exitMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameFrame.this.dispatchEvent(new WindowEvent(GameFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
	}
	
	private void initWindowMenu() {
		windowMenu = new JMenu("Window");
		windowMenu.setEnabled(false);
		mainMenu.add(windowMenu);
		
		windowInventoryButton = new JMenuItem("Inventory");
		windowMenu.add(windowInventoryButton);
		
		windowStatsButton = new JMenuItem("Stats");
		windowMenu.add(windowStatsButton);
		
		windowTextLogButton = new JMenuItem("Text Log");
		windowMenu.add(windowTextLogButton);
	}
	
	private void mainLoop() {
		
	}
}

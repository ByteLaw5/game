package com.game;

import java.awt.*;

import javax.swing.JFrame;

public class Window {
	private final JFrame screen;

	public Window(String title, GameBase game) {
		game.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		game.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		game.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.setUndecorated(true);
		frame.pack();
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setVisible(true);

		screen = frame;
		
		game.start();
	}

	public JFrame getScreen() {
		return screen;
	}
}

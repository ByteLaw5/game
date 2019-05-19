package com.game;

import java.awt.*;

import javax.swing.JFrame;

public class Window {
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double swidth = SCREEN_SIZE.getWidth(), sheight = SCREEN_SIZE.getHeight();

	public Window(int w, int h, String title, GameBase game) {
		game.setPreferredSize(new Dimension(w, h));
		//game.setMaximumSize(new Dimension(w, h));
		game.setMinimumSize(new Dimension(w, h));
		
		JFrame frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
}

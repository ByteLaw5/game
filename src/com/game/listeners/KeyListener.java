package com.game.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.game.objects.Player;
import com.game.util.IsListener;
import com.game.util.Sounds;

@IsListener("key")
public class KeyListener extends KeyAdapter {
	Player player;
	
	/** The actual keycodes are the ones shown below +1.
	 * 82 = S
	 * 86 = W
	 * 64 = A
	 * 67 = D
	 * 32 = Space
	 */
	public boolean[] keysPressed;
	private boolean A = false, D = false;
	
	public KeyListener(Player player) {
		this.player = player;
		keysPressed = new boolean[243];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if((key == KeyEvent.VK_W || key == KeyEvent.VK_SPACE) && player.onGround()) {
			player.setVely(-10.0f);
			player.setJumping(true);
			keysPressed[86] = true;
			if(!Sounds.test_ogg.playing()) {
				Sounds.test_ogg.play();
			}
		}
		if(key == KeyEvent.VK_A)
			player.setVelx(-5.0f); keysPressed[64] = true; A = true;
		if(key == KeyEvent.VK_D)
			player.setVelx(5.0f); keysPressed[67] = true; D = true;
		if(key == KeyEvent.VK_ESCAPE)
			System.exit(1);
		//Unused
		if(key == KeyEvent.VK_S)
			keysPressed[82] = true;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) keysPressed[86] = false;
		if(key == KeyEvent.VK_S) keysPressed[82] = false;
		if(key == KeyEvent.VK_A) keysPressed[64] = false; A = false; if(!D)player.setVelx(0.0f);
		if(key == KeyEvent.VK_D) keysPressed[67] = false; D = false; if(!A)player.setVelx(0.0f);
	}
}

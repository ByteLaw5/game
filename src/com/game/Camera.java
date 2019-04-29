package com.game;

import com.game.objects.Player;
import com.game.util.IHasPlace;

public class Camera implements IHasPlace {
	private float x, y;
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick(Player player) {
		this.setX(-player.getX() + GameBase.WIDTH / 2);
	}
	
	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}
}

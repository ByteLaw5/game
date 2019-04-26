package com.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.GameBase;
import com.game.GameBase.ObjectDoesNotExistException;
import com.game.util.ID;
import com.game.util.IHasPlace;

/**
 * Something that appears in the world.
 */
public abstract class GameObject implements IHasPlace {
	protected float x, y;
	protected ID id;
	protected GameBase game;
	protected int ticks;
	
	public GameObject(float x, float y, ID id, GameBase game) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.game = game;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBoundingBox();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}
	
	public void remove() {
		try {
			this.game.removeObject(this);
		} catch (ObjectDoesNotExistException e) {
			e.printStackTrace();
		}
	}
}

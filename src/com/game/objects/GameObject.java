package com.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.GameBase;
import com.game.GameBase.ObjectDoesNotExistException;
import com.game.util.Direction;
import com.game.util.ID;
import com.game.util.IHasPlace;

/**
 * Something that appears in the level.
 */
public abstract class GameObject implements IHasPlace {
	protected float x, y;
	protected ID id;
	public GameBase game;
	public int ticks;
	protected Random rand = new Random();

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

	public Direction facing(GameObject from, GameObject to) {
		if(from.x > to.x) {
			return Direction.LEFT;
		} else if(from.x < to.x) {
			return Direction.RIGHT;
		} else if(from.x == to.x) {
			return Direction.NONE;
		} else {
			return Direction.NONE;
		}
	}
	public boolean nearTo(float maxPos, GameObject other) {
		float XDelta = this.x - other.x;
		float YDelta = this.y - other.y;
		return XDelta < maxPos && XDelta > -maxPos &&
				YDelta < maxPos && YDelta > -maxPos;
	}
}

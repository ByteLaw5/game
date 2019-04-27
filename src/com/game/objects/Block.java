package com.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.GameBase;
import com.game.util.ID;
import com.game.util.IHasPlace;

public class Block extends GameObject implements IHasPlace {
	protected boolean canCollide = true;
	public Block(float x, float y, GameBase game) {
		super(x, y, ID.Block, game);
	}
	public Block(float x, float y, GameBase game, boolean collidable) {
		super(x, y, ID.Block, game);
		canCollide = collidable;
	}

	public boolean getCollidable() {
		return canCollide;
	}
	/*public boolean setCollidable(boolean val) {
		return canCollide = val;
	}*/

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(getCollidable() ? Color.RED : Color.GREEN);
		g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
		g.setColor(Color.BLACK);
		g.drawRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int)this.x, (int)this.y, 32, 32);
	}
}

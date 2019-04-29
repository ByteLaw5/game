package com.game.objects;

import com.game.GameBase;
import com.game.util.Assets;
import com.game.util.ID;
import com.game.util.IHasPlace;

import java.awt.*;

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
		g.drawImage(getCollidable() ? Assets.block : Assets.block_notcollide, (int)x, (int)y, null);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int)this.x, (int)this.y, 32, 32);
	}
}

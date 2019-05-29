package com.game.objects;

import com.game.GameBase;
import com.game.block.BlockType;
import com.game.util.ID;
import com.game.util.IHasPlace;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Block extends GameObject {
	private boolean canCollide;
	private final BlockType block;
	private final BufferedImage texture;

	public Block(float x, float y, GameBase game, BlockType blockType)
	{
		super(x, y, ID.Block, game);
		this.block = blockType;
		canCollide = blockType.isSolid();
		texture = blockType.getTexture();
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
		g.drawImage(texture, (int)x, (int)y, this.getBoundingBox().width, this.getBoundingBox().height, null);
		g.setColor(Color.RED);
		g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int)this.x, (int)this.y, 32, 32);
	}
}

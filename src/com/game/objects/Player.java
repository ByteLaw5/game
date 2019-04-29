package com.game.objects;

import java.awt.*;

import com.game.GameBase;
import com.game.util.Assets;
import com.game.util.ICollision;
import com.game.util.ID;

public class Player extends GameObjectLiving implements ICollision {
	public Player(float x, float y, GameBase game) {
		super(x, y, ID.Player, game, true);
		health = getMaxHealth();
	}
	
	@Override
	public void tick() {
		super.tick();
		checkCollisions();
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(this.health > 0) {
			g.drawImage(Assets.player, (int)x, (int)y, null);
		}
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(Color.BLUE);
//		g2d.draw(getBoundingBoxDown());
//		g2d.draw(getBoundingBoxLeft());
//		g2d.draw(getBoundingBoxRight());
//		g2d.draw(getBoundingBoxTop());
	}
	
	@Override
	public int getMaxHealth() {
		return 100;
	}
	
	@Override
	public int getHealth() {
		return this.health;
	}
	
	@Override
	public void die() {
		super.die();
	}
	
	public void checkCollisions() {
		for(GameObject object : GameBase.OBJECTS) {
			if(object.getId() == ID.Block && ((Block)object).getCollidable()) {
				if(this.getBoundingBoxTop().intersects(object.getBoundingBox())) {
					this.y = object.getY() + (this.getBoundingBox().height / 2);
					this.vely = 0;
				}
				if(this.getBoundingBoxDown().intersects(object.getBoundingBox())) {
					this.y = object.getY() - this.getBoundingBox().height;
					this.vely = 0;
					this.falling = false;
					this.jumping = false;
				} else {
					this.falling = true;
				}
				if(this.getBoundingBoxRight().intersects(object.getBoundingBox())) {
					this.x = object.getX() - 32;
				}
				if(this.getBoundingBoxLeft().intersects(object.getBoundingBox())) {
					this.x = object.getX() + 32;
				}
			}
			/*if(object.getId() == ID.Enemy) {
				if(this.getBoundingBox().intersects(object.getBoundingBox())) {
					health--;
				}
			}*/
		}
	}
	
	@Override
	public ID getId() {
		return ID.Player;
	}

	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int)this.x, (int)this.y, 32, 64);
	}
	
	public Rectangle getBoundingBoxTop() {
		return new Rectangle((int) ((int)x+(this.getBoundingBox().width/2)-((this.getBoundingBox().width/2)/2)), (int)y, this.getBoundingBox().width/2, (int)this.getBoundingBox().height/2);
	}
	
	public Rectangle getBoundingBoxDown() {
		return new Rectangle((int)((int)x + (this.getBoundingBox().width / 2) - ((this.getBoundingBox().width / 2) / 2)), (int)((int)y + (this.getBoundingBox().height / 2)), this.getBoundingBox().width / 2, this.getBoundingBox().height / 2);
	}
	
	public Rectangle getBoundingBoxRight() {
		return new Rectangle((int)((int)x + this.getBoundingBox().width - 5), (int)this.y + 5, 5, this.getBoundingBox().height - 10);
	}
	
	public Rectangle getBoundingBoxLeft() {
		return new Rectangle((int)this.x, (int)this.y + 5, 5, this.getBoundingBox().height - 10);
	}
}

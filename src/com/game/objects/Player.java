package com.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.GameBase;
import com.game.util.ICollision;
import com.game.util.ID;

public class Player extends GameObjectLiving implements ICollision {
	protected boolean falling = true, jumping = false;
	
	public Player(float x, float y, GameBase game) {
		super(x, y, ID.Player, game, true, true);
		health = getMaxHealth();
	}
	
	@Override
	public void tick() {
		super.tick();
		
		x += velx;
		y += vely;
		
		if(falling || jumping) {
			vely += gravity;
			
			if(vely > MAX_SPEED) {
				vely = MAX_SPEED;
			}
		}
		if(vely > 0) {
			this.setFalling(true);
		}
			
		checkCollisions();
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(this.health > 0) {
			g.setColor(Color.BLACK);
			g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
		}
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(Color.BLUE);
//		g2d.draw(getBoundingBoxDown());
//		g2d.draw(getBoundingBoxLeft());
//		g2d.draw(getBoundingBoxRight());
//		g2d.draw(getBoundingBoxTop());
	}
	
	public boolean inAir() {
		return this.isFalling() && this.isJumping();
	}
	
	public boolean onGround() {
		return !this.inAir();
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
			if(object.getId() == ID.Enemy) {
				if(this.getBoundingBox().intersects(object.getBoundingBox())) {
					this.health--;
				}
			}
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
	
	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
}

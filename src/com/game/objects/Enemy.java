package com.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.GameBase;
import com.game.util.ICollision;
import com.game.util.ID;

public class Enemy extends GameObjectLiving implements ICollision {
	protected float velx = 0, vely = 0;
	protected boolean falling = true;
	protected int ticksLeft = 0;
	
	public Enemy(float x, float y, GameBase game) {
		super(x, y, ID.Enemy, game, true);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		x += velx;
		y += vely;
		
		if(falling) {
			vely += gravity;
			
			if(vely > MAX_SPEED) {
				vely = MAX_SPEED;
			}
		}
		if(vely > 0) {
			this.setFalling(true);
		}
		
		checkCollisions();
		initAI();
		if(ticksLeft > 0) ticksLeft -= 1;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.BLUE);
		g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
	}
	
	@Override
	public int getMaxHealth() {
		return 40;
	}
	
	protected void initAI() {
		for(GameObject object : GameBase.OBJECTS) {
			if(object.getId() == ID.Player) {
				Player player = (Player)object;
				if(player.health > 0) {
					if(this.x > player.x) {
						this.velx = -1.5f;
					} else if(this.x < player.x) {
						this.velx = 1.5f;
					} else if(this.x == player.x) {
						this.velx = 0.0f;
					}
				} else {
					this.velx = 0.0f;
				}
			}
		}
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int) this.x, (int) this.y, 32, 32);
	}

	@Override
	public void checkCollisions() {
		for(GameObject object : GameBase.OBJECTS) {
			if(object.getId() == ID.Block) {
				if(this.getBoundingBoxTop().intersects(object.getBoundingBox())) {
					this.y = object.getY() + (this.getBoundingBox().height / 2);
					this.vely = 0;
				}
				if(this.getBoundingBoxDown().intersects(object.getBoundingBox())) {
					this.y = object.getY() - this.getBoundingBox().height;
					this.vely = 0;
					this.falling = false;
				} else {
					this.falling = true;
				}
				if(this.getBoundingBoxRight().intersects(object.getBoundingBox())) {
					this.x = object.getX() - 32;
				}
				if(this.getBoundingBoxLeft().intersects(object.getBoundingBox())) {
					this.x = object.getX() + 32;
				}
			} else if(object.getId() == ID.Player && this.getBoundingBox().intersects(object.getBoundingBox()) && ticksLeft == 0) {
				Player player = (Player)object;
				player.hit(10, this);
				ticksLeft = 50; // Ticks left for another hit
				player.setJumping(true);
				player.setVely(-6F);
			}
		}
	}

	@Override
	public Rectangle getBoundingBoxTop() {
		return new Rectangle((int)((int)x + (this.getBoundingBox().width / 2) - ((this.getBoundingBox().width / 2) / 2)), (int)y, this.getBoundingBox().width / 2, this.getBoundingBox().height / 2);
	}
	
	@Override
	public Rectangle getBoundingBoxDown() {
		return new Rectangle((int)((int)x + (this.getBoundingBox().width / 2) - ((this.getBoundingBox().width / 2) / 2)), (int)((int)y + (this.getBoundingBox().height / 2)), this.getBoundingBox().width / 2, this.getBoundingBox().height / 2);
	}
	
	@Override
	public Rectangle getBoundingBoxRight() {
		return new Rectangle((int)((int)x + this.getBoundingBox().width - 5), (int)this.y + 5, 5, this.getBoundingBox().height - 10);
	}
	
	@Override
	public Rectangle getBoundingBoxLeft() {
		return new Rectangle((int)this.x, (int)this.y + 5, 5, this.getBoundingBox().height - 10);
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
}

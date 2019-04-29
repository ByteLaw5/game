package com.game.objects;

import java.awt.*;

import com.game.GameBase;
import com.game.util.*;

public abstract class GameObjectLiving extends GameObject implements IHealth, IMoveable, ICollision {
	public boolean isDead = false;
	protected int maxHealth = this.getMaxHealth(), health;
	protected float velx, vely;
	protected float gravity = 0.5F;
	protected final float MAX_SPEED = 10.0f;
	private boolean showHealthBar = false;
	private boolean showBar;
	protected boolean falling = true, jumping = false;
	
	protected GameObjectLiving(float x, float y, ID id, GameBase game, boolean show) {
		super(x, y, id, game);
		this.health = this.getMaxHealth();
		showBar = show;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		if(this.showHealthBar && showBar) {
			Rectangle HEALTH = new Rectangle((int)this.x, (int)this.y - 50, this.getMaxHealth() * 2, 20);
			Color col = new Color(0xbe, 0x52, 0x52);
			HEALTH.x = (int)(this.x * 2) - HEALTH.x - this.getMaxHealth() + 15;
 			g2d.setColor(col.darker());
			g2d.fill(HEALTH);
			Rectangle CURRENT_HEALTH = new Rectangle(HEALTH.x, HEALTH.y, this.getHealth() * 2, HEALTH.height);
			g2d.setColor(col);
			g2d.fill(CURRENT_HEALTH);

			this.game.drawMessage(g2d, String.format("%s / %s", this.getHealth(), this.getMaxHealth()), (int)this.x - 40, (int)this.y - 33);
		}
	}
	
	@Override
	public void tick() {
		checkHealth();
		if(this.y >= GameBase.HEIGHT) {
			this.die();
		}
		ticks++;
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
	}
	
	private void checkHealth() {
		if(this.health >= this.maxHealth) {
			this.health = maxHealth;
		} else if(this.health < this.maxHealth) {
			this.showHealthBar = true;
		}
		if(this.health <= 0) {
			this.die();
		}
	}
	
	@Override
	public void die() {
		this.showHealthBar = false;
		this.isDead = true;
		this.remove();
	}
	
	@Override
	public int getHealth() {
		return health;
	}
	
	@Override
	public int getMaxHealth() {
		return maxHealth;
	}
	
	@Override
	public void setMaxHealth(int health) {
		this.maxHealth = health;
	}
	
	@Override
	public void setHealth(int health) {
		this.health = health;
	}
	
	@Override
	public float getVelx() {
		return velx;
	}
	
	@Override
	public void setVelx(float velx) {
		this.velx = velx;
	}
	
	@Override
	public float getVely() {
		return vely;
	}
	
	@Override
	public void setVely(float vely) {
		this.vely = vely;
	}
	public void hit(int hp, GameObjectLiving living) {
		health -= hp;
		knock(5F, living instanceof ICollision ?
				((ICollision)living).getBoundingBoxLeft().intersects(this.getBoundingBox()) ? Direction.LEFT : Direction.RIGHT
		: Direction.UP);
	}
	public void knock(float strength, Direction dir) {
		if(dir == Direction.DOWN_LEFT || dir == Direction.LEFT || dir == Direction.UP_LEFT) {
			this.setVely(strength);
			this.setVelx(-strength);
		} else if(dir == Direction.DOWN_RIGHT || dir == Direction.RIGHT || dir == Direction.UP_RIGHT) {
			this.setVely(strength);
			this.setVelx(strength);
		} else this.setVely(strength);
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

	public boolean inAir() {
		return this.isFalling() && this.isJumping();
	}

	public boolean onGround() {
		return !this.inAir();
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
}

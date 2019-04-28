package com.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.GameBase;
import com.game.ai.AIType;
import com.game.ai.EnemyAI;
import com.game.ai.IHasAI;
import com.game.util.ICollision;
import com.game.util.ID;

public class Enemy extends GameObjectLiving implements ICollision {
	public static final EnemyAI ENEMY_AI = new EnemyAI();
	public Enemy(float x, float y, GameBase game) {
		super(x, y, ID.Enemy, game, true);
	}
	
	@Override
	public void tick() {
		super.tick();
		initAI();
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

	protected void appendAI(IHasAI ai, AIType type) {
		switch(type) {
			case Looped:
				for(GameObject object : GameBase.OBJECTS) ai.onInit(this, object);
				break;
		}
	}

	protected void initAI() {
		appendAI(ENEMY_AI, AIType.Looped);
	}
	
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int) this.x, (int) this.y, 32, 32);
	}

	@Override
	public void checkCollisions() {};

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

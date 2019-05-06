package com.game.entity;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import com.game.GameBase;
import com.game.ai.AIType;
import com.game.ai.EnemyAI;
import com.game.ai.IHasAI;
import com.game.ai.JumpAI;
import com.game.objects.Block;
import com.game.objects.GameObject;
import com.game.objects.GameObjectLiving;
import com.game.objects.Player;
import com.game.util.ICollision;
import com.game.util.ID;

public abstract class CommonEntity extends GameObjectLiving implements ICollision {
	private List<EntityAI> _AI = new ArrayList<>();
	protected static final IHasAI AI_JUMP = new JumpAI();
	protected static final IHasAI AI_ENEMY = new EnemyAI();
	protected int ticksLeft = 0;
	private int _w, _h;
	protected CommonEntity(float x, float y, int width, int height, GameBase game) {
		super(x, y, ID.Enemy, game, true);
		initAI();
		_w = width;
		_h = height;
	}
	@Override
	public void tick() {
		super.tick();
		checkCollisions();
		tickedAI();
		if(ticksLeft > 0) ticksLeft--;
	}
	@Override
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.BLUE);
		//g.fillRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);
	}
	@Override
	public int getMaxHealth() {
		return 40;
	}
	protected void appendAI(IHasAI ai, AIType type) {
		boolean contains = false;
		for(EntityAI a: _AI) if((a.getAi() == ai || a.getAi().equals(ai)) && (a.getType() == type)) contains = true; // Not sure if Set actually checks `equals`
		if(!contains) _AI.add(new EntityAI(ai, type));
	}
	protected void appendAI(IHasAI ai, AIType type, int i) {
		boolean contains = false;
		for(EntityAI a: _AI) if((a.getAi() == ai || a.getAi().equals(ai)) && (a.getType() == type)) contains = true; // Not sure if Set actually checks `equals`
		if(!contains) _AI.add(new EntityAI(ai, type, i));
	}
	protected void popAI(EntityAI ai) {
		if(_AI.contains(ai)) _AI.remove(ai);
	}
	protected abstract void initAI();
	@Override
	public Rectangle getBoundingBox() {
		return new Rectangle((int) this.x, (int) this.y, _w, _h);
	}
	@Override
	public void checkCollisions() {
		for(GameObject object : GameBase.OBJECTS) {
			if(object.getId() == ID.Block) {
				if(this.getBoundingBoxTop().intersects(object.getBoundingBox()) && ((Block)object).getCollidable()) {
					this.y = object.getY() + (this.getBoundingBox().height / 2);
					this.vely = 0;
				}
				if(this.getBoundingBoxDown().intersects(object.getBoundingBox()) && ((Block)object).getCollidable()) {
					this.y = object.getY() - this.getBoundingBox().height;
					this.vely = 0;
					this.falling = false;
				} else {
					this.falling = true;
				}
				if(this.getBoundingBoxRight().intersects(object.getBoundingBox()) && ((Block)object).getCollidable()) {
					this.x = object.getX() - 32;
					this.jump();
				}
				if(this.getBoundingBoxLeft().intersects(object.getBoundingBox()) && ((Block)object).getCollidable()) {
					this.x = object.getX() + 32;
					this.jump();
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

	protected void jump() {
		this.setJumping(true);
		this.setVely(-7.0f);
	}

	protected void jump(float strength) {
		this.setJumping(true);
		this.setVely(-strength);
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
	private void tickedAI() {
		for (EntityAI ai: _AI) {
			switch(ai.getType()) {
				case Looped:
					for(GameObject object : GameBase.OBJECTS) ai.getAi().onInit(this, object);
					break;
				case Timed:
					double t = (double)ticks;
					double expr = t / (double)ai.getTime();
					if(Math.floor(expr) == expr) ai.getAi().onInit(this, this);
					break;
				case TimedLoop:
					double ti = (double)ticks;
					double exp = ti / (double)ai.getTime();
					if(Math.floor(exp) == exp) for(GameObject object : GameBase.OBJECTS) ai.getAi().onInit(this, object);
					break;
				case Default:
					ai.getAi().onInit(this, this);
					break;
			}
		}
	}
}

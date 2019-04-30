package com.game.util;

/**
 * Each ID has it's own integer id. That means you can spawn an enemy using the number 3.
 */
public enum ID {
	Player(1),
	Block(2),
	Enemy(3),
	Bullet(4),
	@Deprecated EnemyShooting(5),
	@Deprecated EnemyJumping(6),
	Item(7);
	
	private int id;
	
	ID(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

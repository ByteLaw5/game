package com.game.util;

/**
 * Each ID has it's own integer id. That means you can spawn an enemy using the number 3.
 */
public enum ID {
	Player(1),
	Block(2),
	Enemy(3),
	Bullet(4),
	EnemyShooting(5);
	
	private int id;
	
	private ID(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}

package com.game.util;

public interface IHealth {
	int getMaxHealth();
	int getHealth();
	void setHealth(int health);
	void setMaxHealth(int health);
	void die();
}

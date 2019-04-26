package com.game.util;

/**
 * Used for things that don't move, but still have place in the world
 */
public interface IHasPlace {
	/**
	 * Get the X value of the object.
	 * @return the X value.
	 */
	float getX();
	/**
	 * Get the Y value of the object.
	 * @return the Y value.
	 */
	float getY();
	/**
	 * Set the X value of the object.
	 * @param x The value to change the X to.
	 */
	void setX(float x);
	/**
	 * Set the Y value of the object.
	 * @param y The value to change the Y to.
	 */
	void setY(float y);
}

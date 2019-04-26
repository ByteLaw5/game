package com.game.util;

/**
 * Something that has place in the world AND can move.
 */
public interface IMoveable extends IHasPlace {
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
	/**
	 * Get the VelX of the object.
	 * @return the VelX.
	 */
	float getVelx();
	/**
	 * Get the VelY of the object.
	 * @return the VelY.
	 */
	float getVely();
	/**
	 * Set the VelX value of the object.
	 * @param velx The value to change the VelX to.
	 */
	void setVelx(float velx);
	/**
	 * Set the VelY value of the object.
	 * @param vely The value to change the VelY to.
	 */
	void setVely(float vely);
	/**
	 * Get the {@link ID} of the object.
	 * @return The {@link ID}.
	 */
	ID getId();
}

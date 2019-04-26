package com.game.util;

import java.awt.Rectangle;

/**
 * Indicates that an object can have collision with other objects.
 */
public interface ICollision {
	/**
	 * Check if the object collides with other objects.
	 */
	void checkCollisions();
	/**
	 * Get the top bounding box of an object.
	 * @return Top bounding box.
	 */
	Rectangle getBoundingBoxTop();
	/**
	 * Get the bottom bounding box of an object.
	 * @return Bottom bounding box.
	 */
	Rectangle getBoundingBoxDown();
	/**
	 * Get the left bounding box of an object.
	 * @return Left bounding box.
	 */
	Rectangle getBoundingBoxLeft();
	/**
	 * Get the right bounding box of an object.
	 * @return Right bounding box.
	 */
	Rectangle getBoundingBoxRight();
}

package com.game.util;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
/**
 * Means that a class is a listener.
 */
public @interface IsListener {
	/**
	 * The name of the listener.
	 */
	String value();
}
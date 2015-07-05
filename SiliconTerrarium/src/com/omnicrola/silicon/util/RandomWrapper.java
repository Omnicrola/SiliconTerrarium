package com.omnicrola.silicon.util;

import java.util.Random;

import org.newdawn.slick.geom.Vector2f;

public class RandomWrapper {
	private final Random random;

	public RandomWrapper() {
		this.random = new Random();
	}

	public Vector2f randomVector(float max) {
		return randomVector(max, max);
	}

	public Vector2f randomVector(float maxX, float maxY) {
		final float x = this.random.nextFloat() * maxX;
		final float y = this.random.nextFloat() * maxY;
		return new Vector2f(x, y);
	}
}

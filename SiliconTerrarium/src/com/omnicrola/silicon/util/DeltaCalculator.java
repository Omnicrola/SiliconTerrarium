package com.omnicrola.silicon.util;

public class DeltaCalculator {

	private final float targetMillisecondsPerFrame;

	public DeltaCalculator(int targetFps) {
		this.targetMillisecondsPerFrame = 1000f / targetFps;
	}

	public float update(int elapsedTime) {
		return elapsedTime / this.targetMillisecondsPerFrame;
	}

}

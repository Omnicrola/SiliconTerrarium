package com.omnicrola.silicon;

public class TerrariumSettings {
	public static final TerrariumSettings INSTANCE = new TerrariumSettings();

	private static final int SCREEN_X = 1280;
	private static final int SCREEN_Y = 600;
	private static final int TARGET_FPS = 60;

	private static final int INITIAL_CREATURE_COUNT = 30;
	private static final int INITIAL_FOOD_COUNT = 50;
	private static final float CREATURE_ENERGY_DECAY_RATE = 0.1f;

	private TerrariumSettings() {
	}

	public int getScreenWidth() {
		return SCREEN_X;
	}

	public int getScreenHeight() {
		return SCREEN_Y;
	}

	public int getTargetFps() {
		return TARGET_FPS;
	}

	public int getInitialCreatureCount() {
		return INITIAL_CREATURE_COUNT;
	}

	public int getInitialFoodCount() {
		return INITIAL_FOOD_COUNT;
	}

	public float getCreatureEnergyDecayRate() {
		return CREATURE_ENERGY_DECAY_RATE;
	}
}

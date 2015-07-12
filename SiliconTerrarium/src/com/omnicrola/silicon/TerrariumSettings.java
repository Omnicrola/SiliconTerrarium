package com.omnicrola.silicon;

public class TerrariumSettings {
	public static final TerrariumSettings INSTANCE = new TerrariumSettings();

	private static final int SCREEN_X = 800;
	private static final int SCREEN_Y = 600;
	private static final int TARGET_FPS = 60;

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
}

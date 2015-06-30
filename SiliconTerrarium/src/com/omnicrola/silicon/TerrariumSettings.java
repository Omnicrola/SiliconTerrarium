package com.omnicrola.silicon;

public class TerrariumSettings {
	public static final TerrariumSettings INSTANCE = new TerrariumSettings();

	private static final int SCREEN_X = 800;
	private static final int SCREEN_Y = 600;

	private TerrariumSettings() {
	}

	public int getScreenX() {
		return SCREEN_X;
	}

	public int getScreenY() {
		return SCREEN_Y;
	}
}

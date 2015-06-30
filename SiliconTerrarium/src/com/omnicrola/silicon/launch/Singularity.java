package com.omnicrola.silicon.launch;

public class Singularity {
	public static SiliconTerrariumLauncher bang() {
		return new SiliconTerrariumLauncher(new SiliconTerrariumBuilder());
	}
}

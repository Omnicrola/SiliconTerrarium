package com.omnicrola.silicon.launch;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import com.omnicrola.silicon.SiliconTerrarium;
import com.omnicrola.silicon.TerrariumSettings;

public class SiliconTerrariumLauncher {
	private final SiliconTerrariumBuilder siliconTerrariumBuilder;

	public static void main(String[] args) {
		final SiliconTerrariumLauncher launcher = Singularity.bang();
		launcher.launch();
	}

	public SiliconTerrariumLauncher(SiliconTerrariumBuilder siliconTerrariumBuilder) {
		this.siliconTerrariumBuilder = siliconTerrariumBuilder;
	}

	private void launch() {
		final TerrariumSettings settings = TerrariumSettings.INSTANCE;
		try {
			final SiliconTerrarium terrarium = this.siliconTerrariumBuilder.build();
			final AppGameContainer appGameContainer = new AppGameContainer(terrarium);
			final int width = settings.getScreenWidth();
			final int height = settings.getScreenHeight();
			appGameContainer.setDisplayMode(width, height, false);
			appGameContainer.start();
		} catch (final SlickException e) {
			e.printStackTrace();
		}
	}
}

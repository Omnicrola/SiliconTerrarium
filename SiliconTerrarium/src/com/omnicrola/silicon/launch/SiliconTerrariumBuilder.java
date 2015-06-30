package com.omnicrola.silicon.launch;

import com.omnicrola.silicon.SiliconTerrarium;
import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.entity.EntityManager;

public class SiliconTerrariumBuilder {
	public SiliconTerrariumBuilder() {
	}

	public SiliconTerrarium build() {
		final TerrariumInitializer initializer = new TerrariumInitializer(TerrariumSettings.INSTANCE);
		final EntityManager entityManager = new EntityManager();
		final CommandQueue commandQueue = CommandQueue.instance();
		return new SiliconTerrarium(initializer, entityManager, commandQueue);
	}
}

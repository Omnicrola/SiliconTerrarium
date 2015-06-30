package com.omnicrola.silicon.launch;

import com.omnicrola.silicon.SiliconTerrarium;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.entity.EntityManager;

public class SiliconTerrariumBuilder {
	public SiliconTerrariumBuilder() {
	}

	public SiliconTerrarium build() {
		return new SiliconTerrarium(new TerrariumInitializer(), new EntityManager(), CommandQueue.instance());
	}
}

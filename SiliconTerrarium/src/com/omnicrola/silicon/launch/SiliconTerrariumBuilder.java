package com.omnicrola.silicon.launch;

import com.omnicrola.silicon.SiliconTerrarium;
import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.behavior.BehaviorFactor;
import com.omnicrola.silicon.entity.behavior.NeuralNetworkFactory;
import com.omnicrola.silicon.util.DeltaCalculator;
import com.omnicrola.silicon.util.RandomWrapper;

public class SiliconTerrariumBuilder {
	public SiliconTerrariumBuilder() {
	}

	public SiliconTerrarium build() {
		final TerrariumSettings settings = TerrariumSettings.INSTANCE;

		final EntityManager entityManager = new EntityManager();
		final TerrariumInitializer initializer = new TerrariumInitializer(createEntityFactory(settings, entityManager));
		final CommandQueue commandQueue = CommandQueue.instance();
		return new SiliconTerrarium(initializer, entityManager, commandQueue, new DeltaCalculator(
				settings.getTargetFps()));
	}

	private EntityFactory createEntityFactory(TerrariumSettings settings, EntityManager entityManager) {
		final NeuralNetworkFactory neuralNetworkFactory = new NeuralNetworkFactory(entityManager);
		final BehaviorFactor behaviorFactory = new BehaviorFactor(neuralNetworkFactory);
		final RandomWrapper random = new RandomWrapper();

		return new EntityFactory(behaviorFactory, settings, random);
	}
}

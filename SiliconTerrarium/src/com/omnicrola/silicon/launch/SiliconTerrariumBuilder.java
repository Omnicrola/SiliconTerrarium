package com.omnicrola.silicon.launch;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.core.SiliconTerrarium;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.behavior.BehaviorFactor;
import com.omnicrola.silicon.entity.behavior.NeuralNetworkFactory;
import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.neural.NeuralInputFactory;
import com.omnicrola.silicon.neural.NeuralOutputFactory;
import com.omnicrola.silicon.util.DeltaCalculator;

public class SiliconTerrariumBuilder {
	public SiliconTerrariumBuilder() {
	}

	public SiliconTerrarium build() {
		final TerrariumSettings settings = TerrariumSettings.INSTANCE;

		final EntityManager entityManager = new EntityManager(settings, new CollisionManager());
		final TerrariumInitializer initializer = new TerrariumInitializer(createEntityFactory(settings, entityManager),
				settings);
		final CommandQueue commandQueue = CommandQueue.instance();
		return new SiliconTerrarium(initializer, entityManager, commandQueue, new DeltaCalculator(
				settings.getTargetFps()));
	}

	private EntityFactory createEntityFactory(TerrariumSettings settings, EntityManager entityManager) {
		final NeuralOutputFactory neuralOutputFactory = new NeuralOutputFactory();
		final NeuralInputFactory neuralInputFactory = new NeuralInputFactory();
		final NeuralNetworkFactory neuralNetworkFactory = new NeuralNetworkFactory(entityManager, neuralInputFactory,
				neuralOutputFactory);
		final BehaviorFactor behaviorFactory = new BehaviorFactor(neuralNetworkFactory);

		return new EntityFactory(behaviorFactory);
	}
}

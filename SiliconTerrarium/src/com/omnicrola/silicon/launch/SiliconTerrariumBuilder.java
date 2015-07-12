package com.omnicrola.silicon.launch;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.command.GenerationTrigger;
import com.omnicrola.silicon.core.SiliconTerrarium;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.ResurrectionManager;
import com.omnicrola.silicon.entity.behavior.BehaviorFactory;
import com.omnicrola.silicon.entity.behavior.NeuralNetworkFactory;
import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.input.InputHandler;
import com.omnicrola.silicon.neural.NeuralInputFactory;
import com.omnicrola.silicon.neural.NeuralOutputFactory;
import com.omnicrola.silicon.util.DeltaCalculator;

public class SiliconTerrariumBuilder {
	public SiliconTerrariumBuilder() {
	}

	public SiliconTerrarium build() {
		final TerrariumSettings settings = TerrariumSettings.INSTANCE;

		final CollisionManager collisionManager = new CollisionManager();
		final EntityFactory entityFactory = createEntityFactory(settings, collisionManager);
		final ResurrectionManager resurrectionManager = new ResurrectionManager(entityFactory, settings);
		final EntityManager entityManager = new EntityManager(settings, collisionManager, resurrectionManager);
		final CommandQueue commandQueue = CommandQueue.instance();
		final SiliconTerrarium siliconTerrarium = new SiliconTerrarium(commandQueue, new DeltaCalculator(
				settings.getTargetFps()));
		siliconTerrarium.addSubsystem(entityManager);
		siliconTerrarium.addSubsystem(new InputHandler(settings));
		loadInitialEntities(entityFactory);
		setGenerationTrigger(siliconTerrarium);
		return siliconTerrarium;
	}

	private EntityFactory createEntityFactory(TerrariumSettings settings, CollisionManager collisionManager) {
		final NeuralOutputFactory neuralOutputFactory = new NeuralOutputFactory();
		final NeuralInputFactory neuralInputFactory = new NeuralInputFactory();
		final NeuralNetworkFactory neuralNetworkFactory = new NeuralNetworkFactory(collisionManager,
				neuralInputFactory, neuralOutputFactory);
		final BehaviorFactory behaviorFactory = new BehaviorFactory();

		return new EntityFactory(behaviorFactory, neuralNetworkFactory);
	}

	private void loadInitialEntities(EntityFactory entityFactory) {
		final CreateNewTerrariumCommand newTerrariumCommand = new CreateNewTerrariumCommand(entityFactory,
				TerrariumSettings.INSTANCE);
		CommandQueue.instance().enqueueCommand(newTerrariumCommand);
	}

	private void setGenerationTrigger(SiliconTerrarium siliconTerrarium) {
		CommandQueue.instance().enqueueCommand(new GenerationTrigger());
	}
}

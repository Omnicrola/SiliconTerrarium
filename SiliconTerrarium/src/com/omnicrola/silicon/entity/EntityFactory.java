package com.omnicrola.silicon.entity;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.behavior.BehaviorFactory;
import com.omnicrola.silicon.entity.behavior.NeuralNetworkFactory;
import com.omnicrola.silicon.neural.INeuralNetwork;
import com.omnicrola.silicon.neural.NullNeuralNetwork;

public class EntityFactory {
	private final BehaviorFactory behaviorFactory;
	private final NeuralNetworkFactory neuralNetworkFactory;

	public EntityFactory(BehaviorFactory behaviorFactory, NeuralNetworkFactory neuralNetworkFactory) {
		this.behaviorFactory = behaviorFactory;
		this.neuralNetworkFactory = neuralNetworkFactory;
	}

	public ISiliconEntity buildFood(Vector2f position, Vector2f velocity) {
		final EntityShape shape = EntityShape.createFoodShape();
		final SiliconEntity siliconEntity = new SiliconEntity(NullNeuralNetwork.NULL, shape, EntityType.FOOD, 1f);
		siliconEntity.setPosition(position);
		siliconEntity.setVelocity(velocity);
		return siliconEntity;
	}

	public ISiliconEntity buildCritter(Vector2f position, Vector2f velocity) {
		final INeuralNetwork neuralNetwork = this.neuralNetworkFactory.build();
		return buildCritter(position, velocity, neuralNetwork);
	}

	public ISiliconEntity buildCritter(Vector2f position, Vector2f vector2f, INeuralNetwork neuralNetwork) {
		final EntityShape renderShape = EntityShape.defaultCritterShape();
		final SiliconEntity siliconEntity = new SiliconEntity(neuralNetwork, renderShape, EntityType.CREATURE, 10f);
		siliconEntity.addUpdateBehavior(this.behaviorFactory.buildFitnessOverTime());
		siliconEntity.addUpdateBehavior(this.behaviorFactory.buildUseEnergy());
		siliconEntity.addCollisionBehavior(this.behaviorFactory.buildEatBehavior());
		siliconEntity.setPosition(position);
		return siliconEntity;
	}
}

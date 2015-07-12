package com.omnicrola.silicon.entity;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.behavior.BehaviorFactor;

public class EntityFactory {
	private final BehaviorFactor behaviorFactory;

	public EntityFactory(BehaviorFactor behaviorFactory) {
		this.behaviorFactory = behaviorFactory;
	}

	public ISiliconEntity buildFood(Vector2f position, Vector2f velocity) {
		final EntityShape shape = EntityShape.createFoodShape();
		final SiliconEntity siliconEntity = new SiliconEntity(shape, EntityType.FOOD, 1f);
		siliconEntity.setPosition(position);
		siliconEntity.setVelocity(velocity);
		return siliconEntity;
	}

	public ISiliconEntity buildCritter(Vector2f position, Vector2f velocity) {
		final EntityShape renderShape = EntityShape.defaultCritterShape();
		final SiliconEntity siliconEntity = new SiliconEntity(renderShape, EntityType.CREATURE, 10f);
		siliconEntity.addUpdateBehavior(this.behaviorFactory.buildFitnessOverTime());
		siliconEntity.addUpdateBehavior(this.behaviorFactory.buildUseEnergy());
		siliconEntity.addUpdateBehavior(this.behaviorFactory.buildNeuralNetwork(siliconEntity));
		siliconEntity.addCollisionBehavior(this.behaviorFactory.buildEatBehavior());
		siliconEntity.setPosition(position);
		return siliconEntity;
	}
}

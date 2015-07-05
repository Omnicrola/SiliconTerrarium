package com.omnicrola.silicon.entity;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.behavior.BehaviorFactor;

public class EntityFactory {
	private final BehaviorFactor behaviorFactory;

	public EntityFactory(BehaviorFactor behaviorFactory) {
		this.behaviorFactory = behaviorFactory;
	}

	public SiliconEntity buildFood(Vector2f position, Vector2f velocity) {
		final EntityShape shape = EntityShape.createFoodShape();
		final SiliconEntity siliconEntity = new SiliconEntity(shape, EntityType.FOOD);
		siliconEntity.setPosition(position);
		siliconEntity.setVelocity(velocity);
		return siliconEntity;
	}

	public SiliconEntity buildCritter(Vector2f position, Vector2f velocity) {
		final EntityShape renderShape = EntityShape.defaultCritterShape();
		final SiliconEntity siliconEntity = new SiliconEntity(renderShape, EntityType.CREATURE);
		siliconEntity.addBehavior(this.behaviorFactory.buildNeuralNetwork(siliconEntity));
		siliconEntity.setPosition(position);
		siliconEntity.setVelocity(velocity);
		return siliconEntity;
	}

}

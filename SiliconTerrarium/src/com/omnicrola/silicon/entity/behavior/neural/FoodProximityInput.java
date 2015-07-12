package com.omnicrola.silicon.entity.behavior.neural;

import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class FoodProximityInput implements INeuralInput {

	private final EntityManager entityManager;
	private final ISiliconEntity siliconEntity;

	public FoodProximityInput(NeuralContext neuralContext) {
		this.siliconEntity = neuralContext.getEntity();
		this.entityManager = neuralContext.getEntityManager();
	}

	@Override
	public float evaluate() {
		final Vector2f ourPosition = this.siliconEntity.getPosition();
		final Optional<ISiliconEntity> food = this.entityManager.getNearestEntityOfType(EntityType.FOOD,
				this.siliconEntity);
		if (food.isPresent()) {
			final float distance = ourPosition.distance(food.get().getPosition());
			return distance;
		}
		return 0;
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

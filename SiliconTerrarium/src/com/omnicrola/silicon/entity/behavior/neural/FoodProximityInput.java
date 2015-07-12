package com.omnicrola.silicon.entity.behavior.neural;

import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class FoodProximityInput implements INeuralInput {

	@Override
	public float evaluate(NeuralContext context) {
		final ISiliconEntity entity = context.getEntity();
		final Vector2f ourPosition = entity.getPosition();
		final Optional<ISiliconEntity> food = context.getEnvironmentHandler().getNearestEntityOfType(EntityType.FOOD,
				entity);
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

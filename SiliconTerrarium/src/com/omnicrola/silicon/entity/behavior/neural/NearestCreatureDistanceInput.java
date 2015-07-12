package com.omnicrola.silicon.entity.behavior.neural;

import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class NearestCreatureDistanceInput implements INeuralInput {
	public static final float MAX_DISTANCE = 10f;

	@Override
	public float evaluate(NeuralContext context) {
		final ISiliconEntity entity = context.getEntity();
		final Optional<ISiliconEntity> nearestCreature = context.getEnvironmentHandler().getNearestEntityOfType(
				EntityType.CREATURE, entity);
		if (nearestCreature.isPresent()) {
			final Vector2f otherPosition = nearestCreature.get().getPosition();
			final float distance = entity.getPosition().distance(otherPosition);
			return MAX_DISTANCE - distance;
		}
		return 0;
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return this;
	}

}

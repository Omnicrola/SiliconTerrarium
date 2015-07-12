package com.omnicrola.silicon.entity.behavior.neural;

import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class NearestCreatureDistanceInput implements INeuralInput {
	public static final float MAX_DISTANCE = 10f;
	private final ISiliconEntity siliconEntity;
	private final EntityManager entityManager;

	public NearestCreatureDistanceInput(NeuralContext neuralContext) {
		this.siliconEntity = neuralContext.getEntity();
		this.entityManager = neuralContext.getEntityManager();
	}

	@Override
	public float evaluate() {
		final Optional<ISiliconEntity> nearestCreature = this.entityManager.getNearestEntityOfType(EntityType.CREATURE,
				this.siliconEntity);
		if (nearestCreature.isPresent()) {
			final Vector2f otherPosition = nearestCreature.get().getPosition();
			final float distance = this.siliconEntity.getPosition().distance(otherPosition);
			return MAX_DISTANCE - distance;
		}
		return 0;
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

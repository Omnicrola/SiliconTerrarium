package com.omnicrola.silicon.entity.behavior.neural;

import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;

public class NearestCreatureDistanceInput implements INeuralInput {

	private final ISiliconEntity siliconEntity;
	private final EntityManager entityManager;

	public NearestCreatureDistanceInput(ISiliconEntity siliconEntity, EntityManager entityManager) {
		this.siliconEntity = siliconEntity;
		this.entityManager = entityManager;
	}

	@Override
	public float evaluate() {
		final Optional<ISiliconEntity> nearestCreature = this.entityManager.getNearestEntityOfType(EntityType.CREATURE,
				this.siliconEntity);
		if (nearestCreature.isPresent()) {
			final Vector2f otherPosition = nearestCreature.get().getPosition();
			return this.siliconEntity.getPosition().distance(otherPosition);
		}
		return 0;
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

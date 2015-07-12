package com.omnicrola.silicon.neural;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.ISiliconEntity;

public class NeuralContext {

	private final ISiliconEntity siliconEntity;
	private final EntityManager entityManager;

	public NeuralContext(ISiliconEntity siliconEntity, EntityManager entityManager) {
		this.siliconEntity = siliconEntity;
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public ISiliconEntity getEntity() {
		return this.siliconEntity;
	}
}

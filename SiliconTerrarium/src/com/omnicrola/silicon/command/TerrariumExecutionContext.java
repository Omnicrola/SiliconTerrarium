package com.omnicrola.silicon.command;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.SiliconEntity;

public class TerrariumExecutionContext implements ICommandContext {

	private final EntityManager entityManager;

	public TerrariumExecutionContext(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void clearAllEntities() {
		this.entityManager.clearAll();
	}

	@Override
	public void addEntity(SiliconEntity newEntity) {
		this.entityManager.addEntity(newEntity);
	}

}

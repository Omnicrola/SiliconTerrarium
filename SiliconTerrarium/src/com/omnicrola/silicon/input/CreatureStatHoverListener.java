package com.omnicrola.silicon.input;

import java.util.Optional;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.ISiliconEntity;

public class CreatureStatHoverListener implements IMouseMoveListener {

	private final EntityManager entityManager;

	public CreatureStatHoverListener(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		final Optional<ISiliconEntity> entity = this.entityManager.getEntityAt(newX, newY);
		if (entity.isPresent()) {
			System.out.println("E:" + entity.get().getEnergy());
		}
	}

}

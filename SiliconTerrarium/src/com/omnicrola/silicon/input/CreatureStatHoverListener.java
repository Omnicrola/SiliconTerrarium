package com.omnicrola.silicon.input;

import java.util.Optional;

import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.entity.physics.CollisionManager;

public class CreatureStatHoverListener implements IMouseMoveListener {

	private final CollisionManager collisionManager;

	public CreatureStatHoverListener(CollisionManager collisionManager) {
		this.collisionManager = collisionManager;
	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		final Optional<ISiliconEntity> entity = this.collisionManager.getEntityAt(newX, newY);
		if (entity.isPresent()) {
			System.out.println("E:" + entity.get().getEnergy());
		}
	}

}

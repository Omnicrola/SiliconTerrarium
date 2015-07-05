package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class EntityManager {

	private final ArrayList<ISiliconEntity> entities;
	private final CollisionManager collisionManager;

	public EntityManager(CollisionManager collisionManager) {
		this.collisionManager = collisionManager;
		this.entities = new ArrayList<>();
	}

	public void addEntity(ISiliconEntity entity) {
		this.entities.add(entity);
		this.collisionManager.addEntity(entity);
	}

	public void render(IRenderWrapper renderWrapper) {
		for (final ISiliconEntity entity : this.entities) {
			final EntityShape renderShape = entity.getShape();
			renderWrapper.renderShape(renderShape);
		}
	}

	public void clearAll() {
		this.entities.clear();
	}

	public void update(float delta) {
		updateEntities(delta);
		this.collisionManager.update(delta);
		cleanDeadEntities();
	}

	private void updateEntities(float delta) {
		for (final ISiliconEntity siliconEntity : this.entities) {
			siliconEntity.update(delta);
		}
	}

	private void cleanDeadEntities() {
		for (final ISiliconEntity siliconEntity : new ArrayList<>(this.entities)) {
			if (!siliconEntity.isAlive()) {
				this.entities.remove(siliconEntity);
			}
		}
	}

}

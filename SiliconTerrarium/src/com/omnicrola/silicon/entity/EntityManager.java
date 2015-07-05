package com.omnicrola.silicon.entity;

import java.util.ArrayList;
import java.util.Optional;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.core.IRenderable;
import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class EntityManager implements IRenderable {

	private final ArrayList<ISiliconEntity> entities;
	private final CollisionManager collisionManager;
	private final TerrariumSettings settings;

	public EntityManager(TerrariumSettings settings, CollisionManager collisionManager) {
		this.settings = settings;
		this.collisionManager = collisionManager;
		this.entities = new ArrayList<>();
	}

	public void addEntity(ISiliconEntity entity) {
		this.entities.add(entity);
		this.collisionManager.addEntity(entity);
	}

	@Override
	public void render(IRenderWrapper renderWrapper) {
		for (final ISiliconEntity entity : this.entities) {
			final EntityShape renderShape = entity.getShape();
			renderWrapper.renderShape(renderShape);
		}
	}

	public void clearAll() {
		this.entities.clear();
	}

	@Override
	public void update(float delta) {
		updateEntities(delta);
		wrapEdges();
		this.collisionManager.update(delta);
		cleanDeadEntities();
	}

	private void wrapEdges() {
		for (final ISiliconEntity entity : this.entities) {
			final Vector2f p = entity.getPosition();
			final int screenWidth = this.settings.getScreenWidth();
			final int screenHeight = this.settings.getScreenHeight();
			if (p.x < 0) {
				p.x = screenWidth;
			} else if (p.x > screenWidth) {
				p.x = 0;
			}
			if (p.y < 0) {
				p.y = screenHeight;
			} else if (p.y > screenHeight) {
				p.y = 0;
			}
			entity.setPosition(p);
		}
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

	public Optional<ISiliconEntity> getEntityAt(int x, int y) {
		return this.collisionManager.getEntityAt(x, y);
	}

	public Optional<ISiliconEntity> getNearestEntityOfType(EntityType type, ISiliconEntity searchEntity) {
		return this.collisionManager.getNearestEntityOfType(type, searchEntity);
	}

}

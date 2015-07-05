package com.omnicrola.silicon.entity.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.geom.Shape;

import com.omnicrola.silicon.core.IUpdatable;
import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;

public class CollisionManager implements IUpdatable {
	private final HashMap<EntityType, List<ISiliconEntity>> collisionGroups;

	public CollisionManager() {
		this.collisionGroups = new HashMap<>();
		initGroups();
	}

	private void initGroups() {
		for (final EntityType entityType : EntityType.values()) {
			this.collisionGroups.put(entityType, new ArrayList<>());
		}
	}

	public void addEntity(ISiliconEntity entity) {
		this.collisionGroups.get(entity.getType()).add(entity);
	}

	@Override
	public void update(float delta) {
		final Set<Entry<EntityType, List<ISiliconEntity>>> groups = this.collisionGroups.entrySet();
		for (final Entry<EntityType, List<ISiliconEntity>> group1 : groups) {
			for (final Entry<EntityType, List<ISiliconEntity>> group2 : groups) {
				if (!group1.getKey().equals(group2.getKey())) {
					checkCollisions(group1.getValue(), group2.getValue());
				}
			}
		}
	}

	private void checkCollisions(List<ISiliconEntity> group1, List<ISiliconEntity> group2) {
		for (final ISiliconEntity entity1 : group1) {
			for (final ISiliconEntity entity2 : group2) {
				checkCollision(entity1, entity2);
			}
		}
	}

	private void checkCollision(final ISiliconEntity entity1, final ISiliconEntity entity2) {
		if (entity1.isAlive() && entity2.isAlive()) {
			final Shape shape1 = entity1.getShape().getShape();
			final Shape shape2 = entity2.getShape().getShape();
			if (shape1.intersects(shape2)) {
				entity1.collide(entity2);
				entity2.collide(entity1);
			}
		}
	}

}

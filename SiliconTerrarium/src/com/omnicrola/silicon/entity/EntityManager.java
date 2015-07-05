package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class EntityManager {

	private final ArrayList<ISiliconEntity> entities;

	public EntityManager() {
		this.entities = new ArrayList<>();
	}

	public void addEntity(ISiliconEntity creature) {
		this.entities.add(creature);
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
		for (final ISiliconEntity siliconEntity : this.entities) {
			siliconEntity.update(delta);
		}
	}

}

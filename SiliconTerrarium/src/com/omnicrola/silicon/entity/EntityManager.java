package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import com.omnicrola.silicon.creature.shape.RenderShape;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class EntityManager {

	private final ArrayList<SiliconEntity> entities;

	public EntityManager() {
		this.entities = new ArrayList<>();
	}

	public void addEntity(SiliconEntity creature) {
		this.entities.add(creature);
	}

	public void render(IRenderWrapper renderWrapper) {
		for (final SiliconEntity entity : this.entities) {
			final RenderShape renderShape = entity.getShape();
			renderWrapper.renderShape(renderShape);
		}
	}

	public void clearAll() {
		this.entities.clear();
	}

	public void update(float delta) {
		for (final SiliconEntity siliconEntity : this.entities) {
			siliconEntity.update(delta);
		}
	}

}

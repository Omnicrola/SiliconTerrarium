package com.omnicrola.silicon.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Polygon;

import com.omnicrola.silicon.creature.shape.RenderShape;
import com.omnicrola.silicon.util.RandomWrapper;

public class EntityFactory {
	private final RandomWrapper random;

	public EntityFactory(RandomWrapper random) {
		this.random = random;
	}

	public SiliconEntity build() {
		final Polygon renderShape = new Polygon();
		renderShape.addPoint(5, 5);
		renderShape.addPoint(-5, 5);
		renderShape.addPoint(-5, -5);
		renderShape.addPoint(5, -5);
		final SiliconEntity siliconEntity = new SiliconEntity(new RenderShape(renderShape, Color.white));
		siliconEntity.setPosition(this.random.randomVector(500, 500));
		return siliconEntity;
	}

}

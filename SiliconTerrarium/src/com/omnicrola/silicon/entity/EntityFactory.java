package com.omnicrola.silicon.entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.creature.shape.RenderShape;
import com.omnicrola.silicon.entity.behavior.BehaviorFactor;
import com.omnicrola.silicon.util.RandomWrapper;

public class EntityFactory {
	private final RandomWrapper random;
	private final TerrariumSettings terrariumSettings;
	private final BehaviorFactor behaviorFactory;

	public EntityFactory(BehaviorFactor behaviorFactory, TerrariumSettings terrariumSettings, RandomWrapper random) {
		this.behaviorFactory = behaviorFactory;
		this.terrariumSettings = terrariumSettings;
		this.random = random;
	}

	public SiliconEntity buildCritter() {
		final Polygon renderShape = new Polygon();
		renderShape.addPoint(-5, -5);
		renderShape.addPoint(0, -5);
		renderShape.addPoint(10, 0);
		renderShape.addPoint(0, 5);
		renderShape.addPoint(-5, 5);
		final SiliconEntity siliconEntity = new SiliconEntity(new RenderShape(renderShape, Color.white));
		setRandomPosition(siliconEntity);
		setRandomVelocity(siliconEntity);
		return siliconEntity;
	}

	private void setRandomVelocity(SiliconEntity siliconEntity) {
		final Vector2f velocity = this.random.randomVector(3f, 3f).sub(this.random.randomVector(3f, 3f));
		siliconEntity.setVelocity(velocity);
	}

	private void setRandomPosition(final SiliconEntity siliconEntity) {
		final int screenX = this.terrariumSettings.getScreenWidth();
		final int screenY = this.terrariumSettings.getScreenHeight();
		final Vector2f randomVector = this.random.randomVector(screenX, screenY);
		siliconEntity.setPosition(randomVector);
	}

}

package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.behavior.IBehavior;

public class SiliconEntity implements ISiliconEntity {
	private final EntityShape baseShape;
	private float size;
	private final ArrayList<IBehavior> behaviors;
	private final MotionGovernor motionGovernor;
	private final EntityType entityType;
	private boolean isAlive = true;

	public SiliconEntity(EntityShape entityShape, EntityType entityType) {
		this.entityType = entityType;
		this.behaviors = new ArrayList<>();
		this.baseShape = entityShape;
		this.motionGovernor = new MotionGovernor();
		this.size = 1.0f;
	}

	public EntityType getEntityType() {
		return this.entityType;
	}

	public void addBehavior(IBehavior behavior) {
		this.behaviors.add(behavior);
	}

	@Override
	public void setPosition(Vector2f position) {
		this.motionGovernor.setPosition(position);
	}

	@Override
	public Vector2f getPosition() {
		return this.motionGovernor.getPosition();
	}

	public void setSize(float size) {
		this.size = size;
	}

	public IMotionGovernor getMotionGovernor() {
		return this.motionGovernor;
	}

	public void setVelocity(Vector2f velocity) {
		this.motionGovernor.setVelocity(velocity);
	}

	//@formatter:off
	@Override
	public EntityShape getShape() {
		final Vector2f p = this.motionGovernor.getPosition();
		final Vector2f v = this.motionGovernor.getVelocity();
		final Transform translation = Transform.createTranslateTransform(p.x, p.y);
		final Transform rotation = Transform.createRotateTransform((float) Math.toRadians(v.getTheta()));
		final Transform scaling = Transform.createScaleTransform(this.size, this.size);

		final Shape transformedShape = this.baseShape.getShape()
				.transform(scaling)
				.transform(rotation)
				.transform(translation);
		return new EntityShape(transformedShape, this.baseShape.getColor());
	}
	//@formatter:on

	@Override
	public void update(float delta) {
		updateBehaviors(delta);
		this.motionGovernor.update(delta);
	}

	private void updateBehaviors(float delta) {
		for (final IBehavior behavior : this.behaviors) {
			behavior.execute(this, delta);
		}

	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}

	public void destroy() {
		this.isAlive = false;
	}

}

package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.RenderShape;
import com.omnicrola.silicon.entity.behavior.IBehavior;

public class SiliconEntity implements ISiliconEntity {
	private final RenderShape baseShape;
	private float size;
	private final ArrayList<IBehavior> behaviors;
	private final MotionGovernor motionGovernor;

	public SiliconEntity(RenderShape renderShape) {
		this.behaviors = new ArrayList<>();
		this.baseShape = renderShape;
		this.motionGovernor = new MotionGovernor();
		this.size = 1.0f;
	}

	public void addBehavior(IBehavior behavior) {
		this.behaviors.add(behavior);
	}

	public void setPosition(Vector2f position) {
		this.motionGovernor.setPosition(position);
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
	public RenderShape getShape() {
		final Vector2f p = this.motionGovernor.getPosition();
		final Vector2f v = this.motionGovernor.getVelocity();
		final Transform translation = Transform.createTranslateTransform(p.x, p.y);
		final Transform rotation = Transform.createRotateTransform((float) Math.toRadians(v.getTheta()));
		final Transform scaling = Transform.createScaleTransform(this.size, this.size);

		final Shape transformedShape = this.baseShape.getShape()
				.transform(scaling)
				.transform(rotation)
				.transform(translation);
		return new RenderShape(transformedShape, this.baseShape.getColor());
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

}

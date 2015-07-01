package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.RenderShape;
import com.omnicrola.silicon.entity.behavior.IBehavior;

public class SiliconEntity implements ISiliconEntity {
	private Vector2f position;
	private Vector2f velocity;
	private final RenderShape baseShape;
	private float size;
	private final ArrayList<IBehavior> behaviors;

	public SiliconEntity(RenderShape renderShape) {
		this.position = new Vector2f();
		this.velocity = new Vector2f();
		this.behaviors = new ArrayList<>();
		this.baseShape = renderShape;
		this.size = 1.0f;
	}

	public void addBehavior(IBehavior behavior) {
		this.behaviors.add(behavior);
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}

	//@formatter:off
	public RenderShape getShape() {
			final Transform translation = Transform.createTranslateTransform(this.position.x, this.position.y);
			final Transform rotation = Transform.createRotateTransform((float) Math.toRadians(this.velocity.getTheta()));
			final Transform scaling = Transform.createScaleTransform(this.size, this.size);

			final Shape transformedShape = this.baseShape.getShape()
						.transform(scaling)
						.transform(rotation)
						.transform(translation);
			return new RenderShape(transformedShape, this.baseShape.getColor());
	}
	//@formatter:on

	public void update(float delta) {
		updateBehaviors(delta);
		this.position.x += this.velocity.x * delta;
		this.position.y += this.velocity.y * delta;

	}

	private void updateBehaviors(float delta) {
		for (final IBehavior behavior : this.behaviors) {
			behavior.execute(this, delta);
		}

	}

}

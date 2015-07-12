package com.omnicrola.silicon.entity;

import org.newdawn.slick.geom.Vector2f;

public class MotionGovernor implements IMotionGovernor {
	private final Vector2f position;
	private final Vector2f velocity;

	public MotionGovernor() {
		this.position = new Vector2f();
		this.velocity = new Vector2f();
	}

	public void setPosition(Vector2f position) {
		this.position.set(position);
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity.set(velocity);
	}

	public Vector2f getPosition() {
		return this.position;
	}

	public Vector2f getVelocity() {
		return this.velocity;
	}

	public void update(float delta) {
		this.position.x += this.velocity.x * delta;
		this.position.y += this.velocity.y * delta;

	}

	@Override
	public void rotate(float degrees) {
		this.velocity.setTheta(degrees);
	}

	@Override
	public void modifyVelocity(float scaleAmount) {
		this.velocity.scale(scaleAmount);
	}

}

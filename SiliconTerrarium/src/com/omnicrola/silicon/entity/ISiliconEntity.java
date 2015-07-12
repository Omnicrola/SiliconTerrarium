package com.omnicrola.silicon.entity;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;

public interface ISiliconEntity {

	void update(float delta);

	EntityShape getShape();

	boolean isAlive();

	Vector2f getPosition();

	void setPosition(Vector2f position);

	EntityType getType();

	void collide(ISiliconEntity otherEntity);

	void adjustEnergy(float amount);

	float getEnergy();

	void adjustFitness(float amount);

	public abstract float getFitness();

	IMotionGovernor getMotionGovernor();

}

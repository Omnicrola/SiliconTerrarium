package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.creature.shape.EntityShape;
import com.omnicrola.silicon.entity.behavior.ICollisionBehavior;
import com.omnicrola.silicon.entity.behavior.IUpdateBehavior;
import com.omnicrola.silicon.neural.INeuralNetwork;
import com.omnicrola.silicon.neural.MutationDirective;

public class SiliconEntity implements ISiliconEntity {
	private final EntityShape baseShape;
	private float size;
	private final ArrayList<IUpdateBehavior> behaviorsUpdate;
	private final ArrayList<ICollisionBehavior> behaviorsCollision;
	private final MotionGovernor motionGovernor;
	private final EntityType entityType;
	private boolean isAlive = true;
	private float energy;
	private float fitness;
	private final INeuralNetwork neuralNetwork;

	public SiliconEntity(INeuralNetwork neuralNetwork, EntityShape entityShape, EntityType entityType,
			float startingEnergy) {
		this.entityType = entityType;
		this.behaviorsUpdate = new ArrayList<>();
		this.behaviorsCollision = new ArrayList<>();
		this.baseShape = entityShape;
		this.motionGovernor = new MotionGovernor();
		this.size = 1.0f;
		this.fitness = 0.0f;
		this.energy = startingEnergy;
		this.neuralNetwork = neuralNetwork;
	}

	@Override
	public EntityType getType() {
		return this.entityType;
	}

	public void addUpdateBehavior(IUpdateBehavior behavior) {
		this.behaviorsUpdate.add(behavior);
	}

	public void addCollisionBehavior(ICollisionBehavior behavior) {
		this.behaviorsCollision.add(behavior);
	}

	@Override
	public void collide(ISiliconEntity otherEntity) {
		for (final ICollisionBehavior collisionBehavior : this.behaviorsCollision) {
			collisionBehavior.collide(this, otherEntity);
		}
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

	@Override
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
		this.neuralNetwork.evaluate(this);
		this.motionGovernor.update(delta);
	}

	private void updateBehaviors(float delta) {
		for (final IUpdateBehavior behavior : this.behaviorsUpdate) {
			behavior.execute(this, delta);
		}

	}

	@Override
	public boolean isAlive() {
		return this.isAlive && this.energy > 0f;
	}

	public void destroy() {
		this.isAlive = false;
	}

	@Override
	public void adjustEnergy(float amount) {
		this.energy += amount;
	}

	@Override
	public float getEnergy() {
		return this.energy;
	}

	@Override
	public void adjustFitness(float amount) {
		this.fitness += amount;
	}

	@Override
	public float getFitness() {
		return this.fitness + (this.energy / 2f);
	}

	@Override
	public INeuralNetwork mutateNeuralNetwork(MutationDirective mutationDirective) {
		return this.neuralNetwork.mutate(mutationDirective);
	}
}

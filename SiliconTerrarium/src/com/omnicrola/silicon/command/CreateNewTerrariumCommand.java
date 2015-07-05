package com.omnicrola.silicon.command;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.SiliconEntity;
import com.omnicrola.silicon.util.RandomWrapper;

public class CreateNewTerrariumCommand implements ICommand {

	private static final int NEW_CREATURE_COUNT = 20;
	private static final int FOOD_COUNT = 50;

	private final EntityFactory entityFactory;
	private final RandomWrapper random;
	private final TerrariumSettings settings;

	public CreateNewTerrariumCommand(EntityFactory entityFactory, TerrariumSettings settings) {
		this.entityFactory = entityFactory;
		this.settings = settings;
		this.random = new RandomWrapper();
	}

	@Override
	public void execute(ICommandContext executionContext) {
		executionContext.clearAllEntities();
		buildCreatures(executionContext);
		addFood(executionContext);
	}

	private void addFood(ICommandContext executionContext) {
		for (int i = 0; i < FOOD_COUNT; i++) {
			final Vector2f position = randomPosition();
			final Vector2f velocity = randomVelocity();
			final SiliconEntity newEntity = this.entityFactory.buildFood(position, velocity);
			executionContext.addEntity(newEntity);
		}
	}

	private void buildCreatures(ICommandContext executionContext) {
		for (int i = 0; i < NEW_CREATURE_COUNT; i++) {
			final Vector2f position = randomPosition();
			final Vector2f velocity = randomVelocity();
			final SiliconEntity newEntity = this.entityFactory.buildCritter(position, velocity);
			executionContext.addEntity(newEntity);
		}
	}

	private Vector2f randomVelocity() {
		final float max = 1f;
		return this.random.randomVector(max).sub(this.random.randomVector(max));
	}

	private Vector2f randomPosition() {
		return this.random.randomVector(this.settings.getScreenWidth(), this.settings.getScreenHeight());
	}

}

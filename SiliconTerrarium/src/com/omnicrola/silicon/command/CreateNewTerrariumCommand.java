package com.omnicrola.silicon.command;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.util.RandomWrapper;

public class CreateNewTerrariumCommand implements ICommand {

	private final EntityFactory entityFactory;
	private final RandomWrapper random;
	private final TerrariumSettings settings;

	public CreateNewTerrariumCommand(EntityFactory entityFactory, TerrariumSettings settings) {
		this.entityFactory = entityFactory;
		this.settings = settings;
		this.random = new RandomWrapper();
	}

	@Override
	public void execute(IGameContext executionContext) {
		final EntityManager entityManager = executionContext.getContextFor(EntityManager.class);
		entityManager.clearAll();
		buildCreatures(entityManager);
		addFood(entityManager);
	}

	private void addFood(EntityManager entityManager) {
		for (int i = 0; i < this.settings.getInitialFoodCount(); i++) {
			final Vector2f position = randomPosition();
			final Vector2f velocity = randomVelocity();
			final ISiliconEntity newEntity = this.entityFactory.buildFood(position, velocity);
			entityManager.addEntity(newEntity);
		}
	}

	private void buildCreatures(EntityManager entityManager) {
		for (int i = 0; i < this.settings.getInitialCreatureCount(); i++) {
			final Vector2f position = randomPosition();
			final Vector2f velocity = randomVelocity();
			final ISiliconEntity newEntity = this.entityFactory.buildCritter(position, velocity);
			entityManager.addEntity(newEntity);
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

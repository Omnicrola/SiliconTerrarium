package com.omnicrola.silicon.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.geom.Vector2f;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.neural.INeuralNetwork;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.util.RandomWrapper;

public class ResurrectionManager {
	private static final float MUTATION_PROBABILITY = 0.2f;
	private static final float MUTATION_DELTA = 1.0f;
	private static final int RESPAWN_THRESHOLD = 10;

	private final HashMap<EntityType, List<ISiliconEntity>> deadEntities;
	private final MutationDirective mutationDirective;
	private final EntityFactory entityFactory;
	private final TerrariumSettings settings;

	private final RandomWrapper random;

	public ResurrectionManager(EntityFactory entityFactory, TerrariumSettings settings) {
		this.entityFactory = entityFactory;
		this.settings = settings;
		this.random = new RandomWrapper();
		this.deadEntities = createDeadEntityMap();
		this.mutationDirective = new MutationDirective(MUTATION_PROBABILITY, MUTATION_DELTA);
	}

	private HashMap<EntityType, List<ISiliconEntity>> createDeadEntityMap() {
		final HashMap<EntityType, List<ISiliconEntity>> entityMap = new HashMap<>();
		for (final EntityType entityType : EntityType.values()) {
			entityMap.put(entityType, new ArrayList<>());
		}
		return entityMap;
	}

	public void queueForReincarnation(ISiliconEntity siliconEntity) {
		this.deadEntities.get(siliconEntity.getType()).add(siliconEntity);
	}

	public void resurrect(EntityManager entityManager) {
		resurrectCreatures(entityManager);
		spawnMoreFood(entityManager);
	}

	private void spawnMoreFood(EntityManager entityManager) {
		final List<ISiliconEntity> food = this.deadEntities.get(EntityType.FOOD);
		final int foodCount = food.size();
		for (int i = 0; i < foodCount; i++) {
			final ISiliconEntity newFood = this.entityFactory.buildFood(randomPosition(), randomVelocity());
			entityManager.addEntity(newFood);
		}
		food.clear();

	}

	private void resurrectCreatures(EntityManager entityManager) {
		final List<ISiliconEntity> creatures = this.deadEntities.get(EntityType.CREATURE);
		final int creatureCount = creatures.size();
		if (creatureCount >= RESPAWN_THRESHOLD) {
			final ISiliconEntity mostFitEntity = findMostFit(creatures);
			for (int i = 0; i < creatureCount; i++) {
				final INeuralNetwork neuralNetwork = mostFitEntity.mutateNeuralNetwork(this.mutationDirective);
				final ISiliconEntity newCreature = this.entityFactory.buildCritter(randomPosition(), new Vector2f(),
						neuralNetwork);
				entityManager.addEntity(newCreature);
			}
			creatures.clear();
		}
	}

	private ISiliconEntity findMostFit(List<ISiliconEntity> creatures) {
		ISiliconEntity mostFitEntity = null;
		float highestFitness = Float.MIN_VALUE;
		for (final ISiliconEntity singleEntity : creatures) {
			final float fitness = singleEntity.getFitness();
			if (fitness > highestFitness) {
				highestFitness = fitness;
				mostFitEntity = singleEntity;
			}
		}
		return mostFitEntity;
	}

	private Vector2f randomPosition() {
		return this.random.randomVector(this.settings.getScreenWidth(), this.settings.getScreenHeight());

	}

	private Vector2f randomVelocity() {
		final float max = 1f;
		return this.random.randomVector(max).sub(this.random.randomVector(max));
	}

}

package com.omnicrola.silicon.entity;

import java.util.ArrayList;

import com.omnicrola.silicon.neural.MutationDirective;

public class ResurrectionManager {
	private static final float MUTATION_PROBABILITY = 0.2f;

	private final ArrayList<ISiliconEntity> deadEntities;
	private final MutationDirective mutationDirective;

	public ResurrectionManager() {
		this.deadEntities = new ArrayList<>();
		this.mutationDirective = new MutationDirective(MUTATION_PROBABILITY);
	}

	public void queueForReincarnation(ISiliconEntity siliconEntity) {
		this.deadEntities.add(siliconEntity);
	}

	public void resurrect(EntityManager entityManager) {
		for (final ISiliconEntity siliconEntity : this.deadEntities) {

		}
	}

}

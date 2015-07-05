package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.core.IRenderable;
import com.omnicrola.silicon.entity.SiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;

public class FoodProximityInput implements INeuralInput {

	private final IRenderable entityManager;
	private final SiliconEntity siliconEntity;

	public FoodProximityInput(SiliconEntity siliconEntity, IRenderable entityManager) {
		this.siliconEntity = siliconEntity;
		this.entityManager = entityManager;
	}

	@Override
	public float evaluate() {
		return 0;
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

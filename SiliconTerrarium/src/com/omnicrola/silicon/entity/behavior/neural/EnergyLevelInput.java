package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;

public class EnergyLevelInput implements INeuralInput {

	private final ISiliconEntity siliconEntity;

	public EnergyLevelInput(ISiliconEntity siliconEntity) {
		this.siliconEntity = siliconEntity;
	}

	@Override
	public float evaluate() {
		return this.siliconEntity.getEnergy();
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

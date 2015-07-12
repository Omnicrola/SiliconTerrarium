package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class EnergyLevelInput implements INeuralInput {

	private final ISiliconEntity siliconEntity;

	public EnergyLevelInput(NeuralContext context) {
		this.siliconEntity = context.getEntity();
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

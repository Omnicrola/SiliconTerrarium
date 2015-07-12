package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.MutationDirective;
import com.omnicrola.silicon.neural.NeuralContext;

public class EnergyLevelInput implements INeuralInput {

	@Override
	public float evaluate(NeuralContext context) {
		return context.getEntity().getEnergy();
	}

	@Override
	public INeuralInput mutate(MutationDirective mutationDirective) {
		return null;
	}

}

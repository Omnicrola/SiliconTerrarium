package com.omnicrola.silicon.neural;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class NullNeuralNetwork implements INeuralNetwork {
	public static final NullNeuralNetwork NULL = new NullNeuralNetwork();

	private NullNeuralNetwork() {
	}

	@Override
	public INeuralNetwork mutate(MutationDirective mutationDirective) {
		return this;
	}

	@Override
	public void evaluate(ISiliconEntity siliconEntity) {
	}

}

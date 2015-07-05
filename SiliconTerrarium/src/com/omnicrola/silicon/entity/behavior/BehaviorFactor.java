package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.SiliconEntity;
import com.omnicrola.silicon.neural.NeuralNetwork;

public class BehaviorFactor {
	private final NeuralNetworkFactory neuralNetworkFactory;

	public BehaviorFactor(NeuralNetworkFactory neuralNetworkFactory) {
		this.neuralNetworkFactory = neuralNetworkFactory;
	}

	public IBehavior buildNeuralNetwork(SiliconEntity siliconEntity) {
		final NeuralNetwork neuralNetwork = this.neuralNetworkFactory.build(siliconEntity);
		final NeuralNetworkBehavior neuralNetworkBehavior = new NeuralNetworkBehavior(neuralNetwork);
		return neuralNetworkBehavior;
	}

}

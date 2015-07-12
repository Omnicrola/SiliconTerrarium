package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.NeuralNetwork;

public class BehaviorFactor {
	private final NeuralNetworkFactory neuralNetworkFactory;

	public BehaviorFactor(NeuralNetworkFactory neuralNetworkFactory) {
		this.neuralNetworkFactory = neuralNetworkFactory;
	}

	public IUpdateBehavior buildNeuralNetwork(ISiliconEntity siliconEntity) {
		final NeuralNetwork neuralNetwork = this.neuralNetworkFactory.build(siliconEntity);
		final NeuralNetworkBehavior neuralNetworkBehavior = new NeuralNetworkBehavior(neuralNetwork);
		return neuralNetworkBehavior;
	}

	public ICollisionBehavior buildEatBehavior() {
		return EatOtherEntityBehavior.INSTANCE;
	}

	public IUpdateBehavior buildFitnessOverTime() {
		return FitnessOverTimeBehavior.INSTANCE;
	}

	public IUpdateBehavior buildUseEnergy() {
		return EnergyConsumptionBehavior.INSTANCE;
	}

}

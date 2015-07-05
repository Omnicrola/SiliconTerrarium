package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.neural.NeuralNetwork;

public class NeuralNetworkBehavior implements IUpdateBehavior {
	private final NeuralNetwork neuralNetwork;

	public NeuralNetworkBehavior(NeuralNetwork neuralNetwork) {
		this.neuralNetwork = neuralNetwork;
	}

	@Override
	public void execute(ISiliconEntity siliconEntity, float delta) {
		this.neuralNetwork.evaluate();
	}

}

package com.omnicrola.silicon.neural;

import java.util.List;

public class NeuralNetworkFactory {
	public NeuralNetwork build(List<INeuralInput> inputs, int layerCount) {
		final NeuralNetwork neuralNetwork = new NeuralNetwork(inputs);
		return neuralNetwork;
	}
}

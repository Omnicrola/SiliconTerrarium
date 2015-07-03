package com.omnicrola.silicon.neural;

import java.util.LinkedHashMap;

public class Neuron implements INeuralInput {
	private final LinkedHashMap<INeuralInput, Float> inputs;

	public Neuron(LinkedHashMap<INeuralInput, Float> inputs) {
		this.inputs = inputs;
	}

	@Override
	public float getOutputSignal() {
		return 1.0f;
	}

}

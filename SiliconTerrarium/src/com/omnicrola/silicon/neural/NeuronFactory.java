package com.omnicrola.silicon.neural;

import java.util.List;

public class NeuronFactory {

	public Neuron build(List<INeuralInput> inputs) {
		return new Neuron(0);
	}

}

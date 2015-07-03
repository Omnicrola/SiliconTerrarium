package com.omnicrola.silicon.neural;

import java.util.LinkedList;
import java.util.List;

public class NeuralNetwork {

	private final List<INeuralInput> inputs;
	private final LinkedList<INeuralOutput> outputs;
	private final LinkedList<Neuron> neurons;

	public NeuralNetwork(LinkedList<INeuralInput> inputs, LinkedList<INeuralOutput> outputs, NeuronFactory neuronFactory) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.neurons = new LinkedList<>();
		growNeurons(neuronFactory);
	}

	private void growNeurons(NeuronFactory neuronFactory) {
		final int neuronCount = this.inputs.size();
		for (int i = 0; i < neuronCount; i++) {
			this.neurons.add(neuronFactory.build(this.inputs));
		}
	}

}

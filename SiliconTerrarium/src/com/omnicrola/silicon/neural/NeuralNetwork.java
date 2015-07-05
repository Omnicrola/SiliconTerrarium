package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
	private final List<INeuralInput> neurons;

	public NeuralNetwork(List<INeuralInput> neurons) {
		this.neurons = neurons;
	}

	public void evaluate() {
		for (final INeuralInput neuron : this.neurons) {
			neuron.evaluate();
		}
	}

	public NeuralNetwork mutate(MutationDirective mutationDirective) {
		final List<INeuralInput> mutatedNeurons = new ArrayList<>();
		for (final INeuralInput neuron : this.neurons) {
			mutatedNeurons.add(neuron.mutate(mutationDirective));
		}
		return new NeuralNetwork(mutatedNeurons);
	}
}

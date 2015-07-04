package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
	private final List<Neuron> neurons;

	public NeuralNetwork(List<Neuron> neurons) {
		this.neurons = neurons;
	}

	public void evaluate() {
		for (final Neuron neuron : this.neurons) {
			neuron.evaluate();
		}
	}

	public NeuralNetwork mutate(MutationDirective mutationDirective) {
		final List<Neuron> mutatedNeurons = new ArrayList<>();
		for (final Neuron neuron : this.neurons) {
			mutatedNeurons.add(neuron.mutate(mutationDirective));
		}
		return new NeuralNetwork(mutatedNeurons);
	}
}

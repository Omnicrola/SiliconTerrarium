package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.List;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class NeuralNetwork implements INeuralNetwork {
	private final List<INeuralInput> neurons;
	private final IEnvironmentQueryHandler environmentHandler;

	public NeuralNetwork(IEnvironmentQueryHandler environmentHandler, List<INeuralInput> neurons) {
		this.environmentHandler = environmentHandler;
		this.neurons = neurons;
	}

	@Override
	public void evaluate(ISiliconEntity siliconEntity) {
		for (final INeuralInput neuron : this.neurons) {
			neuron.evaluate(new NeuralContext(siliconEntity, this.environmentHandler));
		}
	}

	@Override
	public INeuralNetwork mutate(MutationDirective mutationDirective) {
		final List<INeuralInput> mutatedNeurons = new ArrayList<>();
		for (final INeuralInput neuralInput : this.neurons) {
			mutatedNeurons.add(neuralInput.mutate(mutationDirective));
		}
		return new NeuralNetwork(this.environmentHandler, mutatedNeurons);
	}
}

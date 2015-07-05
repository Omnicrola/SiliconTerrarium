package com.omnicrola.silicon.entity.behavior;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.omnicrola.silicon.core.IRenderable;
import com.omnicrola.silicon.entity.SiliconEntity;
import com.omnicrola.silicon.entity.behavior.neural.FoodProximityInput;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.NeuralInputWeight;
import com.omnicrola.silicon.neural.NeuralNetwork;
import com.omnicrola.silicon.neural.Neuron;

public class NeuralNetworkFactory {
	private static final Random R = new Random();
	private final IRenderable entityManager;

	public NeuralNetworkFactory(IRenderable entityManager) {
		this.entityManager = entityManager;
	}

	public NeuralNetwork build(SiliconEntity siliconEntity) {
		final List<INeuralInput> inputs = createInputs(siliconEntity);
		final List<INeuralInput> neuronLayer1 = createNeuronLayer(inputs);
		final List<INeuralInput> neuronLayer2 = createNeuronLayer(neuronLayer1);
		return new NeuralNetwork(neuronLayer2);
	}

	private List<INeuralInput> createInputs(SiliconEntity siliconEntity) {
		final FoodProximityInput foodProximityInput = new FoodProximityInput(siliconEntity, this.entityManager);
		final ArrayList<INeuralInput> inputs = new ArrayList<>();
		inputs.add(foodProximityInput);
		return inputs;
	}

	private List<INeuralInput> createNeuronLayer(List<INeuralInput> inputs) {
		final List<INeuralInput> neurons = new ArrayList<>();
		for (int i = 0; i < inputs.size(); i++) {
			neurons.add(createNeuron(inputs));
		}
		return neurons;
	}

	private Neuron createNeuron(List<INeuralInput> inputs) {
		final Map<INeuralInput, NeuralInputWeight> weightedInputs = new LinkedHashMap<>();
		for (final INeuralInput neuralInput : inputs) {
			weightedInputs.put(neuralInput, randomWeight());
		}
		final Neuron neuron = new Neuron(weightedInputs);
		return neuron;
	}

	private NeuralInputWeight randomWeight() {
		return new NeuralInputWeight(R.nextFloat());
	}

}

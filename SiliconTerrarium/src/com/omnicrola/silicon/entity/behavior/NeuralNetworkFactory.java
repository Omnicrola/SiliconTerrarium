package com.omnicrola.silicon.entity.behavior;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.entity.ISiliconEntity;
import com.omnicrola.silicon.entity.behavior.neural.EnergyLevelInput;
import com.omnicrola.silicon.entity.behavior.neural.FoodProximityInput;
import com.omnicrola.silicon.entity.behavior.neural.NearestCreatureDistanceInput;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.NeuralInputWeight;
import com.omnicrola.silicon.neural.NeuralNetwork;
import com.omnicrola.silicon.neural.Neuron;

public class NeuralNetworkFactory {
	private static final Random R = new Random();
	private final EntityManager entityManager;

	public NeuralNetworkFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public NeuralNetwork build(ISiliconEntity siliconEntity) {
		final List<INeuralInput> inputs = createInputs(siliconEntity);
		final List<INeuralInput> neuronLayer1 = createNeuronLayer(inputs);
		final List<INeuralInput> neuronLayer2 = createNeuronLayer(neuronLayer1);
		return new NeuralNetwork(neuronLayer2);
	}

	private List<INeuralInput> createInputs(ISiliconEntity siliconEntity) {
		final FoodProximityInput foodProximityInput = new FoodProximityInput(siliconEntity, this.entityManager);
		final EnergyLevelInput energyLevelInput = new EnergyLevelInput(siliconEntity);
		final NearestCreatureDistanceInput nearestCreatureDistanceInput = new NearestCreatureDistanceInput(
				siliconEntity, this.entityManager);

		final ArrayList<INeuralInput> inputs = new ArrayList<>();
		inputs.add(foodProximityInput);
		inputs.add(energyLevelInput);
		inputs.add(nearestCreatureDistanceInput);
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

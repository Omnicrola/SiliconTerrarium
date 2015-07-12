package com.omnicrola.silicon.entity.behavior;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.omnicrola.silicon.entity.physics.CollisionManager;
import com.omnicrola.silicon.neural.INeuralAction;
import com.omnicrola.silicon.neural.INeuralInput;
import com.omnicrola.silicon.neural.INeuralNetwork;
import com.omnicrola.silicon.neural.NeuralInput;
import com.omnicrola.silicon.neural.NeuralInputFactory;
import com.omnicrola.silicon.neural.NeuralInputWeight;
import com.omnicrola.silicon.neural.NeuralNetwork;
import com.omnicrola.silicon.neural.NeuralOutput;
import com.omnicrola.silicon.neural.NeuralOutputFactory;
import com.omnicrola.silicon.neural.Neuron;

public class NeuralNetworkFactory {
	private static final Random R = new Random();
	private static final int LAYERS = 1;

	private final NeuralInputFactory neuralInputFactory;
	private final NeuralOutputFactory neuralOutputFactory;
	private final CollisionManager collisionManager;

	public NeuralNetworkFactory(CollisionManager collisionManager, NeuralInputFactory neuralInputFactory,
			NeuralOutputFactory neuralOutputFactory) {
		this.collisionManager = collisionManager;
		this.neuralInputFactory = neuralInputFactory;
		this.neuralOutputFactory = neuralOutputFactory;
	}

	public INeuralNetwork build() {
		final ArrayList<INeuralAction> emptyActionList = new ArrayList<>();
		List<INeuralInput> inputs = createInputs();
		List<INeuralInput> newLayer = new ArrayList<>();
		for (int l = 0; l < LAYERS; l++) {
			newLayer = createNeuronLayer(inputs, emptyActionList);
			inputs = newLayer;
		}
		final List<INeuralInput> outputLayer = createNeuronLayer(newLayer, buildNeuralActions());
		return new NeuralNetwork(this.collisionManager, outputLayer);
	}

	private ArrayList<INeuralAction> buildNeuralActions() {
		final ArrayList<INeuralAction> neuralActions = new ArrayList<>();
		for (final NeuralOutput neuralOutput : NeuralOutput.values()) {
			neuralActions.add(this.neuralOutputFactory.create(neuralOutput));
		}
		return neuralActions;
	}

	private List<INeuralInput> createInputs() {
		final ArrayList<INeuralInput> inputs = new ArrayList<>();
		for (final NeuralInput neuralInput : NeuralInput.values()) {
			inputs.add(this.neuralInputFactory.create(neuralInput));
		}
		return inputs;
	}

	private List<INeuralInput> createNeuronLayer(List<INeuralInput> inputs, ArrayList<INeuralAction> actions) {
		final List<INeuralInput> neurons = new ArrayList<>();
		for (int i = 0; i < inputs.size(); i++) {
			final Neuron neuron = createNeuron(inputs);
			addNeuralAction(actions, i, neuron);
			neurons.add(neuron);
		}
		return neurons;
	}

	private void addNeuralAction(ArrayList<INeuralAction> actions, int i, final Neuron neuron) {
		if (actions.size() > i) {
			neuron.addNeuralAction(actions.get(i));
		}
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
		return new NeuralInputWeight(R.nextFloat() / 10f);
	}

}

package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NeuralNetworkTest {
	private static final Random R = new Random();

	private static class NeuralAction implements INeuralAction {
		private final String id;
		private final float threshold;

		public NeuralAction(String id, float threshold) {
			this.id = id;
			this.threshold = threshold;
		}

		@Override
		public void activate(float activationValue) {
			System.out.println("ID:" + this.id + " action!");
		}

		@Override
		public float getThreshold() {
			return this.threshold;
		}

	}

	private static class StaticNeuralInput implements INeuralInput {
		private final float value;

		public StaticNeuralInput(float value) {
			this.value = value;
		}

		@Override
		public float evaluate() {
			return this.value;
		}

		@Override
		public INeuralInput mutate(MutationDirective mutationDirective) {
			return new StaticNeuralInput(this.value);
		}

	}

	public static void main(String[] args) {
		final ArrayList<INeuralInput> inputs = new ArrayList<>();
		inputs.add(randInput());
		inputs.add(randInput());
		inputs.add(randInput());

		final ArrayList<INeuralInput> neuronLayer1 = new ArrayList<>();
		neuronLayer1.add(randNeuron(inputs));
		neuronLayer1.add(randNeuron(inputs));
		neuronLayer1.add(randNeuron(inputs));

		final ArrayList<Neuron> neuronLayer2 = new ArrayList<>();
		neuronLayer2.add(randNeuron(neuronLayer1, new NeuralAction("one", R.nextFloat())));
		neuronLayer2.add(randNeuron(neuronLayer1, new NeuralAction("two", R.nextFloat())));
		neuronLayer2.add(randNeuron(neuronLayer1, new NeuralAction("three", R.nextFloat())));
		neuronLayer2.add(randNeuron(neuronLayer1, new NeuralAction("four", R.nextFloat())));

		final NeuralNetwork neuralNetwork = new NeuralNetwork(neuronLayer2);
		neuralNetwork.evaluate();
		System.out.println("-----");
		neuralNetwork.evaluate();
		System.out.println("-----");
		neuralNetwork.evaluate();
		System.out.println("-----");
		final NeuralNetwork newNetwork = neuralNetwork.mutate(new MutationDirective(0.5f));
		newNetwork.evaluate();
		System.out.println("-----");

	}

	private static Neuron randNeuron(final ArrayList<INeuralInput> inputs, INeuralAction... actions) {
		final Map<INeuralInput, NeuralInputWeight> weightedInputs = new HashMap<>();
		for (final INeuralInput iNeuralInput : inputs) {
			weightedInputs.put(iNeuralInput, new NeuralInputWeight(R.nextFloat() - 0.1f));
		}
		final Neuron neuron = new Neuron(weightedInputs);
		for (final INeuralAction neuralAction : actions) {
			neuron.addNeuralAction(neuralAction);
		}
		return neuron;
	}

	private static INeuralInput randInput() {
		return new StaticNeuralInput(R.nextFloat() * 1f);
	}
}

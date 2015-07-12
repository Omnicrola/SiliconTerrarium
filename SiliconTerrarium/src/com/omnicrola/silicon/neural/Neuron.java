package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Neuron implements INeuralInput {
	private static final float P = 1.0f;

	private final Map<INeuralInput, NeuralInputWeight> inputs;
	private final ArrayList<INeuralAction> actions;

	public Neuron(Map<INeuralInput, NeuralInputWeight> inputs) {
		this.inputs = inputs;
		this.actions = new ArrayList<>();
	}

	public void addNeuralAction(INeuralAction neuralAction) {
		this.actions.add(neuralAction);
	}

	private void setActions(ArrayList<INeuralAction> actions) {
		this.actions.addAll(actions);
	}

	@Override
	public float evaluate(NeuralContext context) {
		final float netInput = getNetActivation(context);
		triggerActivation(context, netInput);
		final float sigmoid = sigmoid(netInput);
		return sigmoid;
	}

	private float getNetActivation(NeuralContext context) {
		float netInput = 0f;
		for (final Entry<INeuralInput, NeuralInputWeight> neuralInput : this.inputs.entrySet()) {
			netInput += neuralInput.getKey().evaluate(context) * neuralInput.getValue().weight();
		}
		return netInput;
	}

	private void triggerActivation(NeuralContext context, float netInput) {
		for (final INeuralAction neuralAction : this.actions) {
			final float threshold = neuralAction.getThreshold();
			if (netInput >= threshold) {
				neuralAction.activate(context, netInput);
			}
		}
	}

	public static float sigmoid(float activationValue) {
		return (float) (1f / (1f + Math.exp(-activationValue / P)));
	}

	@Override
	public Neuron mutate(MutationDirective mutationDirective) {
		final Map<INeuralInput, NeuralInputWeight> mutatedInputs = new HashMap<>();
		for (final Entry<INeuralInput, NeuralInputWeight> inputs : this.inputs.entrySet()) {
			mutatedInputs.put(inputs.getKey().mutate(mutationDirective), mutationDirective.mutate(inputs.getValue()));
		}

		final Neuron neuron = new Neuron(mutatedInputs);
		neuron.setActions(this.actions);
		return neuron;
	}

}

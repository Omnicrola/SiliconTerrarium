package com.omnicrola.silicon.neural;

public class Neuron {
	final int inputCount;
	final float[] weights;

	public Neuron(int inputCount) {
		this.inputCount = inputCount + 1; // add 1 extra for bias
		this.weights = new float[inputCount];
		randomizeWeights();
	}

	private void randomizeWeights() {
		for (int i = 0; i < this.weights.length; i++) {
			this.weights[i] = (float) Math.random();
		}
	}
}

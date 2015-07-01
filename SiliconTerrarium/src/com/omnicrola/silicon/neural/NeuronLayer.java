package com.omnicrola.silicon.neural;

import java.util.Vector;

public class NeuronLayer {
	final int neuronCount;
	final int inputsPerNeuron;
	Vector<Neuron> neurons = new Vector<>();

	public NeuronLayer(int neuronCount, int inputsPerNeuron) {
		this.neuronCount = neuronCount;
		this.inputsPerNeuron = inputsPerNeuron;
	}

}

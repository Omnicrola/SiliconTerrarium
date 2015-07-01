package com.omnicrola.silicon.neural;

import java.util.Vector;

public class Genome {
	double fitness;
	Vector<Float> weights;

	public Genome(Vector<Float> weights, double fitness) {
		this.weights = weights;
		this.fitness = fitness;
	}
}

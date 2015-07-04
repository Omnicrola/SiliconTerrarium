package com.omnicrola.silicon.neural;

import java.util.Random;

public class MutationDirective {
	private final float mutationProbability;
	private static final Random R = new Random();

	public MutationDirective(float mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public NeuralInputWeight mutate(NeuralInputWeight value) {
		return new NeuralInputWeight(mutateValue(value.weight()));
	}

	private float mutateValue(float value) {
		final float dice = R.nextFloat();
		if (dice <= this.mutationProbability) {
			return R.nextFloat();
		}
		return value;
	}

}

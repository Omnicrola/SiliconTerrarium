package com.omnicrola.silicon.neural;

import java.util.Random;

public class MutationDirective {
	private final float mutationProbability;
	private static final Random R = new Random();
	private final float mutationDelta;

	public MutationDirective(float mutationProbability, float mutationDelta) {
		this.mutationProbability = mutationProbability;
		this.mutationDelta = mutationDelta;
	}

	public NeuralInputWeight mutateWeight(NeuralInputWeight value) {
		return new NeuralInputWeight(mutateValue(value.weight()));
	}

	private float mutateValue(float value) {
		final float dice = R.nextFloat();
		if (dice <= this.mutationProbability) {
			return R.nextFloat() * this.mutationDelta;
		}
		return value;
	}

}

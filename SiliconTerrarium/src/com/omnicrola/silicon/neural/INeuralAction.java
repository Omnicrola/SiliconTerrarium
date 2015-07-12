package com.omnicrola.silicon.neural;

public interface INeuralAction {

	void activate(NeuralContext context, float activationValue);

	float getThreshold();

}

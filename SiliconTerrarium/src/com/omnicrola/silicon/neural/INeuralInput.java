package com.omnicrola.silicon.neural;

public interface INeuralInput {

	float evaluate(NeuralContext context);

	INeuralInput mutate(MutationDirective mutationDirective);

}

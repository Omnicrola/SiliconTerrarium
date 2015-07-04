package com.omnicrola.silicon.neural;

public interface INeuralInput {

	float evaluate();

	INeuralInput mutate(MutationDirective mutationDirective);

}

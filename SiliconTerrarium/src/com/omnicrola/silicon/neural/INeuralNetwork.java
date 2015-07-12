package com.omnicrola.silicon.neural;

import com.omnicrola.silicon.entity.ISiliconEntity;

public interface INeuralNetwork {

	public abstract INeuralNetwork mutate(MutationDirective mutationDirective);

	public abstract void evaluate(ISiliconEntity siliconEntity);

}

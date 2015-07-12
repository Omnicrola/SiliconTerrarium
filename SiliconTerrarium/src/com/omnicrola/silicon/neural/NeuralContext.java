package com.omnicrola.silicon.neural;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class NeuralContext {

	private final ISiliconEntity siliconEntity;
	private final IEnvironmentQueryHandler environmentHandler;

	public NeuralContext(ISiliconEntity siliconEntity, IEnvironmentQueryHandler environmentHandler) {
		this.siliconEntity = siliconEntity;
		this.environmentHandler = environmentHandler;
	}

	public ISiliconEntity getEntity() {
		return this.siliconEntity;
	}

	public IEnvironmentQueryHandler getEnvironmentHandler() {
		return this.environmentHandler;
	}
}

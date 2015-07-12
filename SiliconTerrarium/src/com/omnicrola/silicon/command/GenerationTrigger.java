package com.omnicrola.silicon.command;

import com.omnicrola.silicon.entity.EntityManager;

public class GenerationTrigger implements ICommand {
	private static final int GENERATION_TIME_MS = 10_000;
	private long lastGeneration;

	public GenerationTrigger() {
		this.lastGeneration = System.currentTimeMillis();
	}

	@Override
	public void execute(IGameContext executionContext) {
		final long elapsed = System.currentTimeMillis() - this.lastGeneration;
		if (elapsed > GENERATION_TIME_MS) {
			final EntityManager entityManager = executionContext.getContextFor(EntityManager.class);
			entityManager.killAll();
			this.lastGeneration = System.currentTimeMillis();
		}
		CommandQueue.instance().enqueueCommand(this);
	}

}

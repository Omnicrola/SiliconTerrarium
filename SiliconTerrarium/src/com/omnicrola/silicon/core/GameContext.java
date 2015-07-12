package com.omnicrola.silicon.core;

import java.util.HashMap;

import com.omnicrola.silicon.command.IGameContext;

public class GameContext implements IGameContext {
	private final HashMap<Object, Object> contexts;

	public GameContext() {
		this.contexts = new HashMap<>();
	}

	@Override
	public void setContext(Class<?> keyClass, Object subcontext) {
		this.contexts.put(keyClass, subcontext);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getContextFor(Class<T> keyClass) {
		return (T) this.contexts.get(keyClass);
	}
}

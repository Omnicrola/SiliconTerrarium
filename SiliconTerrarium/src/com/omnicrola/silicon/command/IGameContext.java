package com.omnicrola.silicon.command;


public interface IGameContext {

	public abstract <T> T getContextFor(Class<T> keyClass);

	public abstract void setContext(Class<?> keyClass, Object subcontext);

}

package com.omnicrola.silicon.command;

import java.util.ArrayList;
import java.util.List;

public class CommandQueue implements ICommandQueue {

	private static CommandQueue instance;
	private static final Object mutex = new Object();

	public static CommandQueue instance() {
		if (instance == null) {
			synchronized (mutex) {
				if (instance == null) {
					instance = new CommandQueue();
				}
			}
		}
		return instance;
	}

	private final ArrayList<ICommand> commands;

	private CommandQueue() {
		this.commands = new ArrayList<>();
	}

	public synchronized List<ICommand> fetchQueue() {
		final ArrayList<ICommand> listCopy = new ArrayList<>(this.commands);
		this.commands.clear();
		return listCopy;
	}

	public synchronized void enqueueCommand(ICommand command) {
		this.commands.add(command);
	}
}

package com.omnicrola.silicon.input;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;

public class ReloadWorldListener implements IKeyListener {

	private final EntityFactory entityFactory;

	public ReloadWorldListener(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	@Override
	public void keyDown() {
	}

	@Override
	public void keyUp() {
		final CreateNewTerrariumCommand command = new CreateNewTerrariumCommand(this.entityFactory);
		CommandQueue.instance().enqueueCommand(command);
	}

}

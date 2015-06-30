package com.omnicrola.silicon.input;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.util.RandomWrapper;

public class ReloadWorldListener implements IKeyListener {

	@Override
	public void keyDown() {
	}

	@Override
	public void keyUp() {
		final CreateNewTerrariumCommand command = new CreateNewTerrariumCommand(new EntityFactory(new RandomWrapper()));
		CommandQueue.instance().enqueueCommand(command);
	}

}

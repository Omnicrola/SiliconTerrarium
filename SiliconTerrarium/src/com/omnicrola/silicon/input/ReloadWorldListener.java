package com.omnicrola.silicon.input;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;

public class ReloadWorldListener implements IKeyListener {

	private final EntityFactory entityFactory;
	private final TerrariumSettings settings;

	public ReloadWorldListener(EntityFactory entityFactory, TerrariumSettings settings) {
		this.entityFactory = entityFactory;
		this.settings = settings;
	}

	@Override
	public void keyDown() {
	}

	@Override
	public void keyUp() {
		final CreateNewTerrariumCommand command = new CreateNewTerrariumCommand(this.entityFactory, this.settings);
		CommandQueue.instance().enqueueCommand(command);
	}

}

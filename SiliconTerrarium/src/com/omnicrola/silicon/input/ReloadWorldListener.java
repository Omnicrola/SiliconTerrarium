package com.omnicrola.silicon.input;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.command.IGameContext;
import com.omnicrola.silicon.entity.EntityFactory;

public class ReloadWorldListener implements IKeyListener {

	private final TerrariumSettings settings;
	private final IGameContext context;

	public ReloadWorldListener(IGameContext context, TerrariumSettings settings) {
		this.context = context;
		this.settings = settings;
	}

	@Override
	public void keyDown() {
	}

	@Override
	public void keyUp() {
		final EntityFactory entityFactory = this.context.getContextFor(EntityFactory.class);
		final CreateNewTerrariumCommand command = new CreateNewTerrariumCommand(entityFactory, this.settings);
		CommandQueue.instance().enqueueCommand(command);
	}

}

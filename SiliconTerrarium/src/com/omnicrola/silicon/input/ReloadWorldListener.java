package com.omnicrola.silicon.input;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.util.RandomWrapper;

public class ReloadWorldListener implements IKeyListener {

	private final TerrariumSettings settings;

	public ReloadWorldListener(TerrariumSettings settings) {
		this.settings = settings;
	}

	@Override
	public void keyDown() {
	}

	@Override
	public void keyUp() {
		final CreateNewTerrariumCommand command = new CreateNewTerrariumCommand(
				new EntityFactory(this.settings, new RandomWrapper()));
		CommandQueue.instance().enqueueCommand(command);
	}

}

package com.omnicrola.silicon.launch;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.input.KeyInputHandler;
import com.omnicrola.silicon.input.ReloadWorldListener;

public class TerrariumInitializer {
	private final EntityFactory entityFactory;

	public TerrariumInitializer(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public void init(GameContainer container) {
		loadInitialEntities(this.entityFactory);
		loadInputListeners(container.getInput());
	}

	private void loadInputListeners(Input input) {
		final KeyInputHandler keyHandler = new KeyInputHandler();
		input.addKeyListener(keyHandler);

		final ReloadWorldListener reloadWorldListener = new ReloadWorldListener(this.entityFactory);
		keyHandler.addListener(Input.KEY_R, reloadWorldListener);
	}

	private void loadInitialEntities(EntityFactory entityFactory) {
		final CreateNewTerrariumCommand newTerrariumCommand = new CreateNewTerrariumCommand(entityFactory);
		CommandQueue.instance().enqueueCommand(newTerrariumCommand);
	}
}

package com.omnicrola.silicon.launch;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.input.KeyInputHandler;
import com.omnicrola.silicon.input.ReloadWorldListener;
import com.omnicrola.silicon.util.RandomWrapper;

public class TerrariumInitializer {

	public void init(GameContainer container) {
		loadInitialEntities();
		loadInputListeners(container.getInput());
	}

	private void loadInputListeners(Input input) {
		final KeyInputHandler keyHandler = new KeyInputHandler();
		input.addKeyListener(keyHandler);

		final ReloadWorldListener reloadWorldListener = new ReloadWorldListener();
		keyHandler.addListener(Input.KEY_R, reloadWorldListener);
	}

	private void loadInitialEntities() {
		final CreateNewTerrariumCommand newTerrariumCommand = new CreateNewTerrariumCommand(
				new EntityFactory(new RandomWrapper()));
		CommandQueue.instance().enqueueCommand(newTerrariumCommand);
	}

}

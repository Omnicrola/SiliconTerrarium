package com.omnicrola.silicon.launch;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.input.CreatureStatHoverListener;
import com.omnicrola.silicon.input.KeyInputHandler;
import com.omnicrola.silicon.input.MouseInputHandler;
import com.omnicrola.silicon.input.ReloadWorldListener;

public class TerrariumInitializer {
	private final EntityFactory entityFactory;
	private final TerrariumSettings settings;

	public TerrariumInitializer(EntityFactory entityFactory, TerrariumSettings settings) {
		this.entityFactory = entityFactory;
		this.settings = settings;
	}

	public void init(GameContainer container, EntityManager entityManager) {
		loadInitialEntities(this.entityFactory);
		loadInputListeners(container.getInput(), entityManager);
	}

	private void loadInputListeners(Input input, EntityManager entityManager) {
		setupKeyboard(input);
		setupMouse(input, entityManager);
	}

	private void setupMouse(Input input, EntityManager entityManager) {
		final MouseInputHandler mouseInputHandler = new MouseInputHandler();
		input.addMouseListener(mouseInputHandler);

		final CreatureStatHoverListener creatureStatHoverListener = new CreatureStatHoverListener(entityManager);
		mouseInputHandler.addMouseMoveListener(creatureStatHoverListener);
	}

	private void setupKeyboard(Input input) {
		final KeyInputHandler keyHandler = new KeyInputHandler();
		input.addKeyListener(keyHandler);

		final ReloadWorldListener reloadWorldListener = new ReloadWorldListener(this.entityFactory, this.settings);
		keyHandler.addListener(Input.KEY_R, reloadWorldListener);
	}

	private void loadInitialEntities(EntityFactory entityFactory) {
		final CreateNewTerrariumCommand newTerrariumCommand = new CreateNewTerrariumCommand(entityFactory,
				this.settings);
		CommandQueue.instance().enqueueCommand(newTerrariumCommand);
	}
}

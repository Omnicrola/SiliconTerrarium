package com.omnicrola.silicon.input;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.command.IGameContext;
import com.omnicrola.silicon.core.IGameSubsystem;
import com.omnicrola.silicon.slick.IRenderWrapper;

public class InputHandler implements IGameSubsystem {
	private final TerrariumSettings settings;

	public InputHandler(TerrariumSettings settings) {
		this.settings = settings;
	}

	@Override
	public void render(IRenderWrapper renderWrapper) {
	}

	@Override
	public void update(float update) {
	}

	@Override
	public void init(IGameContext context, GameContainer container) {
		loadInputListeners(container.getInput(), context);
	}

	private void loadInputListeners(Input input, IGameContext context) {
		setupKeyboard(input, context);
		setupMouse(input);
	}

	private void setupMouse(Input input) {
		final MouseInputHandler mouseInputHandler = new MouseInputHandler();
		input.addMouseListener(mouseInputHandler);

	}

	private void setupKeyboard(Input input, IGameContext context) {
		final KeyInputHandler keyHandler = new KeyInputHandler();
		input.addKeyListener(keyHandler);

		final ReloadWorldListener reloadWorldListener = new ReloadWorldListener(context, this.settings);
		keyHandler.addListener(Input.KEY_R, reloadWorldListener);
	}

}

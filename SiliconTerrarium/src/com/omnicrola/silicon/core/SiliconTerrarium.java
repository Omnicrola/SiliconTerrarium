package com.omnicrola.silicon.core;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.ICommand;
import com.omnicrola.silicon.command.TerrariumExecutionContext;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.launch.TerrariumInitializer;
import com.omnicrola.silicon.slick.SlickRenderWrapper;
import com.omnicrola.silicon.util.DeltaCalculator;

public class SiliconTerrarium extends BasicGame {

	private final EntityManager entityManager;
	private final CommandQueue commandQueue;
	private final TerrariumInitializer initializer;
	private final DeltaCalculator deltaCalculator;

	public SiliconTerrarium(TerrariumInitializer initializer, EntityManager entityManager, CommandQueue commandQueue,
			DeltaCalculator deltaCalculator) {
		super("Silicon Terrarium");
		this.initializer = initializer;
		this.entityManager = entityManager;
		this.commandQueue = commandQueue;
		this.deltaCalculator = deltaCalculator;

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		final SlickRenderWrapper renderWrapper = new SlickRenderWrapper(graphics);
		this.entityManager.render(renderWrapper);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		this.initializer.init(container);
	}

	@Override
	public void update(GameContainer container, int elapsedTime) throws SlickException {
		executeCommands();
		this.entityManager.update(this.deltaCalculator.update(elapsedTime));
	}

	private void executeCommands() {
		final List<ICommand> commands = this.commandQueue.fetchQueue();
		final TerrariumExecutionContext commandExecutionContext = new TerrariumExecutionContext(this.entityManager);
		for (final ICommand command : commands) {
			command.execute(commandExecutionContext);
		}
	}

}

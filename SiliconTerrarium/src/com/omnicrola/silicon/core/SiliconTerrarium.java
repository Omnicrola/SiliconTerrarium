package com.omnicrola.silicon.core;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.ICommand;
import com.omnicrola.silicon.command.IGameContext;
import com.omnicrola.silicon.slick.SlickRenderWrapper;
import com.omnicrola.silicon.util.DeltaCalculator;

public class SiliconTerrarium extends BasicGame {

	private final CommandQueue commandQueue;
	private final DeltaCalculator deltaCalculator;
	private final ArrayList<IGameSubsystem> subsystems;
	private final IGameContext context;

	public SiliconTerrarium(CommandQueue commandQueue, DeltaCalculator deltaCalculator) {
		super("Silicon Terrarium");
		this.commandQueue = commandQueue;
		this.subsystems = new ArrayList<>();
		this.deltaCalculator = deltaCalculator;
		this.context = new GameContext();
	}

	public void addSubsystem(IGameSubsystem gameSubsystem) {
		this.subsystems.add(gameSubsystem);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for (final IGameSubsystem gameSubsystem : this.subsystems) {
			gameSubsystem.init(this.context, container);
		}
	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		final SlickRenderWrapper renderWrapper = new SlickRenderWrapper(graphics);
		for (final IGameSubsystem subsystem : this.subsystems) {
			subsystem.render(renderWrapper);
		}
	}

	@Override
	public void update(GameContainer container, int elapsedTime) throws SlickException {
		executeCommands();
		for (final IGameSubsystem iGameSubsystem : this.subsystems) {
			iGameSubsystem.update(this.deltaCalculator.update(elapsedTime));
		}
	}

	private void executeCommands() {
		final List<ICommand> commands = this.commandQueue.fetchQueue();
		for (final ICommand command : commands) {
			command.execute(this.context);
		}
	}

}

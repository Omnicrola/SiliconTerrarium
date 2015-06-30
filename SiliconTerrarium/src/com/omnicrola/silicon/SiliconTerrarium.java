package com.omnicrola.silicon;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.omnicrola.silicon.command.CommandQueue;
import com.omnicrola.silicon.command.CreateNewTerrariumCommand;
import com.omnicrola.silicon.command.ICommand;
import com.omnicrola.silicon.command.TerrariumExecutionContext;
import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.EntityManager;
import com.omnicrola.silicon.slick.SlickRenderWrapper;
import com.omnicrola.silicon.util.RandomWrapper;

public class SiliconTerrarium extends BasicGame {

	private final EntityManager entityManager;
	private final CommandQueue commandQueue;

	public SiliconTerrarium(EntityManager entityManager, CommandQueue commandQueue) {
		super("Silicon Terrarium");
		this.entityManager = entityManager;
		this.commandQueue = commandQueue;

	}

	@Override
	public void render(GameContainer container, Graphics graphics) throws SlickException {
		final SlickRenderWrapper renderWrapper = new SlickRenderWrapper(graphics);
		this.entityManager.render(renderWrapper);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		CommandQueue.instance().enqueueCommand(new CreateNewTerrariumCommand(new EntityFactory(new RandomWrapper())));
	}

	@Override
	public void update(GameContainer container, int i) throws SlickException {
		final List<ICommand> commands = this.commandQueue.fetchQueue();
		final TerrariumExecutionContext commandExecutionContext = new TerrariumExecutionContext(this.entityManager);
		for (final ICommand iCommand : commands) {
			iCommand.execute(commandExecutionContext);
		}
	}

}

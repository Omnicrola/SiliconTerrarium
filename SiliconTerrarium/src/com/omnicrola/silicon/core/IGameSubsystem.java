package com.omnicrola.silicon.core;

import org.newdawn.slick.GameContainer;

import com.omnicrola.silicon.command.IGameContext;
import com.omnicrola.silicon.slick.IRenderWrapper;

public interface IGameSubsystem {

	void render(IRenderWrapper renderWrapper);

	void update(float update);

	void init(IGameContext context, GameContainer container);

}

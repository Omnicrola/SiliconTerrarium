package com.omnicrola.silicon.command;

import com.omnicrola.silicon.entity.EntityFactory;
import com.omnicrola.silicon.entity.SiliconEntity;

public class CreateNewTerrariumCommand implements ICommand {

	private static final int NEW_CREATURE_COUNT = 20;
	private final EntityFactory entityFactory;

	public CreateNewTerrariumCommand(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	@Override
	public void execute(ICommandContext executionContext) {
		executionContext.clearAllEntities();
		for (int i = 0; i < NEW_CREATURE_COUNT; i++) {
			final SiliconEntity newEntity = this.entityFactory.build();
			executionContext.addEntity(newEntity);
		}
	}

}

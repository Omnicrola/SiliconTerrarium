package com.omnicrola.silicon.command;

import com.omnicrola.silicon.entity.ISiliconEntity;

public interface ICommandContext {

	void clearAllEntities();

	void addEntity(ISiliconEntity newEntity);

}

package com.omnicrola.silicon.command;

import com.omnicrola.silicon.entity.SiliconEntity;

public interface ICommandContext {

	void clearAllEntities();

	void addEntity(SiliconEntity newEntity);

}

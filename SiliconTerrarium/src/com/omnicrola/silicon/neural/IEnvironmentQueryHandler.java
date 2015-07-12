package com.omnicrola.silicon.neural;

import java.util.Optional;

import com.omnicrola.silicon.entity.EntityType;
import com.omnicrola.silicon.entity.ISiliconEntity;

public interface IEnvironmentQueryHandler {
	public abstract Optional<ISiliconEntity> getNearestEntityOfType(EntityType type, ISiliconEntity sourceEntity);

	public abstract Optional<ISiliconEntity> getEntityAt(int x, int y);

}

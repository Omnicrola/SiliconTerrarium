package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;

public interface ICollisionBehavior {

	void collide(ISiliconEntity primaryEntity, ISiliconEntity otherEntity);

}

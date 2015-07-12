package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class EatOtherEntityBehavior implements ICollisionBehavior {

	public static final ICollisionBehavior INSTANCE = new EatOtherEntityBehavior();

	private EatOtherEntityBehavior() {
	}

	@Override
	public void collide(ISiliconEntity primaryEntity, ISiliconEntity otherEntity) {
		primaryEntity.adjustEnergy(1f);
		otherEntity.adjustEnergy(-1f);
	}

}

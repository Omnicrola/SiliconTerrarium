package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class FitnessOverTimeBehavior implements IUpdateBehavior {
	private static final float FITNESS_PER_FRAME = 0.1f;

	@Override
	public void execute(ISiliconEntity siliconEntity, float delta) {
		siliconEntity.adjustFitness(FITNESS_PER_FRAME * delta);
	}

}

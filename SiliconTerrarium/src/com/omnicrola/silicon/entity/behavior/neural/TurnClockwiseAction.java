package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.neural.INeuralAction;
import com.omnicrola.silicon.neural.NeuralContext;

public class TurnClockwiseAction implements INeuralAction {

	private static final float THRESHOLD = 0.8f;

	@Override
	public void activate(NeuralContext context, float activationValue) {
		context.getEntity().getMotionGovernor().rotate(activationValue);
	}

	@Override
	public float getThreshold() {
		return THRESHOLD;
	}

}

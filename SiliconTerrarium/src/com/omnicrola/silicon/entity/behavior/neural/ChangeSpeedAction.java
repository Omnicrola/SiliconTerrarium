package com.omnicrola.silicon.entity.behavior.neural;

import com.omnicrola.silicon.entity.IMotionGovernor;
import com.omnicrola.silicon.neural.INeuralAction;
import com.omnicrola.silicon.neural.NeuralContext;

public class ChangeSpeedAction implements INeuralAction {

	private static final float THRESHOLD = 0.8f;
	private final IMotionGovernor motionGovernor;

	public ChangeSpeedAction(NeuralContext neuralContext) {
		this.motionGovernor = neuralContext.getEntity().getMotionGovernor();
	}

	@Override
	public void activate(float activationValue) {
		this.motionGovernor.modifyVelocity(activationValue);
	}

	@Override
	public float getThreshold() {
		return THRESHOLD;
	}

}

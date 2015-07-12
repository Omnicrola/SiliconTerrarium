package com.omnicrola.silicon.entity.behavior;


public class BehaviorFactory {

	public ICollisionBehavior buildEatBehavior() {
		return EatOtherEntityBehavior.INSTANCE;
	}

	public IUpdateBehavior buildFitnessOverTime() {
		return FitnessOverTimeBehavior.INSTANCE;
	}

	public IUpdateBehavior buildUseEnergy() {
		return EnergyConsumptionBehavior.INSTANCE;
	}

}

package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.entity.ISiliconEntity;

public class EnergyConsumptionBehavior implements IUpdateBehavior {

	public static final IUpdateBehavior INSTANCE = new EnergyConsumptionBehavior();
	public static final float ENERGY_PER_FRAME = 0.001f;

	private EnergyConsumptionBehavior() {
	}

	@Override
	public void execute(ISiliconEntity siliconEntity, float delta) {
		siliconEntity.adjustEnergy(ENERGY_PER_FRAME * delta * -1);
	}

}

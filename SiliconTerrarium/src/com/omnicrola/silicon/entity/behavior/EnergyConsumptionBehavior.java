package com.omnicrola.silicon.entity.behavior;

import com.omnicrola.silicon.TerrariumSettings;
import com.omnicrola.silicon.entity.ISiliconEntity;

public class EnergyConsumptionBehavior implements IUpdateBehavior {

	public static final IUpdateBehavior INSTANCE = new EnergyConsumptionBehavior();

	private EnergyConsumptionBehavior() {
	}

	@Override
	public void execute(ISiliconEntity siliconEntity, float delta) {
		siliconEntity.adjustEnergy(TerrariumSettings.INSTANCE.getCreatureEnergyDecayRate() * delta * -1);
	}

}

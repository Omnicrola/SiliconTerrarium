package com.omnicrola.silicon.neural;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.omnicrola.silicon.entity.behavior.neural.EnergyLevelInput;
import com.omnicrola.silicon.entity.behavior.neural.FoodProximityInput;
import com.omnicrola.silicon.entity.behavior.neural.NearestCreatureDistanceInput;

public class NeuralInputFactory {
	private final HashMap<NeuralInput, Class<?>> inputMap;

	public NeuralInputFactory() {
		this.inputMap = new HashMap<>();
		this.inputMap.put(NeuralInput.ENERGY_LEVEL, EnergyLevelInput.class);
		this.inputMap.put(NeuralInput.FOOD_PROXIMITY, FoodProximityInput.class);
		this.inputMap.put(NeuralInput.NEAREST_CREATURE, NearestCreatureDistanceInput.class);
	}

	public INeuralInput create(NeuralInput type) {
		try {
			return (INeuralInput) this.inputMap.get(type).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InvalidNeuralInputException(e.getMessage());
		}
	}
}

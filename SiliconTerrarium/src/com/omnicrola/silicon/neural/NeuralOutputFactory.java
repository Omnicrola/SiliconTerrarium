package com.omnicrola.silicon.neural;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.omnicrola.silicon.entity.behavior.neural.ChangeSpeedAction;
import com.omnicrola.silicon.entity.behavior.neural.TurnClockwiseAction;
import com.omnicrola.silicon.entity.behavior.neural.TurnCounterClockwiseAction;

public class NeuralOutputFactory {
	private final HashMap<NeuralOutput, Class<? extends INeuralAction>> outputMap;

	public NeuralOutputFactory() {
		this.outputMap = new HashMap<>();
		this.outputMap.put(NeuralOutput.TURN_CLOCKWISE, TurnClockwiseAction.class);
		this.outputMap.put(NeuralOutput.TURN_COUNTERCLOCKWISE, TurnCounterClockwiseAction.class);
		this.outputMap.put(NeuralOutput.CHANGE_SPEED, ChangeSpeedAction.class);

	}

	public INeuralAction create(NeuralOutput type, NeuralContext neuralPackage) {
		try {
			return this.outputMap.get(type).getConstructor(NeuralContext.class).newInstance(neuralPackage);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InvalidNeuralInputException(e.getMessage());
		}
	}

}

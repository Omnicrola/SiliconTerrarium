package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class NeuralNetworkNumberFinder {
	private static class Grid {
		private final boolean[][] gridArray = new boolean[5][5];

		public boolean isCellLit(int x, int y) {
			return this.gridArray[x][y];
		}
	}

	private static class GridInput implements INeuralInput {
		private final int y;
		private final Grid grid;
		private final int x;

		public GridInput(Grid grid, int x, int y) {
			this.grid = grid;
			this.x = x;
			this.y = y;
		}

		@Override
		public float evaluate() {
			final boolean isCellLit = this.grid.isCellLit(this.x, this.y);
			return isCellLit ? 1.0f : 0.0f;
		}

		@Override
		public INeuralInput mutate(MutationDirective mutationDirective) {
			return this;
		}

	}

	private static class SuccessAction implements INeuralAction {
		private boolean success;

		public SuccessAction() {
			this.success = false;
		}

		@Override
		public void activate(float activationValue) {
			System.out.println("Success");
			this.success = true;
		}

		@Override
		public float getThreshold() {
			return 1.0f;
		}

	}

	private static final Random R = new Random();
	private static final int GRID_X = 5;
	private static final int GRID_Y = 5;
	private static Grid inputGrid;

	public static void main(String[] args) {
		final List<INeuralInput> inputs = createInputs();
		final List<INeuralInput> neuronLayer1 = createNeurons(inputs);
		final List<INeuralInput> neurons = createNeurons(neuronLayer1);
		final SuccessAction success0 = new SuccessAction();
		final SuccessAction success1 = new SuccessAction();
		((Neuron) neurons.get(0)).addNeuralAction(success0);
		((Neuron) neurons.get(1)).addNeuralAction(success1);
		final NeuralNetwork neuralNetwork = new NeuralNetwork(neurons);
		clearGrid(true);
		neuralNetwork.evaluate();
		clearGrid(false);
		neuralNetwork.evaluate();
		setGridToDisplay(0);
		neuralNetwork.evaluate();
		setGridToDisplay(1);
		neuralNetwork.evaluate();
	}

	private static void setGridToDisplay(int number) {
		clearGrid(false);
		if (number == 0) {
			inputGrid.gridArray[1][1] = true;
			inputGrid.gridArray[2][1] = true;
			inputGrid.gridArray[3][1] = true;
			inputGrid.gridArray[1][2] = true;
			inputGrid.gridArray[3][2] = true;
			inputGrid.gridArray[1][3] = true;
			inputGrid.gridArray[2][3] = true;
			inputGrid.gridArray[3][3] = true;
		} else if (number == 1) {
			inputGrid.gridArray[2][1] = true;
			inputGrid.gridArray[2][2] = true;
			inputGrid.gridArray[2][3] = true;
		}
	}

	private static void printGrid() {
		for (int x = 0; x < GRID_X; x++) {
			for (int y = 0; y < GRID_Y; y++) {
				System.out.println(inputGrid.gridArray[x][y] ? "X" : "0");
			}
		}
	}

	private static void clearGrid(boolean state) {
		for (int x = 0; x < GRID_X; x++) {
			for (int y = 0; y < GRID_Y; y++) {
				inputGrid.gridArray[x][y] = state;
			}
		}
	}

	private static List<INeuralInput> createNeurons(List<INeuralInput> neuronInputs) {
		final List<INeuralInput> neurons = new ArrayList<>();
		for (int i = 0; i < GRID_X * GRID_Y; i++) {
			final Map<INeuralInput, NeuralInputWeight> weightedInputs = randomWeights(neuronInputs);
			neurons.add(new Neuron(weightedInputs));
		}
		return neurons;
	}

	private static Map<INeuralInput, NeuralInputWeight> randomWeights(List<INeuralInput> neuronInputs) {
		final Map<INeuralInput, NeuralInputWeight> weightedInputs = new HashMap<>();
		for (final INeuralInput iNeuralInput : neuronInputs) {
			weightedInputs.put(iNeuralInput, randWeight());
		}
		return weightedInputs;
	}

	private static List<INeuralInput> createInputs() {
		inputGrid = new Grid();
		final ArrayList<INeuralInput> inputs = new ArrayList<>();
		for (int x = 0; x < GRID_X; x++) {
			for (int y = 0; y < GRID_Y; y++) {
				inputs.add(new GridInput(inputGrid, x, y));
			}
		}
		return inputs;
	}

	private static NeuralInputWeight randWeight() {
		return new NeuralInputWeight(R.nextFloat());
	}
}

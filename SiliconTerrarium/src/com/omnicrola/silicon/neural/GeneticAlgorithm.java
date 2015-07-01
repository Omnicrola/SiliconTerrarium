package com.omnicrola.silicon.neural;

import java.util.Vector;

public class GeneticAlgorithm {
	private final Vector<Genome> m_vecPop = new Vector<>();
	private int m_iPopSize;
	private int m_iChromoLength;
	private float m_fTotalFitness;
	private float m_fBestFitness;
	private float m_fAverageFitness;
	private float m_fWorstFitness;
	private int m_iFittestGenome;

	// usually 0.05 to 0.3
	private final float m_fMutationRate = 0.1f;
	private final float m_fCrossoverRate = 0.7f;
	private int m_cGeneration;

	public GeneticAlgorithm(int population, float mutationRate, float crossoverRate, int numberOfWeights) {
	}

	public Vector<Genome> epoch(Vector<Genome> oldPopulation) {
		return null;
	}

	public Vector<Genome> getChromosomes() {
		return this.m_vecPop;
	}

	public float averageFitness() {
		return this.m_fTotalFitness / this.m_iPopSize;
	}

	public float bestFitness() {
		return this.m_fBestFitness;
	}

	private void crossover(Vector<Float> mom, Vector<Float> dad, Vector<Float> baby1, Vector<Float> baby2) {
	}

	private void mutate(Vector<Float> chromosomes) {

	}

	private Genome getChromosomeRoulette() {
		return null;
	}

	private void grabNBest(int count, int copies, Vector<Genome> population) {

	}

	private void calculateBestWorstAvTot() {

	}

	private void reset() {
	}

}

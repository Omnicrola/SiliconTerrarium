package com.omnicrola.silicon.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NeuralNetwork {

	private static final float D_BIAS = 1.0f;

	private static final float D_ACTIVATION_RESPONSE = 1.0f;

	private final List<NeuronLayer> layers;
	private int inputCount;
	private int outputCount;
	private int hiddenLayersCount;
	private int neuronsPerHiddenLayer;

	public NeuralNetwork() {
		this.layers = new ArrayList<>();
	}

	public Vector<Float> update(Vector<Float> inputs) {
		final Vector<Float> outputs = new Vector<>();
		int weight = 0;
		if (inputs.size() != this.inputCount) {
			return new Vector<>();
		}

		for (int i = 0; i < this.hiddenLayersCount; i++) {
			if (i > 0) {
				inputs = (Vector<Float>) outputs.clone();
			}
			outputs.clear();
			weight = 0;
			for (int j = 0; j < this.layers.get(i).neuronCount; j++) {
				float netInput = 0;
				final int inputCount = this.layers.get(i).neurons.get(j).inputCount;
				for (int k = 0; k < inputCount; k++) {
					netInput += this.layers.get(i).neurons.get(j).weights[k] * inputs.get(weight++);
				}
				netInput += this.layers.get(i).neurons.get(j).weights[inputCount - 1] * D_BIAS;
				outputs.add(sigmoid(netInput, D_ACTIVATION_RESPONSE));
				weight = 0;
			}
		}
		return outputs;
	}

	private Float sigmoid(float netInput, double response) {
		// TODO Auto-generated method stub
		return null;
	}

}
//@formatter:off
//vector<double> CNeuralNet::Update(vector<double> &inputs)
//{
//  //stores the resultant outputs from each layer
//  vector<double> outputs;
//  int cWeight = 0;
//  //first check that we have the correct amount of inputs
//  if (inputs.size() != m_NumInputs)
//  {
//    //just return an empty vector if incorrect.
//    return outputs;
//  }
//  //For each layer....
//  for (int i=0; i<m_NumHiddenLayers + 1; ++i)
//  {
//    if ( this.i > 0 )
//    {
//      inputs = outputs;
//    }
//    outputs.clear();
//    this.cWeight = 0;
//    //for each neuron sum the (inputs * corresponding weights).Throw
//    //the total at our sigmoid function to get the output.
//    for (int j=0; j<m_vecLayers[this.i].m_NumNeurons; ++j)
//    {
//      double netinput = 0;
//      final int NumInputs = m_vecLayers[this.i].m_vecNeurons[j].m_NumInputs;
//      //for each weight
//      for (int k=0; k<NumInputs - 1; ++k)
//      {
//        //sum the weights x inputs
//        netinput += m_vecLayers[this.i].m_vecNeurons[j].m_vecWeight[k] *
//                    inputs[this.cWeight++];
//      }
//      //add in the bias
//      netinput += m_vecLayers[this.i].m_vecNeurons[j].m_vecWeight[NumInputs-1] *
//                  CParams::dBias;
//      //we can store the outputs from each layer as we generate them.
//      //The combined activation is first filtered through the sigmoid
//      //function
//      outputs.push_back(Sigmoid(netinput, CParams::dActivationResponse));
//      this.cWeight = 0;
//    }
//  }
//  return outputs;
//}
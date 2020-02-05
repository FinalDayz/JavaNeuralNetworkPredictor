package com.github.FinalDayz.NeuralNetwork;

public class WeightsLayer extends Layer {

    protected double[][] weights;
    protected double[] bias;

    public WeightsLayer(int size) {
        super(size);
    }

    public void initWeights() {
        this.initWeights(-1, 1);
    }

    public void initWeights(double minValue, double maxValue) {
        prefLayerIsDefined();
        this.weights = new double[size][prefLayer.size];
        this.bias = new double[size];
        for(int index = 0; index < weights.length; index++) {
            for(int prefLayerIndex = 0; prefLayerIndex < weights[index].length; prefLayerIndex++) {
                weights[index][prefLayerIndex] = NNUtils.random(minValue, maxValue);
            }
        }
    }

    @Override
    void feedForward(double[] input) {
        //for every neuron, calculate the value by taking the incomming connection
        for(int thisY = 0; thisY < this.weights.length; thisY++) {
            this.inputs[thisY] = 0;
            for(int prefY = 0; prefY < this.weights[thisY].length; prefY++) {
                this.inputs[thisY] += this.weights[thisY][prefY] * this.prefLayer.outputs[prefY];
            }

            this.inputs[thisY] += this.bias[thisY];

            this.outputs[thisY] = this.activate(this.inputs[thisY]);
        }

    }

    @Override
    public void init() {
        this.initWeights();
    }
}

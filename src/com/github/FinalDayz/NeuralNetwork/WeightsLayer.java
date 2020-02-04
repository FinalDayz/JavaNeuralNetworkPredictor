package com.github.FinalDayz.NeuralNetwork;

public class WeightsLayer extends Layer {

    protected double[][] weights;

    public WeightsLayer(int size) {
        super(size);
    }

    public void initWeights() {
        this.initWeights(-1, 1);
    }

    public void initWeights(double minValue, double maxValue) {
        nextLayerIsDefined();
        this.weights = new double[size][nextLayer.size];
        for(int index = 0; index < weights.length; index++) {
            for(int nextLayerIndex = 0; nextLayerIndex < weights[index].length; nextLayerIndex++) {
                weights[index][nextLayerIndex] = NNUtils.random(minValue, maxValue);
            }
        }
    }

    @Override
    void feedForward(double[] input) {

    }
}

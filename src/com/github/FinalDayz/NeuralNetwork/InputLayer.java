package com.github.FinalDayz.NeuralNetwork;

public class InputLayer extends Layer {
    public InputLayer(int size) {
        super(size);
    }

    @Override
    void feedForward(double[] input) {
        this.inputs = input;
        prefLayerIsDefined();
        this.prefLayer.feedForward(input);
    }

    public String toString() {
        return "[InputLayer, size: " + this.size + "]";
    }

    @Override
    public void init() {

    }
}

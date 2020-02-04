package com.github.FinalDayz.NeuralNetwork;

public class InputLayer extends Layer {
    public InputLayer(int size) {
        super(size);
    }

    @Override
    void feedForward(double[] input) {
        this.inputs = input;
        nextLayerIsDefined();
        this.nextLayer.feedForward(input);
    }

    public String toString() {
        return "[InputLayer, size: " + this.size + "]";
    }
}

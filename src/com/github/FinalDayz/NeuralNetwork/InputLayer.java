package com.github.FinalDayz.NeuralNetwork;

public class InputLayer extends Layer {

    public InputLayer(int size) {
        super(size, null);
    }

    @Override
    void feedForward(double[] input) {
        this.inputs = input;
        this.outputs = input;
        this.nextLayer.feedForward(input);
    }

    public String toString() {
        return "[InputLayer, size: " + this.size + "]";
    }

    @Override
    public void calculateDerivative() {}

    @Override
    public void ajustParameters(double[] wantedOutput) {}

    @Override
    public InputLayer init() {
        return this;
    }
}

package com.github.FinalDayz.NeuralNetwork;

public abstract class Layer {

    protected double[] inputs;
    protected int size;
    protected Layer nextLayer;

    public Layer(int size) {
        this.size = size;
    }

    abstract void feedForward(double[] input);

    public int getSize() {
        return this.size;
    }

    public void connectTo(Layer outputLayer) {
        this.nextLayer = nextLayer;
    }

    public String toString() {
        return "[Layer, size: " + this.size + "]";
    }
}

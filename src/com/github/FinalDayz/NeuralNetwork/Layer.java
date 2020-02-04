package com.github.FinalDayz.NeuralNetwork;

public abstract class Layer {

    protected double[] inputs;
    protected int size;
    protected Layer nextLayer;
    protected double[] output;

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

    public double[] getOutput () {
        return this.output;
    }

    protected void nextLayerIsDefined() {
        if(this.nextLayer == null) {
            throw new IllegalStateException("Next layer is not defined yet");
        }
    }
}

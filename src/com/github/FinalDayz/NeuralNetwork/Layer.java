package com.github.FinalDayz.NeuralNetwork;

public abstract class Layer {

    protected double[] inputs;
    protected int size;
    protected Layer prefLayer;
    protected double[] outputs;

    public Layer(int size) {
        this.size = size;
    }

    abstract void feedForward(double[] input);

    public int getSize() {
        return this.size;
    }

    public void connectTo(Layer outputLayer) {
        System.out.println("Connect this layer " + this + " to " + outputLayer);
        this.prefLayer = outputLayer;
    }

    public String toString() {
        return "[Layer, size: " + this.size + "]";
    }

    public double[] getOutputs() {
        return this.outputs;
    }

    protected void prefLayerIsDefined() {
        if(this.prefLayer == null) {
            throw new IllegalStateException("Next layer is not defined yet");
        }
    }

    public abstract void init();
}

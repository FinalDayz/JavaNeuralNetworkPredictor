package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.NeuralNetwork.activation.Activation;

public abstract class Layer {

    protected double[] inputs;
    protected int size;
    protected Layer prefLayer;
    protected Layer nextLayer;
    protected double[] outputs;
    protected Activation activation;

    public Layer(int size, Activation activation) {
        this.size = size;
        this.activation = activation;
    }

    public abstract Layer init();

    abstract void feedForward(double[] input);

    public int getSize() {
        return this.size;
    }

    public void connectPrefLayer(Layer inputLayer) {
        this.prefLayer = inputLayer;
        inputLayer.connectNextLayer(this);
    }

    public void connectNextLayer(Layer nextLayer) {
        this.nextLayer = nextLayer;
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

    protected double[] activateValues(double[] inputValues) {
        return activation.activateValues(inputValues);
    }

    public abstract void calculateDerivative(double[] outputDerivatives);

    public abstract void ajustParameters(double learningRate);

    public abstract void printBlackBox();
}

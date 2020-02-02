package com.github.FinalDayz.NeuralNetwork;

public class NeuralNetwork {

    private Layer[] layers;
    private int inputSize, outputSize;

    public NeuralNetwork(int inputSize, int outputSize) {
        this.layers = new Layer[0];
        this.inputSize = inputSize;
        this.outputSize = outputSize;
    }

    public void addLayer(int size, Activation activation) {
        Layer[] newLayers = new Layer[this.layers.length + 1];

        for(int index = 0; index < this.layers.length; index++) {
            newLayers[index] = layers[index];
        }

        Layer newLayer = new HiddenLayer(size);

        newLayers[this.layers.length] = newLayer;
        this.layers = newLayers;
    }

    public void compile() {

    }

}

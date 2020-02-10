package com.github.FinalDayz.NeuralNetwork;

import java.util.Arrays;

public class NeuralNetwork {

    private Layer[] layers;
    private InputLayer inputLayer;
    private OutputLayer outputLayer;

    public NeuralNetwork(InputLayer inputLayer, OutputLayer outputLayer) {
        this.layers = new Layer[0];
        this.inputLayer = inputLayer;
        this.outputLayer = outputLayer;
    }

    public void addLayer(Layer layer) {
        Layer[] newLayers = new Layer[this.layers.length + 1];

        for(int index = 0; index < this.layers.length; index++) {
            newLayers[index] = layers[index];
        }

        newLayers[this.layers.length] = layer;
        this.layers = newLayers;
    }

    public void connectLayers() {
        Layer prefLayer = this.inputLayer;
        for(Layer layer : this.layers) {
            layer.connectPrefLayer(prefLayer);
            prefLayer = layer;
        }
        this.outputLayer.connectPrefLayer(prefLayer);
    }

    public String toString() {
        String output =  "[Neural network, \n\t" + this.layers.length + " hidden layers, " +
                "\n\tInput size: " + this.inputLayer.getSize() + ", " +
                "\n\tOutput size: " + this.outputLayer.getSize() + "]\n";

        output += "[Layers]:\n";

        output += "\t"+this.inputLayer.toString() + "\n";
        for(Layer layer : this.layers) {
            output += "\t"+layer.toString() + "\n";
        }
        output += "\t"+this.outputLayer.toString() + "\n";

        return output;
    }

    public double[] feedForward(double[] inputs) {
        if(inputs.length != this.inputLayer.size) {
            throw new IllegalArgumentException("Input size does not equal predefined input layer size");
        }
        this.inputLayer.feedForward(inputs);

        return this.outputLayer.getOutputs();
    }

    public double[] lastOutput() {
        return this.outputLayer.getOutputs();
    }

    public void initializeLayers() {
        this.inputLayer.init();

        for(Layer layer : this.layers) {
            layer.init();
        }

        this.outputLayer.init();
    }

    public void printLayerOutput() {
        System.out.println(Arrays.toString(this.inputLayer.getOutputs()));

        for(Layer layer : this.layers) {
            System.out.println(Arrays.toString(layer.getOutputs()));
        }

        System.out.println(Arrays.toString(this.outputLayer.getOutputs()));
    }

    public double backporpogate(double[] wantedOutput) {
        if(wantedOutput.length != this.outputLayer.size) {
            throw new IllegalArgumentException("Wanted output size is not equal to output layer size");
        }
        return this.outputLayer.beginBackpropogate(wantedOutput, 0.1);
    }

    public void printBlackBox() {
        inputLayer.printBlackBox();
        for(Layer layer : this.layers) {
            layer.printBlackBox();
        }
        outputLayer.printBlackBox();
    }
}

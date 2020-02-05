package com.github.FinalDayz.NeuralNetwork;

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
            layer.connectTo(prefLayer);
            prefLayer = layer;
        }
        prefLayer.connectTo(this.outputLayer);
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
        this.inputLayer.feedForward(inputs);

        return this.outputLayer.getOutputs();
    }

    public void initializeLayers() {
        for(Layer layer : this.layers) {
            layer.init();
        }
    }
}

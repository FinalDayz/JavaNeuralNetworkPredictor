package com.github.FinalDayz.NeuralNetwork;

public class OutputLayer extends WeightsLayer {
    public OutputLayer(int size) {
        super(size);
    }

    public String toString() {
        return "[OutputLayer, size: " + this.size + "]";
    }

}

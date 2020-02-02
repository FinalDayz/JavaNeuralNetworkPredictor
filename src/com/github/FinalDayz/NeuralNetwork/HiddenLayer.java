package com.github.FinalDayz.NeuralNetwork;

public class HiddenLayer extends WeightsLayer {

    public HiddenLayer(int size) {
        super(size);
    }

    public String toString() {
        return "[HiddenLayer, size: " + this.size + "]";
    }
}

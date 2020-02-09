package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.NeuralNetwork.activation.Activation;

public class OutputLayer extends WeightsLayer {

    public OutputLayer(int size, Activation activation) {
        super(size, activation);
    }

    public String toString() {
        return "[OutputLayer, size: " + this.size + "]";
    }

    public double beginBackpropogate(double[] wantedOutput) {
        super.ajustParameters(wantedOutput);

        double MSE = 0;
        for(int index = 0; index < wantedOutput.length; index++) {
            double error = wantedOutput[index] - outputs[index];
            MSE += error * error;
        }
        MSE /= size;

        return MSE;
    }

}

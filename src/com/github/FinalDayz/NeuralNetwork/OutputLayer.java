package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.NeuralNetwork.activation.Activation;

import java.util.Arrays;

public class OutputLayer extends WeightsLayer {

    public OutputLayer(int size, Activation activation) {
        super(size, activation);
    }

    public String toString() {
        return "[OutputLayer, size: " + this.size + "]";
    }

    public double beginBackpropogate(double[] wantedOutput, double learningRate) {
        double[] derivativesError = new double[wantedOutput.length];

        for(int index = 0; index < wantedOutput.length; index++) {
            double error = wantedOutput[index] - outputs[index];
            derivativesError[index] = error;
        }
        super.calculateDerivative(derivativesError);
        super.ajustParameters(learningRate);

        double MSE = 0;
        for(int index = 0; index < wantedOutput.length; index++) {
            double error = wantedOutput[index] - outputs[index];
            MSE += error * error;
//            MSE += derivativesError[index];
        }
       MSE /= size;

        return MSE;
    }

}

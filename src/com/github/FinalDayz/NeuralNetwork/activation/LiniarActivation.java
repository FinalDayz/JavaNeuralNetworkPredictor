package com.github.FinalDayz.NeuralNetwork.activation;

public class LiniarActivation extends DefaultActivation {
    @Override
    protected double activateValue(double input) {
        return input;
    }

    @Override
    protected double derivativeValue(double input) {
        return 1;
    }
}

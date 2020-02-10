package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;

public class ReLuActivation extends DefaultActivation {

    protected double activateValue(double input) {
        return Math.max(input * 0.01, input);
    }

    @Override
    protected double derivativeValue(double input) {
        return input > 0 ? 1 : 0.01;
    }
}

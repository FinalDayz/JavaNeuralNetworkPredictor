package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;

public class ReLuActivation extends DefaultActivation {

    protected double activateValue(double input) {
        return Math.max(0, input);
    }

    @Override
    protected double derivativeValue(double input) {
        return input > 0 ? 1 : 0;
    }
}

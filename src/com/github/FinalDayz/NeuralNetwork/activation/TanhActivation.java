package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;

public class TanhActivation extends DefaultActivation{

    protected double activateValue(double input) {
        return Math.tanh(input);
    }

    @Override
    protected double derivativeValue(double input) {
        return 1 - input * input;
    }
}

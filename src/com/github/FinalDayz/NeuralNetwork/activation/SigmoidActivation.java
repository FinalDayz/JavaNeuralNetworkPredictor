package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;

public class SigmoidActivation extends DefaultActivation {

    protected double activateValue(double input) {
        return (1/( 1 + Math.pow(Math.E,(-1 * input))));
    }

    @Override
    protected double derivativeValue(double input) {
        return input * ( 1 - input);
    }
}

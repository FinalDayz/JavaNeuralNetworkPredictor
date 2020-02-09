package com.github.FinalDayz.NeuralNetwork.activation;


public interface Activation {

    public double[] activateValues(double[] valuesToActivate);

    public double[] derivativeValues(double[] values);
}

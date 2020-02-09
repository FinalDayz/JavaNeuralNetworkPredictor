package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;

public abstract class DefaultActivation implements Activation {
    @Override
    public double[] activateValues(double[] valuesToActivate) {
        double[] activatedValues = new double[valuesToActivate.length];

        for(int index = 0; index < valuesToActivate.length; index++) {
            activatedValues[index] = activateValue(valuesToActivate[index]);
        }

        return activatedValues;
    }

    @Override
    public double[] derivativeValues(double[] values) {
        double[] newDerivatives = new double[values.length];
        for(int index = 0; index < newDerivatives.length; index++) {
            newDerivatives[index] = derivativeValue(values[index]);
        }
        return newDerivatives;
    }

    protected abstract double activateValue(double input);

    protected abstract double derivativeValue(double input);
}

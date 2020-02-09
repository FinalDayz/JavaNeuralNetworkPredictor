package com.github.FinalDayz.NeuralNetwork.activation;

import com.github.FinalDayz.NeuralNetwork.Layer;
import jdk.jshell.spi.ExecutionControl;

public class SoftmaxActivation implements Activation {
    @Override
    public double[] activateValues(double[] valuesToActivate) {
        double sumEValues = 0;
        double[] activatedValues = new double[valuesToActivate.length];
        // Execute pow (E, x) for each element
        for (int index = 0; index < valuesToActivate.length; index++) {
            double value = valuesToActivate[index];
            activatedValues[index] = Math.pow(Math.E, value);
            sumEValues += activatedValues[index];
        }
        // Next, divide every value by the sum of all E^values
        for (int index = 0; index < valuesToActivate.length; index++) {
            activatedValues[index] /= sumEValues;
        }

        return activatedValues;
    }

    @Override
    public double[] derivativeValues(double[] values) {
        throw new NoSuchMethodError("Method not implemented yet");
    }
}

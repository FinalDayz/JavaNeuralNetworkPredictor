package com.github.FinalDayz.NeuralNetwork;

public class NNUtils {
    public static double random(double minValue, double maxValue) {
        return (Math.random() * (maxValue - minValue)) - maxValue;
    }
}

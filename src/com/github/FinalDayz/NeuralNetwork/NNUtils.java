package com.github.FinalDayz.NeuralNetwork;

public class NNUtils {
    public static double random(double minValue, double maxValue) {
        return (Math.random() * (maxValue - minValue)) - maxValue;
    }

    public static String dblToStr(double x) {
        if(Double.isNaN(x)) {
            return "NaN";
        }
        x = Math.round(x*10000) / 10000.0;
        return String.valueOf(x);
    }
}

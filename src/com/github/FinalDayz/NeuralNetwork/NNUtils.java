package com.github.FinalDayz.NeuralNetwork;

public class NNUtils {
    public static double random(double minValue, double maxValue) {return 1;
        //return (Math.random() * (maxValue - minValue)) - maxValue;
    }

    public static String dblToStr(double x) {
        x = Math.round(x*1000) / 1000.0;
        return String.valueOf(x);
    }
}

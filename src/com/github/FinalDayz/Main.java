package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;

public class Main {

    public static void main(String[] args) {


        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(2),
                new OutputLayer(1)
        );

        network.addLayer(
                new HiddenLayer(10)
        );

        network.feedForward(new double[]{1, 0});

        network.connectLayers();

        System.out.println(network.toString());

    }
}

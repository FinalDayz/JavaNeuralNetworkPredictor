package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;

public class Main {

    public static void main(String[] args) {


        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(2),
                new OutputLayer(1)
        );

//        network.addLayer(
//                new HiddenLayer(10)
//        );

        network.connectLayers();

        network.initializeLayers();

        network.feedForward(new double[]{1, 0});

        System.out.println(network.toString());

    }
}

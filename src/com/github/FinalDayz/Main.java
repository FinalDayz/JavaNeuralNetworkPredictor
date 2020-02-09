package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;
import com.github.FinalDayz.NeuralNetwork.activation.TanhActivation;

public class Main extends SysUtils {

    public static void main(String[] args) {


        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(2),
                new OutputLayer(1, new TanhActivation())
        );

        network.addLayer(
                new HiddenLayer(10, new TanhActivation())
        );

        network.connectLayers();

        network.initializeLayers();

        network.feedForward(new double[]{1, 0});

        println(network.lastOutput());

        System.out.println(network.toString());
        network.printLayerOutput();
        println("mse: " + network.backporpogate(new double[]{1}));

    }
}

package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;
import com.github.FinalDayz.NeuralNetwork.activation.LiniarActivation;
import com.github.FinalDayz.NeuralNetwork.activation.ReLuActivation;
import com.github.FinalDayz.NeuralNetwork.activation.TanhActivation;

public class Main extends SysUtils {

    public static void main(String[] args) {


        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(1),
                new OutputLayer(1, new ReLuActivation())
        );

        network.addLayer(
                new HiddenLayer(2, new ReLuActivation())
        );

        network.connectLayers();

        network.initializeLayers();

        for(int i = 0; i < 10; i++) {
            println("["+i+"]");
            print("lastOutput: ");
            println(
                    network.feedForward(new double[]{1})
            );

            println("mse: " + network.backporpogate(new double[]{1}));
        }

    }
}

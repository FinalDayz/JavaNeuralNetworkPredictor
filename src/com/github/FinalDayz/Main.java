package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;
import com.github.FinalDayz.NeuralNetwork.activation.LiniarActivation;
import com.github.FinalDayz.NeuralNetwork.activation.ReLuActivation;
import com.github.FinalDayz.NeuralNetwork.activation.TanhActivation;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends SysUtils {

    public static void main(String[] args) {

        SysUtils.DEBUG = true;
        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(2),
                new OutputLayer(1, new TanhActivation())
        );

        network.addLayer(
                new HiddenLayer(2, new TanhActivation())
        );


        network.connectLayers();
        network.initializeLayers();

        Dataset dataset = new Dataset(
                new File("C:/Users/stee2/Desktop/XOR input.csv")
        );
        try {
            dataset.processFile(0, ',', false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dataset.setInputData(0, 1);
        dataset.setOutputData(2, 2);

        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer(
                network,
                dataset
        );

        trainer.trainNetwork(20);

//        for(int i = 0; i < 10; i++) {
//            println("["+i+"]");
//            print("lastOutput: ");
//            println(
//                    network.feedForward(new double[]{1})
//            );
//
//            println("mse: " + network.backporpogate(new double[]{1}));
//        }

    }
}

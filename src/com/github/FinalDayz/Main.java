package com.github.FinalDayz;

import com.github.FinalDayz.NeuralNetwork.*;
import com.github.FinalDayz.NeuralNetwork.activation.*;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends SysUtils {

    public static void main(String[] args) {

        NeuralNetwork network = new NeuralNetwork(
                new InputLayer(4),
                new OutputLayer(3, new SoftmaxActivation())
        );

        network.addLayer(
                new HiddenLayer(20, new TanhActivation()),
                new HiddenLayer(100, new ReLuActivation())
        );

        network.connectLayers();
        network.initializeLayers();

        Dataset dataset = new Dataset(
                new File("C:/Users/stee2/Desktop/iris.csv")
        );

        String[] categories = new String[] {
                "setosa", "versicolor", "virginica"
        };

        dataset.replace(categories[0], "1.0,0.0,0.0");
        dataset.replace(categories[1], "0.0,1.0,0.0");
        dataset.replace(categories[2], "0.0,0.0,1.0");

        try {
            dataset.processFile(1, ',', false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        dataset.setInputData(0, 3);
        dataset.setOutputData(4, 6);

//        dataset.setInputData(0, 1);
//        dataset.setOutputData(2, 2);

        NeuralNetworkTrainer trainer = new NeuralNetworkTrainer(
                network,
                dataset
        );

        SysUtils.DEBUG = false;

        trainer.trainNetwork(2000);

        for (int index = 0; index < dataset.getSize(); index++) {
            double[] input = dataset.getInputDouble(index);

            double[] output = network.feedForward(input);

            print(index + ") input: ");
            print(input);

            print(" "+network.<String>outputToOneHot(categories));
            print(", ");
            print(output);
            println();
        }

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

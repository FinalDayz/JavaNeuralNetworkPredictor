package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.SysUtils;

import java.util.Arrays;

public class NeuralNetworkTrainer {
    private final NeuralNetwork network;
    private final Dataset dataset;
    private Dataset testData;

    public NeuralNetworkTrainer(
            NeuralNetwork network,
            Dataset dataset
    ) {
        this.network = network;
        this.dataset = dataset;
    }

    public NeuralNetworkTrainer(
            NeuralNetwork network,
            Dataset dataset,
            Dataset testData
    ) {
        this(network, dataset);
        this.testData = testData;
    }

    public void trainNetwork(int epochs) {
        this.trainNetwork(epochs, 1);
    }

    public void trainNetwork(int epochs, int printPerEpoch) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            double averageMSE = 0;
            for (int index = 0; index < this.dataset.getSize(); index++) {
                double[] input = this.dataset.getInputDouble(index);
                double[] output = this.dataset.getOutputDouble(index);

                this.network.feedForward(input);
                if (SysUtils.DEBUG) {
                    SysUtils.print("Input: ");
                    SysUtils.println(input);
                    SysUtils.print("output: ");
                    SysUtils.println(output);
                    SysUtils.print("got: ");
                    SysUtils.println(network.lastOutput());
                    network.printBlackBox();
                }

                double MSE = network.backporpogate(output);
                averageMSE += MSE;

            }
//            if (SysUtils.DEBUG) {
//                network.printBlackBox();
//            }
            System.out.println(epoch + "] average MSE " + averageMSE / dataset.getSize());

        }
    }
}

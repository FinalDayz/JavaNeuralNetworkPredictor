package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.NeuralNetwork.activation.Activation;
import com.sun.nio.sctp.InvalidStreamException;

import java.util.Arrays;

public class WeightsLayer extends Layer {

    protected double[][] weights;
    protected double[] bias;
    protected double[][] weightsDerivative;
    protected double[] inputDerivative;

    public WeightsLayer(int size, Activation activation) {
        super(size, activation);
    }

    public void initParameters() {
        this.initParameters(-2, 2);
    }

    public void initParameters(double minValue, double maxValue) {
        prefLayerIsDefined();
        this.weights = new double[size][prefLayer.size];
        this.bias = new double[size];
        for(int index = 0; index < weights.length; index++) {
            bias[index] = NNUtils.random(minValue, maxValue);
            for(int prefLayerIndex = 0; prefLayerIndex < weights[index].length; prefLayerIndex++) {
                weights[index][prefLayerIndex] = NNUtils.random(minValue, maxValue);
            }
        }
    }

    @Override
    void feedForward(double[] input) {
        hasWeights();
        this.inputs = new double[this.size];
        this.outputs = new double[this.size];
        //for every neuron, calculate the value by taking the incomming connection
        for(int thisY = 0; thisY < this.weights.length; thisY++) {
            this.inputs[thisY] = 0;
            for(int prefY = 0; prefY < this.weights[thisY].length; prefY++) {
                try {
                    this.inputs[thisY] += this.weights[thisY][prefY] * this.prefLayer.outputs[prefY];
                } catch(Exception e) {
                    e.printStackTrace();
                    System.out.println("Debug info:");
                    if(this.inputs == null)
                        System.out.println("ERROR IS:: This.inputs is null!");
                    if(this.weights == null)
                        System.out.println("ERROR IS:: this.weights is null!");
                    if(this.prefLayer.outputs == null)
                        System.out.println("ERROR IS:: his.prefLayer.outputs is null!");
                    System.out.println("this.inputs size: " + this.inputs.length+" (weights length: " + this.weights.length + ")");
                    System.out.println("Pref layer output size: " + this.prefLayer.outputs.length+" (weights length: " + this.weights[0].length + ")");
                    System.exit(0);
                }
            }

            this.inputs[thisY] += this.bias[thisY];
        }
        this.outputs = this.activateValues(this.inputs);

        if(this.nextLayer != null) {
            this.nextLayer.feedForward(this.outputs);
        }

    }

    @Override
    public void calculateDerivative(double[] outputDerivatives) {
        this.inputDerivative = this.activation.derivativeValues(this.inputs);
        for(int index = 0; index < this.inputDerivative.length; index++) {
            this.inputDerivative[index] *= outputDerivatives[index];
        }

        // This is to store the derivatives for the output of the previous layer
        double[] prefLayerDerivative = new double[this.prefLayer.size];
        Arrays.fill(prefLayerDerivative, 0);

        // Now that we have the derivative for the input with respect to the final output
        // We can calculate the derivative or each weight
        // It will be equal to all the derivatives of the weights that it is connected to
        weightsDerivative = new double[this.weights.length][];
        for(int thisY = 0; thisY < this.weights.length; thisY++) {
            weightsDerivative[thisY] = new double[weights[thisY].length];
            for (int prefY = 0; prefY < this.weights[thisY].length; prefY++) {
                weightsDerivative[thisY][prefY] = this.inputDerivative[thisY] * this.prefLayer.outputs[prefY];
                prefLayerDerivative[prefY] += this.weights[thisY][prefY] * this.inputDerivative[thisY];
            }
        }

        this.prefLayer.calculateDerivative(prefLayerDerivative);
    }

    @Override
    public void ajustParameters(double learningRate) {

        for(int index = 0; index < this.bias.length; index++) {
            this.bias[index] += inputDerivative[index] * learningRate;
        }


        for(int thisY = 0; thisY < this.weightsDerivative.length; thisY++) {
            for (int prefY = 0; prefY < this.weightsDerivative[thisY].length; prefY++) {
                this.weights[thisY][prefY] += this.weightsDerivative[thisY][prefY] * learningRate;
            }
        }
        this.prefLayer.ajustParameters(learningRate);
    }

    private void hasWeights() {
        if(this.weights == null)
            throw new InvalidStreamException("Weights have not been initialized yet (initWeights)");
    }

    @Override
    public WeightsLayer init() {
        this.initParameters();
        return this;
    }

    @Override
    public void printBlackBox() {
        System.out.println("[WeightsLayer]");

        System.out.print("\t[bias], ");
        for(int index = 0; index < size; index++) {
            System.out.print("\t" + NNUtils.dblToStr(bias[index]));
        }

        for(int index = 0; index < size; index++) {
            System.out.println("");
            System.out.print("\t[weights neuron "+index+"]");
            for(int prefIndex = 0; prefIndex < this.prefLayer.size; prefIndex++) {
                System.out.print("\t" +NNUtils.dblToStr(weights[index][prefIndex]));
            }
        }
        System.out.println();

        System.out.print("\t[before activation], ");
        for(int index = 0; index < size; index++) {
            System.out.print("\t" +NNUtils.dblToStr(inputs[index]));
        }
        System.out.println();
        System.out.print("\t[after activation], ");
        for(int index = 0; index < size; index++) {
            System.out.print("\t" +NNUtils.dblToStr(outputs[index]));
        }
        System.out.println();
        if(weightsDerivative == null) {
            return;
        }

        System.out.print("\t--derivatives--");

        for(int index = 0; index < weightsDerivative.length; index++) {
            System.out.println("");
            System.out.print("\t[derivative neuron "+index+"]");
            for(int prefIndex = 0; prefIndex < this.prefLayer.size; prefIndex++) {
                System.out.print("\t" +NNUtils.dblToStr(weightsDerivative[index][prefIndex]));
            }
        }
        System.out.println();

        System.out.print("\t[derivative input], ");
        for(int index = 0; index < size; index++) {
            System.out.print("\t" +NNUtils.dblToStr(inputDerivative[index]));
        }
        System.out.println();
    }
}

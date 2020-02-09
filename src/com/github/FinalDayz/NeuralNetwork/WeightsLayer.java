package com.github.FinalDayz.NeuralNetwork;

import com.github.FinalDayz.NeuralNetwork.activation.Activation;
import com.github.FinalDayz.SysUtils;
import com.sun.nio.sctp.InvalidStreamException;

public class WeightsLayer extends Layer {

    protected double[][] weights;
    protected double[] bias;
    protected double[] weightsDerivative;
    protected double[] inputDerivative;

    public WeightsLayer(int size, Activation activation) {
        super(size, activation);
    }

    public void initWeights() {
        this.initWeights(-1, 1);
    }

    public void initWeights(double minValue, double maxValue) {
        prefLayerIsDefined();
        this.weights = new double[size][prefLayer.size];
        this.bias = new double[size];
        for(int index = 0; index < weights.length; index++) {
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
    }

    @Override
    public void ajustParameters(double[] wantedOutput) {

    }

    private void hasWeights() {
        if(this.weights == null)
            throw new InvalidStreamException("Weights have not been initialized yet (initWeights)");
    }

    @Override
    public WeightsLayer init() {
        this.initWeights();
        return this;
    }
}

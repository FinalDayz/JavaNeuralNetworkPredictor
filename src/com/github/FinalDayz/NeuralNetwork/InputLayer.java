package com.github.FinalDayz.NeuralNetwork;

public class InputLayer extends Layer {

    public InputLayer(int size) {
        super(size, null);
    }

    @Override
    void feedForward(double[] input) {
        this.inputs = input;
        this.outputs = input;
        this.nextLayer.feedForward(input);
    }

    public String toString() {
        return "[InputLayer, size: " + this.size + "]";
    }

    @Override
    public void calculateDerivative(double[] outputDerivatives) {}

    @Override
    public void ajustParameters(double learningRate) { }

    @Override
    public InputLayer init() {
        return this;
    }

    @Override
    public void printBlackBox() {
        System.out.println("[input layer]");
        System.out.print("\t[inputs]");
        for(int index = 0; index < size; index++) {
            System.out.print("\t" +NNUtils.dblToStr(inputs[index]));
        }
        System.out.println();
    }
}

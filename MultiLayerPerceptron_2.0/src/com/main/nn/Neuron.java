package com.main.nn;

import java.util.concurrent.ThreadLocalRandom;

public class Neuron {

	public final double LR = Network.Learning_Rate;

	public double[] input;
	public double[] weight;

	public double bias = 1;
	public double bias_weight;

	public double target;
	public double sum;
	public double output;
	public double error = 0;

	public double sigma, gamma;

	public Neuron(double[] data) {
		this.input = data;
		init_weight();
	}
	
	public Neuron(Neuron[] layer) {
		this.input = new double[layer.length];
		init_input(layer);
		init_weight();
	}

	public Neuron(Neuron[] layer, double target) {
		this.input = new double[layer.length];
		this.target = target;
		init_input(layer);
		init_weight();
	}

	public void init_input(Neuron[] layer) {
		for (int i = 0; i < layer.length; i++) {
			input[i] = layer[i].output;
		}
	}

	public void init_weight() {
		weight = new double[input.length];
		for (int i = 0; i < weight.length; i++) {
			weight[i] = ThreadLocalRandom.current().nextDouble(-1, 1);
		}
		bias_weight = ThreadLocalRandom.current().nextDouble(-1, 1);
	}

	public void feedForward() {
		sum();
		tanh();
		error();
	}

	public void feedBackward_out() {
		sigma = 0;
		sigma = error * (1 - (output * output));
		for (int i = 0; i < weight.length; i++) {
			weight[i] -= sigma * input[i] * LR;
		}
		bias_weight -= sigma * LR;
		error = 0;
	}

	public void feedBackward_hidd(Neuron[] layer, int index) {
		gamma = 0;
		for (int i = 0; i < layer.length; i++) {
			gamma += layer[i].sigma * layer[i].weight[index];
		}
		sigma = 0;
		sigma = gamma * (1 - (output * output));
		for (int i = 0; i < weight.length; i++) {
			weight[i] -= sigma * input[i] * LR;
		}
		bias_weight -= sigma * LR;
	}

	public void sum() {
		sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i] * weight[i];
		}
		sum += (bias * bias_weight);
	}

	public void tanh() {
		output = 0;
		output = Math.tanh(sum);
	}

	public void error() {
		error += (output - target);
	}
}

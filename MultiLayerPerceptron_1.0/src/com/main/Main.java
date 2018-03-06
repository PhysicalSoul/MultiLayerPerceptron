package com.main;

import com.main.nn.Network;

public class Main {

	// Simple java implementation of a MultiLayerPerceptron by PhysicalSoul.
	
	/* The network can support only: (at the moment)
	 * 01: input layer
	 * 01: hidden layer
	 * 01: output layer
	 * 
	 * The net have 7 params
	 * 1- The number of input neurons (integer)
	 * 2- The number of hidden neurons (integer)
	 * 3- The number of output neurons (integer)
	 * 4- The data array, must be a bidimensional array (double[][])
	 * 5- The target array, must be a simple array (double[])
	 * 6- The learning rate (double)
	 * 7- The boolean to watch in the console the learning process (boolean)
	 * ----------------------------------------------------------------
	 * The net have 06 methods
	 * 1- init();
	 * 2- train();
	 * 3- test(); ==> it takes a (double[][])
	 * 4- set_data();
	 * 5- set_target();
	 * 6- show(); ====> to see in the console the learning process
	 * 
	 */
	
	public double[][] data = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } };
	public double[] target = { 0, 1, 1, 0 };

	public Network net;

	public Main() {
		net = new Network(2, 3, 1, data, target, 0.02, true);
		net.train(10000);
		
		// Testing on a set of data
		double[][] data_2 = { {1, 0}, {0, 1}, {0, 0}, {1, 0}, {1, 1} };
		net.test(data_2);
	}

	public static void main(String[] args) {
		new Main();
	}

}
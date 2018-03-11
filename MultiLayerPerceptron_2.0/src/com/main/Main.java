package com.main;

import com.main.nn.Network;

public class Main {

	// New java implementation of a MultiLayerPerceptron_2.0 by PhysicalSoul.
	
	/* The network can support:
	 * 01: input layer ==> any number of neuron
	 * any number of ==> hidden layer ==> with any number of neuron per layer
	 * 01: output layer ==> any number of neuron
	 * 
	 * The net have 8 params
	 * 1- The number of input neurons (int)
	 * 2- The nb of hidden layers (int)
	 * 3- The nb of neuron per hidden layer (int)
	 * 4- The nb of output neurons (int)
	 * 5- The data array, must be a bidimensional array (double[][])
	 * 6- The target array, must be a simple array (double[])
	 * 7- The learning rate (double)
	 * 8- The boolean to see in the console the learning process (boolean)
	 * ----------------------------------------------------------------
	 * The net have 06 methods
	 * 1- init();  to setup everything
	 * 2- train(); to "TRAIN" the net
	 * 3- test(); ==> it takes a (double[][])
	 * 4- set_data();
	 * 5- set_target();
	 * 6- show(); ====> to see in the console the learning process
	 * 
	 * The code is not perfect and it's a bit confusing to use it the first time. But trust me this works fine (i think..)
	 * I am currently working on a more easy to use version, if you have any question about this code or any suggestion for 
	 * improving it please feel free to contact me at any time.
	 * 
	 * PhysicalSoul.
	 */
	
	public double[][] data = { { 0, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 } };
	public double[] target = { 0, 1, 1, 0 };

	public Network net;

	public Main() {
		net = new Network(2, 4, 4, 1, data, target, 0.07, true);
		net.train(20000);
		
		// Testing on a set of data
		double[][] data_2 = { {1, 0}, {0, 1}, {1, 1}, {1, 0}, {0, 0}};
		//         target = {   1   ,   1   ,   0   ,   1   ,   0   };
		// the network's output is displayed but the expected output (target) is not. So keep that in mind.
		net.test(data_2);
	}

	public static void main(String[] args) {
		new Main();
	}

}
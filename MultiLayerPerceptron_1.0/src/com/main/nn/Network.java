package com.main.nn;

public class Network {

	public static double Learning_Rate;

	public double[][] data;
	public double[] target;

	public Neuron[] input_layer;
	public Neuron[] hidden_layer;
	public Neuron[] output_layer;

	public boolean show;

	public Network(int in, int hidd, int out, double[][] data, double[] taregt, double Lr, boolean show) {
		Network.Learning_Rate = Lr;

		this.data = data;
		this.target = taregt;
		this.show = show;

		init(in, hidd, out);
		set_data(data);
		set_target(taregt);
	}

	public void init(int in, int hidd, int out) {
		input_layer = new Neuron[in];
		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(data[0]);
		}
		hidden_layer = new Neuron[hidd];
		for (int i = 0; i < hidden_layer.length; i++) {
			hidden_layer[i] = new Neuron(input_layer);
		}
		output_layer = new Neuron[out];
		for (int i = 0; i < output_layer.length; i++) {
			output_layer[i] = new Neuron(hidden_layer, target[0]);
		}
	}

	public void train(int nb) {
		for (int i = 0; i < nb; i++) {
			System.out
					.println("-------------------------------- Iter: " + (i + 1) + "---------------------------------");
			for (int j = 0; j < data.length; j++) {

				// Forward stuff....
				for (Neuron n : input_layer) {
					n.input = data[j];
					n.feedForward();
				}

				for (Neuron n : hidden_layer) {
					n.init_input(input_layer);
					n.feedForward();
				}
				for (Neuron n : output_layer) {
					n.init_input(hidden_layer);
					n.target = target[j];
					n.feedForward();
				}

				if (show)
					show(j);

				// Backward stuff....
				for (Neuron n : output_layer) {
					n.feedBackward_out();
				}
				for (int x = 0; x < hidden_layer.length; x++) {
					hidden_layer[x].feedBackward_hidd(output_layer, x);
				}
				for (int x = 0; x < input_layer.length; x++) {
					input_layer[x].feedBackward_hidd(hidden_layer, x);
				}
			}
		}
	}

	public void test(double[][] data) {
		for (int j = 0; j < data.length; j++) {
			// Forward stuff....
			for (Neuron n : input_layer) {
				n.input = data[j];
				n.feedForward();
			}

			for (Neuron n : hidden_layer) {
				n.init_input(input_layer);
				n.feedForward();
			}
			for (Neuron n : output_layer) {
				n.init_input(hidden_layer);
				n.feedForward();
			}
		}
	}

	public void show(int j) {
		for (int y = 0; y < output_layer.length; y++) {
			System.out.println("Out: " + output_layer[y].output + " || Tar: " + target[j] + " || Err: "
					+ (output_layer[y].output - target[j]));
		}
	}

	public void set_data(double[][] data) {
		this.data = data;
	}

	public void set_target(double[] target) {
		this.target = target;
	}
}
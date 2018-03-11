package com.main.nn;

public class Network {

	public static double Learning_Rate;

	public double[][] data;
	public double[] target;

	public Neuron[] input_layer;
	public Neuron[][] hidden_layer;
	public Neuron[] output_layer;

	public boolean show;

	public Network(int in, int hidden_layer, int neuron_per_hidden_layer, int out, double[][] data, double[] taregt, double Lr,
			boolean show) {
		Network.Learning_Rate = Lr;

		this.data = data;
		this.target = taregt;
		this.show = show;

		init(in, hidden_layer, neuron_per_hidden_layer, out);
		set_data(data);
		set_target(taregt);
	}

	public void init(int in, int hidd_layer, int neuron_per_layer, int out) {
		input_layer = new Neuron[in];
		for (int i = 0; i < input_layer.length; i++) {
			input_layer[i] = new Neuron(data[0]);
		}
		hidden_layer = new Neuron[hidd_layer][neuron_per_layer];
		for (int i = 0; i < hidden_layer.length; i++) {
			for (int j = 0; j < hidden_layer[i].length; j++) {
				if (i == 0) {
					hidden_layer[i][j] = new Neuron(input_layer);
				} else {
					hidden_layer[i][j] = new Neuron(hidden_layer[i - 1]);
				}
			}
		}

		output_layer = new Neuron[out];
		for (int i = 0; i < output_layer.length; i++) {
			output_layer[i] = new Neuron(hidden_layer[hidden_layer.length - 1], target[0]); // -1
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

				for (int k = 0; k < hidden_layer.length; k++) {
					for (Neuron n : hidden_layer[k]) {
						if (k == 0) {
							n.init_input(input_layer);
						} else {
							n.init_input(hidden_layer[k - 1]);
						}
						n.feedForward();
					}
				}

				for (Neuron n : output_layer) {
					n.init_input(hidden_layer[hidden_layer.length - 1]);
					n.target = target[j];
					n.feedForward();
				}

				if (show)
					show(j);

				// Backward stuff....
				for (Neuron n : output_layer) {
					n.feedBackward_out();
				}
				for (int z = hidden_layer.length - 1; z >= 0; z--) {
					for (int x = 0; x < hidden_layer[z].length; x++) {
						if (z == hidden_layer.length - 1) {
							hidden_layer[z][x].feedBackward_hidd(output_layer, x);
						} else {
							hidden_layer[z][x].feedBackward_hidd(hidden_layer[z + 1], x);
						}
					}
				}
				for (int x = 0; x < input_layer.length; x++) {
					input_layer[x].feedBackward_hidd(hidden_layer[0], x);
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

			for (int k = 0; k < hidden_layer.length; k++) {
				for (Neuron n : hidden_layer[k]) {
					if (k == 0) {
						n.init_input(input_layer);
					} else {
						n.init_input(hidden_layer[k - 1]);
					}
					n.feedForward();
				}
			}

			for (Neuron n : output_layer) {
				n.init_input(hidden_layer[hidden_layer.length - 1]);
				n.feedForward();
				System.out.println(n.output);
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
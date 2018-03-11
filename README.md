# New MultiLayer_Perceptron_2.0

Java implementation of a MultiLayerPerceptron_2.0 by PhysicalSoul
	
	 The network can support:
	  -one input layer*
	  -any number of hidden layer*
	  -one output layer*
	  
	  *and of course any number of neuron per layer
	  
	  The network have 8 params----------------------------------------------------------------------------------------
	  1- The nb of input neurons (int)
	  2- The nb of hidden layers (int)
	  3- The nb of neuron per hidden layer (int) (all the hidden layers will have the same number of neuron)
	  4- The nb of output neurons (int)
	  5- The data array, must be a bidimensional array (double[][])
	  6- The target array, must be a simple array (double[])
	  7- The learning rate (double)
	  8- The boolean to see in the console the learning process (boolean)
	  -----------------------------------------------------------------------------------------------------------------
	  The network have 06 methods
	  1- init();  to setup everything
	  2- train(); to "TRAIN" the net
	  3- test(); ==> it takes a (double[][])
	  4- set_data();
	  5- set_target();
	  6- show(); ====> to see in the console the learning process
	  -----------------------------------------------------------------------------------------------------------------
	  The code is not perfect and bit confused. But trust me it works fine.
	  I am currently working on a more easy to use version, if you have any question about this code or any suggestion 
	  for improving it please feel free to contact me at any time.

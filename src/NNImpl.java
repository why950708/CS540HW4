/**
 * The main class that handles the entire network
 * Has multiple attributes each with its own use
 * 
 */

import java.util.*;


public class NNImpl{
	public ArrayList<Node> inputNodes=null;//list of the output layer nodes.
	public ArrayList<Node> hiddenNodes=null;//list of the hidden layer nodes
	public ArrayList<Node> outputNodes=null;// list of the output layer nodes
	
	public ArrayList<Instance> trainingSet=null;//the training set
	
	Double learningRate=1.0; // variable to store the learning rate
	int maxEpoch=1; // variable to store the maximum number of epochs
	
	/**
 	* This constructor creates the nodes necessary for the neural network
 	* Also connects the nodes of different layers
 	* After calling the constructor the last node of both inputNodes and  
 	* hiddenNodes will be bias nodes. 
 	*/
	
	public NNImpl(ArrayList<Instance> trainingSet, int hiddenNodeCount, Double learningRate, int maxEpoch, Double [][]hiddenWeights, Double[][] outputWeights)
	{
		this.trainingSet=trainingSet;
		this.learningRate=learningRate;
		this.maxEpoch=maxEpoch;
		
		//input layer nodes
		inputNodes=new ArrayList<Node>();
		int inputNodeCount=trainingSet.get(0).attributes.size();
		int outputNodeCount=trainingSet.get(0).classValues.size();
		for(int i=0;i<inputNodeCount;i++)
		{
			Node node=new Node(0);
			inputNodes.add(node);
		}
		
		//bias node from input layer to hidden
		Node biasToHidden=new Node(1);
		inputNodes.add(biasToHidden);
		
		//hidden layer nodes
		hiddenNodes=new ArrayList<Node> ();
		for(int i=0;i<hiddenNodeCount;i++)
		{
			Node node=new Node(2);
			//Connecting hidden layer nodes with input layer nodes
			for(int j=0;j<inputNodes.size();j++)
			{
				NodeWeightPair nwp=new NodeWeightPair(inputNodes.get(j),hiddenWeights[i][j]);
				node.parents.add(nwp);
			}
			hiddenNodes.add(node);
		}
		
		//bias node from hidden layer to output
		Node biasToOutput=new Node(3);
		hiddenNodes.add(biasToOutput);
			
		//Output node layer
		outputNodes=new ArrayList<Node> ();
		for(int i=0;i<outputNodeCount;i++)
		{
			Node node=new Node(4);
			//Connecting output layer nodes with hidden layer nodes
			for(int j=0;j<hiddenNodes.size();j++)
			{
				NodeWeightPair nwp=new NodeWeightPair(hiddenNodes.get(j), outputWeights[i][j]);
				node.parents.add(nwp);
			}	
			outputNodes.add(node);
		}	
	}
	
	/**
	 * Get the output from the neural network for a single instance
	 * Return the idx with highest output values. For example if the outputs
	 * of the outputNodes are [0.1, 0.5, 0.2], it should return 1. If outputs
	 * of the outputNodes are [0.1, 0.5, 0.5], it should return 2. 
	 * The parameter is a single instance. 
	 */
	
	public int calculateOutputForInstance(Instance inst)
	{
		// TODO: add code here
		//Get the input from the instance
		for (int i=0;i<inst.attributes.size();i++)
		{
			this.inputNodes.get(i).setInput(inst.attributes.get(i));
		}
		
		for(Node hiddenNode:this.hiddenNodes)
		{
			hiddenNode.calculateOutput();
		}
		
		for(Node outputNode:this.outputNodes)
		{
			outputNode.calculateOutput();
		}
	
		double max =0 ;
		int index = 0;
		
		for(int i = 0;i<this.outputNodes.size();i++){
			Node output = this.outputNodes.get(i);
			if(output.getOutput()>max)
			{
				max=output.getOutput();
				index = i;
			}
		}
		return index;
		
	}
	

	
	
	
	/**
	 * Train the neural networks with the given parameters
	 * 
	 * The parameters are stored as attributes of this class
	 */
	
	public void train()
	{
		// TODO: add code here
		for(int i=0;i<maxEpoch;i++){
		for(Instance inst :this.trainingSet)
		{
			this.calculateOutputForInstance(inst); //forward 
		//Backward
		for(int j =0; j<this.outputNodes.size();j++)
		{
			double TO = inst.classValues.get(i)-outputNodes.get(i).getOutput();
			for(NodeWeightPair pair: outputNodes.get(i).parents)
			{
				new weight = this.learningRate*pair.node.getOutput()*
			}
				
		}
		//Compute Wjk
			
			
		}
		//Compute Wij 
		
		//The derivative
		}
		}	
	}
}

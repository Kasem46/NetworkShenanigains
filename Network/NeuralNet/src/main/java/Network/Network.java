/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network;

/**
 *
 * @author Administrator
 */
public class Network {
    Layer[] layers;
    double[][] trainingData;
    
    public Network(int numLayers,double[] inputVals, int numOutputs,int nodesPerLayer, double[][] expectedVals){
        if(numLayers < 2){
            numLayers = 2;
        }
        
        layers = new Layer[numLayers];
        layers[0] = new Layer(inputVals);
        for(int i = 1; i < layers.length - 1; i++){
            layers[i] = new Layer(nodesPerLayer,layers[i-1]);
        }
        layers[layers.length-1] = new Layer(numOutputs,layers[layers.length-2]);
        
        trainingData = expectedVals;
    }
    
    public double[] calculateOutputs(){
        return layers[layers.length - 1].processLayer();
    }
    
    //compares the expected value too the actual one and return the squared difference
    public double Cost(){
        double cost = 0;
        
        
        //determine the cost for each set of training data
        for(int i = 0; i < trainingData.length;i++){
            inputs
            double[] outputs = this.calculateOutputs();
             //for the individual set 
            for(int j = 0; j < outputs.length;j++){
                cost += (trainingData[i][j] - outputs[j]);
            }
        }
        
        return cost;
    }
    
    
}

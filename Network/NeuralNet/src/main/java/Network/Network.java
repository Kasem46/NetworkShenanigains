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
    double[][] trainingDataInputs;
    double[][] trainingDataOutputs;
    
    public Network(int numLayers,double[] inputVals, int numOutputs,int nodesPerLayer, double[][] expectedValsOut,double[][]expectedValsIn){
        if(numLayers < 2){
            numLayers = 2;
        }
        
        layers = new Layer[numLayers];
        layers[0] = new Layer(inputVals);
        for(int i = 1; i < layers.length - 1; i++){
            layers[i] = new Layer(nodesPerLayer,layers[i-1]);
        }
        layers[layers.length-1] = new Layer(numOutputs,layers[layers.length-2]);
        
        trainingDataOutputs = expectedValsOut;
        trainingDataInputs = expectedValsIn;
    }
    
    public double[] calculateOutputs(){
        return layers[layers.length - 1].processLayer();
    }
    
    //compares the expected value too the actual one and return the squared difference
    public double Cost(){
        double cost = 0;
        
        //determine the cost for each set of training data
        for(int i = 0; i < trainingDataOutputs.length;i++){
            layers[0].setInputLayer(trainingDataInputs[i]);
            double[] outputs = this.calculateOutputs();
             //for the individual set 
            for(int j = 0; j < outputs.length;j++){
                cost += (trainingDataOutputs[i][j] - outputs[j]);
            }
            cost /= outputs.length;
        }
        cost /= trainingDataOutputs.length;
        return cost;
    }
    
    public void optimise(double learnRate){
        //make small changes to each weight then see how it is improved/not improved
        //for every weight in the network
        for(int i = 1; i < layers.length;i++){
            for(int j = 0; j < layers[i].getNodes().length;j++){
                for(int k = 0; k < layers[i].getNodes()[j].getWeights().length; k++){
                    //this is the individual weight
                    double currentCost = this.Cost();
                    double orriginalWeight = layers[i].getNodes()[j].getWeights()[k];
                    //alter the weight positivly
                    layers[i].getNodes()[j].ajustWeights(k, orriginalWeight + learnRate);
                    double newCost = this.Cost();
                    //if it is worse go back and try the negitive
                    if(newCost > currentCost){
                        layers[i].getNodes()[j].ajustWeights(k, orriginalWeight);
                        //alter the weight negitivly
                        layers[i].getNodes()[j].ajustWeights(k, orriginalWeight - learnRate);
                        newCost = this.Cost();
                        //see if it helps, if no then revert
                        if(newCost > currentCost){
                            layers[i].getNodes()[j].ajustWeights(k, orriginalWeight);
                        }
                    }
                }
            }
        }
        
    }
    
}

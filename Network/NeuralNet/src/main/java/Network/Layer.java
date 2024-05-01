/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network;

/**
 *
 * @author Administrator
 */
public class Layer {
    private Node[] nodes;
    private Layer previousLayer;
    private boolean inputLayer;
    
    //use this constructor when it is a hidden or output layer
    public Layer(int numNodes,Layer previousLayer){
        this.previousLayer = previousLayer;
        nodes = new Node[numNodes];
        for(int i = 0; i < nodes.length;i++){
            nodes[i] = new Node(previousLayer.getNodes());
        }
    }
    
    //use this constructor when it is the input layer
    public Layer(double[] inputVals){
        nodes = new Node[inputVals.length];
        for(int i = 0; i < nodes.length; i++){
            nodes[i] = new Node(inputVals[i]);
        }
    }
    
    public Node[] getNodes(){
        return nodes;
    }
    
    public void setInputLayer(double[] inputs){
        if(inputLayer){
            for(int i = 0; i < nodes.length;i++){
                nodes[i].setOutput(inputs[i]);
            }
        }
    }
    
    public double[] processLayer(){
        double[] out = new double[nodes.length];
        for(int i = 0; i < nodes.length; i++){
            out[i] = nodes[i].getOutput();
        }
        return out;
    }
    
}

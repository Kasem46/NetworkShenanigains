/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Network;

/**
 *
 * @author Administrator
 */
public class Node {
    private boolean inputNode;
    private Node[] inputs;
    private double[] weights;
    private double bias;
    private double output;
    
    //if it is a layer node use this construter to use the outputs of the preivous nodes
    public Node(Node[] inputs){
        this.inputs = inputs;
        this.weights = new double[inputs.length];
        inputNode = false;
    }
    
    //if it is a base node use this constructer to set values
    public Node(double input){
        this.output = input;
        inputNode = true;
    }
    
    public void setOutput(double output){
        if(inputNode){
            this.output=output;
        }else{
            System.out.println("do not use this function if the node is not an input node, instead get the output to procces");
        }
    }
    
    public void ajustWeights(int node, double ajustVal){
        //rn just set, later use this value to ajust the current weight for that given node
        weights[node] = ajustVal;
    }
    
    public void setBias(int node, double ajustVal){
        //rn just set, later use this value to ajust the current bias
        bias = ajustVal;
    }
    
    public void process(){
        //take the weighted average of all the inputs to find the output.
        double sum = 0;
        for(int i = 0; i < inputs.length;i++){
            sum += inputs[i].getOutput()*weights[i];
        }
        
        output = sum + bias;
        
        output = activate(output);
    }
    
    public double activate(double input){
        double out = input;
        out = 1.0/(1.0+Math.exp(-out));
        return out;
    }
    
    public double getOutput(){
        if(inputNode == false){
            this.process();
        }
        return output;
    }
}

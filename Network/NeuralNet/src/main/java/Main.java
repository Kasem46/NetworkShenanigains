/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Administrator
 */
import Network.*;
import java.util.*;
import java.io.*;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //set up expected values
        
        //files to read from for the inputs and outputs are structed as such
        //2 lines are each input x and y, and they are order such that this
        //lines up with the expected output
        //output of 1,0 means that the point x,y falls under the line
        //output of 0,1 means that the point x,y falls over the line
        //the line is the function y= -4(x-0.807)^4 - (x-0.807)^3+4(x-0.807)^2 + 0.5
        
        //hard code the input lengths
        double[][] expectedInputs = new double[26][2];
        double[][] expectedOuts = new double[26][2];
        
        try{
            Scanner input1 = new Scanner(new File("res//CorrectDataOutputs.txt"));
            for(int i = 0; i < expectedOuts.length;i++){
                expectedOuts[i][0] = Double.parseDouble(input1.nextLine());
                expectedOuts[i][1] = Double.parseDouble(input1.nextLine());
            }
            input1.close();
        }catch(Exception e){
        
        }
        try{
            Scanner input2 = new Scanner(new File("res//CorrectDataInputs.txt"));
            for(int i = 0; i < expectedInputs.length;i++){
                expectedInputs[i][0] = Double.parseDouble(input2.nextLine());
                expectedInputs[i][1] = Double.parseDouble(input2.nextLine());
            }
            input2.close();
        }catch(Exception e){
        
        }
        
        Network neuralNet = new Network(4,2,2,4,expectedOuts,expectedInputs);
        
        int trainingIterations = 100;
        
        for(int i = 0; i < trainingIterations;i++){
            neuralNet.optimise(0.1);
        }
        
        Scanner input3 = new Scanner(System.in);
        String in = "";
        double[] testPoint = {1.0,1.5};
        while(true){
            System.out.println("Continue?");
            in = input3.nextLine();
            if(in.toLowerCase().equals("yes")){
            
                System.out.println("Input x coordanate");
                in = input3.nextLine();
                testPoint[0] = Double.parseDouble(in);
                System.out.println("Input y coordanate");
                in = input3.nextLine();
                testPoint[1] = Double.parseDouble(in);
        
                if(neuralNet.calculateOutputs(testPoint)[0] > neuralNet.calculateOutputs(testPoint)[1]){
                    System.out.println("Under the line");
                }else{
                    System.out.println("Over the line");
                }
            }else{
                break;
            }
        
        }
        input3.close();
    }
    
}

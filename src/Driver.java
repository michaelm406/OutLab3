
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
Authors: Andrew Smith, Michael Miller

Date submitted: April x, 2018

Overview: Program takes input from input file (input.txt) in the project directory
and creates different graphs with given adjacency matrix. This graphs are implemented 
using Prim's Algorithm, Kruskal's Algorithm, and Floyd-Warshall's Algorithm. Create
an input file titled "input.txt" and place it in the project directory for the program
to work correctly. It should be formatted in a table with verticies at the top with
the corresponding adjecency matrix filled. Entries are seperated by commas.

Example Input:

A,B,C,D,E
*,6,8,7,*
6,*,5,3,2
8,5,*,1,*
7,3,1,*,4
*,2,*,4,*

* Represents verticies that are not connected by an edge

*/

public class Driver {

    public static void main(String[] args) {
                
        //-------------------------------------------------//
        Charset charset = Charset.forName("US-ASCII");
        Path p = Paths.get("input.txt");
        int maxVertices = 50;
        //-------------------------------------------------//
        
        
        String[][] info = new String[maxVertices][maxVertices];
        int index = 0;
        int size = 0;
        
        try (BufferedReader reader = Files.newBufferedReader(p, charset)) {
             
            String line = null;
            while ((line = reader.readLine()) != null){
          
                String[] array =  line.split(","); 
                
                for(int i = 0; i < array.length; i++){
                    
                    info[index][i] = array[i];
                }  
                size = array.length;
                index++;
            }
        } 
        catch (IOException x){
            System.err.format("IOException: %s%n", x);
        } 
    
        String[] vertexArray = new String[size];
        for(int i = 0; i < size; i++){
           
            vertexArray[i] = info[0][i];          
        }
        
        String[][] matrixArray = new String[size][size];        
        for(int i = 0; i < size; i++){
           for(int j = 0; j < size; j++)
              
               matrixArray[i][j] = info[i + 1][j];                        
        }        

        Graph g = new Graph();
        
        g.createArray(vertexArray);
        g.createMatrix(matrixArray);
        g.printMatrix();
        
        g.prims();
        g.krus();
        g.floyd();
    }  
}

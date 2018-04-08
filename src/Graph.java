public class Graph {
    
    private Vertex[] array;         //holds array of vertex points with corresponding data
    private int[][] matrix;         //holds adjacency matrix
    
    
    public void createArray(String[] a){        //creates array of vertexes
        
        array = new Vertex[a.length];
            
        for(int i = 0; i < a.length; i++){
            
            array[i] = new Vertex(a[i].charAt(0));
        }
    }
    
    public void createMatrix(String[][] a){     //creates adjeceny matrix from driver data
        
        matrix = new int[a.length][a.length];
        
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a.length; j++){
                
                if(a[i][j].equals("/")){
                    
                    matrix[i][j] = 999999;      //"/" values are interpreted as very high weight paths (999999)
                }
                else{
                    matrix[i][j] = Integer.parseInt(a[i][j]);
                }
            }
        }
    }
    
    public void prims(){                
         
        System.out.println("Prim's Algorithm");
        
        int weight = 0;
        array[0].visited = true; 
         
        while(!queueEmpty()){
            
            int min = 999999;
            int start = -1;
            int finish = -1;
            
            for(int i = 0; i < array.length; i++){                                //goes through all vertices
                if(array[i].visited == true){                                    //checks edges from already visited vertices
                    for(int j = 0; j < array.length; j++){                      //checks for shortest edge from all visited vertices
                        if(matrix[i][j] < min && array[j].visited == false){   //finds lowest weighted edge to unvisited vertice
                            
                            min = matrix[i][j];                              //minimum weighted edge stored here
                            start = i;                                      //i represents where path started 
                            finish = j;                                    //j represents where path ended
                        }
                    }
                }
            }
            array[finish].visited = true;                             //sets visited flag true if vertice has been visited
            weight = weight + min;
            System.out.println(array[start].vertex + "->" + array[finish].vertex);          
        }
        System.out.println("Total weight = " + weight + "\n");
        
        for(int i = 0; i < array.length; i++){                   //resets visited flag for vertices
            
            array[i].visited = false;
        }
    }
    
    public void krus(){
        
        System.out.println("Kruskal's Algorithm");
        
        int weight = 0;
        int edges = 0;
        
        int[][] checkArray = new int[matrix.length][matrix.length];         //creates 2D array that keeps track of already used edges (0 = not used; 1 = used)
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix.length; j++){
                
                checkArray[i][j] = 0;
            }
        }
        
        while(!queueEmpty()){
        
            int min = 999999;
            int start = -1;
            int finish = -1;
  
            for(int i = 0; i < matrix.length; i++){                         //finds lowest weighted edges and connects them to unconnected vertices
                for(int j = 0; j < matrix.length; j++){
                        
                    if((matrix[i][j] < min) && (array[i].visited == false || array[j].visited == false)){
                        min = matrix[i][j];
                        start = i;                                      
                        finish = j;
                    }
                }
            }
            array[start].visited = true;                                    //marks whether or not vertice has been visited
            array[finish].visited = true;                                   
            checkArray[start][finish] = 1;                                  //path connections are same regardless of direction (non directional MST)
            checkArray[finish][start] = 1;
            weight = weight + min;
            edges++;
            System.out.println(array[start].vertex + "->" + array[finish].vertex);
        } 
        
        int min = 999999;
        int start = -1;
        int finish = -1;
        
        if(edges != array.length - 1){
            for(int i = 0; i < matrix.length; i++){                             //finds last edge that connects the trees 
                for(int j = 0; j < matrix.length; j++){                         //minimum of n-1 edges for n vertices

                    if(checkArray[i][j] != 1 && matrix[i][j] < min){

                        min = matrix[i][j];
                        start = i;
                        finish = j;
                    }
                }
            }
            weight = weight + min;                                              //keeps track of total weight of MST
            System.out.println(array[start].vertex + "->" + array[finish].vertex);
        }
        
        for(int i = 0; i < array.length; i++){                   //resets visited flag for vertices

            array[i].visited = false;
        }  
        System.out.println("Total weight = " + weight + "\n");
    }
    
    public void floyd(){
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    public boolean queueEmpty(){
        
        for(int i = 0; i < array.length; i++){
            
            if(array[i].visited == false){
                
                return false;
            }
        }        
        return true;
    }
    
    public void printMatrix(){              //prints adjecency matrix
        
        System.out.println("ADJACENCY MATRIX\n");
        System.out.print("    ");
        for(int i = 0; i < array.length; i++){
            
            System.out.print(array[i].vertex + "        ");
        }
        System.out.println("\n");
        
        for(int i = 0; i < array.length; i++){  
                
            System.out.print(array[i].vertex + "   ");
                
            for(int j = 0; j < array.length; j++){
                
                if(matrix[i][j] < 10){
                    System.out.print(matrix[i][j] + "        ");
                }
                else if(matrix[i][j] < 100){
                    System.out.print(matrix[i][j] + "       ");   
                }
                else if(matrix[i][j] < 1000){
                    System.out.print(matrix[i][j] + "      ");
                }
                else if(matrix[i][j] < 10000){
                    System.out.print(matrix[i][j] + "     ");
                }
                else if(matrix[i][j] < 100000){
                    System.out.print(matrix[i][j] + "    ");
                }
                else{
                    System.out.print(matrix[i][j] + "   ");
                }
        
            }
            System.out.println();
        } 
        System.out.println();
    }
}

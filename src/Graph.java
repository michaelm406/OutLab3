public class Graph {
    private Vertex[] array;
    private int[][] matrix;

    public Graph() {
    }

    public void createArray(String[] a) {
        this.array = new Vertex[a.length];

        for(int i = 0; i < a.length; ++i) {
            this.array[i] = new Vertex(a[i].charAt(0));
        }

    }

    public void createMatrix(String[][] a) {
        this.matrix = new int[a.length][a.length];

        for(int i = 0; i < a.length; ++i) {
            for(int j = 0; j < a.length; ++j) {
                if (a[i][j].equals("*")) {
                    this.matrix[i][j] = 999999;
                } else {
                    this.matrix[i][j] = Integer.parseInt(a[i][j]);
                }
            }
        }

    }

    public void printMatrix() {
        for(int i = 0; i < this.array.length; ++i) {
            System.out.print(this.array[i].vertex + " ");

            for(int j = 0; j < this.array.length; ++j) {
                System.out.print(this.matrix[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public void prims() {
        System.out.println("Prim's Algorithm");
        int weight = 0;
        this.array[0].visited = true;

        int min;
        while(!this.queueEmpty()) {
            min = 999999;
            int start = -1;
            int finish = -1;

            for(int i = 0; i < this.array.length; ++i) {
                if (this.array[i].visited) {
                    for(int j = 0; j < this.array.length; ++j) {
                        if (this.matrix[i][j] < min && !this.array[j].visited) {
                            min = this.matrix[i][j];
                            start = i;
                            finish = j;
                        }
                    }
                }
            }

            this.array[finish].visited = true;
            weight += min;
            System.out.println(this.array[start].vertex + "->" + this.array[finish].vertex);
        }

        System.out.println("Total weight = " + weight + "\n");

        for(min = 0; min < this.array.length; ++min) {
            this.array[min].visited = false;
        }

    }

    public boolean queueEmpty() {
        for(int i = 0; i < this.array.length; ++i) {
            if (!this.array[i].visited) {
                return false;
            }
        }

        return true;
    }

    public void krus() {
        System.out.println("Kruskal's Algorithm");
        int[][] checkArray = new int[this.matrix.length][this.matrix.length];

        int start;
        for(int i = 0; i < this.matrix.length; ++i) {
            for(start = 0; start < this.matrix.length; ++start) {
                checkArray[i][start] = 0;
            }
        }

        //int weight = false;
        start = -1;
        int finish = -1;

        while(!this.queueEmpty()) {
            int min = 999999;

            for(int i = 0; i < this.matrix.length; ++i) {
                for(int j = 0; j < this.matrix.length; ++j) {
                    if (this.matrix[i][j] < min && (!this.array[i].visited || !this.array[j].visited)) {
                        min = this.matrix[i][j];
                        start = i;
                        finish = j;
                    }
                }
            }

            this.array[start].visited = true;
            this.array[finish].visited = true;
            checkArray[start][finish] = 1;
            checkArray[finish][start] = 1;
            System.out.println(this.array[start].vertex + "->" + this.array[finish].vertex);
        }

        while(!this.connected(checkArray)) {
            ;
        }

    }
    //this is what you guys needed right?
    public void floyd() {
        int vertices = matrix[0].length;
        int distance[][] = new int[vertices][vertices];
        int i=0;
        int j=0;
        int k=0;

            while(i<vertices)
            {
                distance[i][j]=matrix[i][j];
                j++;
                if(j==vertices)
                {
                    i++;
                    j=0;
                }
            }
        for (k=0; k < vertices; k++)
        {
            for (i = 0; i < vertices; i++)
            {
                for (j = 0; j < vertices; j++)
                {
                    if (i==j) {
                        distance[i][j] = 0;
                    }
                    if (distance[i][k] + distance[k][j] < distance[i][j])
                    {
                        System.out.println("before number has been changed () are path");
                        printMatrix(distance,i,j,k);
                        distance[i][j] = distance[i][k] + distance[k][j];
                        System.out.println("after number has been changed");
                        printMatrix(distance,i,j,k);

                    }

                }
            }
        }
    }
    private boolean numToChange=false;

    public void printMatrix(int[][] array,int a,int b,int c) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(" ");

            for(int j = 0; j < array.length; ++j) {
                if(i==a && j==b || i==a && j==c || i==c && j==b) {
                    if(j!=b || i!=a) {
                        System.out.print("(" + array[i][j] + ")");
                        numToChange = true;
                    }
                    else if(j==array.length-1) {
                        System.out.print("[" + array[i][j] + "]");
                        numToChange =true;
                    }
                    else
                    System.out.print("[" + array[i][j] + "]");
                    numToChange =false;
                }
                else
                System.out.print(array[i][j] + " ");
                numToChange =false;
            }
            if (!numToChange) {
                System.out.println();
            }
        }
        if(!numToChange)
        System.out.println();
    }

    public boolean connected(int[][] ca) {
        for(int i = 0; i < this.matrix.length; ++i) {
            for(int j = 0; j < this.matrix.length; ++j) {
                System.out.print(ca[i][j]);
            }

            System.out.println();
        }

        return true;
    }
}


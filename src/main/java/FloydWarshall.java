import java.util.Arrays;
import java.util.Stack;

public class FloydWarshall {
    static final int INF = Integer.MAX_VALUE;
    static final int NIL = -1;
    private int[][] graph;
    private int V;
    FloydWarshall(int[][] graph, int V){
        this.graph = graph;
        this.V = V;
    }
    
    public boolean floydWarshall(int[][] matrix, int[][] predecessors){
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < V; i++){
            for (int j = 0; j < V; j++){
                if (i == j){
                    matrix[i][j] = 0;
                    predecessors[i][j] = NIL;
                    continue;
                }
                matrix[i][j] = (graph[i][j] == 0) ? INF : graph[i][j];
                predecessors[i][j] = (matrix[i][j] == INF) ? NIL : i;
            }
        }
        
        for (int k = 0; k < matrix.length; k++){
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix.length; j++){
                    if (matrix[i][k] != Integer.MAX_VALUE && matrix[k][j] != Integer.MAX_VALUE &&
                            (matrix[i][j] > matrix[i][k] + matrix[k][j])){

                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        predecessors[i][j] = predecessors[k][j];
                    }
                }
            }
        }
        // check negative cycle
        for (int i = 0; i < matrix.length; i++){
            if (matrix[i][i] < 0)
                return false;
        }
        return true;
    }

    public void printMatrices(int[][] matrix, int[][] predecessors){
        System.out.println(">>>>Cost Matrix");
        printMatrix(matrix, Integer.MAX_VALUE, "INF");
        System.out.println("\n>>>>Predecessors Matrix");
        printMatrix(predecessors, -1, "NIL");
    }
    public void printMatrix(int[][] matrix, int op, String str){

        System.out.println();
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                if (matrix[i][j] == op){
                    System.out.print(str + "\t\t");
                    continue;
                }
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println();
        }
    }

    public void printRow(int src, int[][] matrix, int[][] predecessors) {
        Stack<Integer> stack = new Stack<>();
        System.out.print("Vertex \t\t Distance\tPath");
        for (int i = 0; i < predecessors.length; i++){
            if (i == src)
                continue;
            int dest = i;
            stack.push(dest);
            while (dest != src){
                stack.push(predecessors[src][dest]);
                dest = predecessors[src][dest];
            }
            System.out.print("\n" + src + " -> " + i + " \t\t ");
            System.out.print(matrix[src][i]);
            System.out.print("\t\t\t");
            while (!stack.empty())
                System.out.print(stack.pop() + " ");

//            System.out.println();
        }
        System.out.println();
    }
    
    public void printPath(int src, int node, int[][] predecessors) {
        Stack<Integer> stack = new Stack<>();

        int dest = node;
        stack.push(dest);
        while (dest != src){
            stack.push(predecessors[src][dest]);
            dest = predecessors[src][dest];
        }

        while (!stack.empty())
            System.out.print(stack.pop() + " ");

        System.out.println();
    }
}

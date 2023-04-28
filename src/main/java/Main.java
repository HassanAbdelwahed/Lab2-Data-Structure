import java.util.Arrays;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static final int NIL = -1;
    public static void main(String[] args) {
//       GraphMatrix a = new GraphMatrix("ex1.txt");
        GraphMatrix a = new GraphMatrix("ex3.txt");
        int[][] graph = a.getGraph();
        int V = a.Size();
        // Dijkestra
        Dijkestra dij = new Dijkestra(graph, V);
        int[] dist = new int[V];
        int[] parents = new int[V];
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>> Dijkestra >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        dij.dijkestra(0, dist, parents);
        dij.printDijkestra(0, dist, parents);


        // Floyd-Warshall
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>> Floyd-Warshall >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < a.Size(); i++){
            for (int j = 0; j < a.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = NIL;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? INF : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == INF) ? NIL : i;
            }
        }
        System.out.println(floyd.floydWarshall(costMatrix, predecessors));
        //floyd.printMatrices(costMatrix, predecessors);
        floyd.printPath(0, costMatrix, predecessors);
    }
}

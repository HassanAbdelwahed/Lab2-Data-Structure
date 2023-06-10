import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Testing {



    @Test
    void test1() {
        GraphMatrix graph = new GraphMatrix("ex1.txt");
//        int[][] graph = a.getGraph();
//        int V = a.Size();
//        5
//                [[0, 3, 8, 0, -4], [0, 0, 0, 1, 7], [0, 4, 0, 0, 0], [2, 0, -5, 0, 0], [0, 0, 0, 6, 0]]
        assertEquals(6,graph.Size());

    }
    @Test
    void test2() {
        GraphMatrix graph = new GraphMatrix("ex2.txt");
//        int[][] graph = a.getGraph();
//        int V = a.Size();
//        5
//                [[0, 3, 8, 0, -4], [0, 0, 0, 1, 7], [0, 4, 0, 0, 0], [2, 0, -5, 0, 0], [0, 0, 0, 6, 0]]
        assertEquals(5,graph.Size());

    }
    @Test
    void test3() {
        GraphMatrix graph = new GraphMatrix("ex3.txt");
//        int[][] graph = a.getGraph();
//        int V = a.Size();
//        5
//                [[0, 3, 8, 0, -4], [0, 0, 0, 1, 7], [0, 4, 0, 0, 0], [2, 0, -5, 0, 0], [0, 0, 0, 6, 0]]
        assertEquals(5,graph.Size());

    }
    @Test
    void test4(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        assertTrue(floyd.floydWarshall(costMatrix, predecessors));
    }
    @Test
    void test5(){
        GraphMatrix g = new GraphMatrix("ex5.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        assertFalse(floyd.floydWarshall(costMatrix, predecessors));
    }
    @Test
    void test6(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        long dijkestra, floydWarshall;
        long startDij = System.nanoTime();
        Dijkestra dij = new Dijkestra(graph, V);
        long endDij = System.nanoTime();
        dijkestra = endDij - startDij;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        long startFloyd = System.nanoTime();
        floyd.floydWarshall(costMatrix, predecessors);
        long endFloyd = System.nanoTime();
        floydWarshall = endFloyd - startFloyd;
        //floydwarshall is o(v^3) while dijkestra is o(v^2)
        // for not high dense graoh fw > dij
        assertTrue(floydWarshall > dijkestra);

    }
    @Test
    void test7(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        long dijkestra, floydWarshall;
        Dijkestra dij = new Dijkestra(graph, V);
        int[] dist = new int[V];
        int[] parents = new int[V];
        long startDij = System.nanoTime();
        dij.printDijkestra(0, dist, parents);
        long endDij = System.nanoTime();
        dijkestra = endDij - startDij;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        floyd.floydWarshall(costMatrix, predecessors);
        long startFloyd = System.nanoTime();
        floyd.printPath(0, 0, predecessors);
        long endFloyd = System.nanoTime();

        floydWarshall = endFloyd - startFloyd;
        assertTrue(floydWarshall < dijkestra);
    }
    @Test
    void test8(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        long dijkestra, floydWarshall;
        Dijkestra dij = new Dijkestra(graph, V);
        int[] dist = new int[V];
        int[] parents = new int[V];
        long startDij = System.nanoTime();
        dij.printDijkestra(0, dist, parents);
        long endDij = System.nanoTime();
        dijkestra = endDij - startDij;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        floyd.floydWarshall(costMatrix, predecessors);
        long startFloyd = System.nanoTime();
        floyd.printPath(0, 0, predecessors);
        long endFloyd = System.nanoTime();

        floydWarshall = endFloyd - startFloyd;
        assertTrue(floydWarshall < dijkestra);
    }
    @Test
    void test9(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        BellmanFord BF = new BellmanFord(graph, V, E);
        int[] costs = new int[V];
        int[] parents = new int[V];
        assertTrue(BF.bellmanFord(0, costs, parents));
    }
    @Test
    void test10(){
        GraphMatrix g = new GraphMatrix("ex5.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        BellmanFord BF = new BellmanFord(graph, V, E);
        int[] costs = new int[V];
        int[] parents = new int[V];
        assertFalse(BF.bellmanFord(0, costs, parents));
    }
    @Test
    void test11(){
        GraphMatrix g = new GraphMatrix("ex1.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        long dijkestra, bf;
        Dijkestra dij = new Dijkestra(graph, V);
        int[] dist = new int[V];
        int[] parents = new int[V];
        long startDij = System.nanoTime();
        dij.printDijkestra(0, dist, parents);
        long endDij = System.nanoTime();
        dijkestra = endDij - startDij;

        BellmanFord bell = new BellmanFord(graph, V, E);
        int [] costs = new int[V];
        int[] parents2 = new int[V];
        long startBF = System.nanoTime();
        bell.bellmanFord(0, costs, parents2);
        long endBF = System.nanoTime();

        bf = endBF - startBF;
        assertTrue(bf < dijkestra);

    }
    @Test
    void test12(){

        GraphMatrix g = new GraphMatrix("ex2.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        long dijkestra, bf;
        Dijkestra dij = new Dijkestra(graph, V);
        int[] dist = new int[V];
        int[] parents = new int[V];
        long startDij = System.nanoTime();
        dij.printDijkestra(0, dist, parents);
        long endDij = System.nanoTime();
        dijkestra = endDij - startDij;

        BellmanFord bell = new BellmanFord(graph, V, E);
        int [] costs = new int[V];
        int[] parents2 = new int[V];
        long startBF= System.nanoTime();
        bell.printBellmanFord(0, costs, parents);
        long endBF = System.nanoTime();

        bf = endBF - startBF;
        // dij > bf for not high dense graph
        assertTrue(bf > dijkestra);
    }

    @Test
    void test13(){
        GraphMatrix g = new GraphMatrix("ex4.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        long floydWarshall, bf;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }

        long startFloyd = System.nanoTime();
        floyd.floydWarshall(costMatrix, predecessors);
        long endFloyd = System.nanoTime();
        floydWarshall = endFloyd - startFloyd;

        BellmanFord bell = new BellmanFord(graph, V, E);
        int [] costs = new int[V];
        int[] parents2 = new int[V];
        long startBF = System.nanoTime();
        bell.bellmanFord(0, costs, parents2);
        long endBF = System.nanoTime();

        bf = endBF - startBF;
        assertTrue(bf < floydWarshall);
    }

    @Test
    void test14(){
        GraphMatrix g = new GraphMatrix("ex4.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        long floydWarshall, bf;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }
        floyd.floydWarshall(costMatrix, predecessors);
        long startFloyd = System.nanoTime();
        floyd.printPath(0, 0, predecessors);
        long endFloyd = System.nanoTime();
        floydWarshall = endFloyd - startFloyd;

        BellmanFord bell = new BellmanFord(graph, V, E);
        int [] costs = new int[V];
        int[] parents2 = new int[V];
        bell.bellmanFord(0, costs, parents2);
        long startBF = System.nanoTime();
        bell.printPath(0,0,parents2);
        long endBF = System.nanoTime();

        bf = endBF - startBF;
        assertTrue(bf < floydWarshall);
    }
    // for more edges graph bellman ford is faster than floydwarshall
    @Test
    void test15(){
        GraphMatrix g = new GraphMatrix("ex6.txt");
        int[][] graph = g.getGraph();
        int V = g.Size();
        int E = g.getEdges();
        long floydWarshall, bf;
        FloydWarshall floyd = new FloydWarshall();
        int[][] costMatrix = new int[V][V];
        int[][] predecessors = new int[V][V];
        // prepare cost Matrix and predecessors matrix
        for (int i = 0; i < g.Size(); i++){
            for (int j = 0; j < g.Size(); j++){
                if (i == j){
                    costMatrix[i][j] = 0;
                    predecessors[i][j] = -1;
                    continue;
                }
                costMatrix[i][j] = (graph[i][j] == 0) ? Integer.MAX_VALUE : graph[i][j];
                predecessors[i][j] = (costMatrix[i][j] == Integer.MAX_VALUE) ? -1 : i;
            }
        }

        long startFloyd = System.nanoTime();
        floyd.floydWarshall(costMatrix, predecessors);
        long endFloyd = System.nanoTime();
        floydWarshall = endFloyd - startFloyd;

        BellmanFord bell = new BellmanFord(graph, V, E);
        int [] costs = new int[V];
        int[] parents2 = new int[V];
        long startBF = System.nanoTime();
        bell.bellmanFord(0, costs, parents2);
        long endBF = System.nanoTime();

        bf = endBF - startBF;
        assertTrue(bf < floydWarshall);
    }

}

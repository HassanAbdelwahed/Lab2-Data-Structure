
import java.util.Arrays;
import java.util.Stack;

public class BellmanFord {
    private int[][] graph;
    private int V;
    BellmanFord(int[][] graph, int V){
        this.graph = graph;
        this.V = V;
    }
    public boolean bellmanFord(int src, int[] costs, int[] parents) {
        for (int i = 0; i < V; i++) {
            costs[i] = Integer.MAX_VALUE;
            parents[i] = i;
        }

        costs[src] = 0;
//        parents[src] = src;
        
        for (int h = 0; h < V - 1; h++) {
            boolean noChange = true;
            // loop through each vertex for V times
            for (int i = 0; i < V; i++) {
                //loop through the neighbours of each vertex
                for (int j = 0; j < V; j++) {
                    if (graph[i][j] != 0
                            && costs[i] != Integer.MAX_VALUE
                            && costs[i] + graph[i][j] < costs[j]) {
                        costs[j] = costs[i] + graph[i][j];
                        parents[j] = i;
                        noChange = false;
                    }
                }
            }   
            if (noChange)
                break;
        }
        
        boolean notNegative = true;
//        int[] lastCosts = Arrays.copyOf(costs, V);

        // loop through each vertex for V times
        for (int i = 0; i < V; i++) {
            //loop through the neighbours of each vertex
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != 0
                        && costs[i] != Integer.MAX_VALUE
                        && costs[i] + graph[i][j] < costs[j]) {
                    notNegative = false;
                    return notNegative;
                }
            }
        }
        
        return notNegative;
    }


    public void printBellmanFord(int src, int[] distances, int[] parents) {
        System.out.print("Vertex \t\t Distance\tPath");

        for (int i = 0; i < V; i++) {
            if (i != src) {
                System.out.print("\n" + src + " -> ");
                System.out.print(i + " \t\t ");
                System.out.print(distances[i] + "\t\t\t");
                printPath(src, i, parents);
            }
        }
        System.out.println();
    }

    public void printPath(int src, int currentVertex, int[] parents) {
        Stack<Integer> stack = new Stack<>();

        int dest = currentVertex;
        stack.push(dest);
        while (dest != src){
            stack.push(parents[dest]);
            dest = parents[dest];
        }

        while (!stack.empty())
            System.out.print(stack.pop() + " ");
    }
}

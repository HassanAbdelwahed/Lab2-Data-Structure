public class Dijkestra {
    private int[][] graph;
    private int V;
    Dijkestra(int[][] graph, int V){
        this.graph = graph;
        this.V = V;
    }
    public void dijkestra(int src, int[] dist, int[] parents) {
        boolean sptSet[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            parents[i] = i;
            sptSet[i] = false;
        }

        dist[src] = 0;
//        parents[src] = src;

        for (int i = 0; i < V; i++) {
            int u = minDistance(dist, sptSet);
            if (u == -1)
                return;
            sptSet[u] = true;

            for (int j = 0; j < V; j++) {
                // (1)Update dist[j] only if is not in sptSet,
                // (2)there is an edge from u to v, (3)dist[u] is not equal to INF
                // as it will lead to overflow error
                // and (4)total weight of path from src to v through u is
                // smaller than current value of dist[v]
                if (!sptSet[j] && graph[u][j] != 0
                        && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][j] < dist[j]) {
                    dist[j] = dist[u] + graph[u][j];
                    parents[j] = u;
                }
            }
        }
    }

    public int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int i = 0; i < dist.length; i++) {
            if (!sptSet[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void printDijkestra(int src, int[] distances, int[] parents) {
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
        if (currentVertex == src) {
            System.out.print(src + " ");
            return;
        }
        printPath(src, parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }

}

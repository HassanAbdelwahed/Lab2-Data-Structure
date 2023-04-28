public class Dijkestra {
    private int[][] graph;
    int V;
    Dijkestra(int[][] graph, int V){
        this.graph = graph;
        this.V = V;
    }
    public void dijkestra(int src, int[] dist, int[] parents) {
        boolean sptSet[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;
        parents[src] = src;

        for (int i = 0; i < V; i++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int j = 0; j < V; j++) {
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

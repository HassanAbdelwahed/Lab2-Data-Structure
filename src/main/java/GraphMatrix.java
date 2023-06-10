
import java.nio.file.Paths;
import java.util.Scanner;

// Implementation of directed weighted graphs
public class GraphMatrix {

    private int V, E;
    private int[][] graph;

    public GraphMatrix(String fpath) {

        try (Scanner scanner = new Scanner(Paths.get(fpath))) {
            String[] temp = scanner.nextLine().split(" ");
            this.V = Integer.parseInt(temp[0]);
            this.E = Integer.parseInt(temp[1]);
            this.graph = new int[V][V];

//            while (scanner.hasNextLine())
            for (int m = 0; m < E; m++) {
                temp = scanner.nextLine().split(" ");
                int i = Integer.parseInt(temp[0]),
                        j = Integer.parseInt(temp[1]),
                        w = Integer.parseInt(temp[2]);
                this.graph[i][j] = w;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public GraphMatrix(int V, int E) {
        this.V = V;
        this.E = E;
        this.graph = new int[V][V];
    }

    public GraphMatrix(int V, int E, int[][] graph) {
        this.V = V;
        this.E = E;
        this.graph = graph;
    }

    public void addEdge(int i, int j, int w) {
        this.graph[i][j] = w;
    }

    public void removeEdge(int i, int j) {
        this.graph[i][j] = 0;
    }

    public int Size() {
        return this.V;
    }
    
    public int getEdges() {
        return this.E;
    }

    public int[][] getGraph(){
        return graph;
    }


//
//    public String toString() {
//        StringBuilder s = new StringBuilder();
//        for (int i = 0; i < V; i++) {
//            s.append(i + ": ");
//            for (int j = 0; j < V; j++) {
//                if (graph[i][j] != 0)
//                    s.append(graph[i][j] + " ");
//            }
//            s.append("\n");
//        }
//        return s.toString();
//    }

}

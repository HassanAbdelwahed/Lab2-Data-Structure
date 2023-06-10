import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static final int NIL = -1;
    public static void main(String[] args) {
        
        /** CLI **/
        System.out.println("-- Shortest Paths Algorithms --\n");
        System.out.println("Enter the path or name of file:");
        System.out.print(">> ");
        Scanner scanner = new Scanner(System.in);
        String fpath = scanner.nextLine();
        GraphMatrix a = new GraphMatrix(fpath);
        int[][] graph = a.getGraph();
        int V = a.Size();
        int E = a.getEdges();
        
        while (true) {
            System.out.println("Enter Command number:");
            System.out.println("1. Finds the shortest paths from source node to all other nodes");
            System.out.println("2. Finds the shortest paths between all the pairs of nodes");
            System.out.println("3. Check if the graph contains a negative cycle");
            System.out.println("4. Exit");
            System.out.print("\n>> ");
            int num = scanner.nextInt();

            if (num == 1) {
                System.out.println("\nEnter source node number:");
                System.out.print(">> ");
                int src = scanner.nextInt();

                System.out.println("Enter Algorithm number to use:");
                System.out.println("1. Dijkstra Algorithm");;
                System.out.println("2. Bellman-Ford Algorithm");
                System.out.println("3. Floyd-Warshall Algorithm");
                System.out.println("\n>> ");
                int algNum = scanner.nextInt();

                int[] costs = null;
                int[] parents = null;
                int[][] costMatrix = null;
                int[][] predecessors = null;
                Dijkestra dij = null;
                BellmanFord bell = null;
                FloydWarshall floyd = null;

                if (algNum == 1) {
                    dij = new Dijkestra(graph, V);
                    costs = new int[V];
                    parents = new int[V];
                    dij.dijkestra(src, costs, parents);
                } else if (algNum == 2) {
                    bell = new BellmanFord(graph, V);
                    costs = new int[V];
                    parents = new int[V];            
                    bell.bellmanFord(src, costs, parents);
                } else if (algNum == 3) {
                    floyd = new FloydWarshall(graph, V);
                    costMatrix = new int[V][V];
                    predecessors = new int[V][V];
                    floyd.floydWarshall(costMatrix, predecessors);
    //                System.out.println(floyd.floydWarshall(costMatrix, predecessors));
                    //floyd.printMatrices(costMatrix, predecessors);
    //                floyd.printPath(0, costMatrix, predecessors);              
                }

                while (true) {
                    System.out.println("Enter Command number:");
                    System.out.println("1. cost of the path to a specific node");
                    System.out.println("2. path to a specific node");
                    System.out.println("3. print paths table from source");
                    System.out.println("4. return to main menu");
                    System.out.print(">> ");
                    int x = scanner.nextInt(), node = 0;
                    if (x == 4) break;
                    else if (x == 1 || x == 2) {
                        System.out.print(">> node number: ");
                        node = scanner.nextInt();
                    }

                    if (algNum == 1) {
                        if (x == 1) {
                            System.out.println("> cost from node " + src + " to node " + node + " = "+ costs[node]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + src + " to node " + node + ":\t");
                            dij.printPath(src, node, parents);
                        } else if (x == 3) {
                            dij.printDijkestra(src, costs, parents);
                        }
                    } else if (algNum == 2) {
                        if (x == 1) {
                            System.out.println("> cost from node " + src + " to node " + node + " = "+ costs[node]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + src + " to node " + node + ":\t");
                            bell.printPath(src, node, parents);
                        } else if (x == 3) {
                            bell.printBellmanFord(src, costs, parents);
                        }
                    } else if (algNum == 3) {
                        if (x == 1) {
                            System.out.println("> cost from node " + src + " to node " + node + " = "+ costMatrix[src][node]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + src + " to node " + node + ":\t");
                            floyd.printPath(src, node, predecessors);              
                        } else if (x == 3) {
                            floyd.printRow(src, costMatrix, predecessors);
                        }
                    }

                }
            } else if (num == 2) {
                ////////////////////////////////////////
                System.out.println("Enter Algorithm number to use:");
                System.out.println("1. Dijkstra Algorithm");;
                System.out.println("2. Bellman-Ford Algorithm");
                System.out.println("3. Floyd-Warshall Algorithm");
                System.out.println("\n>> ");
                int algNum = scanner.nextInt();

                int[][] costMatrix = new int[V][V];
                int[][] predecessors = new int[V][V];
                Dijkestra dij = null;
                BellmanFord bell = null;
                FloydWarshall floyd = null;

                if (algNum == 1) {
                    dij = new Dijkestra(graph, V);
                    for (int k = 0;k < V; k++) {
                        dij.dijkestra(k, costMatrix[k], predecessors[k]);
                    }
                } else if (algNum == 2) {
                    bell = new BellmanFord(graph, V);
                    for (int k = 0;k < V; k++) {
                        bell.bellmanFord(k, costMatrix[k], predecessors[k]);
                    }
                } else if (algNum == 3) {
                    floyd = new FloydWarshall(graph, V);
                    floyd.floydWarshall(costMatrix, predecessors);
    //                System.out.println(floyd.floydWarshall(costMatrix, predecessors));
                    //floyd.printMatrices(costMatrix, predecessors);
    //                floyd.printPath(0, costMatrix, predecessors);              
                }

                while (true) {
                    System.out.println("Enter Command number:");
                    System.out.println("1. cost of a path from a specific node to another one");
                    System.out.println("2. path from a specific node to another one");
                    System.out.println("3. print paths table from a specific node");
                    System.out.println("4. return to main menu");
                    System.out.print(">> ");
                    
                    int x = scanner.nextInt(), node1 = 0, node2 = 0;
                    if (x == 4) break;
                    else if (x == 1 || x == 2) {
                        System.out.println(">> Enter node 1 number: ");
                        node1 = scanner.nextInt();
                        System.out.println(">> Enter node 2 number: ");
                        node2 = scanner.nextInt();
                    } else if (x == 3) {
                        System.out.println(">> Enter node number: ");
                        node1 = scanner.nextInt();                    }

                    if (algNum == 1) {
                        if (x == 1) {
                            System.out.println("> cost from node " + node1 + " to node " + node2 + " = "+ costMatrix[node1][node2]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + node1 + " to node " + node2 + ":\t");
                            dij.printPath(node1, node2, predecessors[node1]);
                        } else if (x == 3) {
                            dij.printDijkestra(node1, costMatrix[node1], predecessors[node1]);
                        }
                    } else if (algNum == 2) {
                        if (x == 1) {
                            System.out.println("> cost from node " + node1 + " to node " + node2 + " = "+ costMatrix[node1][node2]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + node1 + " to node " + node2 + ":\t");
                            bell.printPath(node1, node2, predecessors[node1]);
                        } else if (x == 3) {
                            bell.printBellmanFord(node1, costMatrix[node1], predecessors[node1]);
                        }    
                    } else if (algNum == 3) {
                        if (x == 1) {
                            System.out.println("> cost from node " + node1 + " to node " + node2 + " = "+ costMatrix[node1][node2]);
                        } else if (x == 2) {
                            System.out.print("> path from node " + node1 + " to node " + node2 + ":\t");
                            floyd.printPath(node1, node2, predecessors);              
                        } else if (x == 3) {
                            floyd.printRow(node1, costMatrix, predecessors);
                        }
                    }

                }
            } else if (num == 3) {
                //////////////////////////////////////////
                System.out.println("Enter Algorithm number to use:");
                System.out.println("1. Bellman-Ford Algorithm");
                System.out.println("2. Floyd-Warshall Algorithm");
                System.out.println("\n>> ");
                int algNum = scanner.nextInt();

                int[] costs = null;
                int[] parents = null;
                int[][] costMatrix = null;
                int[][] predecessors = null;
                BellmanFord bell = null;
                FloydWarshall floyd = null;

                boolean notNegative = true;
                
                if (algNum == 1) {
                    bell = new BellmanFord(graph, V);
                    costs = new int[V];
                    parents = new int[V];
                    notNegative = bell.bellmanFord(0, costs, parents);
                } else if (algNum == 2) {
                    floyd = new FloydWarshall(graph, V);
                    costMatrix = new int[V][V];
                    predecessors = new int[V][V];
                    notNegative = floyd.floydWarshall(costMatrix, predecessors);
                    //floyd.printMatrices(costMatrix, predecessors);
    //                floyd.printPath(0, costMatrix, predecessors);              
                }
                
                System.out.println(notNegative);
                if (notNegative)
                    System.out.println("The graph does not contain a negative cycle.");
                else
                    System.out.println("The graph contains a negative cycle.");
            } else if (num == 4) {
                break;
            }
        
        }
         
        /****/
        
        
        
        
        /*
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
        floyd.printRow(0, costMatrix, predecessors);
        */
    }
}

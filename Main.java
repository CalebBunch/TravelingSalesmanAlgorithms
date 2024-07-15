import java.util.ArrayList;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int MIN = 100;
    private static final int MAX = 199;

    public static void main(String[] args) {
        Graph graph = generateGraph(5);
        Vertex root = graph.getVertices().get(0);
        
        Algorithms.Helpers.printGraph(graph);
        System.out.println("\n");
        Graph mst = Algorithms.MinimumSpanningTree.primsAlgorithm(graph, root);
        Algorithms.Helpers.printGraph(mst); 
        
        ArrayList<Vertex> path = Algorithms.BruteForce.findBestPath(graph, root);
        Algorithms.Helpers.printPath(path);

        path = Algorithms.NearestNeighbor.nearestNeighborPath(graph, root);
        Algorithms.Helpers.printPath(path);
        System.out.println("1 Tree: " + Algorithms.Helpers.oneTreeLowerBound(graph));
        if (Algorithms.Helpers.calculateWeight(path) < Algorithms.Helpers.oneTreeLowerBound(graph)) {
            System.out.println("not allowed");
        }

        path = Algorithms.Optimization.randomSwapping(path, 5);
        Algorithms.Helpers.printPath(path);
        
        path = Greedy.greedyPath(graph, root); 
        Algorithms.Helpers.printPath(path);

    }

    private static Graph generateGraph(int num_vertices) {
        Graph graph = new Graph();
        
        for (int i = 0; i < num_vertices; i++) {
            Vertex v = new Vertex(Integer.toString(i));
            graph.addVertex(i, v);
        }
        
        for (Vertex vi : graph.getVertices()) {
            for (Vertex vj : graph.getVertices()) {
                if (vi != vj && !vi.getEdges().stream().map(e -> e.getTo()).anyMatch(x -> x.equals(vj))) {
                    int randInt = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
                    vi.addEdge(new Edge(vj, randInt));
                    vj.addEdge(new Edge(vi, randInt));
                }
            }
        }

        return graph;
    }
}

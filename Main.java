import java.util.ArrayList;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int MIN = 100;
    private static final int MAX = 199;

    public static void main(String[] args) {
        Graph graph = Algorithms.Helpers.generateGraph(5);
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
            System.out.println("WARNING: Lower bound should never be larger than the optimal solution.");
        }

        path = Algorithms.Optimization.randomSwapping(path, 5);
        Algorithms.Helpers.printPath(path);
        
    }
}

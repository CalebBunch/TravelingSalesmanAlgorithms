import java.util.ArrayList;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int MIN = 100;
    private static final int MAX = 199;

    public static void main(String[] args) {
        Graph graph = Algorithms.Helpers.generateGraph(20, MIN, MAX);
        Vertex root = graph.getVertices().get(0);
        
        // Algorithms.Helpers.printGraph(graph);
        // System.out.println("\n");
        // Graph mst = Algorithms.MinimumSpanningTree.primsAlgorithm(graph, root);
        // Algorithms.Helpers.printGraph(mst); 
        
        //ArrayList<Vertex> path = Algorithms.BruteForce.findBestPath(graph, root);
        //System.out.println("Brute Force:");
        //Algorithms.Helpers.printPath(path);

        ArrayList<Vertex> path = Algorithms.NearestNeighbor.nearestNeighborPath(graph, root);
        System.out.println("Nearest Neighbor:");
        Algorithms.Helpers.printPath(path);
        System.out.println("1 Tree: " + Algorithms.Helpers.oneTreeLowerBound(graph) + "\n");
        if (Algorithms.Helpers.calculateWeight(path) < Algorithms.Helpers.oneTreeLowerBound(graph)) {
            System.out.println("WARNING: Lower bound should never be larger than the optimal solution.");
        }
        
        // All of these optimizations done to NearestNeighbor
        System.out.println("Simulated Annealing:");
        Algorithms.Helpers.printPath(Algorithms.Optimization.simulatedAnnealing(path, 900));

        System.out.println("Two Opt:");
        Algorithms.Helpers.printPath(Algorithms.Optimization.twoOpt(path));

        System.out.println("Random Swapping:");
        Algorithms.Helpers.printPath(Algorithms.Optimization.randomSwapping(path, 900));
    }
}

import java.util.ArrayList;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int MIN = 100;
    private static final int MAX = 199;

    public static void main(String[] args) {
        Graph graph = generateGraph(5);
        
        /*
        for (Vertex v : graph.getVertices()) {
            System.out.println(v.getLabel());
            for (Edge e : v.getEdges()) {
                System.out.println(e.getTo().getLabel() + " " + Integer.toString(e.getWeight()));
            }
            System.out.println();
        }
        */
    
        ArrayList<Vertex> path = Algorithms.BruteForce.findBestPath(graph, graph.getVertices().get(0));
        Algorithms.Helpers.printPath(path);

        path = Algorithms.NearestNeighbor.nearestNeighborPath(graph, graph.getVertices().get(0));
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

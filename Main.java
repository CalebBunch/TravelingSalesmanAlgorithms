import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Integer;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static final int MIN = 100;
    private static final int MAX = 199;

    public static void main(String[] args) {
        Graph graph = generateGraph(5);

        for (Vertex v : graph.getVertices()) {
            for (Edge e: v.getEdges()) {
                System.out.printf("%s, %s, %d\n", v.getLabel(), e.getTo().getLabel(), e.getWeight());
            }
        }

        ArrayList<ArrayList<Vertex>> paths = Algorithms.findHamoltonianCycles(graph);
        for (ArrayList<Vertex> path : paths) {
            for (Vertex v : path) {
                System.out.print(v.getLabel() + " ");
            }
            System.out.println();
        }

        // System.out.println(graph.getVertices());
    }

    private static Graph generateGraph(int num_vertices) {
        Graph graph = new Graph();
        
        for (int i = 0; i < num_vertices; i++) {
            Vertex v = new Vertex(Integer.toString(i));
            graph.addVertex(v);
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

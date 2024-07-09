// import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Algorithms {
    
    // static Pair<int, List<Vertex>> bruteForce(Graph g) {
        // Pair<int, List<Vertex>> best = new Pair<int, List<Vertex>>();
        // ArrayList<Vertex> visited = new ArrayList<Vertex>();
        // for (Vertex v : g.getVertices()) {
            
        // }

        //return best;
    //} 

    public static ArrayList<ArrayList<Vertex>> findHamiltonianCycles(Graph g) {
        // Algorithms.dfs(graph, graph.getVertices().get(0));
    }

    private static ArrayList<Vertex> dfs(Graph g, Vertex root) {
        Stack<Vertex> s = new Stack<Vertex>();
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> res = new ArrayList<Vertex>();
        s.push(root);
        while (!s.isEmpty()) {
            Vertex v = s.pop();
            res.add(v);
            System.out.println(v.getLabel());
            if (!visited.contains(root)) {
                visited.add(root);
                for (Edge e : v.getEdges()) {
                    s.push(e.getTo());
                }
            }
        }
        return res;
    }

}




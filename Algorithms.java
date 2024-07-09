// import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        // Algorithms.dfs(graph, graph.getVertices().get(0));private
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
                    dfs(g, e.getTo());
                }
            }
        }
        res.add(root);
        return res;
    }


    // https://en.wikipedia.org/wiki/Topological_sorting
    // https://stackoverflow.com/questions/9535819/find-all-paths-between-two-graph-nodes
    private static Graph topologicalSort(Graph g) {
        Graph res = new Graph();
        Map<Vertex, int> marks = new Map<Vertex, int>();
        for (Vertex v : g.getVertices()) {
            marks.put(v, -1);
        }
        Local.topoHelper(g.getVertices().get(0));

        // hack code
        class Local {
            private static void topoHelper(Vertex v) {
                if (marks.get(v) == 1) {
                    return null;
                }

            /*
            if (marks.get(v) == 0) {
                return null;
            }
            */

            // marks.put(v, 0);
            for (Edge e : v.getEdges()) {
                topoHelper(m, e.getTo());
            }

            marks.put(v, 1);
            res.addVertex(0, v);
            }
        }
    }


}




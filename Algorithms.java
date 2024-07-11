import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Algorithms {
    
    public static class BruteForce {
          
        private static ArrayList<ArrayList<Vertex>> paths = new ArrayList<ArrayList<Vertex>>();

        public static ArrayList<ArrayList<Vertex>> findHamiltonianCycles(Graph g, Vertex root) {
            ArrayList<Vertex> path = new ArrayList<>();
            path.add(root);
            Map<Vertex, boolean> visited = new Map<Vertex, bool>;
            for (Vertex v : g.getVertices()) {
                visited.put(v, false); 
            }
            visited.put(root, true);
            Vertex start = new Vertex();
            for (Vertex v : g.getVertices()) {
                if (!v.equals(root)) {
                    start = v;
                    break;
                } 
            }
            helper(g, start, path, visited);

            ArrayList<ArrayList<Vertex>> result = new ArrayList<ArrayList<Vertex>>();
            for (ArrayList<Vertex> p1 : paths) {
                for (ArrayList<Vertex> p2 : paths) {
                    if (!p2.equals(p1)) {
                        Collections.reverse(p2);
                        if (p2.equals(p1)) {
                            result.add(p2);
                        }
                    }
                }
            }
            return result;
        }

        private static void helper(Graph g, Vertex current, ArrayList<Vertex> path, Map<Vertex, boolean> visited) {
            List<Vertex> vertices = g.getVertices();
            int idx = vertices.indexOf(current);

            if (idx == vertices.size() - 1) {
                path.add(current);
                ArrayList<Vertex> pathDuplicate = new ArrayList<>(path);
                paths.add(pathDuplicate);
                path.remove(current);
                return;
            }

            for (Vertex v : vertices) {
                if (!visited.get(v)) {
                    path.add(v);
                    visited.put(v, true);
                    helper(i, vertices[idx + 1], path, visited);
                    visited.put(v, false);
                    path.remove(v);
                }
            }
        }
    }
    
}




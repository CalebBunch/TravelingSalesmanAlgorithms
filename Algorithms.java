import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Algorithms {
    
    public static int calculateWeight(ArrayList<Vertex> path) {
        int weight = 0;
        for (int i = 0; i < (path.size() - 1); i++) {
            for (Edge e : path.get(i).getEdges()) {
                if (e.getTo().equals(path.get(i + 1))) {
                  weight += e.getWeight();
                }
            }
        }
        return weight;
    }

    public static class BruteForce {
          
        private static ArrayList<ArrayList<Vertex>> paths = new ArrayList<ArrayList<Vertex>>();

        public static ArrayList<Vertex> findBestPath(Graph g, Vertex root) {
            ArrayList<Vertex> path = new ArrayList<>();
            path.add(root);
            Map<Vertex, Boolean> visited = new HashMap<Vertex, Boolean>();
            for (Vertex v : g.getVertices()) {
                visited.put(v, false); 
            }

            visited.put(root, true);
            helper(g, root, root, path, visited);

            ArrayList<ArrayList<Vertex>> options = new ArrayList<ArrayList<Vertex>>();
            for (ArrayList<Vertex> p1 : paths) {
                for (ArrayList<Vertex> p2 : paths) {
                    if (!p2.equals(p1)) {
                        Collections.reverse(p2);
                        if (p2.equals(p1)) {
                            options.add(p2);
                        }
                    }
                }
            }
            
            int minWeight = calculateWeight(options.get(0));
            ArrayList<Vertex> bestPath = new ArrayList<>(options.get(0));
            for (ArrayList<Vertex> p : options) {
                int w = calculateWeight(p);
                if (w < minWeight) {
                    minWeight = w;
                    bestPath = p;
                }
            }

            return bestPath;
        }

        private static void helper(Graph g, Vertex current, Vertex root, ArrayList<Vertex> path, Map<Vertex, Boolean> visited) {
            List<Vertex> vertices = g.getVertices();
            int idx = vertices.indexOf(current);

            if (idx == vertices.size() - 1) {
                path.add(root);
                ArrayList<Vertex> pathDuplicate = new ArrayList<>(path);
                paths.add(pathDuplicate);
                path.remove(path.size() - 1);
                return;
            }

            for (Vertex v : vertices) {
                if (!visited.get(v)) {
                    path.add(v);
                    visited.put(v, true);
                    helper(g, vertices.get(idx + 1), root, path, visited);
                    visited.put(v, false);
                    path.remove(path.size() - 1);
                }
            }
        }
    }
    
}




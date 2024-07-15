import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;


public class Algorithms {
    
    public static class Helpers {
        
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
        
        public static int oneTreeLowerBound(Graph g) {
            Graph duplicate = new Graph();
            for (int i = 1; i < g.getVertices().size(); i++) {
                duplicate.addVertex(duplicate.getVertices().size(), g.getVertices().get(i));
            }
            
            Graph mst = MinimumSpanningTree.primsAlgorithm(duplicate, duplicate.getVertices().get(0));
            
            int lowerBound = 0;
            for (Vertex v : mst.getVertices()) {
                for (Edge e : v.getEdges()) {
                    lowerBound += e.getWeight();
                }
            }

            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (Edge e : g.getVertices().get(0).getEdges()) {
                edges.add(e);
            }

            ArrayList<Integer> sorted = getWeights(edges).stream().sorted().collect(Collectors.toCollection(ArrayList::new));
            lowerBound += (sorted.get(0) + sorted.get(1));
            return lowerBound;
        }

        public static ArrayList<Integer> getWeights(ArrayList<Edge> edges) {
            ArrayList<Integer> weights = new ArrayList<Integer>();
            for (Edge e : edges) {
                weights.add(e.getWeight());
            }
            return weights;
        }
        
        public static void printPath(ArrayList<Vertex> path) {
            System.out.print("Path: ");
            for (int i = 0; i < (path.size() - 1); i++) {
                System.out.print(path.get(i).getLabel() + " -> ");
            }
            System.out.print(path.get(path.size() - 1).getLabel());
            System.out.println("\nWeight: " + Integer.toString(Helpers.calculateWeight(path)) + "\n");
        }

        public static void printGraph(Graph g) {
            for (Vertex v : g.getVertices()) {
                for (Edge e : v.getEdges()) {
                    System.out.println(v.getLabel() + " -> " + e.getTo().getLabel() + " : " + e.getWeight());
                }
            }
        }
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

            int minWeight = Helpers.calculateWeight(options.get(0));
            ArrayList<Vertex> bestPath = new ArrayList<>(options.get(0));
            for (ArrayList<Vertex> p : options) {
                int w = Helpers.calculateWeight(p);
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

    public static class NearestNeighbor {

        public static ArrayList<Vertex> nearestNeighborPath(Graph g, Vertex root) {
            ArrayList<Vertex> res = new ArrayList<Vertex>();
            res.add(root);

            HashSet<Vertex> notConnected = new HashSet<Vertex>();
            for (Vertex v : g.getVertices()) {
                if (v != root) notConnected.add(v);
            }

            int temp = notConnected.size(); // because notConnected changes size
            for (int i = 0; i < temp; i++) { 
                Vertex nN = nearestNeighbor(res.get(i), notConnected);
                res.add(nN);
                notConnected.remove(nN);
            }
            res.add(root);

            return res;
        }


        // returns the nearest vertex to root in notConnected
        private static Vertex nearestNeighbor(Vertex curr, HashSet<Vertex> notConnected) {
            ArrayList<Integer> relDist = new ArrayList<Integer>();
            HashMap<Integer, Vertex> eWeights = new HashMap<Integer, Vertex>();
        
            for (Edge e : curr.getEdges()) {
                if (notConnected.contains(e.getTo())) {
                    eWeights.put(e.getWeight(), e.getTo());
                    relDist.add(e.getWeight());
                }
            }
            Collections.sort(relDist);

            return eWeights.get(relDist.get(0));
        }
    }

    public static class Optimization {
        
        public static ArrayList<Vertex> randomSwapping(ArrayList<Vertex> path, int iterations) {
            ArrayList<Vertex> bestPath = new ArrayList<>(path);
            ArrayList<Vertex> testPath = new ArrayList<>(path);
            int minWeight = Helpers.calculateWeight(path);
            int count = 0;
            while (count < iterations) {
                int idx1 = ThreadLocalRandom.current().nextInt(1, testPath.size() - 1);
                int idx2 = ThreadLocalRandom.current().nextInt(1, testPath.size() - 1);
                Collections.swap(testPath, idx1, idx2);
                int weight = Helpers.calculateWeight(testPath);
                if (weight < minWeight) {
                    minWeight = weight;
                    bestPath = new ArrayList<>(testPath);
                } 
                
                testPath = new ArrayList<>(path);
                count++;
            }

            return bestPath;
        }
        
    }

    public class MinimumSpanningTree {

        private static ArrayList<String> getLabels(Graph g) {
            ArrayList<String> labels = new ArrayList<String>();
            for (Vertex v : g.getVertices()) {
                labels.add(v.getLabel());
            }
            return labels;
        }
  
        public static Graph primsAlgorithm(Graph g, Vertex root) {
            Graph mst = new Graph();
            mst.addVertex(0, new Vertex(root.getLabel()));
            while (mst.getVertices().size() < g.getVertices().size()) {
                int minWeight = Integer.MAX_VALUE;
                Vertex best = new Vertex("-1");
                Vertex from = new Vertex("-1");
                for (Vertex v : g.getVertices()) {
                    if (getLabels(mst).contains(v.getLabel())) {
                        for (Edge e : v.getEdges()) {
                            if (e.getWeight() < minWeight && !getLabels(mst).contains(e.getTo().getLabel())) {
                                minWeight = e.getWeight();
                                best = e.getTo();
                                from = v;
                            }
                        }
                    }
                }
                Vertex toAdd = new Vertex(best.getLabel());
                for (Vertex v : mst.getVertices()) {
                    if (v.getLabel() == from.getLabel()) {
                        v.addEdge(new Edge(toAdd, minWeight));
                        break;
                    } 
                }
                mst.addVertex(mst.getVertices().size(), toAdd);
            }
            return mst;
        }
    }
    
}




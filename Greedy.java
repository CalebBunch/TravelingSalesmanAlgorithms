import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;

public class Greedy {
    
    public static ArrayList<Vertex> greedyPath(Graph g, Vertex root) {
        HashSet<Edge> hashRes = new HashSet<Edge>();
        HashSet<Vertex> notFull = new HashSet<Vertex>();
        HashMap<Vertex, Integer> edgeNum = new HashMap<Vertex, Integer>();
        for (Vertex v : g.getVertices()) {
            notFull.add(v);
            edgeNum.put(v, 0);
        }
        
        int temp = notFull.size();
        for (int i = 0; i < temp; i++) {
            Edge gEdge = greedyEdge(g, notFull);
            hashRes.add(gEdge);

            // update + check edge degrees
            for (Vertex v : g.getVertices()) {
                if (v.getEdges().contains(gEdge)) {
                    edgeNum.put(v, edgeNum.get(v)+1);
                    edgeNum.put(gEdge.getTo(), edgeNum.get(gEdge.getTo())+1);
                }

                if (edgeNum.get(v) >= 2) notFull.remove(v);
                if (edgeNum.get(gEdge.getTo()) >= 2) notFull.remove(gEdge.getTo());
            }
        }

        ArrayList<Vertex> res = new ArrayList<Vertex>();
        res.add(root);
        for (int i = 0; i < temp; i++) {
            for (Edge e : res.get(i).getEdges()) {
                if (hashRes.contains(e)) {
                    res.add(e.getTo());
                }
            }
        }

        return res;
    }

    private static Edge greedyEdge(Graph g, HashSet<Vertex> notFull) {
        ArrayList<Integer> dist = new ArrayList<Integer>();
        HashMap<Integer, Edge> eWeights = new HashMap<Integer, Edge>();

        for (Vertex v : g.getVertices()) {
            for (Edge e : v.getEdges()) {
                if (notFull.contains(v) && notFull.contains(e.getTo())) {
                    dist.add(e.getWeight());
                    eWeights.put(e.getWeight(), e);
                }
            }
        }
        Collections.sort(dist);

        return eWeights.get(dist.get(0));
    }
}

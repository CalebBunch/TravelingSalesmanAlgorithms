import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithms {
    
    static Pair<int, List<Vertex>> bruteForce(Graph g) {
        Pair<int, List<Vertex>> best = new Pair<int, List<Vertex>>();
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        for (Vertex v : g.getVertices()) {
            
        }

        return best;
    } 

    private static List<List<Vertex>> bfs(Graph g, Vertex root) {
        Queue<Vertex> q = new LinkedList<Vertex>();
        q.add(root);
        while (q.size() != 0) {
            Vertex v = q.peek();
            q.remove(v);
            for (Vertex w : g.getVertices()) {
                
            }

        }
    }


}




import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Graph{

    private Set<Vertex> vertices;

    public Graph() {
        vertices = new HashSet<>();
    } 

    List<Vertex> getVertices() {
        return new ArrayList<>(vertices);
    }   

    boolean addVertex(Vertex vertex){
        return vertices.add(vertex);
    }
}




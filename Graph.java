import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Graph {

    private ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    } 

    List<Vertex> getVertices() {
        return vertices;
    }   

    void addVertex(int index, Vertex vertex){
        vertices.add(index, vertex);
    }
}




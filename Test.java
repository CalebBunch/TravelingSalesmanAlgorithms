import java.util.ArrayList;
import java.util.Collections;

class Test {
    boolean isSafe(int v, int graph[][], ArrayList<Integer> path, int pos) {
        // If the vertex is adjacent to the vertex of the previously added vertex
        if (graph[path.get(pos - 1)][v] == 0) {
            return false;
        }
        // If the vertex has already been included in the path
        for (int i = 0; i < pos; i++) {
            if (path.get(i) == v) {
                return false;
            }
        }
        // Both the above conditions are not true, return true
        return true;
    }
 
    // To check if there exists at least 1 hamiltonian cycle
    boolean hasCycle;
    // Function to find all possible hamiltonian cycles
    void hasCycle(int graph[][]) {
        // Initially value of boolean flag is false
        hasCycle = false;
 
        // Store the resultant path
        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);
 
        // Keeps the track of the visited vertices
        boolean[] visited = new boolean[graph.length];
 
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
 
        visited[0] = true;
 
        // Function call to find all hamiltonian cycles
        FindHamCycle(graph, 1, path, visited);
 
        if (!hasCycle) {
            // If no Hamiltonian Cycle is possible for the given graph
            System.out.println("No Hamiltonian Cycle" + "possible ");
            return;
        }
    }
 
    static int count;
    static ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    // Recursive function to find all hamiltonian cycles
    void FindHamCycle(int graph[][], int pos, ArrayList<Integer> path, boolean[] visited) {
        // If all vertices are include in Hamiltonian Cycle
        if (pos == graph.length) {
            // If there is an edge from the last vertex to the source vertex
            if (graph[path.get(path.size() - 1)][path.get(0)] != 0) {
                // Include source vertex into the path and print the path
                path.add(0);
                // System.out.println(path);
                //for (int i = 0; i < path.size(); i++) {
                //    System.out.print(path.get(i) + " ");
                //}
                ArrayList<Integer> newPath = new ArrayList<>(path);
                paths.add(newPath); 
                // System.out.println(path);
                
                //System.out.println();
                count++;
 
                // Remove the source vertex added
                path.remove(path.size() - 1);
 
                // Update the hasCycle as true
                hasCycle = true;
            }
            return;
        }

        // Try different vertices as the next vertex
        for (int v = 0; v < graph.length; v++) {
 
            // Check if this vertex can be added to Cycle
            if (isSafe(v, graph, path, pos) && !visited[v]) {
 
                path.add(v);
                visited[v] = true;
 
                // Recur to construct rest of the path
                FindHamCycle(graph, pos + 1, path, visited);
 
                // Remove current vertex from path and process other vertices
                visited[v] = false;
                path.remove(path.size() - 1);
            }
        }
    }
 
    public static void main(String args[]) {
        Test hamiltonian = new Test();
        int[][] graph = {
            { 0, 1, 1, 1, 1, 1 },
            { 1, 0, 1, 1, 1, 1 },
            { 1, 1, 0, 1, 1, 1 },
            { 1, 1, 1, 0, 1, 1 },
            { 1, 1, 1, 1, 0, 1 },
            { 1, 1, 1, 1, 1, 0 },
        };

        hamiltonian.hasCycle(graph);

        for (ArrayList<Integer> p : paths) {
            for (ArrayList<Integer> pp : paths) {
                if (pp != p) {
                    Collections.reverse(pp);
                    if (pp == p) {
                        paths.remove(pp);
                    }
                }
            }
        }
        for (ArrayList<Integer> p : paths) {
            for (Integer v : p) {
                System.out.print(v.toString() + " ");
            }
            System.out.println();
        }
        // System.out.println(count);
        System.out.println(paths.size());
    }
}

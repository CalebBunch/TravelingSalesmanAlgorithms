import java.util.ArrayList;
import java.util.Collections;

class Test {
    void hasCycle(int graph[][]) {
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
    }
 
    static ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    // Recursive function to find all hamiltonian cycles
    void FindHamCycle(int graph[][], int pos, ArrayList<Integer> path, boolean[] visited) {
        // If all vertices are include in Hamiltonian Cycle
        if (pos == graph.length) {
            // Include source vertex into the path and print the path
            path.add(0);
            ArrayList<Integer> newPath = new ArrayList<>(path);
            paths.add(newPath); 
 
            // Remove the source vertex added
            path.remove(path.size() - 1);
            return;
        }

        // Try different vertices as the next vertex
        for (int v = 0; v < graph.length; v++) {
            // Check if this vertex can be added to Cycle
            if (!visited[v]) {
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
        
        ArrayList<ArrayList<Integer>> original = new ArrayList<ArrayList<Integer>>();
        for (ArrayList<Integer> p : paths) {
            for (ArrayList<Integer> pp : paths) {
                if (!pp.equals(p)) {
                    Collections.reverse(pp);
                    if (pp.equals(p)) {
                        original.add(pp);
                    }
                }
            }
        }
        for (ArrayList<Integer> p : original) {
            for (Integer v : p) {
                System.out.print(v.toString() + " ");
            }
            System.out.println();
        }
        System.out.println(original.size());
    }
}

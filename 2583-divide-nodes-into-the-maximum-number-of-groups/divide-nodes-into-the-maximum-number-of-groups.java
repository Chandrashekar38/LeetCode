import java.util.*;

class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build adjacency list
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // Step 1: Find connected components using BFS
        int[] component = new int[n + 1];
        Arrays.fill(component, -1);
        List<List<Integer>> components = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (component[i] == -1) {
                List<Integer> comp = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                component[i] = components.size();
                comp.add(i);

                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    for (int neighbor : graph.get(node)) {
                        if (component[neighbor] == -1) {
                            component[neighbor] = components.size();
                            comp.add(neighbor);
                            queue.offer(neighbor);
                        }
                    }
                }
                components.add(comp);
            }
        }

        // Step 2: Check for bipartiteness & Find the maximum depth
        int result = 0;
        for (List<Integer> comp : components) {
            int maxDepth = 0;
            Map<Integer, Integer> color = new HashMap<>();

            // Bipartiteness check using BFS (2-coloring)
            for (int node : comp) {
                if (!color.containsKey(node)) {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.offer(node);
                    color.put(node, 0);

                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (int neighbor : graph.get(curr)) {
                            if (!color.containsKey(neighbor)) {
                                color.put(neighbor, 1 - color.get(curr));
                                queue.offer(neighbor);
                            } else if (color.get(neighbor) == color.get(curr)) {
                                return -1; // Not bipartite
                            }
                        }
                    }
                }
            }

            // Step 3: Find the maximum BFS depth in the bipartite component
            for (int node : comp) {
                maxDepth = Math.max(maxDepth, bfsDepth(graph, node, n));
            }
            result += maxDepth;
        }
        return result;
    }

    // BFS to find the maximum depth of the component
    private int bfsDepth(List<List<Integer>> graph, int start, int n) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> depth = new HashMap<>();
        queue.offer(start);
        depth.put(start, 1);
        int maxDepth = 1;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!depth.containsKey(neighbor)) {
                    depth.put(neighbor, depth.get(node) + 1);
                    maxDepth = Math.max(maxDepth, depth.get(neighbor));
                    queue.offer(neighbor);
                }
            }
        }
        return maxDepth;
    }
}

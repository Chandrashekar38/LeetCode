import java.util.*;

class Solution {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        
        // Calculate indegree for each node
        for (int i = 0; i < n; i++) {
            indegree[favorite[i]]++;
        }
        
        // First find all nodes with no incoming edges
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Maximum length to each node
        int[] maxLength = new int[n];
        Arrays.fill(maxLength, 1);
        
        // Process nodes with no incoming edges first
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int next = favorite[curr];
            maxLength[next] = Math.max(maxLength[next], maxLength[curr] + 1);
            indegree[next]--;
            if (indegree[next] == 0) {
                queue.offer(next);
            }
        }
        
        // Now handle the cycles
        int maxCycle = 0;
        int sumChains = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i] && indegree[i] > 0) {
                int size = 0;
                int curr = i;
                while (!visited[curr]) {
                    visited[curr] = true;
                    size++;
                    curr = favorite[curr];
                }
                
                // If it's a 2-cycle (mutual pair)
                if (size == 2 && favorite[favorite[i]] == i) {
                    sumChains += maxLength[i] + maxLength[favorite[i]];
                } else {
                    maxCycle = Math.max(maxCycle, size);
                }
            }
        }
        
        return Math.max(maxCycle, sumChains);
    }
}
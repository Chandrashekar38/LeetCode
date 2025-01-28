class Solution {
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxFish = 0;
        
        // Create a visited array to track visited cells
        boolean[][] visited = new boolean[m][n];
        
        // Iterate over all cells in the grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    // Start DFS from this water cell and update maxFish
                    maxFish = Math.max(maxFish, dfs(grid, visited, i, j));
                }
            }
        }
        
        return maxFish;
    }
    
    // DFS function to collect fish from connected water cells
    private int dfs(int[][] grid, boolean[][] visited, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Base case: check boundaries and if the cell is already visited or is land
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == 0) {
            return 0;
        }
        
        // Mark the cell as visited and collect the fish
        visited[r][c] = true;
        int fishCount = grid[r][c];
        
        // Explore all four possible directions
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] dir : directions) {
            fishCount += dfs(grid, visited, r + dir[0], c + dir[1]);
        }
        
        return fishCount;
    }
}

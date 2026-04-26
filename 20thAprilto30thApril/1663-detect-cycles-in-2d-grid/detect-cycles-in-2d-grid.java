class Solution {
    // Direction vectors for moving up, down, left, right
    private final int[] dRow = {-1, 1, 0, 0};
    private final int[] dCol = {0, 0, -1, 1};

    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        // Check every unvisited cell as a potential starting point for a component
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    // Start DFS. Pass -1, -1 as the initial parent coordinates.
                    if (dfs(grid, visited, i, j, -1, -1, grid[i][j])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    private boolean dfs(char[][] grid, boolean[][] visited, int r, int c, int pr, int pc, char target) {
        visited[r][c] = true;

        // Explore all 4 adjacent directions
        for (int i = 0; i < 4; i++) {
            int nr = r + dRow[i];
            int nc = c + dCol[i];

            // Check boundaries and if the neighbor matches our target character
            if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length && grid[nr][nc] == target) {
                
                if (!visited[nr][nc]) {
                    // If not visited, recursively visit it. Update current cell as parent.
                    if (dfs(grid, visited, nr, nc, r, c, target)) {
                        return true;
                    }
                } else if (nr != pr || nc != pc) {
                    // If it IS visited, but it is NOT the parent we just came from, we found a cycle.
                    return true;
                }
            }
        }

        return false;
    }
}
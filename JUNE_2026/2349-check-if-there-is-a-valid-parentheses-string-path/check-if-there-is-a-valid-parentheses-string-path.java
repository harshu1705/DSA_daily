class Solution {

    int m, n;
    int[][][] dp;

    public boolean hasValidPath(char[][] grid) {

        m = grid.length;
        n = grid[0].length;

        int length = m + n - 1;

        // Valid parentheses string must have even length
        if (length % 2 != 0) {
            return false;
        }

        dp = new int[m][n][length + 1];

        // -1 = not calculated
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= length; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return solve(0, 0, 0, grid);
    }

    private boolean solve(int i, int j, int openCount, char[][] grid) {

        // Process current cell
        if (grid[i][j] == '(') {
            openCount++;
        } else {
            openCount--;
        }

        // Invalid
        if (openCount < 0) {
            return false;
        }

        // Already calculated
        if (dp[i][j][openCount] != -1) {
            return dp[i][j][openCount] == 1;
        }

        // Destination
        if (i == m - 1 && j == n - 1) {

            dp[i][j][openCount] = (openCount == 0) ? 1 : 0;

            return openCount == 0;
        }

        // DOWN
        if (i + 1 < m) {
            if (solve(i + 1, j, openCount, grid)) {

                dp[i][j][openCount] = 1;

                return true;
            }
        }

        // RIGHT
        if (j + 1 < n) {
            if (solve(i, j + 1, openCount, grid)) {

                dp[i][j][openCount] = 1;

                return true;
            }
        }

        // No valid path
        dp[i][j][openCount] = 0;

        return false;
    }
}
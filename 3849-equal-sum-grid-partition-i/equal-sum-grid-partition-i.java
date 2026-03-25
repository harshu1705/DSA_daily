class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long total = 0;

        // Step 1: total sum
        for (int[] row : grid) {
            for (int val : row) {
                total += val;
            }
        }

        // Step 2: if odd → impossible
        if (total % 2 != 0) return false;

        // Step 3: Try horizontal cuts
        long rowSum = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < m; j++) {
                rowSum += grid[i][j];
            }
            if (rowSum == total - rowSum) return true;
        }

        // Step 4: Try vertical cuts
        long colSum = 0;
        for (int j = 0; j < m - 1; j++) {
            for (int i = 0; i < n; i++) {
                colSum += grid[i][j];
            }
            if (colSum == total - colSum) return true;
        }

        return false;
    }
}
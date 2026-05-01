import java.util.Arrays;

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        
        // colSum[c][r] is the sum of grid cells from row 0 to r-1 in column c
        long[][] colSum = new long[n][n + 1];
        for (int c = 0; c < n; c++) {
            for (int r = 0; r < n; r++) {
                colSum[c][r + 1] = colSum[c][r] + grid[r][c];
            }
        }

        // dp[i % 2][u][v] = max score for prefix up to col i, given h_i = u and h_{i+1} = v
        // We only need the current and previous column states, so size is 2 instead of n
        long[][][] dp = new long[2][n + 1][n + 1];

        // Base case: process column 0
        // It has no left neighbor, so we assume h_{-1} = 0
        for (int u = 0; u <= n; u++) {
            for (int v = 0; v <= n; v++) {
                long score = 0;
                int limit = Math.max(0, v); // max(h_{-1}, h_1)
                if (limit > u) {
                    score = colSum[0][limit] - colSum[0][u];
                }
                dp[0][u][v] = score;
            }
        }

        // DP transitions for columns 1 to n-1
        for (int i = 1; i < n; i++) {
            int curr = i % 2;
            int prev = (i - 1) % 2;

            for (int u = 0; u <= n; u++) {
                
                // OPTIMIZATION: Precompute prefix and suffix maximums over t (which represents h_{i-1})
                // This reduces the inner transition from O(N) to O(1), making overall time O(N^3)
                long[] prefMax = new long[n + 1];
                long maxSoFar = -1;
                for (int t = 0; t <= n; t++) {
                    maxSoFar = Math.max(maxSoFar, dp[prev][t][u]);
                    prefMax[t] = maxSoFar;
                }

                long[] suffMax = new long[n + 2];
                Arrays.fill(suffMax, -1);
                maxSoFar = -1;
                for (int t = n; t >= 0; t--) {
                    long val = dp[prev][t][u];
                    if (t > u) {
                        val += colSum[i][t] - colSum[i][u];
                    }
                    maxSoFar = Math.max(maxSoFar, val);
                    suffMax[t] = maxSoFar;
                }

                // Calculate DP for the current column
                for (int v = 0; v <= n; v++) {
                    // Case 1: t <= v (limit is dictated by right neighbor v)
                    long ans1 = prefMax[v];
                    if (v > u) {
                        ans1 += colSum[i][v] - colSum[i][u];
                    }
                    
                    // Case 2: t > v (limit is dictated by left neighbor t)
                    long ans2 = suffMax[v + 1];

                    dp[curr][u][v] = Math.max(ans1, ans2);
                }
            }
        }

        // The answer is the maximum score at the last column.
        // For the last column (n-1), its non-existent right neighbor has height 0 (v = 0).
        long maxScore = 0;
        int last = (n - 1) % 2;
        for (int u = 0; u <= n; u++) {
            maxScore = Math.max(maxScore, dp[last][u][0]);
        }

        return maxScore;
    }
}
class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;
        
        // dp[i][j][k] means max coins at (i, j) using AT MOST k neutralizations
        int[][][] dp = new int[m][n][3];
        
        // A safely small value to prevent underflow when adding negative numbers
        int MIN_INF = -1_000_000_000; 

        // Initialize DP table with negative infinity
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][0] = MIN_INF;
                dp[i][j][1] = MIN_INF;
                dp[i][j][2] = MIN_INF;
            }
        }

        // Base case: Starting at (0, 0)
        dp[0][0][0] = coins[0][0];
        dp[0][0][1] = coins[0][0] < 0 ? 0 : coins[0][0];
        dp[0][0][2] = coins[0][0] < 0 ? 0 : coins[0][0];

        // Fill the DP table
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Skip the starting cell as it's already initialized
                if (i == 0 && j == 0) continue;

                for (int k = 0; k <= 2; k++) {
                    // 1. Calculate the max value coming from Top or Left WITHOUT using a new ability here
                    int maxPrev = MIN_INF;
                    if (i > 0) maxPrev = Math.max(maxPrev, dp[i - 1][j][k]);
                    if (j > 0) maxPrev = Math.max(maxPrev, dp[i][j - 1][k]);

                    if (maxPrev != MIN_INF) {
                        dp[i][j][k] = maxPrev + coins[i][j];
                    }

                    // 2. If it's a robber and we have abilities left, consider neutralizing it
                    if (coins[i][j] < 0 && k > 0) {
                        int maxPrevAbility = MIN_INF;
                        if (i > 0) maxPrevAbility = Math.max(maxPrevAbility, dp[i - 1][j][k - 1]);
                        if (j > 0) maxPrevAbility = Math.max(maxPrevAbility, dp[i][j - 1][k - 1]);

                        if (maxPrevAbility != MIN_INF) {
                            // Maximize between accepting the penalty vs neutralizing it (+ 0)
                            dp[i][j][k] = Math.max(dp[i][j][k], maxPrevAbility);
                        }
                    }
                }
            }
        }

        // The answer is at the bottom-right corner, having used at most 2 abilities
        return dp[m - 1][n - 1][2];
    }
}
class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        if (n == 0) return 0;
        
        // dp[i][M] will store the max stones a player can get starting at index i with parameter M
        int[][] dp = new int[n][n + 1];
        
        // suffixSum[i] stores the total stones from index i to the end
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        return dfs(0, 1, piles, suffixSum, dp);
    }
    
    private int dfs(int i, int m, int[] piles, int[] suffixSum, int[][] dp) {
        int n = piles.length;
        
        // If we've exhausted all piles
        if (i >= n) {
            return 0;
        }
        
        // If we can take all the remaining piles, we take them all
        if (i + 2 * m >= n) {
            return suffixSum[i];
        }
        
        // If we have already calculated the result for this state, return it
        if (dp[i][m] != 0) {
            return dp[i][m];
        }
        
        int maxStones = 0;
        
        // We can take X piles, where 1 <= X <= 2 * M
        for (int x = 1; x <= 2 * m; x++) {
            // The opponent's best score from the remaining piles
            int opponentBest = dfs(i + x, Math.max(m, x), piles, suffixSum, dp);
            
            // Our score is the total remaining stones minus the opponent's best possible score
            int myScore = suffixSum[i] - opponentBest;
            
            maxStones = Math.max(maxStones, myScore);
        }
        
        // Memoize the result
        dp[i][m] = maxStones;
        
        return maxStones;
    }
}
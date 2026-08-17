class Solution {
    private int[][] memo;
    private int[] prefix;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        memo = new int[n][n];
        prefix = new int[n + 1];

        // Build prefix sum array for O(1) range sum queries
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        // Base case: Only 1 stone remaining, game ends
        if (i == j) {
            return 0;
        }
        
        // Return already calculated result to save time
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int maxScore = 0;

        // Try all possible split points
        for (int k = i; k < j; k++) {
            int leftSum = prefix[k + 1] - prefix[i];
            int rightSum = prefix[j + 1] - prefix[k + 1];

            if (leftSum < rightSum) {
                // Bob throws away the right part (larger sum)
                maxScore = Math.max(maxScore, leftSum + dfs(i, k));
            } else if (leftSum > rightSum) {
                // Bob throws away the left part (larger sum)
                maxScore = Math.max(maxScore, rightSum + dfs(k + 1, j));
            } else {
                // Sums are equal, Alice chooses the path that yields the max total score
                maxScore = Math.max(maxScore, leftSum + Math.max(dfs(i, k), dfs(k + 1, j)));
            }
        }

        // Memoize and return the best outcome for range [i, j]
        return memo[i][j] = maxScore;
    }
}
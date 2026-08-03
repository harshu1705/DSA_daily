class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] represents the max score difference the current player 
        // can get starting from index i
        int[] dp = new int[n + 1];
        
        // Traverse the array backwards
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int currentTake = 0;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3; k++) {
                if (i + k - 1 < n) {
                    currentTake += stoneValue[i + k - 1];
                    // The score difference is what we take MINUS the opponent's best future difference
                    dp[i] = Math.max(dp[i], currentTake - dp[i + k]);
                }
            }
        }
        
        // Alice starts at index 0
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
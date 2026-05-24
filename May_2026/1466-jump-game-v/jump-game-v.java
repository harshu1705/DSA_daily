class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int maxResult = 1;
        
        // Calculate the maximum jumps starting from ANY index
        for (int i = 0; i < n; i++) {
            maxResult = Math.max(maxResult, dfs(arr, d, i, dp));
        }
        
        return maxResult;
    }
    
    private int dfs(int[] arr, int d, int i, int[] dp) {
        // If already computed, return the cached result
        if (dp[i] != 0) {
            return dp[i];
        }
        
        int maxJumps = 1; // You can always visit the current index itself
        int n = arr.length;
        
        // 1. Look Right
        for (int j = i + 1; j <= Math.min(i + d, n - 1); j++) {
            if (arr[j] >= arr[i]) {
                break; // Blocked by a taller or equal building
            }
            maxJumps = Math.max(maxJumps, 1 + dfs(arr, d, j, dp));
        }
        
        // 2. Look Left
        for (int j = i - 1; j >= Math.max(i - d, 0); j--) {
            if (arr[j] >= arr[i]) {
                break; // Blocked by a taller or equal building
            }
            maxJumps = Math.max(maxJumps, 1 + dfs(arr, d, j, dp));
        }
        
        // Memoize and return
        dp[i] = maxJumps;
        return maxJumps;
    }
}
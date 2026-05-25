class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        
        // Quick optimization: If the last character is '1', we can never reach it.
        if (s.charAt(n - 1) == '1') {
            return false;
        }
        
        // dp[i] will be true if we can reach index i
        boolean[] dp = new boolean[n];
        dp[0] = true;
        
        int reachableCount = 0;
        
        for (int i = 1; i < n; i++) {
            // 1. Add the element that just entered our valid jump window
            if (i >= minJump && dp[i - minJump]) {
                reachableCount++;
            }
            
            // 2. Remove the element that just exited our valid jump window
            if (i > maxJump && dp[i - maxJump - 1]) {
                reachableCount--;
            }
            
            // 3. If there is at least one reachable spot behind us, and current is '0'
            if (reachableCount > 0 && s.charAt(i) == '0') {
                dp[i] = true;
            }
        }
        
        return dp[n - 1];
    }
}
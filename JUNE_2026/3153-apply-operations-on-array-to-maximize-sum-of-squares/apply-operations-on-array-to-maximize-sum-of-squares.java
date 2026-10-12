import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007;
        int[] bitCounts = new int[30];
        
        // Count the frequency of each bit across all numbers
        for (int x : nums) {
            for (int b = 0; b < 30; b++) {
                if (((x >> b) & 1) == 1) {
                    bitCounts[b]++;
                }
            }
        }
        
        // Construct the k largest possible numbers
        long[] resNums = new long[k];
        for (int b = 29; b >= 0; b--) {
            int cnt = bitCounts[b];
            for (int i = 0; i < Math.min(cnt, k); i++) {
                resNums[i] |= (1L << b);
            }
        }
        
        // Calculate the sum of squares modulo 10^9 + 7
        long ans = 0;
        for (long x : resNums) {
            ans = (ans + (x * x) % MOD) % MOD;
        }
        
        return (int) ans;
    }
}
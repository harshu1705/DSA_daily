import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        // Total possible subarrays = N * (N + 1) / 2
        // Use long to prevent integer overflow during multiplication
        long totalSubarrays = (long) n * (n + 1) / 2;
        
        // Find the absolute min and max of the array to set binary search bounds
        int minVal = nums[0];
        int maxVal = nums[0];
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        
        // Trick: Using an infinitely large V yields the total sum of ALL subarrays.
        // 2 billion is larger than any possible max difference (10^9)
        long[] totalStats = countAndSumLess(nums, 2_000_000_000L);
        long sTotal = totalStats[1];
        
        long left = 0;
        long right = maxVal - minVal;
        long vTh = 0;
        
        // Binary search to find the value of the K-th largest subarray
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long[] stats = countAndSumLess(nums, mid);
            long cntLess = stats[0];
            long cntGeq = totalSubarrays - cntLess;
            
            // If there are at least k subarrays with value >= mid, search higher
            if (cntGeq >= k) {
                vTh = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // Calculate exactly how many strictly exceed vTh to avoid over-counting ties
        long[] lessStrictStats = countAndSumLess(nums, vTh + 1);
        long cntLessStrict = lessStrictStats[0];
        long sumLessStrict = lessStrictStats[1];
        
        long cntGeqStrict = totalSubarrays - cntLessStrict;
        long sumGeqStrict = sTotal - sumLessStrict;
        
        // Final Answer = (Sum of strictly greater) + (Remaining padded with exact V_th matches)
        return sumGeqStrict + (k - cntGeqStrict) * vTh;
    }
    
    /**
     * Helper method to count subarrays with (max - min) < V 
     * and calculate their total sum.
     * * @return A long array where index 0 is the count, and index 1 is the sum.
     */
    private long[] countAndSumLess(int[] nums, long V) {
        int n = nums.length;
        
        // Monotonic deques storing arrays of {value, count}
        Deque<int[]> maxQ = new ArrayDeque<>();
        Deque<int[]> minQ = new ArrayDeque<>();
        
        long sumMax = 0;
        long sumMin = 0;
        
        long totalCount = 0;
        long totalSum = 0;
        int L = 0;
        
        for (int R = 0; R < n; R++) {
            int val = nums[R];
            
            // 1. Update the monotonic max queue
            int cMax = 1;
            while (!maxQ.isEmpty() && maxQ.peekLast()[0] <= val) {
                int[] p = maxQ.pollLast();
                sumMax -= (long) p[0] * p[1];
                cMax += p[1];
            }
            maxQ.addLast(new int[]{val, cMax});
            sumMax += (long) val * cMax;
            
            // 2. Update the monotonic min queue
            int cMin = 1;
            while (!minQ.isEmpty() && minQ.peekLast()[0] >= val) {
                int[] p = minQ.pollLast();
                sumMin -= (long) p[0] * p[1];
                cMin += p[1];
            }
            minQ.addLast(new int[]{val, cMin});
            sumMin += (long) val * cMin;
            
            // 3. Shrink window from the left while max - min >= V
            while (!maxQ.isEmpty() && !minQ.isEmpty() && (long) maxQ.peekFirst()[0] - minQ.peekFirst()[0] >= V) {
                int[] firstMax = maxQ.peekFirst();
                firstMax[1]--;
                sumMax -= firstMax[0];
                if (firstMax[1] == 0) {
                    maxQ.pollFirst();
                }
                
                int[] firstMin = minQ.peekFirst();
                firstMin[1]--;
                sumMin -= firstMin[0];
                if (firstMin[1] == 0) {
                    minQ.pollFirst();
                }
                L++;
            }
            
            // 4. Accumulate counts and sums for all valid starting points
            totalCount += (R - L + 1);
            totalSum += (sumMax - sumMin);
        }
        
        return new long[]{totalCount, totalSum};
    }
}
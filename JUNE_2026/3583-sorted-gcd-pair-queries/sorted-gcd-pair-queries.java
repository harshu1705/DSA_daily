class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        // 1. Find the maximum value to size our arrays
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        // 2. Build frequency map
        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }
        
        // exactGcd[g] will store the number of pairs with GCD exactly equal to g
        // We use long because the number of pairs can exceed Integer.MAX_VALUE
        long[] exactGcd = new long[maxVal + 1];
        
        // 3. Iterate backward to compute exact_gcd using multiples
        for (int g = maxVal; g >= 1; g--) {
            long multCount = 0;
            
            // Count how many numbers in nums are multiples of g
            for (int m = g; m <= maxVal; m += g) {
                multCount += count[m];
            }
            
            // Total pairs where both numbers are multiples of g
            long pairs = multCount * (multCount - 1) / 2;
            
            // Subtract pairs where the exact GCD is a strictly larger multiple of g
            for (int m = 2 * g; m <= maxVal; m += g) {
                pairs -= exactGcd[m];
            }
            
            exactGcd[g] = pairs;
        }
        
        // 4. Compute prefix sums for binary searching the queries
        long[] prefix = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefix[i] = prefix[i - 1] + exactGcd[i];
        }
        
        // 5. Answer each query using binary search
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            
            // Custom binary search to find the first index where cumulative count exceeds q
            int left = 1;
            int right = maxVal;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] <= q) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            ans[i] = left;
        }
        
        return ans;
    }
}
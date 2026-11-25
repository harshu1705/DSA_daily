import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 131_542_391L;
    private static final long BASE2 = 265_443_576L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(nums, mid)) {
                ans = mid;
                right = mid - 1; // Try to find an even smaller length
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);

        long h1 = 0, h2 = 0;
        long pow1 = 1, pow2 = 1;

        // Precompute BASE^(len - 1) % MOD
        for (int i = 0; i < len - 1; i++) {
            pow1 = (pow1 * BASE1) % MOD1;
            pow2 = (pow2 * BASE2) % MOD2;
        }

        // Initial window [0 .. len - 1]
        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }
        freq.put((h1 << 32) | h2, 1);

        // Slide the window across the array
        for (int i = len; i < n; i++) {
            int out = nums[i - len];
            int in = nums[i];

            h1 = (h1 - (out * pow1) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + in) % MOD1;

            h2 = (h2 - (out * pow2) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + in) % MOD2;

            long key = (h1 << 32) | h2;
            freq.put(key, freq.getOrDefault(key, 0) + 1);
        }

        // Check if any subarray of this length appeared exactly once
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}

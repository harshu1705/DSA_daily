
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;

        int[] diff = new int[n];
        int maxDiff = 0;
        long totalDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
            totalDiff += diff[i];
        }

        // All differences can become zero
        if (k >= totalDiff) {
            return 0L;
        }

        // Find the minimum achievable maximum difference
        int low = 0;
        int high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int target = low;
        long used = 0;
        long answer = 0;

        // Reduce every difference to at most target
        for (int d : diff) {
            int reduced = Math.min(d, target);
            used += d - reduced;
            answer += (long) reduced * reduced;
        }

        // Spend remaining operations on values equal to target
        long remaining = k - used;
        answer -= remaining * (2L * target - 1);

        return answer;
    }
}

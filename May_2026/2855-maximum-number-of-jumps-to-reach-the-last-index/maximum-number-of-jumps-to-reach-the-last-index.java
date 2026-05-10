class Solution {

    int[] dp;

    public int solve(int i, int[] nums, int target) {

        // reached last index
        if (i == nums.length - 1) {
            return 0;
        }

        // already calculated
        if (dp[i] != -2) {
            return dp[i];
        }

        int maxJumps = -1;

        // try all possible next jumps
        for (int j = i + 1; j < nums.length; j++) {

            // valid jump
            if (Math.abs(nums[j] - nums[i]) <= target) {

                int next = solve(j, nums, target);

                // if path exists
                if (next != -1) {
                    maxJumps = Math.max(maxJumps, 1 + next);
                }
            }
        }

        // store answer
        dp[i] = maxJumps;

        return dp[i];
    }

    public int maximumJumps(int[] nums, int target) {

        int n = nums.length;

        dp = new int[n];

        // -2 means not calculated yet
        Arrays.fill(dp, -2);

        return solve(0, nums, target);
    }
}
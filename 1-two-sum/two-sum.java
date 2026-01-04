class Solution {
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;   // ✅ length is a property, not a method
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j}; // ✅ return indices
                }
            }
        }
        return new int[]{};  // ✅ return empty array if no solution
    }
}
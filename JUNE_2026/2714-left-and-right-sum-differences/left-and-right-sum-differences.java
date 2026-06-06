class Solution {
    public int[] leftRightDifference(int[] nums) {

        int n = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int[] result = new int[n];
        int curr = 0;

        for (int i = 0; i < n; i++) {

            int leftSum = curr;

            curr += nums[i];

            int rightSum = sum - curr;

            result[i] = Math.abs(rightSum - leftSum);
        }

        return result;
    }
}
class Solution {
    public boolean isGood(int[] nums) {

        if(nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);

        int n = nums.length;

        // Last two elements must be same
        if(nums[n-1] != nums[n-2]) {
            return false;
        }

        // Check 1,2,3,... pattern
        for(int i = 0; i < n-1; i++) {

            if(nums[i] != i + 1) {
                return false;
            }
        }

        return true;
    }
}
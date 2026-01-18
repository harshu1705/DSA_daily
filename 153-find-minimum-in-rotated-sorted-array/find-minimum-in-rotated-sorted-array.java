class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            // if already sorted
            if (nums[low] <= nums[high]) {
                return nums[low];
            }

            int mid = low + (high - low) / 2;

            // minimum is in right half
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            }
            // minimum is in left half (including mid)
            else {
                high = mid;
            }
        }

        return nums[low];
    }
}

class Solution {
    public int[] pivotArray(int[] nums, int pivot) {

        int n = nums.length;
        int[] result = new int[n];

        int idx = 0;

        // Add elements smaller than pivot
        for (int num : nums) {
            if (num < pivot) {
                result[idx++] = num;
            }
        }

        // Add elements equal to pivot
        for (int num : nums) {
            if (num == pivot) {
                result[idx++] = num;
            }
        }

        // Add elements greater than pivot
        for (int num : nums) {
            if (num > pivot) {
                result[idx++] = num;
            }
        }

        return result;
    }
}
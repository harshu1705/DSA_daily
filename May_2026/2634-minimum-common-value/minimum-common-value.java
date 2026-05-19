class Solution {

    public int getCommon(int[] nums1, int[] nums2) {

        // Traverse nums1
        for(int num : nums1) {

            // Search current element in nums2
            if(binarySearch(nums2, num)) {
                return num;
            }
        }

        // No common element
        return -1;
    }

    public boolean binarySearch(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;

        while(low <= high) {

            int mid = low + (high - low) / 2;

            // Element found
            if(arr[mid] == target) {
                return true;
            }

            // Search right half
            else if(arr[mid] < target) {
                low = mid + 1;
            }

            // Search left half
            else {
                high = mid - 1;
            }
        }

        return false;
    }
}
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure we always binary search on the smaller array to prevent out-of-bounds errors
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int m = nums1.length;
        int n = nums2.length;
        
        // Binary search bounds for the smaller array
        int low = 0;
        int high = m;
        
        while (low <= high) {
            // Partition index for nums1
            int partitionX = low + (high - low) / 2;
            // Partition index for nums2 (ensures left half has equal or one more element than right half)
            int partitionY = (m + n + 1) / 2 - partitionX;
            
            // Edge cases for when partitions fall on the extreme ends of the arrays
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];
            
            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];
            
            // Check if we have found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If the total length is odd, the median is the maximum of the left halves
                if ((m + n) % 2 != 0) {
                    return Math.max(maxLeftX, maxLeftY);
                } 
                // If the total length is even, average the max of the left halves and the min of the right halves
                else {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                }
            } 
            // If we are too far to the right in nums1, move left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // If we are too far to the left in nums1, move right
            else {
                low = partitionX + 1;
            }
        }
        
        throw new IllegalArgumentException("Input arrays are not sorted or are invalid.");
    }
}
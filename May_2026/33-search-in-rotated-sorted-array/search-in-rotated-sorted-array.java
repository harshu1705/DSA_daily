class Solution {

    public int search(int[] nums, int target) {
        int n = nums.length;

        int pivot = findPivot(nums);

        // right sorted part
        if (target >= nums[pivot] && target <= nums[n - 1]) {
            return binarySearch(nums, pivot, n - 1, target);
        }

        // left sorted part
        return binarySearch(nums, 0, pivot - 1, target);
    }

    private int findPivot(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            if (arr[low] <= arr[high]) return low;

            int mid = low + (high - low) / 2;
            int prev = (mid - 1 + n) % n;
            int next = (mid + 1) % n;

            if (arr[mid] <= arr[prev] && arr[mid] <= arr[next]) {
                return mid;
            }

            if (arr[low] <= arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}

import java.util.Arrays;

class Solution {

    public boolean check(int[] nums) {

        int n = nums.length;

        // Create sorted version
        int[] sorted = nums.clone();

        Arrays.sort(sorted);

        // Try every rotation
        for (int r = 0; r < n; r++) {

            boolean isSorted = true;

            for (int i = 0; i < n; i++) {

                // Compare sorted with rotated nums
                if (sorted[i] != nums[(i + r) % n]) {

                    isSorted = false;
                    break;
                }
            }

            if (isSorted) {
                return true;
            }
        }

        return false;
    }
}
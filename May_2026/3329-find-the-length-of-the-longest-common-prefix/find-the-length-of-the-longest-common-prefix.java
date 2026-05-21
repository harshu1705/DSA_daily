import java.util.HashSet;

class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        HashSet<Integer> prefixes = new HashSet<>();
        
        // Step 1: Store all possible prefixes from arr1
        for (int val : arr1) {
            while (val > 0) {
                prefixes.add(val);
                val /= 10;
            }
        }
        
        int maxLength = 0;
        
        // Step 2: Check prefixes from arr2 against the set
        for (int val : arr2) {
            // Keep dividing until we find a match or hit 0
            while (val > 0 && !prefixes.contains(val)) {
                val /= 10;
            }
            
            // If a match is found, update the maximum length
            if (val > 0) {
                int currentLength = (int) Math.log10(val) + 1;
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        
        return maxLength;
    }
}
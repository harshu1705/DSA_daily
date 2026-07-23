import java.util.ArrayList;
import java.util.List;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int total1s = 0;
        int current0s = 0; // Fixed the variable name here
        List<Integer> blocks = new ArrayList<>();
        
        // Count total '1's and extract the lengths of contiguous '0' blocks
        for (char c : s.toCharArray()) {
            if (c == '1') {
                total1s++;
                if (current0s > 0) {
                    blocks.add(current0s);
                    current0s = 0;
                }
            } else {
                current0s++;
            }
        }
        
        // Don't forget to add the last block of '0's if the string ends with '0'
        if (current0s > 0) {
            blocks.add(current0s);
        }

        // If there are less than 2 blocks of '0's, no trade can be made
        if (blocks.size() < 2) {
            return total1s;
        }

        // Find the maximum sum of two adjacent '0' blocks
        int maxGain = 0;
        for (int i = 0; i < blocks.size() - 1; i++) {
            maxGain = Math.max(maxGain, blocks.get(i) + blocks.get(i + 1));
        }

        return total1s + maxGain;
    }
}
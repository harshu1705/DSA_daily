import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {

        // Store frequency of each character
        int[] freq = new int[26];

        // Count frequency
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Sort in ascending order
        Arrays.sort(freq);

        int result = 0;
        int index = 0;

        // Traverse from highest frequency
        for (int i = 25; i >= 0; i--) {

            // No more characters
            if (freq[i] == 0)
                break;

            // First 8 letters -> 1 push
            // Next 8 letters -> 2 pushes
            // Next 8 letters -> 3 pushes
            int presses = index / 8 + 1;

            result += presses * freq[i];

            index++;
        }

        return result;
    }
}
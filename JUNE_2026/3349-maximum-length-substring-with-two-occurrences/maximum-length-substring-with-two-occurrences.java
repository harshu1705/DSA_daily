class Solution {
    public int maximumLengthSubstring(String s) {
        int result = 0;
        int left = 0;
        int[] freq = new int[26];
        char[] ch = s.toCharArray();

        // Expand the window by moving the right pointer
        for (int right = 0; right < ch.length; right++) {
            freq[ch[right] - 'a']++;

            // If the current character appears more than twice, 
            // shrink the window from the left until it's valid again
            while (freq[ch[right] - 'a'] > 2) {
                freq[ch[left] - 'a']--;
                left++;
            }

            // Update the maximum length found so far
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
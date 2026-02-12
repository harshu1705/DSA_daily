class Solution {
    public int longestBalanced(String s) {
        int maxlen = 0;

        for (int left = 0; left < s.length(); left++) {

            int[] freq = new int[26];
            int distinct = 0;
            int maxFreq = 0;

            for (int right = left; right < s.length(); right++) {

                int idx = s.charAt(right) - 'a';

                if (freq[idx] == 0) {
                    distinct++;
                }

                freq[idx]++;
                maxFreq = Math.max(maxFreq, freq[idx]);

                int length = right - left + 1;

                if (length == distinct * maxFreq) {
                    maxlen = Math.max(maxlen, length);
                }
            }
        }

        return maxlen;
    }
}
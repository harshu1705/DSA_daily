class Solution {
    public int[] validSequence(String word1, String word2) {
        // Converting to byte arrays for fast indexed access without substring/charAt overhead.
        byte[] w1 = word1.getBytes();
        byte[] w2 = word2.getBytes();
        int s1 = w1.length;
        int s2 = w2.length;

        // maxSuffixLen[i] stores the max length of a suffix of word2 that is 
        // a perfect subsequence of word1[i...s1-1].
        int[] maxSuffixLen = new int[s1 + 1];
        int p2 = s2 - 1;
        
        for (int p1 = s1 - 1; p1 >= 0; p1--) {
            if (p2 >= 0 && w1[p1] == w2[p2]) {
                maxSuffixLen[p1] = maxSuffixLen[p1 + 1] + 1;
                p2--;
            } else {
                maxSuffixLen[p1] = maxSuffixLen[p1 + 1];
            }
        }

        boolean changed = false;
        int p1 = 0;
        int[] ans = new int[s2];
        int ansIdx = 0;

        for (int i = 0; i < s2; i++) {
            boolean matched = false;
            
            while (p1 < s1) {
                if (w1[p1] == w2[i]) {
                    // Cost-free exact match. Always optimally safe.
                    ans[ansIdx++] = p1;
                    p1++;
                    matched = true;
                    break;
                } else if (!changed && maxSuffixLen[p1 + 1] >= s2 - i - 1) {
                    // Changing word1[p1] to word2[i].
                    // We only do this if the rest of word2 can be perfectly matched in the rest of word1.
                    changed = true;
                    ans[ansIdx++] = p1;
                    p1++;
                    matched = true;
                    break;
                }
                
                p1++; // If we can't take w1[p1], we move along.
            }
            
            // If we've exhausted word1 but couldn't match a required character of word2
            if (!matched) {
                return new int[0];
            }
        }
        
        return ans;
    }
}
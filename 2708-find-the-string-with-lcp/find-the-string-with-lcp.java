class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];
        char currentChar = 'a';

        // Step 1: Greedily construct the string
        for (int i = 0; i < n; i++) {
            // If the character at i is not yet assigned
            if (word[i] == 0) {
                // If we've exhausted all 26 lowercase English letters, it's invalid
                if (currentChar > 'z') {
                    return "";
                }
                
                word[i] = currentChar;
                
                // All matching prefixes must start with the exact same character
                for (int j = i + 1; j < n; j++) {
                    if (lcp[i][j] > 0) {
                        word[j] = currentChar;
                    }
                }
                
                // Move to the next lexicographical character for the next unique letter
                currentChar++;
            }
        }

        // Step 2: Validate the constructed string against the LCP matrix rules
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word[i] == word[j]) {
                    int expected = 1;
                    if (i + 1 < n && j + 1 < n) {
                        expected += lcp[i + 1][j + 1];
                    }
                    if (lcp[i][j] != expected) {
                        return "";
                    }
                } else {
                    if (lcp[i][j] != 0) {
                        return "";
                    }
                }
            }
        }

        return new String(word);
    }
}
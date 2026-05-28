class TrieNode {
    TrieNode[] children;
    int bestIdx;

    public TrieNode() {
        // 26 lowercase English letters
        children = new TrieNode[26]; 
        bestIdx = -1;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode();

        // 1. Find the globally best index for an empty suffix match
        int globalBestIdx = 0;
        for (int i = 1; i < wordsContainer.length; i++) {
            if (isBetter(i, globalBestIdx, wordsContainer)) {
                globalBestIdx = i;
            }
        }
        root.bestIdx = globalBestIdx;

        // 2. Build the Trie with reversed strings
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            TrieNode curr = root;
            
            // Iterate through the word backwards
            for (int j = word.length() - 1; j >= 0; j--) {
                int charIdx = word.charAt(j) - 'a';
                if (curr.children[charIdx] == null) {
                    curr.children[charIdx] = new TrieNode();
                }
                curr = curr.children[charIdx];
                
                // Update node's best index if current word is a better match
                if (isBetter(i, curr.bestIdx, wordsContainer)) {
                    curr.bestIdx = i;
                }
            }
        }

        // 3. Process queries
        int[] ans = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String query = wordsQuery[i];
            TrieNode curr = root;
            
            // Traverse the Trie with the query backwards
            for (int j = query.length() - 1; j >= 0; j--) {
                int charIdx = query.charAt(j) - 'a';
                if (curr.children[charIdx] != null) {
                    curr = curr.children[charIdx];
                } else {
                    break; // Stop at the deepest common node
                }
            }
            ans[i] = curr.bestIdx;
        }

        return ans;
    }

    // Helper method to determine if the word at idx1 is "better" than the word at idx2
    private boolean isBetter(int idx1, int idx2, String[] wordsContainer) {
        if (idx2 == -1) return true;
        
        int len1 = wordsContainer[idx1].length();
        int len2 = wordsContainer[idx2].length();
        
        // Rule 1: Shortest length wins
        if (len1 != len2) {
            return len1 < len2;
        }
        
        // Rule 2: If lengths are equal, smallest original index wins
        return idx1 < idx2;
    }
}
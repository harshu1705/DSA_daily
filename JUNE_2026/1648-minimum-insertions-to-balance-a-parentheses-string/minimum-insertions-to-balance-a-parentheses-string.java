
class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                open++;
            } else {
                // Case 1: We have a complete "))" pair
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    if (open > 0) {
                        open--;
                    } else {
                        // Insert a missing '('
                        insertions++;
                    }

                    i++; // Skip the second ')'
                } else {
                    // Case 2: We have only one ')'
                    if (open > 0) {
                        open--;
                        insertions++; // Insert the missing ')'
                    } else {
                        // Insert '(' before and ')' after
                        insertions += 2;
                    }
                }
            }
        }

        // Each unmatched '(' needs two ')'
        return insertions + 2 * open;
    }
}

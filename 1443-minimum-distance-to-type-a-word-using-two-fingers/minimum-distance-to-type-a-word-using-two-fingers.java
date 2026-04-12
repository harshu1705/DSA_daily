class Solution {
    // 3D Memoization table
    // memo[word_index][finger1_pos][finger2_pos]
    // 301 for max word length, 27 to include the 26 A-Z characters + 1 'hovering' state
    private Integer[][][] memo = new Integer[301][27][27];

    public int minimumDistance(String word) {
        // Both fingers start in the 'hovering' state (index 26)
        return dfs(0, 26, 26, word);
    }

    private int dfs(int idx, int f1, int f2, String word) {
        // Base case: We've typed the whole word
        if (idx == word.length()) {
            return 0;
        }

        // Return cached result if we've been in this exact state before
        if (memo[idx][f1][f2] != null) {
            return memo[idx][f1][f2];
        }

        // The character we need to type next (0-25)
        int target = word.charAt(idx) - 'A';

        // Option 1: Move Finger 1 to the target
        int cost1 = getDistance(f1, target) + dfs(idx + 1, target, f2, word);

        // Option 2: Move Finger 2 to the target
        int cost2 = getDistance(f2, target) + dfs(idx + 1, f1, target, word);

        // Store the minimum cost of the two options and return it
        return memo[idx][f1][f2] = Math.min(cost1, cost2);
    }

    private int getDistance(int from, int to) {
        // If the finger is currently 'hovering' (26), the first move is free
        if (from == 26) return 0;

        // Calculate Manhattan distance on a 6-column grid
        int row1 = from / 6;
        int col1 = from % 6;
        int row2 = to / 6;
        int col2 = to % 6;

        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }
}
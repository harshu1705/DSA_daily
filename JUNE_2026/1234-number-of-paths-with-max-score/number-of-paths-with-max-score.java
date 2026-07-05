import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1000000007;
        
        // dpSum[r][c] = max score from 'S' to (r, c)
        int[][] dpSum = new int[n][n];
        // dpWays[r][c] = number of ways to get the max score at (r, c)
        int[][] dpWays = new int[n][n];
        
        // Initialize dpSum with -1 to represent unreachable cells
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                dpSum[r][c] = -1;
            }
        }
        
        // Base case: starting point 'S' at bottom-right
        dpSum[n - 1][n - 1] = 0;
        dpWays[n - 1][n - 1] = 1;
        
        // Directions corresponding to: Right, Down, Down-Right
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}};
        
        // Iterate from bottom-right to top-left
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                char ch = board.get(r).charAt(c);
                
                // Skip obstacles and the starting point itself
                if (ch == 'X' || (r == n - 1 && c == n - 1)) {
                    continue;
                }
                
                int maxPrev = -1;
                int ways = 0;
                
                // Check the three possible preceding cells
                for (int[] dir : directions) {
                    int pr = r + dir[0];
                    int pc = c + dir[1];
                    
                    // If the preceding cell is within bounds and reachable
                    if (pr < n && pc < n && dpSum[pr][pc] != -1) {
                        if (dpSum[pr][pc] > maxPrev) {
                            // Found a new maximum score path
                            maxPrev = dpSum[pr][pc];
                            ways = dpWays[pr][pc];
                        } else if (dpSum[pr][pc] == maxPrev) {
                            // Found another path that yields the same max score
                            ways = (ways + dpWays[pr][pc]) % MOD;
                        }
                    }
                }
                
                // If we found at least one valid preceding cell
                if (maxPrev != -1) {
                    // Treat 'E' as 0, otherwise convert char digit to its integer value
                    int val = (ch == 'E') ? 0 : (ch - '0');
                    dpSum[r][c] = maxPrev + val;
                    dpWays[r][c] = ways;
                }
            }
        }
        
        // If the destination 'E' was never reached
        if (dpWays[0][0] == 0) {
            return new int[]{0, 0};
        }
        
        return new int[]{dpSum[0][0], dpWays[0][0]};
    }
}
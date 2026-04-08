import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        
        // Directions: N, E, S, W
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        
        int x = 0, y = 0;
        int dir = 0; // North
        int maxDist = 0;
        
        // Store obstacles
        Set<String> set = new HashSet<>();
        for (int[] obs : obstacles) {
            set.add(obs[0] + "," + obs[1]);
        }
        
        for (int cmd : commands) {
            
            if (cmd == -1) {
                // Turn right
                dir = (dir + 1) % 4;
                
            } else if (cmd == -2) {
                // Turn left
                dir = (dir + 3) % 4;
                
            } else {
                // Move forward
                for (int i = 0; i < cmd; i++) {
                    
                    int newX = x + dx[dir];
                    int newY = y + dy[dir];
                    
                    // Check obstacle
                    if (set.contains(newX + "," + newY)) {
                        break;
                    }
                    
                    x = newX;
                    y = newY;
                    
                    maxDist = Math.max(maxDist, x*x + y*y);
                }
            }
        }
        
        return maxDist;
    }
}
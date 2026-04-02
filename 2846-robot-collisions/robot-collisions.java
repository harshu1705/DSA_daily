import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        
        // Step 1: Create an array of indices and sort it based on the robots' positions
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        
        // Sort indices left-to-right based on actual positions
        Arrays.sort(indices, (a, b) -> Integer.compare(positions[a], positions[b]));
        
        Stack<Integer> stack = new Stack<>();
        
        // Step 2: Process robots from left to right
        for (int i : indices) {
            // If robot is moving Right, it might collide with future Left moving robots
            if (directions.charAt(i) == 'R') {
                stack.push(i);
            } else {
                // If robot is moving Left, it collides with Right-moving robots on the stack
                while (!stack.isEmpty() && directions.charAt(stack.peek()) == 'R' && healths[i] > 0) {
                    int topIndex = stack.peek();
                    
                    if (healths[topIndex] > healths[i]) {
                        // Right-moving robot wins, loses 1 health
                        healths[topIndex] -= 1;
                        healths[i] = 0; // Left-moving robot is destroyed
                    } else if (healths[topIndex] < healths[i]) {
                        // Left-moving robot wins, loses 1 health
                        healths[i] -= 1;
                        healths[topIndex] = 0; // Right-moving robot is destroyed
                        stack.pop(); // Remove the destroyed robot from the stack
                    } else {
                        // Tie: Both are destroyed
                        healths[i] = 0;
                        healths[topIndex] = 0;
                        stack.pop();
                    }
                }
                
                // If the Left-moving robot survived all collisions, push it to the stack
                // (It won't collide with anything further right)
                if (healths[i] > 0) {
                    stack.push(i);
                }
            }
        }
        
        // Step 3: Collect surviving robots in their original order
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (healths[i] > 0) {
                result.add(healths[i]);
            }
        }
        
        return result;
    }
}
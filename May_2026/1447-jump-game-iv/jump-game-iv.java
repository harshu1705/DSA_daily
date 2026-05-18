import java.util.*;

class Solution {
    public int minJumps(int[] arr) {
        int n = arr.length;
        
        // Edge case: start index is already the last index
        if (n <= 1) {
            return 0;
        }

        // Map to store the indices of each unique value
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }

        // BFS setup
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(0);
        visited[0] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process current layer
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();

                // If we've reached the last index, return the step count
                if (curr == n - 1) {
                    return steps;
                }

                // 1. Jump to curr + 1
                if (curr + 1 < n && !visited[curr + 1]) {
                    visited[curr + 1] = true;
                    queue.offer(curr + 1);
                }

                // 2. Jump to curr - 1
                if (curr - 1 >= 0 && !visited[curr - 1]) {
                    visited[curr - 1] = true;
                    queue.offer(curr - 1);
                }

                // 3. Jump to all indices j where arr[curr] == arr[j]
                if (graph.containsKey(arr[curr])) {
                    for (int next : graph.get(arr[curr])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // CRITICAL: Prevent redundant graph lookups to keep it O(N)
                    graph.remove(arr[curr]);
                }
            }
            steps++;
        }
        
        return -1; // Should theoretically never be reached given constraints
    }
}
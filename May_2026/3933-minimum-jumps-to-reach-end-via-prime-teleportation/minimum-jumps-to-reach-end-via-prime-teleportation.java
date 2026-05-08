import java.util.Arrays;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        // Find the maximum number to appropriately size our sieve arrays
        int MAX_NUM = 0;
        for (int num : nums) {
            if (num > MAX_NUM) MAX_NUM = num;
        }
        
        // Step 1: Sieve of Eratosthenes to precompute the Smallest Prime Factor (SPF)
        int[] spf = new int[MAX_NUM + 1];
        for (int i = 2; i <= MAX_NUM; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= MAX_NUM; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= MAX_NUM; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        
        // Boolean array to quickly check if a number is fundamentally prime
        boolean[] isPrime = new boolean[MAX_NUM + 1];
        for (int i = 2; i <= MAX_NUM; i++) {
            if (spf[i] == i) {
                isPrime[i] = true;
            }
        }
        
        // Step 2: Build prime-to-indices mapping using a flat Adjacency List (Head-To-Next structure)
        int[] head = new int[MAX_NUM + 1];
        Arrays.fill(head, -1);
        
        // A number <= 1,000,000 can have at most 7 distinct prime factors
        int maxEdges = n * 8; 
        int[] to = new int[maxEdges];
        int[] next = new int[maxEdges];
        int edgeCount = 0;
        
        for (int i = 0; i < n; i++) {
            int curr = nums[i];
            while (curr > 1) {
                int p = spf[curr];
                
                // Add index `i` to the prime `p`'s list
                to[edgeCount] = i;
                next[edgeCount] = head[p];
                head[p] = edgeCount++;
                
                // Remove all occurrences of the prime factor p
                while (curr % p == 0) {
                    curr /= p;
                }
            }
        }
        
        // Step 3: Breadth-First Search (BFS) Traversal 
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        
        // Use standard arrays for the Queue (significantly faster than java.util.LinkedList)
        int[] q = new int[n];
        int headQ = 0, tailQ = 0;
        q[tailQ++] = 0;
        
        boolean[] usedPrime = new boolean[MAX_NUM + 1];
        
        while (headQ < tailQ) {
            int u = q[headQ++];
            
            if (u == n - 1) return dist[u];
            
            // Unweighted Edges: Try Step Forward & Step Backward
            if (u + 1 < n && dist[u + 1] == -1) {
                dist[u + 1] = dist[u] + 1;
                q[tailQ++] = u + 1;
            }
            if (u - 1 >= 0 && dist[u - 1] == -1) {
                dist[u - 1] = dist[u] + 1;
                q[tailQ++] = u - 1;
            }
            
            // Directed Edges: Teleportation Traversal using Prime Condition
            int val = nums[u];
            if (val >= 2 && isPrime[val] && !usedPrime[val]) {
                usedPrime[val] = true;
                
                for (int e = head[val]; e != -1; e = next[e]) {
                    int v = to[e];
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q[tailQ++] = v;
                    }
                }
            }
        }
        
        return dist[n - 1];
    }
}
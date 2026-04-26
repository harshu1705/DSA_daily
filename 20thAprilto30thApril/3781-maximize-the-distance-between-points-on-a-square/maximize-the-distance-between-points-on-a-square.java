import java.util.Arrays;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] A = new long[n];
        
        // Step 1: Map 2D coordinates to a 1D circular perimeter distance [0, 4 * side)
        for (int i = 0; i < n; i++) {
            long x = points[i][0];
            long y = points[i][1];
            
            if (y == 0) {
                A[i] = x;                       // Bottom edge
            } else if (x == side) {
                A[i] = side + y;                // Right edge
            } else if (y == side) {
                A[i] = 3L * side - x;           // Top edge
            } else {
                A[i] = 4L * side - y;           // Left edge
            }
        }
        
        // Sort points by their 1D position along the perimeter
        Arrays.sort(A);
        
        // Duplicate the array to simulate circular wrap-around easily
        long[] arr = new long[2 * n];
        long C = 4L * side; // Total circumference
        for (int i = 0; i < n; i++) {
            arr[i] = A[i];
            arr[i + n] = A[i] + C;
        }
        
        // Step 2: Binary Search for the optimal maximum-minimum distance
        int[] nxt = new int[2 * n + 1];
        long low = 1, high = side;
        long ans = 1;
        
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Step 3: Check if this minimum distance 'mid' is valid
            if (check(mid, arr, n, k, nxt)) {
                ans = mid;       // Valid! Try to find a larger distance
                low = mid + 1;
            } else {
                high = mid - 1;  // Invalid! We must look for a smaller distance
            }
        }
        
        return (int) ans;
    }
    
    private boolean check(long D, long[] arr, int n, int k, int[] nxt) {
        int twoN = 2 * n;
        int j = 0;
        
        // Precompute the next valid point index for every position in O(N) using two pointers
        for (int i = 0; i < twoN; i++) {
            while (j < twoN && arr[j] - arr[i] < D) {
                j++;
            }
            nxt[i] = j;
        }
        nxt[twoN] = twoN; // safety bound
        
        // Try starting our k-point selection from every possible point in the original array
        for (int i = 0; i < n; i++) {
            int curr = i;
            
            // Greedily take k jumps
            for (int step = 0; step < k; step++) {
                curr = nxt[curr];
                if (curr == twoN) break; // Out of bounds
            }
            
            // If after k jumps, our endpoint wraps around cleanly without exceeding the circle's capacity
            if (curr <= i + n) {
                return true;
            }
        }
        
        return false;
    }
}
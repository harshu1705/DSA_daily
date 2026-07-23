import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int total1s = 0;
        
        // Count total '1's in the entire string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') total1s++;
        }

        // Identify all '0'-block intervals
        List<int[]> intervals = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (start == -1) start = i;
            } else {
                if (start != -1) {
                    intervals.add(new int[]{start, i - 1});
                    start = -1;
                }
            }
        }
        if (start != -1) {
            intervals.add(new int[]{start, n - 1});
        }

        int K = intervals.size();
        int[] u_arr = new int[K];
        int[] v_arr = new int[K];
        for (int i = 0; i < K; i++) {
            u_arr[i] = intervals.get(i)[0];
            v_arr[i] = intervals.get(i)[1];
        }

        // Build Sparse Table for Range Maximum Queries (RMQ)
        int M = Math.max(0, K - 1);
        int[] adj_sum = new int[M];
        for (int i = 0; i < M; i++) {
            adj_sum[i] = (v_arr[i] - u_arr[i] + 1) + (v_arr[i + 1] - u_arr[i + 1] + 1);
        }

        int LOG = 0;
        while ((1 << LOG) <= M) LOG++;
        int[][] st = new int[M][LOG];
        if (M > 0) {
            for (int i = 0; i < M; i++) st[i][0] = adj_sum[i];
            for (int j = 1; j < LOG; j++) {
                for (int i = 0; i + (1 << j) <= M; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
                }
            }
        }

        // Use a List to match the expected return type
        List<Integer> ans = new ArrayList<>();
        
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            if (K < 2) {
                ans.add(total1s);
                continue;
            }

            // Binary search to find `a`: the first interval that intersects [l, r]
            int a = -1;
            int low = 0, high = K - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (v_arr[mid] >= l) {
                    a = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // Binary search to find `b`: the last interval that intersects [l, r]
            int b = -1;
            low = 0; high = K - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (u_arr[mid] <= r) {
                    b = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // If less than 2 blocks of 0s intersect the query, trade is impossible
            if (a == -1 || b == -1 || a >= b) {
                ans.add(total1s);
            } else {
                // Truncate boundary blocks to the query range
                int La = v_arr[a] - Math.max(u_arr[a], l) + 1;
                int Lb = Math.min(v_arr[b], r) - u_arr[b] + 1;
                
                if (b == a + 1) { // Exactly 2 blocks intersect
                    ans.add(total1s + La + Lb);
                } else { // 3 or more blocks intersect
                    int La_next = v_arr[a + 1] - u_arr[a + 1] + 1;
                    int Lb_prev = v_arr[b - 1] - u_arr[b - 1] + 1;
                    
                    // Evaluate bounds involving truncated edges
                    int max_edges = Math.max(La + La_next, Lb_prev + Lb);
                    
                    // Evaluate fully internal adjacent blocks via O(1) RMQ Sparse Table
                    int max_internal = 0;
                    int left = a + 1;
                    int right = b - 2;
                    if (left <= right) {
                        int len = right - left + 1;
                        int j = 31 - Integer.numberOfLeadingZeros(len);
                        max_internal = Math.max(st[left][j], st[right - (1 << j) + 1][j]);
                    }
                    
                    ans.add(total1s + Math.max(max_edges, max_internal));
                }
            }
        }
        
        return ans;
    }
}
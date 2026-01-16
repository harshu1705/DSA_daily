import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final long MOD = 1_000_000_007L;

        // Step 1: Add boundary fences
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];

        h[0] = 1;
        h[h.length - 1] = m;
        v[0] = 1;
        v[v.length - 1] = n;

        System.arraycopy(hFences, 0, h, 1, hFences.length);
        System.arraycopy(vFences, 0, v, 1, vFences.length);

        // Step 2: Sort
        Arrays.sort(h);
        Arrays.sort(v);

        // Step 3: All horizontal distances
        Set<Long> horizontalDistances = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                horizontalDistances.add((long) h[j] - h[i]);
            }
        }

        // Step 4: Find max common distance
        long maxSide = -1;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                long dist = (long) v[j] - v[i];
                if (horizontalDistances.contains(dist)) {
                    maxSide = Math.max(maxSide, dist);
                }
            }
        }

        // Step 5: Result
        if (maxSide == -1) return -1;
        return (int) ((maxSide * maxSide) % MOD);
    }
}

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        // DSU setup
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Find
        java.util.function.IntUnaryOperator find = new java.util.function.IntUnaryOperator() {
            public int applyAsInt(int x) {
                if (parent[x] != x)
                    parent[x] = this.applyAsInt(parent[x]);
                return parent[x];
            }
        };

        // Union
        for (int[] swap : allowedSwaps) {
            int p1 = find.applyAsInt(swap[0]);
            int p2 = find.applyAsInt(swap[1]);
            if (p1 != p2) parent[p1] = p2;
        }

        // Group indices
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int p = find.applyAsInt(i);
            map.putIfAbsent(p, new HashMap<>());
            Map<Integer, Integer> freq = map.get(p);
            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        // Count mismatches
        int res = 0;

        for (int i = 0; i < n; i++) {
            int p = find.applyAsInt(i);
            Map<Integer, Integer> freq = map.get(p);

            if (freq.getOrDefault(target[i], 0) > 0) {
                freq.put(target[i], freq.get(target[i]) - 1);
            } else {
                res++;
            }
        }

        return res;
    }
}
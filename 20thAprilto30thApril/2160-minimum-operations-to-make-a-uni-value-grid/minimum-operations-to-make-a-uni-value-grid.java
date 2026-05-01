class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;

        int size = m * n;
        int[] arr = new int[size];
        int idx = 0;

        // flatten grid
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }

        // check feasibility
        int rem = arr[0] % x;
        for (int val : arr) {
            if (val % x != rem) return -1;
        }

        // sort
        java.util.Arrays.sort(arr);

        // median
        int median = arr[size / 2];

        // calculate operations
        int ops = 0;
        for (int val : arr) {
            ops += Math.abs(val - median) / x;
        }

        return ops;
    }
}
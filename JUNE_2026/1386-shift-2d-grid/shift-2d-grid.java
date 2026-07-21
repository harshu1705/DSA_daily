import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        // Modulo arithmetic to handle cases where k >= total elements
        k = k % total;
        
        // Use a standard 2D array for faster write operations
        int[][] shifted = new int[m][n];
        
        // Map elements to their new positions
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int new1DIndex = (r * n + c + k) % total;
                int newR = new1DIndex / n;
                int newC = new1DIndex % n;
                shifted[newR][newC] = grid[r][c];
            }
        }
        
        // Convert the 2D array to List<List<Integer>> for the final return
        List<List<Integer>> result = new ArrayList<>(m);
        for (int r = 0; r < m; r++) {
            List<Integer> row = new ArrayList<>(n);
            for (int c = 0; c < n; c++) {
                row.add(shifted[r][c]);
            }
            result.add(row);
        }
        
        return result;
    }
}
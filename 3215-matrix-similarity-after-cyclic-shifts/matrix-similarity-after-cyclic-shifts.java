class Solution {
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        k = k % n; // important optimization

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int newIndex;
                if (i % 2 == 0) {
                    // left shift
                    newIndex = (j + k) % n;
                } else {
                    // right shift
                    newIndex = (j - k + n) % n;
                }

                if (mat[i][j] != mat[i][newIndex]) {
                    return false;
                }
            }
        }
        return true;
    }
}
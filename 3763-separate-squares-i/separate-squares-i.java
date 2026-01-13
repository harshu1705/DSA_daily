class Solution {
    public double separateSquares(int[][] squares) {
        double low = Double.MAX_VALUE, high = 0;

        for (int[] s : squares) {
            low = Math.min(low, s[1]);
            high = Math.max(high, s[1] + s[2]);
        }

        for (int iter = 0; iter < 60; iter++) {
            double mid = (low + high) / 2;
            double below = 0, above = 0;

            for (int[] s : squares) {
                double y = s[1], l = s[2];
                double top = y + l;
                double area = l * l;

                if (mid <= y) {
                    above += area;
                } else if (mid >= top) {
                    below += area;
                } else {
                    double h = mid - y;
                    below += h * l;
                    above += (l - h) * l;
                }
            }

            if (below < above) low = mid;
            else high = mid;
        }
        return low;
    }
}

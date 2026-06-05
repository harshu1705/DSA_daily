class Solution {

    class Pair {
        long count;
        long waviness;

        Pair(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    String digits;
    Pair[][][][][] dp;
    boolean[][][][][] vis;

    private Pair dfs(int pos, int prev1, int prev2,
                     int tight, int started) {

        if (pos == digits.length()) {
            return new Pair(started == 1 ? 1 : 0, 0);
        }

        if (vis[pos][prev1][prev2][tight][started]) {
            return dp[pos][prev1][prev2][tight][started];
        }

        vis[pos][prev1][prev2][tight][started] = true;

        long totalCount = 0;
        long totalWaviness = 0;

        int limit = (tight == 1)
                ? digits.charAt(pos) - '0'
                : 9;

        for (int d = 0; d <= limit; d++) {

            int newTight =
                    (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                Pair child =
                        dfs(pos + 1, 10, 10,
                                newTight, 0);

                totalCount += child.count;
                totalWaviness += child.waviness;

            } else if (started == 0) {

                Pair child =
                        dfs(pos + 1, d, 10,
                                newTight, 1);

                totalCount += child.count;
                totalWaviness += child.waviness;

            } else {

                int add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                Pair child =
                        dfs(pos + 1, d, prev1,
                                newTight, 1);

                totalCount += child.count;
                totalWaviness += child.waviness
                               + add * child.count;
            }
        }

        return dp[pos][prev1][prev2][tight][started]
                = new Pair(totalCount, totalWaviness);
    }

    private long wavinessScore(long num) {

        if (num <= 0) {
            return 0;
        }

        digits = String.valueOf(num);

        dp = new Pair[20][11][11][2][2];
        vis = new boolean[20][11][11][2][2];

        return dfs(0, 10, 10, 1, 0).waviness;
    }

    public long totalWaviness(long num1, long num2) {

        return wavinessScore(num2)
             - wavinessScore(num1 - 1);
    }
}
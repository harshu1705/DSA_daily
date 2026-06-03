import java.util.*;

class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        long ans = Long.MAX_VALUE;

        ans = Math.min(ans,
                solve(landStartTime, landDuration,
                      waterStartTime, waterDuration));

        ans = Math.min(ans,
                solve(waterStartTime, waterDuration,
                      landStartTime, landDuration));

        return (int) ans;
    }

    private long solve(int[] firstStart, int[] firstDur,
                       int[] secondStart, int[] secondDur) {

        int m = secondStart.length;

        int[][] rides = new int[m][2];

        for (int i = 0; i < m; i++) {
            rides[i][0] = secondStart[i];
            rides[i][1] = secondDur[i];
        }

        Arrays.sort(rides, (a, b) -> Integer.compare(a[0], b[0]));

        int[] starts = new int[m];

        long[] prefixMinDuration = new long[m];
        long[] suffixMinStartPlusDur = new long[m];

        starts[0] = rides[0][0];
        prefixMinDuration[0] = rides[0][1];

        for (int i = 1; i < m; i++) {
            starts[i] = rides[i][0];
            prefixMinDuration[i] =
                    Math.min(prefixMinDuration[i - 1], rides[i][1]);
        }

        suffixMinStartPlusDur[m - 1] =
                (long) rides[m - 1][0] + rides[m - 1][1];

        for (int i = m - 2; i >= 0; i--) {
            long val = (long) rides[i][0] + rides[i][1];

            suffixMinStartPlusDur[i] =
                    Math.min(val, suffixMinStartPlusDur[i + 1]);
        }

        long best = Long.MAX_VALUE;

        for (int i = 0; i < firstStart.length; i++) {

            long finishFirst =
                    (long) firstStart[i] + firstDur[i];

            int idx = upperBound(starts, (int) finishFirst) - 1;

            if (idx >= 0) {
                best = Math.min(best,
                        finishFirst + prefixMinDuration[idx]);
            }

            if (idx + 1 < m) {
                best = Math.min(best,
                        suffixMinStartPlusDur[idx + 1]);
            }
        }

        return best;
    }

    private int upperBound(int[] arr, int target) {

        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (arr[mid] <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }
}
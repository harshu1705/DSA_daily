import java.util.*;

class Solution {

    // Check if workers can reduce >= mountainHeight in given time
    public boolean check(long mid, int[] workerTimes, int mountainHeight) {
        long h = 0;

        for (int t : workerTimes) {
            // Solve k*(k+1)/2 * t <= mid
            // k = floor((sqrt(1 + 8*(mid/t)) - 1) / 2)

            long k = (long)((Math.sqrt(1 + 8.0 * mid / t) - 1) / 2);
            h += k;

            if (h >= mountainHeight)
                return true;
        }

        return h >= mountainHeight;
    }

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        int maxTime = 0;
        for (int t : workerTimes)
            maxTime = Math.max(maxTime, t);

        long l = 1;
        long r = (long) maxTime * mountainHeight * (mountainHeight + 1) / 2;

        long result = r;

        while (l <= r) {

            long mid = l + (r - l) / 2;

            if (check(mid, workerTimes, mountainHeight)) {
                result = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return result;
    }
}
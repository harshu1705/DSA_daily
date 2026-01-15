import java.util.*;

class Solution {

    private int getMaxGap(int[] bars) {
        Arrays.sort(bars);

        int longest = 1;
        int curr = 1;

        for (int i = 1; i < bars.length; i++) {
            if (bars[i] == bars[i - 1] + 1) {
                curr++;
            } else {
                longest = Math.max(longest, curr);
                curr = 1;
            }
        }

        longest = Math.max(longest, curr);
        return longest + 1; // bars removed → cells merged
    }

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxH = getMaxGap(hBars);
        int maxV = getMaxGap(vBars);

        int side = Math.min(maxH, maxV);
        return side * side;
    }
}
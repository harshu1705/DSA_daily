class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        // Count stones based on remainder when divided by 3
        for (int stone : stones) {
            count[stone % 3]++;
        }

        // If there are no remainder-1 or remainder-2 stones,
        // every sum is divisible by 3 and Alice loses.
        if (count[1] == 0 && count[2] == 0) {
            return false;
        }

        // If count[0] is even, Alice can win when:
        // one of count[1] or count[2] is sufficiently larger.
        if (count[0] % 2 == 0) {
            return count[1] >= 1 && count[2] >= 1;
        }

        // count[0] is odd.
        // Alice needs an imbalance between remainder-1 and remainder-2 stones.
        return Math.abs(count[1] - count[2]) > 2;
    }
}
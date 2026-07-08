class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        long MOD = 1_000_000_007L;
        int n = s.length();

        // Count of non-zero digits up to index i
        int[] count = new int[n + 1];

        // Sum of non-zero digits up to index i
        long[] sum = new long[n + 1];

        // Concatenated non-zero digits modulo MOD
        long[] value = new long[n + 1];

        // Powers of 10 modulo MOD
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Build prefix arrays
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            count[i + 1] = count[i];
            sum[i + 1] = sum[i];
            value[i + 1] = value[i];

            if (digit != 0) {
                count[i + 1]++;
                sum[i + 1] += digit;
                value[i + 1] =
                    (value[i] * 10 + digit) % MOD;
            }
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            // Number of non-zero digits in s[l...r]
            int k = count[r + 1] - count[l];

            // Sum of digits
            long digitSum = sum[r + 1] - sum[l];

            // Extract concatenated number x
            long x = (
                value[r + 1]
                - (value[l] * pow10[k]) % MOD
                + MOD
            ) % MOD;

            // Final answer
            answer[q] = (int) ((x * digitSum) % MOD);
        }

        return answer;
    }
}
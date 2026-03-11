class Solution {

    int MOD = 1000000007;
    Long[][][] memo;

    public int numberOfStableArrays(int zero, int one, int limit) {
        memo = new Long[zero + 1][one + 1][2];

        long ans = (dfs(zero, one, 0, limit) + dfs(zero, one, 1, limit)) % MOD;
        return (int) ans;
    }

    long dfs(int z, int o, int last, int limit) {
        if (z == 0 && o == 0) return 1;

        if (memo[z][o][last] != null) return memo[z][o][last];

        long res = 0;

        if (last == 0) {
            for (int k = 1; k <= Math.min(limit, z); k++) {
                res = (res + dfs(z - k, o, 1, limit)) % MOD;
            }
        } else {
            for (int k = 1; k <= Math.min(limit, o); k++) {
                res = (res + dfs(z, o - k, 0, limit)) % MOD;
            }
        }

        return memo[z][o][last] = res;
    }
}
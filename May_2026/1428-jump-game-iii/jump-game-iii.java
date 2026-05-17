class Solution {
    int n;

    private boolean dfs(int[] arr, int i) {
        if(i < 0 || i >= n || arr[i] < 0) {
            return false;
        }
        if(arr[i] == 0)
            return true;

        arr[i] *= -1;
        boolean left  = dfs(arr, i - arr[i]);
        boolean right = dfs(arr, i + arr[i]);
        return left || right;
    }

    public boolean canReach(int[] arr, int start) {
        n = arr.length;
        return dfs(arr, start);
    }
}
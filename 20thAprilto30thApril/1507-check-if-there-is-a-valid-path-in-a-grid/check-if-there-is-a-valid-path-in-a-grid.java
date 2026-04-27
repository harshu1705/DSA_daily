class Solution {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Directions: {dx, dy}
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        
        // Allowed directions for each type
        int[][][] typeDirs = {
            {},
            {{0,1},{0,-1}},       // 1
            {{1,0},{-1,0}},       // 2
            {{0,-1},{1,0}},       // 3
            {{0,1},{1,0}},        // 4
            {{0,-1},{-1,0}},      // 5
            {{0,1},{-1,0}}        // 6
        };

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            if(x == m-1 && y == n-1) return true;

            for(int[] d : typeDirs[grid[x][y]]) {
                int nx = x + d[0];
                int ny = y + d[1];

                if(nx<0 || ny<0 || nx>=m || ny>=n || visited[nx][ny]) continue;

                // Check reverse connection
                for(int[] back : typeDirs[grid[nx][ny]]) {
                    if(nx + back[0] == x && ny + back[1] == y) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return false;
    }
}
class Robot {

    int width, height;
    int x, y;
    int dir; // 0=East, 1=North, 2=West, 3=South

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};

    public Robot(int width, int height) {
        this.width = width;
        this.height = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East
    }
    
    public void step(int num) {

        int perimeter = 2 * (width + height - 2);

        num = num % perimeter;

        // 🔥 VERY IMPORTANT EDGE CASE
        if (num == 0) num = perimeter;

        while (num > 0) {

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // boundary check
            if (nx < 0 || nx >= width || ny < 0 || ny >= height) {
                dir = (dir + 1) % 4; // turn left (counterclockwise)
            } else {
                x = nx;
                y = ny;
                num--;
            }
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        if (dir == 0) return "East";
        if (dir == 1) return "North";
        if (dir == 2) return "West";
        return "South";
    }
}
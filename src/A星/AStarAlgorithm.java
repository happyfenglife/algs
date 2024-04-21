import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarAlgorithm {
    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    // grid[i][j] == 0 代表障碍
    // grid[i][j] == 1 代表道路
    // 只能走上、下、左、右，不包括斜线方向
    // 返回从(startX, startY)到(targetX, targetY)的最短距离
    public static int dijkstra(int[][] grid, int startX, int startY, int targetX, int targetY) {
        if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
            return -1;
        }

        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[startX][startY] = 1;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        heap.add(new int[]{startX, startY, 1});
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int x = record[0], y = record[1];
            if (x == targetX && y == targetY) {
                return dist[x][y];
            }

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;
            for (int i = 0, nx, ny; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || grid[nx][ny] == 0) {
                    continue;
                }

                if (dist[nx][ny] > 1 + dist[x][y]) {
                    dist[nx][ny] = 1 + dist[x][y];
                    heap.add(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }

        return -1;
    }

    public static int AStar(int[][] grid, int startX, int startY, int targetX, int targetY) {
        if (grid[startX][startY] == 0 || grid[targetX][targetY] == 0) {
            return -1;
        }

        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[startX][startY] = 1;
        boolean[][] visited = new boolean[n][m];
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        heap.add(new int[]{startX, startY, 1});
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int x = record[0], y = record[1];
            if (x == targetX && y == targetY) {
                return dist[x][y];
            }

            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;
            for (int i = 0, nx, ny; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || grid[nx][ny] == 0) {
                    continue;
                }

                if (dist[nx][ny] > 1 + dist[x][y]) {
                    dist[nx][ny] = 1 + dist[x][y];
                    heap.add(new int[]{nx, ny, dist[nx][ny] + f2(startX, startY, targetX, targetY)});
                }
            }
        }

        return -1;
    }

    // 曼哈顿距离
    public static int f1(int x, int y, int targetX, int targetY) {
        return (Math.abs(targetX - x) + Math.abs(targetY - y));
    }

    // 对角线距离
    public static int f2(int x, int y, int targetX, int targetY) {
        return Math.max(Math.abs(targetX - x), Math.abs(targetY - y));
    }

    // 欧式距离
    public static double f3(int x, int y, int targetX, int targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }

    // 为了测试
    public static int[][] randomGrid(int n) {
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.random() < 0.3) {
                    // 每个格子有30%概率是0
                    grid[i][j] = 0;
                } else {
                    // 每个格子有70%概率是1
                    grid[i][j] = 1;
                }
            }
        }

        return grid;
    }

    // 为了测试
    public static void main(String[] args) {
        int len = 100;
        int testTime = 10000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * len) + 2;
            int[][] grid = randomGrid(n);
            int startX = (int) (Math.random() * n);
            int startY = (int) (Math.random() * n);
            int targetX = (int) (Math.random() * n);
            int targetY = (int) (Math.random() * n);
            int ans1 = dijkstra(grid, startX, startY, targetX, targetY);
            int ans2 = AStar(grid, startX, startY, targetX, targetY);
            if (ans1 != ans2) {
                System.out.println("出错了!");
                break;
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int[][] grid = randomGrid(4000);
        int startX = 0;
        int startY = 0;
        int targetX = 3900;
        int targetY = 3900;
        long start, end;
        start = System.currentTimeMillis();
        int ans1 = dijkstra(grid, startX, startY, targetX, targetY);
        end = System.currentTimeMillis();
        System.out.println("运行dijskra算法结果: " + ans1 + ", 运行时间(毫秒) : " + (end - start));
        start = System.currentTimeMillis();
        int ans2 = AStar(grid, startX, startY, targetX, targetY);
        end = System.currentTimeMillis();
        System.out.println("运行A*算法结果: " + ans2 + ", 运行时间(毫秒) : " + (end - start));
        System.out.println("性能测试结束");
    }
}

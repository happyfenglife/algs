import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接 : https://leetcode.cn/problems/swim-in-rising-water/
public class SwimInRisingWater {
    public static int[] move = {-1, 0, 1, 0, -1};

    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        heap.add(new int[]{0, 0, grid[0][0]});
        dist[0][0] = grid[0][0];

        while (!heap.isEmpty()) {
            int x = heap.peek()[0];
            int y = heap.peek()[1];
            int w = heap.peek()[2];
            heap.poll();
            if (visited[x][y]) {
                continue;
            }

            visited[x][y] = true;
            if (x == n - 1 && y == n - 1) {
                return w;
            }

            for (int i = 0, nx, ny, nw; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny]) {
                    continue;
                }

                nw = Math.max(grid[nx][ny], w);
                if (nw < dist[nx][ny]) {
                    dist[nx][ny] = nw;
                    heap.add(new int[]{nx, ny, nw});
                }
            }
        }

        return 0;
    }
}
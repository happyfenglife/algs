import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 测试链接 : https://leetcode.cn/problems/minimum-obstacle-removal-to-reach-corner/
public class MinimumObstacleRemovalToReachCorner {
    public static int[] move = {-1, 0, 1, 0, -1};

    public static int minimumObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1];

            if (x == n - 1 && y == m - 1) {
                return dist[x][y];
            }

            for (int i = 0, nx, ny; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || dist[nx][ny] <= dist[x][y] + grid[nx][ny]) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + grid[nx][ny];
                if (grid[nx][ny] == 1) {
                    deque.offerLast(new int[]{nx, ny});
                } else {
                    deque.offerFirst(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }
}

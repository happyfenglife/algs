import java.util.ArrayDeque;
import java.util.Deque;

// 测试链接 : https://leetcode.cn/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/
public class MinimumCostToMakeAtLeastOneValidPath {
    public static int[][] move = {{}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int MAXN = 101;

    public static int MAXM = 101;

    public static int[][] dist = new int[MAXN][MAXM];

    public static int minCost(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{0, 0});
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0], y = cur[1];

            if (x == n - 1 && y == m - 1) {
                return dist[x][y];
            }

            for (int i = 1, nx, ny, w; i <= 4; i++) {
                nx = x + move[i][0];
                ny = y + move[i][1];
                w = grid[x][y] == i ? 0 : 1;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || dist[nx][ny] <= dist[x][y] + w) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + w;
                if (w == 0) {
                    deque.offerFirst(new int[]{nx, ny});
                } else {
                    deque.offerLast(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }
}

import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接 ：https://leetcode.cn/problems/path-with-minimum-effort/
public class PathWithMinimumEffort {
    public static int[] move = {-1, 0, 1, 0, -1};

    public static int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = 0;

        boolean[][] visited = new boolean[n][m];
        heap.add(new int[]{0, 0, 0});
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0], y = cur[1], w = cur[2];
            if (x == n - 1 && y == m - 1) {
                return w;
            }

            visited[x][y] = true;
            for (int i = 0, nx, ny, nc; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }

                nc = Math.max(w, Math.abs(heights[x][y] - heights[nx][ny]));
                if (nc < dist[nx][ny]) {
                    dist[nx][ny] = nc;
                    heap.add(new int[]{nx, ny, nc});
                }
            }
        }

        return 0;
    }
}

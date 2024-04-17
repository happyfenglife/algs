import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接 : https://leetcode.cn/problems/trapping-rain-water-ii/
public class TrappingRainWaterII {
    public static int[] move = {-1, 0, 1, 0, -1};

    public static int trapRainWater(int[][] heightMap) {
        int n = heightMap.length, m = heightMap[0].length;
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            heap.add(new int[]{i, 0, heightMap[i][0]});
            visited[i][0] = true;

            if (i == 0 || i == n - 1) {
                for (int j = 1; j < m - 1; j++) {
                    heap.add(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }

            heap.add(new int[]{i, m - 1, heightMap[i][m - 1]});
            visited[i][m - 1] = true;
        }


        int ans = 0;
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int x = cur[0], y = cur[1], w = cur[2];
            ans += w - heightMap[x][y];

            for (int i = 0, nx, ny; i < 4; i++) {
                nx = x + move[i];
                ny = y + move[i + 1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                heap.offer(new int[]{nx, ny, Math.max(heightMap[nx][ny], w)});
            }
        }

        return ans;
    }
}

// 测试链接 : https://leetcode.cn/problems/as-far-from-land-as-possible/
public class AsFarFromLandAsPossible {
    public static int MAXN = 101;

    public static boolean[][] visited = new boolean[MAXN][MAXN];

    public static int[][] queue = new int[MAXN * MAXN][2];

    public static int l, r;

    public static int[] move = new int[]{-1, 0, 1, 0, -1};

    public static int maxDistance(int[][] grid) {
        int n = grid.length;
        l = r = 0;

        int seas = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    visited[i][j] = true;
                    queue[r][0] = i;
                    queue[r++][1] = j;
                } else {
                    visited[i][j] = false;
                    seas++;
                }
            }
        }

        if (seas == 0 || seas == n * n) {
            return -1;
        }

        int level = 0;
        while (l < r) {
            level++;
            int size = r - l;

            for (int i = 0, x, y; i < size; i++) {
                x = queue[l][0];
                y = queue[l++][1];

                for (int j = 0, nx, ny; j < 4; j++) {
                    nx = x + move[j];
                    ny = y + move[j + 1];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                        queue[r][0] = nx;
                        queue[r++][1] = ny;
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return level - 1;
    }
}

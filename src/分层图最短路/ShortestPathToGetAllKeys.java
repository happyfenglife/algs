// 测试链接：https://leetcode.cn/problems/shortest-path-to-get-all-keys
public class ShortestPathToGetAllKeys {
    public static int MAXN = 30;

    public static int MAXM = 30;

    public static int K = 6;

    public static char[][] grid = new char[MAXN][];

    public static boolean[][][] visited = new boolean[MAXN][MAXM][1 << K];

    public static int[][] queue = new int[MAXN * MAXM * (1 << K)][3];

    public static int[] move = {-1, 0, 1, 0, -1};

    public static int n, m;

    public static int l, r, key;

    public static void build(String[] g) {
        for (int i = 0; i < n; i++) {
            grid[i] = g[i].toCharArray();
        }

        l = r = key = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '@') {
                    queue[r][0] = i;
                    queue[r][1] = j;
                    queue[r++][2] = 0;
                }

                if (Character.isLowerCase(grid[i][j])) {
                    key |= (1 << (grid[i][j] - 'a'));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int t = 0; t <= key; t++) {
                    visited[i][j][t] = false;
                }
            }
        }
    }

    public static int shortestPathAllKeys(String[] g) {
        n = g.length;
        m = g[0].length();
        build(g);

        int level = 1;
        while (l < r) {
            int size = r - l;
            for (int j = 0; j < size; j++) {
                int x = queue[l][0];
                int y = queue[l][1];
                int s = queue[l++][2];

                for (int i = 0, nx, ny, ns; i < 4; i++) {
                    nx = x + move[i];
                    ny = y + move[i + 1];
                    ns = s;

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    if (grid[nx][ny] == '#') {
                        continue;
                    }

                    if (Character.isUpperCase(grid[nx][ny]) && ((ns & (1 << (grid[nx][ny] - 'A'))) == 0)) {
                        continue;
                    }

                    if (Character.isLowerCase(grid[nx][ny])) {
                        ns |= (1 << (grid[nx][ny] - 'a'));
                    }

                    if (ns == key) {
                        return level;
                    }

                    if (visited[nx][ny][ns]) {
                        continue;
                    }

                    visited[nx][ny][ns] = true;
                    queue[r][0] = nx;
                    queue[r][1] = ny;
                    queue[r++][2] = ns;
                }
            }

            ++level;
        }

        return -1;
    }
}

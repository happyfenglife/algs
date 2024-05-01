// 测试链接 : https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/
public class PathsDivisibleByK {
    public static int MOD = 1000000007;

    public static int numberOfPaths1(int[][] grid, int k) {
        return f(grid, 0, 0, 0, k);
    }

    // (7 - (4 % 7) + 4) % 7 == 0
    // (7 - (17 % 7) + 17) % 7 == 4
    public static int f(int[][] grid, int i, int j, int r, int k) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j] % k == r ? 1 : 0;
        }

        int need = (k + r - (grid[i][j] % k)) % k;
        int ans = 0;
        if (j + 1 < grid[0].length) {
            ans = f(grid, i, j + 1, need, k) % MOD;
        }

        if (i + 1 < grid.length) {
            ans = (ans + f(grid, i + 1, j, need, k) % MOD) % MOD;
        }

        return ans;
    }

    public static int numberOfPaths2(int[][] grid, int k) {
        int n = grid.length, m = grid[0].length;
        int[][][] dp = new int[n][m][k];
        dp[n - 1][m - 1][grid[n - 1][m - 1] % k] = 1;
        for (int i = n - 2; i >= 0; i--) {
            for (int r = 0; r < k; r++) {
                dp[i][m - 1][r] = dp[i + 1][m - 1][(k + r - (grid[i][m - 1] % k)) % k];
            }
        }

        for (int j = m - 2; j >= 0; j--) {
            for (int r = 0; r < k; r++) {
                dp[n - 1][j][r] = dp[n - 1][j + 1][(k + r - (grid[n - 1][j] % k)) % k];
            }
        }

        for (int i = n - 2, need; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                for (int r = 0; r < k; r++) {
                    need = (k + r - (grid[i][j] % k)) % k;

                    int ans = dp[i][j + 1][need] % MOD;
                    ans = (ans + dp[i + 1][j][need] % MOD) % MOD;

                    dp[i][j][r] = ans;
                }
            }
        }

        return dp[0][0][0];
    }
}

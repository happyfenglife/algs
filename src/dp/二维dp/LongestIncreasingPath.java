import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPath {
    public static int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int ans = 1;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(matrix, i, j, dp));
            }
        }

        return ans;
    }

    public static int dfs(int[][] m, int i, int j, int[][] dp) {
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int next = 0;
        if (i - 1 >= 0 && m[i - 1][j] > m[i][j]) {
            next = Math.max(next, dfs(m, i - 1, j, dp));
        }
        if (i + 1 < m.length && m[i + 1][j] > m[i][j]) {
            next = Math.max(next, dfs(m, i + 1, j, dp));
        }
        if (j + 1 < m[0].length && m[i][j + 1] > m[i][j]) {
            next = Math.max(next, dfs(m, i, j + 1, dp));
        }
        if (j - 1 >= 0 && m[i][j - 1] > m[i][j]) {
            next = Math.max(next, dfs(m, i, j - 1, dp));
        }

        return dp[i][j] = next + 1;
    }
}

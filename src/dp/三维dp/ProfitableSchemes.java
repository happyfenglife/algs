// 测试链接 : https://leetcode.cn/problems/profitable-schemes/
public class ProfitableSchemes {
    public static int MOD = 1000000007;

    public static int profitableSchemes1(int n, int minProfit, int[] group, int[] profit) {
        return f(group, profit, 0, n, minProfit);
    }

    public static int f(int[] group, int[] profit, int i, int n, int m) {
        if (i == group.length) {
            return m <= 0 ? 1 : 0;
        }

        int p1 = f(group, profit, i + 1, n, m) % MOD;
        int p2 = 0;
        if (group[i] <= n) {
            p2 = f(group, profit, i + 1, n - group[i], m - profit[i]) % MOD;
        }

        return (p1 + p2) % MOD;
    }

    public static int profitableSchemes2(int n, int m, int[] group, int[] profit) {
        int s = group.length;
        int[][][] dp = new int[n + 1][m + 1][s + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0][s] = 1;
        }

        for (int k = s - 1; k >= 0; k--) {
            for (int i = 0; i <= n; i++) {
                for (int j = 0; j <= m; j++) {
                    int p1 = dp[i][j][k + 1] % MOD;
                    int p2 = 0;
                    if (group[k] <= i) {
                        p2 = dp[i - group[k]][Math.max(0, j - profit[k])][k + 1] % MOD;
                    }

                    dp[i][j][k] = (p1 + p2) % MOD;
                }
            }
        }

        return dp[n][m][0];
    }

    public static int profitableSchemes3(int n, int m, int[] group, int[] profit) {
        int s = group.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int k = s - 1; k >= 0; k--) {
            for (int i = n; i >= 0; i--) {
                for (int j = m; j >= 0; j--) {
                    int p1 = dp[i][j] % MOD;
                    int p2 = 0;
                    if (group[k] <= i) {
                        p2 = dp[i - group[k]][Math.max(0, j - profit[k])] % MOD;
                    }

                    dp[i][j] = (p1 + p2) % MOD;
                }
            }
        }

        return dp[n][m];
    }
}

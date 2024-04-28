import java.util.Arrays;

// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证
public class FillCellsUseAllColorsWays {
    public static int MOD = 1000000007;

    public static int ways1(int n, int m) {
        return f(new int[n], new boolean[m + 1], 0, n, m);
    }

    public static int f(int[] path, boolean[] visited, int i, int n, int m) {
        if (i == n) {
            Arrays.fill(visited, false);
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (!visited[path[j]]) {
                    cnt++;
                    visited[path[j]] = true;
                }
            }

            return cnt == m ? 1 : 0;
        } else {
            int ans = 0;
            for (int j = 1; j <= m; j++) {
                path[i] = j;

                ans = (ans + f(path, visited, i + 1, n, m)) % MOD;
            }

            return ans;
        }
    }

    public static int MAXN = 5001;

    public static int MAXM = 5001;

    public static long[][] dp = new long[MAXN][MAXM];

    public static int ways2(int n, int m) {
        for (int i = 1; i <= n; i++) {
            dp[i][1] = m;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] % MOD * j + dp[i - 1][j - 1] % MOD * (m - j + 1)) % MOD;
            }
        }

        return (int) dp[n][m];
    }

    public static void main(String[] args) {
        int N = 9;
        int M = 9;
        System.out.println("功能测试开始");
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                int ans1 = ways1(n, m);
                int ans2 = ways2(n, m);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                }
            }
        }
        System.out.println("功能测试结束");

        System.out.println("性能测试开始");
        int n = 5000;
        int m = 4877;
        System.out.println("n : " + n);
        System.out.println("m : " + m);
        long start = System.currentTimeMillis();
        int ans = ways2(n, m);
        long end = System.currentTimeMillis();
        System.out.println("取余之后的结果 : " + ans);
        System.out.println("运行时间 : " + (end - start) + " 毫秒");
        System.out.println("性能测试结束");
    }
}

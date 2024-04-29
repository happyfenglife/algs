// 测试链接 : https://leetcode.cn/problems/ones-and-zeroes/
public class OnesAndZeroes {
    public static int zeros, ones;

    public static int findMaxForm1(String[] strs, int m, int n) {
        return f(strs, 0, m, n);
    }

    public static int f(String[] strs, int i, int m, int n) {
        if (i == strs.length) {
            return 0;
        }

        int p1 = f(strs, i + 1, m, n);
        zerosAndOnes(strs[i]);
        int p2 = Integer.MIN_VALUE;
        if (ones <= n && zeros <= m) {
            p2 = f(strs, i + 1, m - zeros, n - ones) + 1;
        }

        return Math.max(p1, p2);
    }

    public static void zerosAndOnes(String s) {
        zeros = ones = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ++ones;
            } else {
                ++zeros;
            }
        }
    }

    public static int findMaxForm2(String[] strs, int m, int n) {
        int s = strs.length;
        int[][][] dp = new int[s + 1][m + 1][n + 1];
        for (int k = s - 1; k >= 0; k--) {
            zerosAndOnes(strs[k]);

            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    dp[k][i][j] = dp[k + 1][i][j];

                    if (zeros <= i && ones <= j) {
                        dp[k][i][j] = Math.max(dp[k][i][j], dp[k + 1][i - zeros][j - ones] + 1);
                    }
                }
            }
        }

        return dp[0][m][n];
    }

    public static int findMaxForm3(String[] strs, int m, int n) {
        int s = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int k = s - 1; k >= 0; k--) {
            zerosAndOnes(strs[k]);

            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (zeros <= i && ones <= j) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                    }
                }
            }
        }

        return dp[m][n];
    }
}

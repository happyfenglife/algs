import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/max-submatrix-lcci/
public class MaximumSubmatrix {
    public static int[] getMaxMatrix(int[][] g) {
        int n = g.length, m = g[0].length;
        int[] dp = new int[m];
        int a = 0, b = 0, c = 0, d = 0;
        int sum = Integer.MIN_VALUE;
        for (int up = 0; up < n; up++) {
            Arrays.fill(dp, 0);

            for (int down = up; down < n; down++) {
                for (int r = 0, l = 0, pre = Integer.MIN_VALUE; r < m; r++) {
                    dp[r] += g[down][r];

                    if (pre < 0) {
                        pre = dp[r];
                        l = r;
                    } else {
                        pre += dp[r];
                    }

                    if (pre > sum) {
                        sum = pre;
                        a = up;
                        b = l;
                        c = down;
                        d = r;
                    }
                }
            }
        }

        return new int[]{a, b, c, d};
    }
}

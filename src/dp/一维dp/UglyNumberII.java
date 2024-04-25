// 测试链接 : https://leetcode.cn/problems/ugly-number-ii/
public class UglyNumberII {
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];
        int a, b, c;
        int cur;
        dp[1] = 1;
        for (int i = 2, i2 = 1, i3 = 1, i5 = 1; i <= n; i++) {
            a = dp[i2] * 2;
            b = dp[i3] * 3;
            c = dp[i5] * 5;
            cur = Math.min(a, Math.min(b, c));
            dp[i] = cur;

            if (a == cur) {
                i2++;
            }
            if (b == cur) {
                i3++;
            }
            if (c == cur) {
                i5++;
            }
        }

        return dp[n];
    }
}

import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/fibonacci-number/
public class FibonacciNumber {
    public static int fib1(int n) {
        return f1(n);
    }

    public static int f1(int i) {
        if (i == 0) {
            return 0;
        }

        if (i == 1) {
            return 1;
        }

        return f1(i - 1) + f1(i - 2);
    }

    public static int fib2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return f2(n, dp);
    }

    public static int f2(int i, int[] dp) {
        if (i == 0) {
            return 0;
        }

        if (i == 1) {
            return 1;
        }

        if (dp[i] != -1) {
            return dp[i];
        }

        int ans = f2(i - 1, dp) + f2(i - 2, dp);
        dp[i] = ans;
        return ans;
    }

    public static int fib3(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static int fib4(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int lastLast = 0, last = 1;
        for (int i = 2, cur; i <= n; i++) {
            cur = lastLast + last;
            lastLast = last;
            last = cur;
        }

        return last;
    }
}

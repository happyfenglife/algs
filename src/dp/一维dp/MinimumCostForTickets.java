import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/minimum-cost-for-tickets/
public class MinimumCostForTickets {
    public static int[] durations = {1, 7, 30};

    public static int mincostTickets1(int[] days, int[] costs) {
        return f1(days, costs, 0);
    }

    public static int f1(int[] days, int[] costs, int i) {
        if (i == days.length) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < 3; k++) {
            while (j < days.length && durations[k] + days[i] > days[j]) {
                ++j;
            }

            ans = Math.min(ans, costs[k] + f1(days, costs, j));
        }

        return ans;
    }

    public static int mincostTickets2(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 0, j = i; k < 3; k++) {
                while (j < days.length && durations[k] + days[i] > days[j]) {
                    ++j;
                }

                dp[i] = Math.min(dp[i], costs[k] + dp[j]);
            }
        }

        return dp[0];
    }
}

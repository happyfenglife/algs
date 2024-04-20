import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/closest-subsequence-sum/
public class ClosestSubsequenceSum {
    public static int MAXN = 40;

    public static long[] lSum = new long[1 << (MAXN >> 1)];

    public static long[] rSum = new long[1 << (MAXN >> 1)];

    public static int fill;

    public static void dfs(int[] nums, int s, int e, long total, long[] sum) {
        if (s == e) {
            sum[fill++] = total;
        } else {
            int j = s + 1;
            while (j < e && nums[j] == nums[j - 1]) {
                j++;
            }

            for (int i = 0; i <= j - s; i++) {
                dfs(nums, j, e, (long) i * nums[s] + total, sum);
            }
        }
    }

    public static int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;

        int posSum = 0, negSum = 0;
        for (int num : nums) {
            if (num > 0) {
                posSum += num;
            } else {
                negSum += num;
            }
        }

        if (posSum <= goal) {
            return goal - posSum;
        }

        if (negSum >= goal) {
            return negSum - goal;
        }

        Arrays.sort(nums);
        fill = 0;
        dfs(nums, 0, n >> 1, 0, lSum);
        int lSize = fill;
        Arrays.sort(lSum, 0, lSize);

        fill = 0;
        dfs(nums, n >> 1, n, 0, rSum);
        int rSize = fill;
        Arrays.sort(rSum, 0, rSize);

        int ans = Integer.MAX_VALUE;
        for (int i = 0, j = rSize - 1; i < lSize; i++) {
            while (j > 0 && Math.abs(lSum[i] + rSum[j] - goal) >= Math.abs(lSum[i] + rSum[j - 1] - goal)) {
                --j;
            }

            ans = Math.min(ans, (int) Math.abs(lSum[i] + rSum[j] - goal));
        }

        return ans;
    }
}

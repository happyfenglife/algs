// 测试链接 : https://leetcode.cn/problems/maximum-subarray/
public class MaximumSubarray {
    public static int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int maxSubArray2(int[] nums) {
        int n = nums.length;
        int pre = nums[0], ans = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(nums[i], nums[i] + pre);
            ans = Math.max(ans, pre);
        }

        return ans;
    }

    // 如下代码为附加问题的实现
    // 子数组中找到拥有最大累加和的子数组
    // 并返回如下三个信息:
    // 1) 最大累加和子数组的开头left
    // 2) 最大累加和子数组的结尾right
    // 3) 最大累加和子数组的累加和sum
    // 如果不止一个子数组拥有最大累加和，那么找到哪一个都可以
    public static int left, right, sum;

    public static void extra(int[] nums) {
        sum = Integer.MIN_VALUE;
        int n = nums.length;
        for (int l = 0, r = 0, pre = Integer.MIN_VALUE; r < n; r++) {
            if (pre < 0) {
                pre = nums[r];
                l = r;
            } else {
                pre += nums[r];
            }

            if (pre > sum) {
                left = l;
                right = r;
                sum = pre;
            }
        }
    }
}

// 测试链接 : https://leetcode.cn/problems/maximum-sum-circular-subarray/
public class MaximumSumCircularSubarray {
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int preMaxSum = nums[0], preMinSum = nums[0];
        int maxSum = preMaxSum, minSum = preMinSum;
        for (int i = 1; i < n; i++) {
            preMaxSum = Math.max(nums[i], nums[i] + preMaxSum);
            maxSum = Math.max(maxSum, preMaxSum);

            preMinSum = Math.min(nums[i], nums[i] + preMinSum);
            minSum = Math.min(minSum, preMinSum);
        }

        return sum == minSum ? maxSum : Math.max(maxSum, sum - minSum);
    }
}

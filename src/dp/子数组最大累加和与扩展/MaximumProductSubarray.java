// 测试链接 : https://leetcode.cn/problems/maximum-product-subarray/
public class MaximumProductSubarray {
    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int preMax = nums[0], preMin = nums[0];
        int ans = nums[0];
        for (int i = 1, curMax, curMin; i < n; i++) {
            curMax = Math.max(nums[i], Math.max(nums[i] * preMin, nums[i] * preMax));
            curMin = Math.min(nums[i], Math.min(nums[i] * preMin, nums[i] * preMax));
            ans = Math.max(ans, curMax);
            preMax = curMax;
            preMin = curMin;
        }

        return ans;
    }
}

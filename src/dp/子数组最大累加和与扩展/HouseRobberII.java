// 测试链接 : https://leetcode.cn/problems/house-robber-ii/
public class HouseRobberII {
    public static int rob(int[] nums) {
        int n = nums.length;

        return Math.max(
                f(nums, 1, n - 1)
                , f(nums, 2, n - 2) + nums[0]);
    }

    public static int f(int[] nums, int l, int r) {
        if (l > r) {
            return 0;
        }

        if (l == r) {
            return nums[l];
        }

        if (l == r - 1) {
            return Math.max(nums[l], nums[r]);
        }

        int prePre = nums[l];
        int pre = Math.max(nums[l], nums[l + 1]);
        for (int i = l + 2, cur; i <= r; i++) {
            cur = Math.max(prePre + nums[i], pre);
            prePre = pre;
            pre = cur;
        }

        return pre;
    }
}

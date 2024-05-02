// 测试链接 : https://leetcode.cn/problems/house-robber-iv/
public class HouseRobberIV {
    public static int minCapability(int[] nums, int k) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int num : nums) {
            l = Math.min(num, l);
            r = Math.max(num, r);
        }

        int ans = r, m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (f2(nums, m) >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }

    public static int f1(int[] nums, int m) {
        int n = nums.length;
        if (n == 1) {
            return nums[0] <= m ? 1 : 0;
        }

        if (n == 2) {
            return (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        }

        int prePre = nums[0] <= m ? 1 : 0;
        int pre = (nums[0] <= m || nums[1] <= m) ? 1 : 0;
        for (int i = 2, cur; i < n; i++) {
            cur = Math.max(prePre + (nums[i] > m ? 0 : 1), pre);
            prePre = pre;
            pre = cur;
        }

        return pre;
    }

    public static int f2(int[] nums, int m) {
        int ans = 0;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (nums[i] <= m) {
                ans++;
                i++;
            }
        }

        return ans;
    }
}

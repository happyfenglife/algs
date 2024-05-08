import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/longest-increasing-subsequence/
public class LongestIncreasingSubsequence {
    public static int lengthOfLIS1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        int[] end = new int[n];
        int len = 0;
        end[len++] = nums[0];

        for (int i = 1; i < n; i++) {
            int pos = bs1(end, nums[i], len);

            if (pos == -1) {
                end[len++] = nums[i];
            } else {
                end[pos] = nums[i];
            }
        }

        return len;
    }

    public static int bs1(int[] end, int t, int len) {
        int l = 0, r = len - 1, m;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;

            if (end[m] >= t) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }


    // 如果求最长不下降子序列，那么使用如下的二分搜索 :
    // ends[0...len-1]是不降序的
    // 在其中找到>num的最左位置，如果不存在返回-1
    // 如果求最长不下降子序列，就在lengthOfLIS中把bs1方法换成bs2方法
    // 已经用对数器验证了，是正确的
    public static int bs2(int[] end, int t, int len) {
        int l = 0, r = len - 1, m;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;

            if (end[m] > t) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }
}

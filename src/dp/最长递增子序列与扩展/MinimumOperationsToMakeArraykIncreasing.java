// 测试链接 : https://leetcode.cn/problems/minimum-operations-to-make-the-array-k-increasing/
public class MinimumOperationsToMakeArraykIncreasing {
    public static int MAXN = 100001;

    public static int[] ends = new int[MAXN];

    public static int[] nums = new int[MAXN];

    public static int kIncreasing(int[] arr, int k) {
        int ans = 0;
        int n = arr.length;
        for (int i = 0, sz; i < k; i++) {
            sz = 0;

            for (int j = i; j < n; j += k) {
                nums[sz++] = arr[j];
            }

            ans += sz - lengthOfNoDecreasing(sz);
        }

        return ans;
    }

    public static int lengthOfNoDecreasing(int n) {
        int len = 0;
        ends[len++] = nums[0];
        for (int i = 1; i < n; i++) {
            int pos = upperBound(len, nums[i]);

            if (pos == -1) {
                ends[len++] = nums[i];
            } else {
                ends[pos] = nums[i];
            }
        }

        return len;
    }

    public static int upperBound(int len, int target) {
        int l = 0, r = len - 1, m;
        int ans = -1;
        while (l <= r) {
            m = l + (r - l) / 2;

            if (ends[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }
}

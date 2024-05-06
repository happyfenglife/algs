// 测试链接 : https://leetcode.cn/problems/maximum-sum-of-3-non-overlapping-subarrays/
public class MaximumSum3UnoverlappingSubarrays {
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n];
        for (int l = 0, r = 0, sum = 0; r < n; r++) {
            sum += nums[r];

            if (r - l + 1 == k) {
                sums[l] = sum;
                sum -= nums[l];
                l++;
            }
        }

        int[] prefix = new int[n];
        for (int r = k, l = 1; r < n; r++, l++) {
            if (sums[l] > sums[prefix[r - 1]]) {
                prefix[r] = l;
            } else {
                prefix[r] = prefix[r - 1];
            }
        }

        int[] suffix = new int[n];
        suffix[n - k] = n - k;
        for (int l = n - k - 1; l >= 0; l--) {
            if (sums[l] >= sums[suffix[l + 1]]) {
                suffix[l] = l;
            } else {
                suffix[l] = suffix[l + 1];
            }
        }

        int ans = 0;
        int a = 0, b = 0, c = 0;
        // j - k + 1 == k
        for (int i = k, j = 2 * k - 1; j < n - k; i++, j++) {
            int sum = sums[prefix[i - 1]] + sums[i] + sums[suffix[j + 1]];

            if (sum > ans) {
                ans = sum;
                a = prefix[i - 1];
                b = i;
                c = suffix[j + 1];
            }
        }

        return new int[]{a, b, c};
    }
}

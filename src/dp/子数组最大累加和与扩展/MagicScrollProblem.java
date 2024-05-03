// 魔法卷轴
// 给定一个数组nums，其中可能有正、负、0
// 每个魔法卷轴可以把nums中连续的一段全变成0
// 你希望数组整体的累加和尽可能大
// 卷轴使不使用、使用多少随意，但一共只有2个魔法卷轴
// 请返回数组尽可能大的累加和
// 对数器验证
public class MagicScrollProblem {
    public static int maxSum1(int[] nums) {
        int p1 = 0;
        for (int num : nums) {
            p1 += num;
        }

        int n = nums.length;
        int p2 = mustOneScroll(nums, 0, n - 1);
        int p3 = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, mustOneScroll(nums, 0, i - 1) + mustOneScroll(nums, i, n - 1));
        }

        return Math.max(p1, Math.max(p2, p3));
    }

    public static int mustOneScroll(int[] nums, int l, int r) {
        int ans = Integer.MIN_VALUE;
        for (int a = l; a <= r; a++) {
            for (int b = a; b <= r; b++) {
                int cur = 0;
                for (int i = l; i < a; i++) {
                    cur += nums[i];
                }
                for (int i = b + 1; i <= r; i++) {
                    cur += nums[i];
                }

                ans = Math.max(ans, cur);
            }
        }

        return ans;
    }

    public static int maxSum2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        int p1 = 0;
        for (int num : nums) {
            p1 += num;
        }

        int[] prefix = new int[n];
        int preMaxSum = Math.max(0, nums[0]);
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = Math.max(prefix[i - 1] + nums[i], preMaxSum);
            sum += nums[i];
            preMaxSum = Math.max(preMaxSum, sum);
        }

        int[] suffix = new int[n];
        int sufMaxSum = Math.max(0, nums[n - 1]);
        sum = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = Math.max(suffix[i + 1] + nums[i], sufMaxSum);
            sum += nums[i];
            sufMaxSum = Math.max(sufMaxSum, sum);
        }

        int p2 = prefix[n - 1];
        int p3 = 0;
        for (int i = 1; i < n; i++) {
            p3 = Math.max(p3, prefix[i - 1] + suffix[i]);
        }

        return Math.max(p1, Math.max(p2, p3));
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * (v * 2 + 1)) - v;
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int n = 50;
        int v = 100;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n);
            int[] nums = randomArray(len, v);
            int ans1 = maxSum1(nums);
            int ans2 = maxSum2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}

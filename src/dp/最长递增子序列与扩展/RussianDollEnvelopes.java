import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/russian-doll-envelopes/
public class RussianDollEnvelopes {
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int n = envelopes.length;
        int[] end = new int[n];
        end[0] = envelopes[0][1];
        int len = 1;

        for (int i = 1, h; i < n; i++) {
            h = envelopes[i][1];
            int pos = lowerBound(end, 0, len, h);

            if (pos == -1) {
                end[len++] = h;
            } else {
                end[pos] = h;
            }
        }

        return len;
    }

    public static int lowerBound(int[] arr, int l, int r, int target) {
        r--;
        int m, ans = -1;

        while (l <= r) {
            m = l + (r - l) / 2;

            if (arr[m] >= target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return ans;
    }
}

// 测试链接 : https://leetcode.cn/problems/longest-valid-parentheses/
public class LongestValidParentheses {
    public static int longestValidParentheses(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 1, p; i < n; i++) {
            if (s[i] == '(') {
                continue;
            }

            p = i - dp[i - 1] - 1;
            if (p >= 0 && s[p] == '(') {
                dp[i] = 2 + dp[i - 1] + (p - 1 >= 0 ? dp[p - 1] : 0);
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}

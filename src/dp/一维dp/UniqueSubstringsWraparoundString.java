// 测试链接 : https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
public class UniqueSubstringsWraparoundString {
    public static int findSubstringInWraproundString(String str) {
        int[] dp = new int[26];

        char[] s = str.toCharArray();
        dp[s[0] - 'a'] = 1;

        for (int i = 1, len = 1; i < s.length; i++) {
            if (s[i] == s[i - 1] + 1 || (s[i] == 'a' && s[i - 1] == 'z')) {
                len++;
            } else {
                len = 1;
            }

            dp[s[i] - 'a'] = Math.max(dp[s[i] - 'a'], len);
        }

        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += dp[i];
        }

        return ans;
    }
}

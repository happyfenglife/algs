// 测试链接 : https://leetcode.cn/problems/decode-ways/
public class DecodeWays {
    public static int numDecodings1(String str) {
        char[] s = str.toCharArray();
        return f1(s, 0);
    }

    public static int f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }

        if (s[i] == '0') {
            return 0;
        }

        int num = f1(s, i + 1);
        if (i + 1 < s.length && (s[i] - '0') * 10 + (s[i + 1] - '0') < 27) {
            num += f1(s, i + 2);
        }

        return num;
    }

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == '0') {
                continue;
            }

            dp[i] = dp[i + 1];
            if (i + 1 < s.length && (s[i] - '0') * 10 + (s[i + 1] - '0') < 27) {
                dp[i] += dp[i + 2];
            }
        }

        return dp[0];
    }
}

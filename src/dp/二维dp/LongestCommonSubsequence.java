import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/longest-common-subsequence/
public class LongestCommonSubsequence {
    public static int longestCommonSubsequence1(String text1, String text2) {
        return f1(text1.toCharArray(), text1.length() - 1, text2.toCharArray(), text2.length() - 1);
    }

    public static int f1(char[] s1, int i, char[] s2, int j) {
        if (i < 0 || j < 0) {
            return 0;
        }

        int p1 = f1(s1, i - 1, s2, j - 1);
        int p2 = f1(s1, i, s2, j - 1);
        int p3 = f1(s1, i - 1, s2, j);
        int p4 = s1[i] == s2[j] ? 1 + f1(s1, i - 1, s2, j - 1) : 0;

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    // 用长度来定义尝试
    public static int longestCommonSubsequence2(String text1, String text2) {
        return f2(text1.toCharArray(), text1.length(), text2.toCharArray(), text2.length());
    }

    public static int f2(char[] s1, int i, char[] s2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        int ans;
        if (s1[i - 1] == s2[j - 1]) {
            ans = 1 + f2(s1, i - 1, s2, j - 1);
        } else {
            ans = Math.max(f2(s1, i, s2, j - 1), f2(s1, i - 1, s2, j));
        }

        return ans;
    }

    public static int longestCommonSubsequence3(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return f3(text1.toCharArray(), n, text2.toCharArray(), m, dp);
    }

    public static int f3(char[] s1, int len1, char[] s2, int len2, int[][] dp) {
        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        if (dp[len1][len2] != -1) {
            return dp[len1][len2];
        }

        int ans;
        if (s1[len1 - 1] == s2[len2 - 1]) {
            ans = 1 + f3(s1, len1 - 1, s2, len2 - 1, dp);
        } else {
            ans = Math.max(f3(s1, len1, s2, len2 - 1, dp), f3(s1, len1 - 1, s2, len2, dp));
        }

        return dp[len1][len2] = ans;
    }

    public static int longestCommonSubsequence4(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        char[] s1 = text1.toCharArray();
        char[] s2 = text2.toCharArray();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n][m];
    }

    public static int longestCommonSubsequence5(String text1, String text2) {
        char[] s1, s2;
        if (text1.length() < text2.length()) {
            s1 = text2.toCharArray();
            s2 = text1.toCharArray();
        } else {
            s1 = text1.toCharArray();
            s2 = text2.toCharArray();
        }

        int n = s1.length, m = s2.length;
        int[] dp = new int[m + 1];
        for (int i = 1, backup, leftUp; i <= n; i++) {
            leftUp = 0;

            for (int j = 1; j <= m; j++) {
                backup = dp[j];
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = 1 + leftUp;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
                leftUp = backup;
            }
        }

        return dp[m];
    }
}

// 测试链接 : https://leetcode.cn/problems/scramble-string/
public class ScrambleString {
    public static boolean isScramble1(String s1, String s2) {
        return f1(s1.toCharArray(), 0, s1.length() - 1, s2.toCharArray(), 0, s2.length() - 1);
    }

    public static boolean f1(char[] s1, int l1, int r1, char[] s2, int l2, int r2) {
        if (l1 == r1) {
            return s1[l1] == s2[l2];
        }

        for (int i = l1, j = l2; i < r1 && j < r2; i++, j++) {
            if (f1(s1, l1, i, s2, l2, j) && f1(s1, i + 1, r1, s2, j + 1, r2)) {
                return true;
            }
        }

        for (int i = l1, j = r2; i < r1 && j > l2; i++, j--) {
            if (f1(s1, l1, i, s2, j, r2) && f1(s1, i + 1, r1, s2, l2, j - 1)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isScramble2(String s1, String s2) {
        return f2(s1.toCharArray(), 0, s2.toCharArray(), 0, s2.length());
    }

    public static boolean f2(char[] s1, int l1, char[] s2, int l2, int len) {
        if (len == 1) {
            return s1[l1] == s2[l2];
        }

        for (int i = 1; i < len; i++) {
            if (f2(s1, l1, s2, l2, i) && f2(s1, l1 + i, s2, l2 + i, len - i)) {
                return true;
            }
        }

        for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; k++, i++, j--) {
            if (f2(s1, l1, s2, j, k) && f2(s1, i, s2, l2, len - k)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isScramble3(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1[i] == s2[j];
            }
        }

        for (int len = 2; len <= n; len++) {
            for (int l1 = 0; l1 <= n - len; l1++) {
                for (int l2 = 0; l2 <= n - len; l2++) {
                    for (int k = 1; k < len; k++) {
                        if (dp[l1][l2][k] && dp[l1 + k][l2 + k][len - k]) {
                            dp[l1][l2][len] = true;
                            break;
                        }
                    }

                    if (!dp[l1][l2][len]) {
                        for (int i = l1 + 1, j = l2 + len - 1, k = 1; k < len; k++, i++, j--) {
                            if (dp[l1][j][k] && dp[i][l2][len - k]) {
                                dp[l1][l2][len] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}

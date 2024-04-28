// 测试链接 : https://leetcode.cn/problems/edit-distance/
public class EditDistance {
    public static int minDistance1(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        return editDistance1(s1, s2, 1, 1, 1);
    }

    public static int editDistance1(char[] s1, char[] s2, int ic, int rc, int dc) {
        int n = s1.length, m = s2.length;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = dc * i;
        }

        for (int j = 0; j <= m; j++) {
            dp[0][j] = ic * j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int p1 = dp[i - 1][j] + dc;
                int p2 = Integer.MAX_VALUE, p3 = Integer.MAX_VALUE, p4;
                if (s1[i - 1] == s2[j - 1]) {
                    p2 = dp[i - 1][j - 1];
                } else {
                    p3 = dp[i - 1][j - 1] + rc;
                }

                p4 = dp[i][j - 1] + ic;

                dp[i][j] = Math.min(Math.min(p1, p2), Math.min(p3, p4));
            }
        }

        return dp[n][m];
    }

    public static int minDistance2(String word1, String word2) {
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();

        return editDistance2(s1, s2, 1, 1, 1);
    }

    public static int editDistance2(char[] s1, char[] s2, int ic, int rc, int dc) {
        int n = s1.length, m = s2.length;
        int[] dp = new int[m + 1];
        for (int j = 0; j <= m; j++) {
            dp[j] = ic * j;
        }

        for (int i = 1, backUp, leftUp; i <= n; i++) {
            leftUp = dp[0];
            dp[0] = dc * i;

            for (int j = 1; j <= m; j++) {
                backUp = dp[j];
                if (s1[i - 1] == s2[j - 1]) {
                    dp[j] = leftUp;
                } else {
                    int p1 = dp[j] + dc;
                    int p2 = leftUp + rc;
                    int p3 = dp[j - 1] + ic;

                    dp[j] = Math.min(Math.min(p1, p2), p3);
                }

                leftUp = backUp;
            }
        }

        return dp[m];
    }
}

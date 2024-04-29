import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 删除至少几个字符可以变成另一个字符串的子串
// 给定两个字符串s1和s2
// 返回s1至少删除多少字符可以成为s2的子串
// 对数器验证
public class MinimumDeleteBecomeSubstring {
    public static int minDelete1(String s1, String s2) {
        List<String> list = new ArrayList<>();
        f(s1.toCharArray(), 0, "", list);
        list.sort(Comparator.comparingInt(a -> -a.length()));
        for (String s : list) {
            if (s2.contains(s)) {
                return s1.length() - s.length();
            }
        }

        return s1.length();
    }

    public static void f(char[] s, int i, String path, List<String> list) {
        if (i == s.length) {
            list.add(path);
        } else {
            f(s, i + 1, path, list);
            f(s, i + 1, path + s[i], list);
        }
    }

    public static int minDelete2(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length, m = s2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + dp[i - 1][j];
                }
            }
        }

        int ans = s1.length;
        for (int j = 0; j <= m; j++) {
            ans = Math.min(ans, dp[n][j]);
        }
        return ans;
    }

    // 为了验证
    // 生成长度为n，有v种字符的随机字符串
    public static String randomString(int n, int v) {
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(ans);
    }

    // 为了验证
    // 对数器
    public static void main(String[] args) {
        // 测试的数据量比较小
        // 那是因为数据量大了，暴力方法过不了
        // 但是这个数据量足够说明正式方法是正确的
        int n = 12;
        int v = 3;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len1 = (int) (Math.random() * n) + 1;
            int len2 = (int) (Math.random() * n) + 1;
            String s1 = randomString(len1, v);
            String s2 = randomString(len2, v);
            int ans1 = minDelete1(s1, s2);
            int ans2 = minDelete2(s1, s2);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}

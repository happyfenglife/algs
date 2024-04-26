// 测试链接 : https://leetcode.cn/problems/distinct-subsequences-ii/
public class DistinctSubsequencesII {
    public static int MOD = 1000000007;

    public static int distinctSubseqII(String str) {
        int[] cnt = new int[26];
        int all = 1, newAdd;
        char[] s = str.toCharArray();
        for (char c : s) {
            newAdd = (all - cnt[c - 'a'] + MOD) % MOD;
            all = (all + newAdd) % MOD;
            cnt[c - 'a'] = (newAdd + cnt[c - 'a']) % MOD;
        }

        return (all - 1 + MOD) % MOD;
    }
}

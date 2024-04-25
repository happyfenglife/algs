// 测试链接 : https://leetcode.cn/problems/decode-ways-ii/
public class DecodeWaysII {
    public static int MOD = 1000000007;

    public static int numDecodings1(String s) {
        return f1(s.toCharArray(), 0);
    }

    public static int f1(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }

        if (s[i] == '0') {
            return 0;
        }

        int ans = (s[i] == '*' ? 9 : 1) * f1(s, i + 1);
        if (i + 1 < s.length) {
            if (s[i] == '*') {
                if (s[i + 1] == '*') {
                    ans += 15 * f1(s, i + 2);
                } else {
                    if (s[i + 1] <= '6') {
                        ans += 2 * f1(s, i + 2);
                    } else {
                        ans += f1(s, i + 2);
                    }
                }
            } else {
                if (Character.isDigit(s[i + 1])) {
                    if ((s[i] - '0') * 10 + s[i + 1] - '0' < 27) {
                        ans += f1(s, i + 2);
                    }
                } else {
                    if (s[i] == '1') {
                        ans += 9 * f1(s, i + 2);
                    } else if (s[i] == '2') {
                        ans += 6 * f1(s, i + 2);
                    }
                }
            }
        }

        return ans;
    }

    public static int numDecodings2(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long[] dp = new long[n + 1];
        dp[n] = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (s[i] == '0') {
                continue;
            }

            long ans = (s[i] == '*' ? 9 : 1) * dp[i + 1];
            if (i + 1 < s.length) {
                if (s[i] == '*') {
                    if (s[i + 1] == '*') {
                        ans += 15 * dp[i + 2];
                    } else {
                        if (s[i + 1] <= '6') {
                            ans += 2 * dp[i + 2];
                        } else {
                            ans += dp[i + 2];
                        }
                    }
                } else {
                    if (Character.isDigit(s[i + 1])) {
                        if ((s[i] - '0') * 10 + s[i + 1] - '0' < 27) {
                            ans += dp[i + 2];
                        }
                    } else {
                        if (s[i] == '1') {
                            ans += 9 * dp[i + 2];
                        } else if (s[i] == '2') {
                            ans += 6 * dp[i + 2];
                        }
                    }
                }
            }

            dp[i] = ans % MOD;
        }

        return (int) dp[0];
    }

    public static int numDecodings3(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        long cur = 0, next = 1, nextNext = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (s[i] != '0') {
                cur = (s[i] == '*' ? 9 : 1) * next;
                if (i + 1 < s.length) {
                    if (s[i] == '*') {
                        if (s[i + 1] == '*') {
                            cur += 15 * nextNext;
                        } else {
                            if (s[i + 1] <= '6') {
                                cur += 2 * nextNext;
                            } else {
                                cur += nextNext;
                            }
                        }
                    } else {
                        if (Character.isDigit(s[i + 1])) {
                            if ((s[i] - '0') * 10 + s[i + 1] - '0' < 27) {
                                cur += nextNext;
                            }
                        } else {
                            if (s[i] == '1') {
                                cur += 9 * nextNext;
                            } else if (s[i] == '2') {
                                cur += 6 * nextNext;
                            }
                        }
                    }
                }

                cur %= MOD;
            }

            nextNext = next;
            next = cur;
            cur = 0;
        }

        return (int) next;
    }
}

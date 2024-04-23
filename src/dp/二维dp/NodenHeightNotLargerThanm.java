import java.io.*;

// 测试链接 : https://www.nowcoder.com/practice/aaefe5896cce4204b276e213e725f3ea
public class NodenHeightNotLargerThanm {
    public static int MOD = 1000000007;

    public static int MAXN = 51;

    public static int MAXM = 51;

    public static long[][] dp1 = new long[MAXN][MAXM];

    static {
        for (int i = 0; i < MAXN; i++) {
            for (int j = 0; j < MAXM; j++) {
                dp1[i][j] = -1;
            }
        }
    }

    public static int compute1(int n, int m) {
        if (n == 0) {
            return 1;
        }

        if (m == 0) {
            return 0;
        }

        if (dp1[n][m] != -1) {
            return (int) dp1[n][m];
        }

        long ans = 0;
        for (int k = 0; k < n; k++) {
            ans += ((long) compute1(k, m - 1) % MOD * compute1(n - 1 - k, m - 1)) % MOD;
            ans %= MOD;
        }

        dp1[n][m] = ans;
        return (int) ans;
    }

    public static long[][] dp2 = new long[MAXN][MAXM];

    public static int compute2(int n, int m) {
        for (int j = 0; j <= m; j++) {
            dp2[0][j] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp2[i][j] = 0;

                for (int k = 0; k < i; k++) {
                    dp2[i][j] += (dp2[k][j - 1] % MOD * dp2[i - 1 - k][j - 1]) % MOD;
                    dp2[i][j] %= MOD;
                }
            }
        }

        return (int) dp2[n][m];
    }

    public static long[] dp3 = new long[MAXN];

    public static int compute3(int n, int m) {
        dp3[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp3[i] = 0;
        }

        for (int j = 1; j <= m; j++) {
            for (int i = n; i >= 1; i--) {
                dp3[i] = 0;

                for (int k = 0; k < i; k++) {
                    dp3[i] += (dp3[k] % MOD * dp3[i - 1 - k]) % MOD;
                    dp3[i] %= MOD;
                }
            }
        }

        return (int) dp3[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;

            // int ans = compute1(n, m);
            // int ans = compute2(n, m);
            int ans = compute3(n, m);
            out.println(ans);
        }

        out.flush();
        out.close();
        br.close();
    }
}

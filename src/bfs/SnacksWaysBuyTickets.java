import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.luogu.com.cn/problem/P4799
public class SnacksWaysBuyTickets {
    public static int MAXN = 40;

    public static long[] prices = new long[MAXN];

    public static long[] lsum = new long[1 << (MAXN >> 1)];

    public static long[] rsum = new long[1 << (MAXN >> 1)];

    public static int N;

    public static long M;

    public static long compute() {
        int lSize = dfs(0, N >> 1, 0, M, 0, lsum);
        Arrays.sort(lsum, 0, lSize);
        int rSize = dfs(N >> 1, N, 0, M, 0, rsum);
        Arrays.sort(rsum, 0, rSize);

        long ans = 0;
        for (int i = lSize - 1, j = 0; i >= 0; i--) {
            while (j < rSize && lsum[i] + rsum[j] <= M) {
                ++j;
            }

            ans += j;
        }

        return ans;
    }

    public static int dfs(int s, int e, long total, long w, int cur, long[] sum) {
        if (total > w) {
            return cur;
        }

        if (s == e) {
            sum[cur++] = total;
        } else {
            cur = dfs(s + 1, e, total, w, cur, sum);
            cur = dfs(s + 1, e, total + prices[s], w, cur, sum);
        }

        return cur;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int) in.nval;
            in.nextToken();
            M = (long) in.nval;

            for (int i = 0; i < N; i++) {
                in.nextToken();
                prices[i] = (long) in.nval;
            }

            long ans = compute();
            out.println(ans);
        }

        out.flush();
        br.close();
        out.close();
    }
}

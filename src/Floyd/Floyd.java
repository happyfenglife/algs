import java.io.*;

// 测试链接 : https://www.luogu.com.cn/problem/P2910
public class Floyd {
    public static int MAXN = 101;

    public static int MAXM = 10001;

    public static int[] path = new int[MAXM];

    public static int[][] dist = new int[MAXN][MAXN];

    public static int n, m;

    public static void floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            for (int i = 1; i <= m; i++) {
                in.nextToken();
                path[i] = (int) in.nval;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    in.nextToken();
                    dist[i][j] = (int) in.nval;
                }
            }

            floyd();
            int ans = 0;
            for (int i = 2; i <= m; i++) {
                ans += dist[path[i - 1]][path[i]];
            }

            out.println(ans);
        }

        out.flush();
        br.close();
        out.close();
    }
}

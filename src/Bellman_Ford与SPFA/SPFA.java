import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.luogu.com.cn/problem/P3385
public class SPFA {
    public static int MAXN = 2001;

    public static int MAXM = 6001;

    public static int MAXQ = 4000001;

    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXM];

    public static int[] to = new int[MAXM];

    public static int[] wt = new int[MAXM];

    public static int cnt;

    public static int[] dist = new int[MAXN];

    public static int[] updateCnt = new int[MAXN];

    public static int[] queue = new int[MAXQ];

    public static boolean[] entered = new boolean[MAXN];

    public static int l, r;

    public static int T, n, m;

    public static void build() {
        cnt = 1;
        l = r = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(updateCnt, 1, n + 1, 0);
        Arrays.fill(dist, 1, n + 1, Integer.MAX_VALUE);
        Arrays.fill(entered, 1, n + 1, false);
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        wt[cnt] = w;
        head[u] = cnt++;
    }

    public static boolean compute() {
        dist[1] = 0;
        updateCnt[1]++;
        entered[1] = true;
        queue[r++] = 1;
        while (l < r) {
            int u = queue[l++];
            entered[u] = false;

            for (int ei = head[u], v, w; ei != 0; ei = next[ei]) {
                v = to[ei];
                w = wt[ei];

                if (dist[u] + w < dist[v]) {
                    dist[v] = w + dist[u];

                    if (!entered[v]) {
                        if (updateCnt[v]++ == n) {
                            return true;
                        }

                        queue[r++] = v;
                        entered[v] = true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        in.nextToken();
        T = (int) in.nval;
        for (int i = 0; i < T; i++) {
            in.nextToken();
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            build();
            for (int j = 0, u, v, w; j < m; j++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;

                addEdge(u, v, w);
                if (w >= 0) {
                    addEdge(v, u, w);
                }
            }

            out.println(compute() ? "YES" : "NO");
        }

        out.flush();
        br.close();
        out.close();
    }
}

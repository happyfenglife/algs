import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.luogu.com.cn/problem/P4017
public class FoodLines {
    public static int MAXN = 5001;

    public static int MAXM = 500001;

    public static int[] head = new int[MAXN];

    public static int[] to = new int[MAXM];

    public static int[] next = new int[MAXM];

    public static int cnt;

    public static int n, m;

    public static int[] queue = new int[MAXN];

    public static int[] indegree = new int[MAXN];

    public static int[] lines = new int[MAXN];

    public static int l, r;

    public static int MOD = 80112002;

    public static void build() {
        cnt = 1;
        l = r = 0;
        Arrays.fill(indegree, 1, n + 1, 0);
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(lines, 1, n + 1, 0);
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
        indegree[v]++;
    }

    public static int compute() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
                lines[i] = 1;
            }
        }

        int ans = 0;
        while (l < r) {
            int u = queue[l++];

            if (head[u] == 0) {
                ans = (ans + lines[u]) % MOD;
            } else {
                for (int ei = head[u], v; ei != 0; ei = next[ei]) {
                    v = to[ei];
                    lines[v] = (lines[v] + lines[u]) % MOD;

                    if (--indegree[v] == 0) {
                        queue[r++] = v;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            build();
            for (int i = 0, u, v; i < m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                addEdge(u, v);
            }

            int ans = compute();
            out.println(ans);
        }

        out.flush();
        br.close();
        out.close();
    }
}

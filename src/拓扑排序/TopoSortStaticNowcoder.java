import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.nowcoder.com/practice/88f7e156ca7d43a1a535f619cd3f495c
public class TopoSortStaticNowcoder {
    public static int MAXN = 200001;

    public static int MAXM = 200001;

    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXM];

    public static int[] to = new int[MAXM];

    public static int[] queue = new int[MAXN];

    public static int[] indegree = new int[MAXN];

    public static int n, m;

    public static int l, r, cnt;

    public static void build() {
        l = r = 0;
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(indegree, 1, n + 1, 0);
    }

    public static void compute() {
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        int cur;
        while (l < r) {
            cur = queue[l++];

            for (int ei = head[cur], v; ei != 0; ei = next[ei]) {
                v = to[ei];

                if (--indegree[v] == 0) {
                    queue[r++] = v;
                }
            }
        }

        if (r != n) {
            System.out.println(r);
            System.out.println(-1);
        } else {
            for (int i = 0; i < n - 1; i++) {
                System.out.print(queue[i] + " ");
            }

            System.out.println(queue[n - 1]);
        }
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
        indegree[v]++;
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

            for (int i = 1, u, v; i <= m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;

                addEdge(u, v);
            }

            compute();
        }

        out.flush();
        br.close();
        out.close();
    }
}

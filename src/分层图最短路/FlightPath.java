import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接 : https://www.luogu.com.cn/problem/P4568
public class FlightPath {
    public static int MAXN = 10000;

    public static int MAXM = 100001;

    public static int MAXK = 11;

    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXM];

    public static int[] to = new int[MAXM];

    public static int[] wt = new int[MAXM];

    public static int cnt;

    public static boolean[][] visited = new boolean[MAXN][MAXK];

    public static int[][] dist = new int[MAXN][MAXK];

    public static PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));

    public static int n, m, k;

    public static int s, e;

    public static void build() {
        cnt = 1;
        heap.clear();
        Arrays.fill(head, 0, n, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                visited[i][j] = false;
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        wt[cnt] = w;
        head[u] = cnt++;
    }

    public static int compute() {
        dist[s][0] = 0;
        heap.add(new int[]{s, 0, 0});
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int u = record[0], cost = record[2];
            if (u == e) {
                return cost;
            }

            int use = record[1];
            if (visited[u][use]) {
                continue;
            }

            visited[u][use] = true;
            for (int ei = head[u], v, w; ei != 0; ei = next[ei]) {
                v = to[ei];
                w = wt[ei];

                // 不使用免费次数
                if (!visited[v][use] && dist[u][use] + w < dist[v][use]) {
                    dist[v][use] = dist[u][use] + w;
                    heap.add(new int[]{v, use, dist[u][use] + w});
                }

                // 使用免费次数
                if (use < k && !visited[v][use + 1] && dist[v][use + 1] > dist[u][use]) {
                    dist[v][use + 1] = dist[u][use];
                    heap.add(new int[]{v, use + 1, dist[u][use]});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;

            in.nextToken();
            s = (int) in.nval;
            in.nextToken();
            e = (int) in.nval;

            build();
            for (int i = 0, u, v, w; i < m; i++) {
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;

                addEdge(u, v, w);
                addEdge(v, u, w);
            }

            int ans = compute();
            out.println(ans);
        }

        out.flush();
        br.close();
        out.close();
    }
}

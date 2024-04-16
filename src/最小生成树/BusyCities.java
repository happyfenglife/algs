import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

// 测试链接 : https://www.luogu.com.cn/problem/P2330
public class BusyCities {
    public static int MAXN = 301;

    public static int MAXM = 8001;

    public static int[][] graph = new int[MAXM][3];

    public static int[] father = new int[MAXN];

    public static int n, m;

    public static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }

        return father[i];
    }

    public static boolean union(int x, int y) {
        int fx = find(x), fy = find(y);

        if (fx != fy) {
            father[fx] = fy;
            return true;
        } else {
            return false;
        }
    }

    public static int compute() {
        int ans = 0;
        for (int i = 0; i < m; i++) {
            if (union(graph[i][0], graph[i][1])) {
                ans = Math.max(ans, graph[i][2]);
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

            for (int i = 0; i < m; i++) {
                in.nextToken();
                graph[i][0] = (int) in.nval;
                in.nextToken();
                graph[i][1] = (int) in.nval;
                in.nextToken();
                graph[i][2] = (int) in.nval;
            }

            Arrays.sort(graph, 0, m, Comparator.comparingInt(e -> e[2]));
            int ans = compute();
            out.print(n - 1);
            out.print(" ");
            out.println(ans);
        }

        out.flush();
        br.close();
        out.close();
    }
}

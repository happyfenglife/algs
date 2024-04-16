import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

// 测试链接 : https://www.luogu.com.cn/problem/P3366
public class Kruskal {
    public static int MAXN = 5001;

    public static int MAXM = 200001;

    public static int[][] graph = new int[MAXM][3];

    public static int[] father = new int[MAXN];

    public static int n, m;

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

    public static void build() {
        for (int i = 1; i <= n; i++) {
            father[i] = i;
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

            build();

            for (int i = 1; i <= m; i++) {
                in.nextToken();
                graph[i][0] = (int) in.nval;
                in.nextToken();
                graph[i][1] = (int) in.nval;
                in.nextToken();
                graph[i][2] = (int) in.nval;
            }

            Arrays.sort(graph, 1, m + 1, Comparator.comparingInt(a -> a[2]));

            int cnt = 0;
            int ans = 0;
            for (int i = 1; i <= m; i++) {
                if (union(graph[i][0], graph[i][1])) {
                    ++cnt;
                    ans += graph[i][2];
                }
            }

            out.println(cnt == n - 1 ? ans : "orz");
        }

        out.flush();
        br.close();
        out.close();
    }
}

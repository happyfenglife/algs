import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// 测试链接 : https://www.luogu.com.cn/problem/P3366
public class PrimDynamic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            in.nextToken();
            int m = (int) in.nval;

            List<List<int[]>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 1, x, y, z; i <= m; i++) {
                in.nextToken();
                x = (int) in.nval;
                in.nextToken();
                y = (int) in.nval;
                in.nextToken();
                z = (int) in.nval;

                graph.get(x).add(new int[]{y, z});
                graph.get(y).add(new int[]{x, z});
            }

            PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            boolean[] visited = new boolean[n + 1];
            heap.addAll(graph.get(1));
            visited[1] = true;

            int ans = 0;
            int nodeCnt = 1;
            while (!heap.isEmpty()) {
                int[] path = heap.poll();
                if (visited[path[0]]) {
                    continue;
                }

                ans += path[1];
                nodeCnt++;
                visited[path[0]] = true;
                heap.addAll(graph.get(path[0]));
            }

            out.println(nodeCnt == n ? ans : "orz");
        }

        out.flush();
        br.close();
        out.close();
    }
}

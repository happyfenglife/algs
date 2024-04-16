import java.io.*;
import java.util.Arrays;

// 测试链接 : https://www.luogu.com.cn/problem/P3366
public class PrimStatic {
    public static int MAXN = 5001;

    public static int MAXM = 400001;

    public static int[] head = new int[MAXN];

    public static int[] next = new int[MAXM];

    public static int[] to = new int[MAXM];

    public static int[] wt = new int[MAXM];

    public static int[][] heap = new int[MAXN][2];

    // where[v] = -1，表示v这个节点，从来没有进入过堆
    // where[v] = -2，表示v这个节点，已经弹出过了
    // where[v] = i(>=0)，表示v这个节点，在堆上的i位置
    public static int[] where = new int[MAXN];

    public static int heapSize;

    public static int cnt;

    public static int nodeCnt;

    public static int n, m, u, w;

    public static void build() {
        cnt = 1;
        heapSize = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(where, 1, n + 1, -1);
    }

    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        wt[cnt] = w;
        head[u] = cnt++;
    }

    public static boolean isEmpty() {
        return heapSize == 0;
    }

    public static void swap(int i, int j) {
        where[heap[i][0]] = j;
        where[heap[j][0]] = i;

        int[] tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static void heapInsert(int i) {
        while (heap[(i - 1) / 2][1] > heap[i][1]) {
            swap((i - 1) / 2, i);
            i = (i - 1) / 2;
        }
    }

    public static void heapify(int i) {
        int l = 2 * i + 1;

        while (l < heapSize) {
            int best = l + 1 < heapSize && heap[l + 1][1] < heap[l][1] ? l + 1 : l;
            best = heap[i][1] < heap[best][1] ? i : best;
            if (i == best) {
                break;
            }

            swap(i, best);
            i = best;
            l = i * 2 + 1;
        }
    }

    public static void pop() {
        u = heap[0][0];
        w = heap[0][1];
        swap(0, --heapSize);
        heapify(0);

        where[u] = -2;
        nodeCnt++;
    }

    public static void addOrUpdateOrIgnore(int ei) {
        int v = to[ei], w = wt[ei];

        if (where[v] == -1) {
            heap[heapSize][0] = v;
            heap[heapSize][1] = w;
            where[v] = heapSize++;
            heapInsert(where[v]);
        } else if (where[v] >= 0) {
            heap[where[v]][1] = Math.min(heap[where[v]][1], w);
            heapInsert(where[v]);
        }
    }

    public static int compute() {
        where[1] = -2;
        nodeCnt = 1;
        int ans = 0;
        for (int ei = head[1]; ei != 0; ei = next[ei]) {
            addOrUpdateOrIgnore(ei);
        }

        while (!isEmpty()) {
            pop();

            ans += w;
            for (int ei = head[u]; ei != 0; ei = next[ei]) {
                addOrUpdateOrIgnore(ei);
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

            for (int i = 1, x, y, z; i <= m; i++) {
                in.nextToken();
                x = (int) in.nval;
                in.nextToken();
                y = (int) in.nval;
                in.nextToken();
                z = (int) in.nval;

                addEdge(x, y, z);
                addEdge(y, x, z);
            }

            int ans = compute();
            out.println(nodeCnt == n ? ans : "orz");
        }

        out.flush();
        br.close();
        out.close();
    }
}

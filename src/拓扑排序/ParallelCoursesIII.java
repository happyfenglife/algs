import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/parallel-courses-iii/
public class ParallelCoursesIII {
    public static int MAXN = 50001;

    public static int MAXM = 50001;

    public static int[] head = new int[MAXN];

    public static int[] to = new int[MAXM];

    public static int[] next = new int[MAXM];

    public static int[] indegree = new int[MAXN];

    public static int[] queue = new int[MAXN];

    public static int l, r;

    public static int cnt;

    public static void build(int n) {
        cnt = 1;
        l = r = 0;
        Arrays.fill(head, 1, n + 1, 0);
        Arrays.fill(indegree, 1, n + 1, 0);
    }

    public static void addEdge(int u, int v) {
        next[cnt] = head[u];
        to[cnt] = v;
        head[u] = cnt++;
        indegree[v]++;
    }

    public static int minimumTime(int n, int[][] relations, int[] time) {
        build(n);
        for (int[] relation : relations) {
            int u = relation[0], v = relation[1];
            addEdge(u, v);
        }

        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
                ans[i] = time[i - 1];
            }
        }

        while (l < r) {
            int u = queue[l++];

            for (int ei = head[u], v; ei != 0; ei = next[ei]) {
                v = to[ei];
                ans[v] = Math.max(ans[v], ans[u] + time[v - 1]);

                if (--indegree[v] == 0) {
                    queue[r++] = v;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i : ans) {
            max = Math.max(max, i);
        }

        return max;
    }
}

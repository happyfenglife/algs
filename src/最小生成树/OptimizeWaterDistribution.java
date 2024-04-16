import java.util.Arrays;
import java.util.Comparator;

// 测试链接 : https://leetcode.cn/problems/optimize-water-distribution-in-a-village/
public class OptimizeWaterDistribution {
    public static int MAXN = 10001;

    public static int MAXM = 20001;

    public static int[][] graph = new int[MAXM][3];

    public static int[] father = new int[MAXN];

    public static int cnt;

    public static void build(int n) {
        cnt = 0;

        for (int i = 0; i <= n; i++) {
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

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        build(n);

        for (int i = 0; i < n; i++, ++cnt) {
            graph[cnt][0] = 0;
            graph[cnt][1] = i + 1;
            graph[cnt][2] = wells[i];
        }

        for (int i = 0; i < pipes.length; i++, ++cnt) {
            graph[cnt][0] = pipes[i][0];
            graph[cnt][1] = pipes[i][1];
            graph[cnt][2] = pipes[i][2];
        }

        Arrays.sort(graph, 0, cnt, Comparator.comparingInt(e -> e[2]));
        int ans = 0;
        for (int i = 0; i < cnt; i++) {
            if (union(graph[i][0], graph[i][1])) {
                ans += graph[i][2];
            }
        }

        return ans;
    }
}

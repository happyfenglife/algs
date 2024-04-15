import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/loud-and-rich/
public class LoudAndRich {
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];
        for (int i = 0, a, b; i < richer.length; i++) {
            a = richer[i][0];
            b = richer[i][1];
            graph.get(a).add(b);
            indegree[b]++;
        }

        int[] queue = new int[n];
        int l = 0, r = 0;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }

        while (l < r) {
            int u = queue[l++];
            for (Integer v : graph.get(u)) {
                if (--indegree[v] == 0) {
                    queue[r++] = v;
                }

                if (quiet[ans[u]] < quiet[ans[v]]) {
                    ans[v] = ans[u];
                }
            }
        }

        return ans;
    }
}

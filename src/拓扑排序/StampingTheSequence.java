import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/stamping-the-sequence/
public class StampingTheSequence {
    public static int[] movesToStamp(String stamp, String target) {
        int n = target.length();
        int m = stamp.length();
        int len = n - m + 1;

        int[] indegree = new int[len];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] queue = new int[len];
        int l = 0, r = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < m; j++) {
                if (stamp.charAt(j) != target.charAt(i + j)) {
                    indegree[i]++;
                    graph.get(j + i).add(i);
                }
            }

            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        boolean[] corrected = new boolean[n];
        while (l < r) {
            int cur = queue[l++];

            for (int j = cur; j < m + cur; j++) {
                if (!corrected[j]) {
                    for (Integer next : graph.get(j)) {
                        if (--indegree[next] == 0) {
                            queue[r++] = next;
                        }
                    }

                    corrected[j] = true;
                }
            }
        }

        if (r != len) {
            return new int[0];
        }

        int[] ans = new int[r];
        for (int i = r - 1, j = 0; i >= 0; i--, j++) {
            ans[j] = queue[i];
        }

        return ans;
    }
}
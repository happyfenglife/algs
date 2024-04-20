import java.util.*;

// 测试链接 : https://leetcode.cn/problems/network-delay-time
public class DijkstraDynamic {
    public static int networkDelayTime(int[][] times, int n, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[1]));
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] time : times) {
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }

        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        heap.add(new int[]{k, 0});

        while (!heap.isEmpty()) {
            int u = heap.poll()[0];
            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            for (int[] e : graph.get(u)) {
                int v = e[0], w = e[1];
                if (visited[e[0]]) {
                    continue;
                }

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    heap.add(new int[]{v, dist[v]});
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i]);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }
}

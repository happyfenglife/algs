import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// 测试链接 : https://leetcode.cn/problems/DFPeFJ/
public class VisitCityMinCost {
    public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
        int n = charge.length;
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            graph.get(path[0]).add(new int[]{path[1], path[2]});
            graph.get(path[1]).add(new int[]{path[0], path[2]});
        }

        boolean[][] visited = new boolean[n][cnt + 1];
        int[][] dist = new int[n][cnt + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= cnt; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
        heap.add(new int[]{start, 0, 0});
        while (!heap.isEmpty()) {
            int[] record = heap.poll();
            int cur = record[0], power = record[1], cost = record[2];

            if (cur == end) {
                return cost;
            }

            if (visited[cur][power]) {
                continue;
            }

            visited[cur][power] = true;
            // 充电
            if (power < cnt) {
                if (!visited[cur][power + 1] && cost + charge[cur] < dist[cur][power + 1]) {
                    dist[cur][power + 1] = cost + charge[cur];
                    heap.add(new int[]{cur, power + 1, cost + charge[cur]});
                }
            }

            // 不充电
            for (int[] e : graph.get(cur)) {
                int nextCur = e[0];
                int nextPower = power - e[1];
                int nextCost = cost + e[1];

                if (nextPower >= 0 && !visited[nextCur][nextPower] && nextCost < dist[nextCur][nextPower]) {
                    dist[nextCur][nextPower] = nextCost;
                    heap.add(new int[]{nextCur, nextPower, nextCost});
                }
            }
        }

        return -1;
    }
}

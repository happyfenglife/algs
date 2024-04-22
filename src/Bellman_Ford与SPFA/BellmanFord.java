import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/cheapest-flights-within-k-stops/
public class BellmanFord {
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = Arrays.copyOf(dist, n);

            for (int[] flight : flights) {
                if (dist[flight[0]] != Integer.MAX_VALUE) {
                    next[flight[1]] = Math.min(next[flight[1]], dist[flight[0]] + flight[2]);
                }
            }

            dist = next;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}

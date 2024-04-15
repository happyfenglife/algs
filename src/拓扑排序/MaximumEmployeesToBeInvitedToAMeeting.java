import java.util.Arrays;

// 测试链接 : https://leetcode.cn/problems/maximum-employees-to-be-invited-to-a-meeting/
public class MaximumEmployeesToBeInvitedToAMeeting {
    public static int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] indegree = new int[n];
        for (int v : favorite) {
            indegree[v]++;
        }

        int[] queue = new int[n];
        int l = 0, r = 0;
        int maxBigRingSize = 0, smallRings = 0;
        int[] chains = new int[n];
        Arrays.fill(chains, 1);
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        while (l < r) {
            int u = queue[l++];
            int v = favorite[u];
            if (--indegree[v] == 0) {
                queue[r++] = v;
            }

            chains[v] = Math.max(chains[v], chains[u] + 1);
        }

        for (int i = 0, j, ringSize; i < n; ++i) {
            if (indegree[i] == 0) {
                continue;
            }

            j = i;
            ringSize = 0;
            while (indegree[j] != 0) {
                indegree[j] = 0;
                ringSize++;
                j = favorite[j];
            }

            // 小环
            if (ringSize == 2) {
                smallRings += chains[i] + chains[favorite[i]];
            } else {
                maxBigRingSize = Math.max(maxBigRingSize, ringSize);
            }
        }

        return Math.max(smallRings, maxBigRingSize);
    }
}

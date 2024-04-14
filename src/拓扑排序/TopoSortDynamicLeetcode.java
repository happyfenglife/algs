import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/course-schedule-ii/
public class TopoSortDynamicLeetcode {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            indegree[prerequisite[0]]++;
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        int l = 0, r = 0;
        int[] queue = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        while (l < r) {
            int cur = queue[l++];

            for (Integer neighbor : graph.get(cur)) {
                if (--indegree[neighbor] == 0) {
                    queue[r++] = neighbor;
                }
            }
        }

        return l == numCourses ? queue : new int[0];
    }
}

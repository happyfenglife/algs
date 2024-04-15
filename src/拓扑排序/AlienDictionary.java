import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/Jf1JuT/
public class AlienDictionary {
    public static String alienOrder(String[] words) {
        int[] indegree = new int[26];
        Arrays.fill(indegree, -1);
        for (String word : words) {
            for (char c : word.toCharArray()) {
                indegree[c - 'a'] = 0;
            }
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        int n = words.length;
        for (int i = 0, j, len; i < n - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            len = Math.min(cur.length(), next.length());

            j = 0;
            for (; j < len; j++) {
                if (cur.charAt(j) != next.charAt(j)) {
                    indegree[next.charAt(j) - 'a']++;
                    graph.get(cur.charAt(j) - 'a').add(next.charAt(j) - 'a');
                    break;
                }
            }

            if (j == next.length() && j < cur.length()) {
                return "";
            }
        }

        int[] queue = new int[26];
        int l = 0, r = 0, cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (indegree[i] != -1) {
                cnt++;
            }

            if (indegree[i] == 0) {
                queue[r++] = i;
            }
        }

        while (l < r) {
            int cur = queue[l++];
            sb.append((char) ('a' + cur));

            for (Integer next : graph.get(cur)) {
                if (--indegree[next] == 0) {
                    queue[r++] = next;
                }
            }
        }

        return r != cnt ? "" : sb.toString();
    }
}

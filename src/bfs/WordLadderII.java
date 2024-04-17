import java.util.*;

// 测试链接 : https://leetcode.cn/problems/word-ladder-ii/
public class WordLadderII {
    public static HashSet<String> dict;

    public static HashSet<String> curLevel = new HashSet<>();

    public static HashSet<String> nextLevel = new HashSet<>();

    public static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    public static LinkedList<String> path = new LinkedList<>();

    public static List<List<String>> ans = new ArrayList<>();

    public static void build(List<String> wordList) {
        dict = new HashSet<>(wordList);
        graph.clear();
        path.clear();
        ans.clear();
        curLevel.clear();
        nextLevel.clear();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        build(wordList);

        if (!dict.contains(endWord)) {
            return ans;
        }

        if (bfs(beginWord, endWord)) {
            dfs(endWord, beginWord);
        }

        return ans;
    }

    public static boolean bfs(String start, String end) {
        boolean flag = false;
        curLevel.add(start);
        while (!curLevel.isEmpty()) {
            dict.removeAll(curLevel);

            for (String word : curLevel) {
                char[] w = word.toCharArray();

                for (int i = 0; i < w.length; i++) {
                    char old = w[i];

                    for (char c = 'a'; c <= 'z'; ++c) {
                        w[i] = c;
                        String s = String.valueOf(w);

                        if (dict.contains(s) && !s.equals(word)) {
                            if (s.equals(end)) {
                                flag = true;
                            }

                            graph.putIfAbsent(s, new ArrayList<>());
                            graph.get(s).add(word);
                            nextLevel.add(s);
                        }
                    }

                    w[i] = old;
                }
            }

            if (flag) {
                return true;
            } else {
                HashSet<String> tmp = curLevel;
                curLevel = nextLevel;
                nextLevel = tmp;
                nextLevel.clear();
            }
        }

        return false;
    }

    public static void dfs(String cur, String target) {
        path.addFirst(cur);

        if (cur.equals(target)) {
            ans.add(new ArrayList<>(path));
        } else {
            for (String next : graph.get(cur)) {
                dfs(next, target);
            }
        }

        path.removeFirst();
    }
}

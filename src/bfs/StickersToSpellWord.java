import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/stickers-to-spell-word/
public class StickersToSpellWord {
    public static int MAXN = 401;

    public static String[] queue = new String[MAXN];

    public static int l, r;

    public static List<List<String>> graph = new ArrayList<>();

    public static HashSet<String> visited = new HashSet();

    public static String strSort(String str) {
        char[] s = str.toCharArray();
        Arrays.sort(s);

        return String.valueOf(s);
    }

    public static String strSubtraction(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int n = s1.length(), m = s2.length();

        for (int i = 0, j = 0; i < n; ) {
            if (j == m) {
                sb.append(s1.charAt(i++));
            } else {
                if (s1.charAt(i) == s2.charAt(j)) {
                    ++i;
                    ++j;
                } else if (s1.charAt(i) < s2.charAt(j)) {
                    sb.append(s1.charAt(i++));
                } else {
                    ++j;
                }
            }
        }

        return sb.toString();
    }

    static {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static void build() {
        for (int i = 0; i < 26; i++) {
            graph.get(i).clear();
        }

        l = r = 0;
        visited.clear();
    }

    public static int minStickers(String[] stickers, String target) {
        build();

        for (String sticker : stickers) {
            sticker = strSort(sticker);
            graph.get(sticker.charAt(0) - 'a').add(sticker);

            for (int i = 1; i < sticker.length(); i++) {
                if (sticker.charAt(i) != sticker.charAt(i - 1)) {
                    graph.get(sticker.charAt(i) - 'a').add(sticker);
                }
            }
        }

        int level = 0;
        target = strSort(target);
        queue[r++] = target;
        visited.add(target);

        while (l < r) {
            int size = r - l;
            ++level;

            for (int i = 0; i < size; i++) {
                String cur = queue[l++];

                for (String sticker : graph.get(cur.charAt(0) - 'a')) {
                    String next = strSubtraction(cur, sticker);

                    if (next.isEmpty()) {
                        return level;
                    } else if (!visited.contains(next)) {
                        visited.add(next);
                        queue[r++] = next;
                    }
                }
            }
        }

        return -1;
    }
}

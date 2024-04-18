import java.util.HashSet;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/word-ladder/
public class WordLadder {
    public static HashSet<String> dict;

    public static HashSet<String> smallLevel = new HashSet<>();

    public static HashSet<String> bigLevel = new HashSet<>();

    public static HashSet<String> nextLevel = new HashSet<>();


    public static void build(List<String> wordList) {
        dict = new HashSet<>(wordList);
        smallLevel.clear();
        bigLevel.clear();
        nextLevel.clear();
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        build(wordList);

        if (!dict.contains(endWord)) {
            return 0;
        }

        smallLevel.add(beginWord);
        bigLevel.add(endWord);
        dict.remove(beginWord);
        dict.remove(endWord);

        for (int len = 2; !smallLevel.isEmpty(); len++) {
            for (String word : smallLevel) {
                char[] s = word.toCharArray();

                for (int i = 0; i < s.length; i++) {
                    char old = s[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (old == c) {
                            continue;
                        }

                        s[i] = c;
                        String w = String.valueOf(s);
                        if (bigLevel.contains(w)) {
                            return len;
                        }

                        if (dict.contains(w)) {
                            nextLevel.add(w);
                            dict.remove(w);
                        }
                    }

                    s[i] = old;
                }
            }

            HashSet<String> tmp = smallLevel;
            if (nextLevel.size() <= bigLevel.size()) {
                smallLevel = nextLevel;
            } else {
                smallLevel = bigLevel;
                bigLevel = nextLevel;
            }

            nextLevel = tmp;
            nextLevel.clear();
        }

        return 0;
    }
}

// 测试链接 : https://leetcode.cn/problems/word-search/
public class WordSearch {
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean dfs(char[][] board, int i, int j, char[] word, int s) {
        if (s == word.length) {
            return true;
        }

        int n = board.length, m = board[0].length;
        if (i < 0 || i >= n || j < 0 || j >= m || board[i][j] != word[s]) {
            return false;
        }

        char tmp = board[i][j];
        board[i][j] = 0;
        boolean ans = dfs(board, i - 1, j, word, s + 1)
                || dfs(board, i + 1, j, word, s + 1)
                || dfs(board, i, j - 1, word, s + 1)
                || dfs(board, i, j + 1, word, s + 1);
        board[i][j] = tmp;

        return ans;
    }
}

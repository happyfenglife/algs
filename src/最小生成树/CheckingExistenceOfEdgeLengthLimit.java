import java.util.Arrays;
import java.util.Comparator;

public class CheckingExistenceOfEdgeLengthLimit {
    public static int MAXN = 100001;

    public static int MAXM = 100001;

    public static int[] father = new int[MAXN];

    public static int[][] questions = new int[MAXM][4];

    public static void build(int n) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    public static int find(int i) {
        if (i != father[i]) {
            father[i] = find(father[i]);
        }

        return father[i];
    }

    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    public static void union(int x, int y) {
        father[find(x)] = find(y);
    }

    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.sort(edgeList, Comparator.comparingInt(e -> e[2]));
        build(n);

        int k = queries.length;
        int m = edgeList.length;
        boolean[] ans = new boolean[k];
        for (int i = 0; i < k; i++) {
            questions[i][0] = queries[i][0];
            questions[i][1] = queries[i][1];
            questions[i][2] = queries[i][2];
            questions[i][3] = i;
        }

        Arrays.sort(questions, 0, k, Comparator.comparingInt(a -> a[2]));
        for (int i = 0, j = 0; j < k; j++) {
            for (; i < m && edgeList[i][2] < questions[j][2]; i++) {
                union(edgeList[i][0], edgeList[i][1]);
            }

            ans[questions[j][3]] = isSameSet(questions[j][0], questions[j][1]);
        }

        return ans;
    }
}

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

class Solution531 {

    public static void main(String[] args) {
        Solution531 s = new Solution531();


        System.out.println(s.rangeBitwiseAnd(5, 7));
        System.out.println(s.maximalSquare(new char[][]{
                new char[]{1, 0, 1, 0, 0},
                new char[]{1, 0, 1, 1, 1},
                new char[]{1, 1, 1, 1, 1},
                new char[]{1, 0, 0, 1, 0}
        }));
    }


    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][][] map = new int[m][n][2];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curr = matrix[i][j];
                if (i == 0 && j == 0) {
                    map[i][j][0] = 0;
                    map[i][j][1] = 0;
                } else if (i == 0) {
                    map[i][j][1] = 0;
                    map[i][j][0] = curr == 0 ? 0 : (map[i][j - 1][0] + 1);
                } else if (j == 0) {
                    map[i][j][0] = 0;
                    map[i][j][1] = curr == 0 ? 0 : (map[i - 1][j][1] + 1);
                } else {
                    map[i][j][0] = curr == 0 ? 0 : (map[i][j - 1][0] + 1);
                    map[i][j][1] = curr == 0 ? 0 : (map[i - 1][j][1] + 1);
                }
                if (curr == 1) {
                    max = Math.max(max, (curr + map[i][j][0]) * (curr + map[i][j][1]));
                }
            }
        }
        return max;
    }

    public int rangeBitwiseAnd(int m, int n) {
        int trans = 0;
        while (m != n) {
            ++trans;
            m >>= 1;
            n >>= 1;
        }
        return m << trans;
    }
}
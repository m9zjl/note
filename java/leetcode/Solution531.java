import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

class Solution407 {

    public static void main(String[] args) {
        Solution407 s = new Solution407();

        /**
         * [[12,13,1,12],[13,4,13,12],[13,8,10,12],[12,13,12,12],[13,13,13,13]]
         */
//        int[][] map = new int[][]{
//                new int[]{12, 13, 1, 12},
//                new int[]{13, 4, 13, 12}, // 12 13
//                new int[]{13, 8, 10, 12}, // 12 12
//                new int[]{12, 13, 12, 12},// 13 12
//                new int[]{13, 13, 13, 13},
//        };
        System.out.println(s.backspaceCompare("ab##", "c#d#"));
    }

    private static class Cell implements Comparable<Cell> {
        private int row;
        private int col;
        private int value;

        public Cell(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.value = v;
        }

        @Override
        public int compareTo(Cell other) {
            return value - other.value;
        }
    }

    public int trapRainWater(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        PriorityQueue<Cell> queue = new PriorityQueue<>();

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heights[i][0]));
            queue.offer(new Cell(i, n - 1, heights[i][n - 1]));
        }
        for (int i = 0; i < n; i++) {
            visited[0][i] = true;
            visited[m - 1][i] = true;
            queue.offer(new Cell(0, i, heights[0][i]));
            queue.offer(new Cell(m - 1, i, heights[m - 1][i]));
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int result = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            for (int[] dir : dirs) {
                int x = cell.row + dir[0];
                int y = cell.col + dir[1];
                if (x >= 0 && y >= 0 && x < m && y < n && !visited[x][y]) {
                    visited[x][y] = true;
                    result += Math.max(0, cell.value - heights[x][y]);
                    queue.offer(new Cell(x, y, Math.max(heights[x][y], cell.value)));
                }
            }
        }
        Set set = new HashSet<>();
        return result;
    }

    public boolean backspaceCompare(String S, String T) {
        Stack<Character> stack = new Stack<>();
        String s1 = getCompressedStr(S, stack);
        String s2 = getCompressedStr(T, stack);
        return s1.compareTo(s2) == 0;
    }

    private String getCompressedStr(String s, Stack<Character> stack) {
        stack.clear();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
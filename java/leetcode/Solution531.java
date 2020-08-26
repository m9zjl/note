
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution531 {

    public static void main(String[] args) {
        Solution531 s = new Solution531();
//        System.out.println(s.rangeBitwiseAnd(5, 7));
//        System.out.println(s.maximalSquare(new char[][]{
//                new char[]{'1', '0', '1', '0', '0'},
//                new char[]{'1', '0', '1', '1', '1'},
//                new char[]{'1', '1', '1', '1', '1'},
//                new char[]{'1', '0', '0', '1', '0'}
//        }));

        System.out.println(s.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
//        s.test();


    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int l = 0;
        int r = 0;
        long prod = 1L;
        int sum = 0;

        while (l < nums.length) {
            while (r < nums.length && prod * nums[r] < k) {
                prod *= nums[r];
                r++;
            }
            if (r < nums.length && prod * nums[r] >= k) {
                r--;
            }
            sum += (r - l + 1);
            prod /= nums[l];
            l++;
        }
        return sum;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            result[i] = calcDFS(graph, queries.get(i).get(0), queries.get(i).get(1), new HashSet<>());
        }
        return result;
    }

    private double calcDFS(Map<String, Map<String, Double>> graph, String l, String r, HashSet<String> visited) {
        if (!graph.containsKey(l) || !graph.containsKey(r)) {
            return -1.0;
        }
        if (graph.get(l).containsKey(r)) {
            return graph.get(l).get(r);
        }
        visited.add(l);
        for (Map.Entry<String, Double> entry : graph.get(l).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                double weight = calcDFS(graph, entry.getKey(), r, visited);
                if (weight != -1) {
                    return entry.getValue() * weight;
                }
            }
        }
        return -1.0d;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> list = equations.get(i);
            String l = list.get(0);
            String r = list.get(1);
            double d = values[i];
            map.computeIfAbsent(l, a -> new HashMap<>()).put(r, d);
            map.computeIfAbsent(r, a -> new HashMap<>()).put(l, 1 / d);
        }
        return map;
    }


    int[][] dirs = new int[][]{new int[]{0, -1}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{1, 0}};
    int res = 0;

    public int uniquePathsIII(int[][] grid) {
        int[] start = new int[2];
        int[] end = new int[2];
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    count++;
                } else if (grid[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                } else if (grid[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        dfs(grid, start[0], start[1], end[0], end[1], count);
        return res;
    }

    public void dfs(int[][] map, int startX, int startY, int endX, int endY, int count) {
        int m = map.length;
        int n = map[0].length;

        for (int i = 0; i < 4; i++) {
            int x = startX + dirs[i][0];
            int y = startY + dirs[i][1];
            if (x >= 0 && x < m && y >= 0 && y < n) {
                if (map[x][y] == 0) {
                    map[x][y] = 1;
                    dfs(map, x, y, endX, endY, count - 1);
                    map[x][y] = 0;
                } else if (map[x][y] == 2 && count == 0) {
                    res++;
                }
            }
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int l = -1;
        int r = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[0] <= intervals[i][1] || newInterval[0] <= intervals[i][0]) {
                l = i;
            }
            if (newInterval[1] >= intervals[i][0]) {
                r = i;
            }
        }
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            l++;
        }
        if (newInterval[1] > intervals[intervals.length - 1][1]) {
            r++;
        }

        List<int[]> res = new ArrayList<>();
        //left
        for (int i = 0; i < l; i++) {
            res.add(intervals[i]);
        }
        //middle
        int newL = l == intervals.length ? newInterval[0] : Math.min(intervals[l][0], newInterval[0]);
        int newR = r == intervals.length ? newInterval[1] : Math.max(intervals[r][1], newInterval[1]);
        res.add(new int[]{newL, newR});
        //right
        for (int i = r + 1; i < intervals.length; i++) {
            res.add(intervals[i]);
        }
        return res.toArray(new int[][]{});
    }

    public int compareVersion(String version1, String version2) {
        String[] vers1 = version1.split("\\.");
        String[] vers2 = version2.split("\\.");
        int m = vers1.length;
        int n = vers2.length;
        int l = 0;
        int r = 0;
        Integer.bitCount(23423);
        while (l < m && r < n) {
            int ver1 = Integer.parseInt(vers1[l]);
            int ver2 = Integer.parseInt(vers2[r]);
            if (ver1 != ver2) {
                return ver1 > ver2 ? 1 : -1;
            }
            l++;
            r++;
        }
        while (l < m) {
            int ver1 = Integer.parseInt(vers1[l++]);
            int ver2 = 0;
            if (ver1 != ver2) {
                return ver1 > ver2 ? 1 : -1;
            }
        }
        while (r < n) {
            int ver1 = 0;
            int ver2 = Integer.parseInt(vers2[r++]);
            if (ver1 != ver2) {
                return ver1 > ver2 ? 1 : -1;
            }
        }
        List<int[]> res = new ArrayList<>();
        res.toArray(new int[][]{});
        return 0;
    }

    private void test() {
        LRUCache lru = new LRUCache(1);
        lru.put(2, 1);
        lru.get(2);
        lru.put(3, 2);
        System.out.println(lru.get(2));

    }


    class LRUCache {

        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

        }

        Node head = new Node(-1, -1);
        Node tail = new Node(-1, -1);
        int capacity;
        Map<Integer, Node> values;

        public LRUCache(int capacity) {
            this.values = new HashMap<>(capacity * 2);
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;

        }

        public int get(int key) {
            Node node = values.get(key);
            if (node == null) {
                return -1;
            }
            node.pre.next = node.next;
            node.next.pre = node.pre;
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
            return node.val;
        }

        public void put(int key, int value) {
            Node node = values.get(key);
            if (node == null) {
                if (values.size() >= capacity) {
                    values.remove(tail.pre.key);
                    tail.pre.pre.next = tail;
                    tail.pre = tail.pre.pre;
                }
                node = new Node(key, value);
                values.put(key, node);
            } else {
                node.val = value;
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }
    }

    public String largestTimeFromDigits(int[] A) {
        int maxh = -1;
        int maxs = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == j) {
                    continue;
                }
                int h = A[i] * 10 + A[j];
                if (h < 24 && h >= maxh) {

                    for (int l = 0; l < 4; l++) {
                        if (l == i || l == j) {
                            continue;
                        }
                        for (int r = 0; r < 4; r++) {
                            if (r == i || r == j || l == r) {
                                continue;
                            }
                            int s = A[l] * 10 + A[r];
                            if (s < 60 && (h > maxh || (h == maxh && s > maxs))) {
                                maxh = h;
                                maxs = s;
                            }
                        }
                    }

                }
            }
        }
        if (maxh == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxh / 10);
        sb.append(maxh % 10);
        sb.append(':');
        sb.append(maxs / 10);
        sb.append(maxs % 10);
        return sb.toString();
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Point>> map = new HashMap<>();

        bfs(root, map);
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.comparingInt(a -> a));
        List<List<Integer>> results = new ArrayList<>();
        for (Integer key : keys) {
            map.get(key).sort((a, b) -> a.index - b.index == 0 ? a.val - b.val : a.index - b.index);
            List<Integer> tmp = new ArrayList<>();
            for (Point point : map.get(key)) {
                tmp.add(point.val);
            }
            results.add(tmp);
        }
        return results;
    }

    class Point {
        int index;
        int val;

        public Point(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public void bfs(TreeNode node, Map<Integer, List<Point>> map) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> indexQ = new LinkedList<>();
        queue.add(node);
        indexQ.add(0);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int index = indexQ.poll();
            List<Point> res = map.computeIfAbsent(index, a -> new ArrayList<>());
            res.add(new Point(index, cur.val));
            if (cur.left != null) {
                indexQ.offer(index - 1);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                indexQ.offer(index + 1);
                queue.offer(cur.right);
            }
        }
    }


    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (l < r && !valid(s.charAt(l))) {
                l++;
            }
            while (l < r && !valid(s.charAt(r))) {
                r--;
            }
            if (l >= r) {
                return true;
            }
            char lChar = s.charAt(l);
            int lIndex = lChar >= 'a' && lChar <= 'z' ? lChar - 'a' : lChar - 'A';
            char rChar = s.charAt(r);
            int rIndex = rChar >= 'a' && rChar <= 'z' ? rChar - 'a' : rChar - 'A';
            if (lIndex != rIndex) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public boolean valid(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }


    public void testHashSet() {
        MyHashSet hashSet = new MyHashSet();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.remove(5);
        hashSet.contains(4);
        System.out.println(hashSet.contains(2));    // returns false (already removed)

    }


    class MyHashSet {

        float expandFactor = 0.75f;

        Node[] nodes;

        int initialCapacity = 4;

        class Node {
            int val;
            Node next;

            public Node(int val) {
                this.val = val;
            }
        }

        int mask = initialCapacity - 1;
        int currOccupied = 0;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            nodes = new Node[initialCapacity];
        }

        public void add(int key) {
            int index = Objects.hash(key) & mask;
            Node node = nodes[index];
            if (node == null) {
                node = new Node(key);
                nodes[index] = node;
                currOccupied++;
                if (currOccupied > nodes.length * expandFactor) {
                    rehash();
                }
            } else {
                if (node.val == key) {
                    return;
                }
                boolean exists = false;
                while (node.next != null) {
                    if (node.val == key) {
                        exists = true;
                        break;
                    }
                    node = node.next;
                }
                if (!exists) {
                    node.next = new Node(key);
                }
            }
        }

        public void rehash() {
            Node[] newNodes = new Node[nodes.length * 2];
            mask = newNodes.length - 1;
            Node[] oldNodes = nodes;
            currOccupied = 0;
            nodes = newNodes;
            for (Node node : oldNodes) {
                while (node != null) {
                    this.add(node.val);
                    node = node.next;
                }
            }
        }

        public void remove(int key) {
            int index = Objects.hash(key) & mask;
            Node node = nodes[index];
            if (node == null) {
                return;
            }
            // node is the first node;
            if (node.val == key) {
                nodes[index] = node.next;
                return;
            }
            // node is not the first one
            Node pre = node;
            node = node.next;
            while (node != null && node.val != key && node.next != null) {
                node = node.next;
                pre = pre.next;
            }
            if (node != null && node.val == key) {
                pre.next = node.next;
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            int index = Objects.hash(key) & mask;
            Node node = nodes[index];
            if (node == null) {
                return false;
            }
            // node is the first node;
            if (node.val == key) {
                return true;
            }
            // node is not the first one
            Node pre = node;
            node = node.next;
            while (node != null && node.val != key && node.next != null) {
                node = node.next;
                pre = pre.next;
            }
            if (node != null && node.val == key) {
                pre.next = node.next;
                return true;
            }
            return false;
        }
    }


    class Result {
        int curEdge;
        int curPath;
        int edge;
        int path;

        public Result(int a, int b, int c, int d) {
            this.curEdge = a;
            this.curPath = b;
            this.edge = c;
            this.path = d;
        }
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        Result result = maxPath(root);
        return result.path;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public Result maxPath(TreeNode root) {
        if (root == null) {
            return new Result(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
        }
        Result left = maxPath(root.left);
        Result right = maxPath(root.right);

        int curEdge = Math.max(0, Math.max(left.curEdge, right.curEdge)) + root.val;
        int curPath = Math.max(0, left.curEdge) + Math.max(0, right.curEdge) + root.val;

        return new Result(
                curEdge,
                curPath,
                Math.max(curEdge, Math.max(left.edge, right.edge)),
                Math.max(curPath, Math.max(left.path, right.path))
        );
    }


    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] map = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    map[i + 1][j + 1] = Math.min(map[i][j], Math.min(map[i][j + 1], map[i + 1][j])) + 1;
                    max = Math.max(max, map[i + 1][j + 1]);
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
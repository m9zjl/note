import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class WordSearch212 {
    public static void main(String[] args) {
        WordSearch212 solution = new WordSearch212();
        char[][] board = new char[][] { { 'a' }, { 'a' } };
        String[] words = new String[] { "aa" };
        System.out.println(solution.findWords(board, words));
    }

    public List<String> findWords(char[][] board, String[] words) {
        int maxLen = 0;
        int m = board.length;
        int n = board[0].length;
        int maxSize = m + n - 1;
        for (String str : words) {
            if (str.length() > maxSize) {
                continue;
            }
            maxLen = Math.max(maxLen, str.length());
        }
        Node head = buildTrieTree(words, maxSize);
        boolean[][] map = new boolean[m][n];
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                map[i][j] = true;
                if (head.next[c - 'a'] != null) {
                    List<Node> nodes = new ArrayList<>();
                    nodes.add(head.next[c - 'a']);
                    dfs(head, board, res, i, j, nodes, map);
                }
                map[i][j] = false;
            }
        }
        return new ArrayList<>(new HashSet<>(res));
    }

    private void dfs(Node head, char[][] board, List<String> res, int x, int y, List<Node> nodes, boolean[][] map) {
        int m = board.length;
        int n = board[0].length;
        int direction[] = new int[] { -1, 1 };
        for (Node node : nodes) {
            if (node.wordFlag) {
                res.add(node.word);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int newM = x;
                int newN = y;
                if (i == 0) {
                    newN = y + direction[j];
                }
                if (i == 1) {
                    newM = x + direction[j];
                }
                if (newM >= 0 && newM < m && newN >= 0 && newN < n) {
                    if (map[newM][newN]) {
                        continue;
                    }
                    List<Node> newNodes = new ArrayList<>();
                    for (Node node : nodes) {
                        if (node.next[board[newM][newN] - 'a'] != null) {
                            newNodes.add(node.next[board[newM][newN] - 'a']);
                        }
                    }
                    if (!newNodes.isEmpty()) {
                        map[newM][newN] = true;
                        dfs(head, board, res, newM, newN, newNodes,map);
                        map[newM][newN] = false;;
                    }
                }
            }
        }
    }

    private Node buildTrieTree(String[] words, int maxSize) {
        Node node = new Node();
        for (String str : words) {
            Node tmp = node;
            for (int i = 0; i < str.length(); i++) {
                Node currentNode = tmp.next[str.charAt(i) - 'a'];
                if (currentNode == null) {
                    currentNode = new Node();
                    tmp.next[str.charAt(i) - 'a'] = currentNode;
                }
                if (i == str.length() - 1) {
                    currentNode.wordFlag = true;
                    currentNode.word = str;
                }
                tmp = currentNode;
            }
        }
        return node;
    }

    class Node {
        boolean wordFlag = false;
        String word = null;
        Node[] next = new Node[26];
    }
}
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    }

    class Node {
        int x;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.x = val;
            this.min = min;
            this.next = next;
        }
    }

    Node head = null;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
    }

    public void push(int x) {
        head = head == null ? new Node(x, x, null) : new Node(x, Math.min(x, head.x), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        int val = head.x;
        head = head.next;
        return val;

    }

    public int getMin() {
        return head.min;
    }


}

class Solution {

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

    class Result {
        int halfMax;
        int pathMax;

        public Result(int x, int y) {
            this.halfMax = x;
            this.pathMax = y;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        Result result = dfs(root);
        return result.pathMax;
    }

    public Result dfs(TreeNode root) {

        if (root == null) {
            return new Result(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Result(root.val, 0);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);

        return new Result(
                Math.max(left.halfMax, right.halfMax) + root.val,
                Math.max(left.halfMax + right.halfMax + root.val, Math.max(left.pathMax, right.pathMax))
        );
    }


    public int lastStoneWeight(int[] stones) {
        if (stones == null || stones.length == 0) {
            return 0;
        }
        if (stones.length == 1) {
            return stones[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }

        while (queue.size() >= 2) {
            queue.offer(Math.abs(queue.poll() - queue.poll()));
        }
        return queue.poll();
    }

//    public boolean checkValidString(String s) {
//        int min = 0;
//        int max = 0;
//        for(int i = 0;i<s.length();i++){
//            switch(s.charAt(i)){
//                case '(': {
//                    min++;
//                    max++;
//                    break;
//                }
//                case ')':{
//                    min--;
//                    max--;
//                    break;
//                }
//                default:{
//                    min--;
//                    max++;
//                }
//                if (max<0) {
//                    return false;
//                }
//                min = Math.max()
//            }
//        }
//    }
public int rangeBitwiseAnd(int m, int n) {
    if(m==n){
        return m;
    }
    if(m ==1){
        return 0;
    }
    int modBase = getModBase(m);
    int sum  = 0;
    if(n/2<modBase){
        sum += modBase;
    }
    return sum + rangeBitwiseAnd(m%modBase,n%modBase);
}

    public int getModBase(int m){
        int t = 0;
        int value = m;
        while(value >1){
            value = value >> 1;
            t++;
        }
        return 2<<t;
    }
}
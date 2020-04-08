import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class TreeTraversal {
    public static void main(final String[] args) {
        final TreeTraversal traversal = new TreeTraversal();
        /**
         * 
         * 0 / \ 1 2 / \ 3 4
         * 
         * @return
         */
        final TreeNode root = traversal.generateTree();
        System.out.println(traversal.preOrderTraversal(root));
        System.out.println(traversal.inOrderTraversal(root));
        System.out.println(traversal.postOrderTraversalWithoutTopologicalDependience(root));
        System.out.println(traversal.postOrderTraversalWithTopologicalDependience(root));

    }

    private List<Integer> postOrderTraversalWithTopologicalDependience(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new ArrayList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if (
                (cur.left == null || cur.right == null)
                ||
                (pre!=null && (pre ==cur.left || pre == cur.right))
            ) {
                list.add(cur.val);
                pre = cur;
                stack.pop();
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
        return list;
    }

    private List<Integer> postOrderTraversalWithoutTopologicalDependience(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Collections.reverse(list);
        return list;
    }

    private List<Integer> preOrderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return list;
    }

    private List<Integer> inOrderTraversal(final TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        List<Integer> list = new ArrayList<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }
        return list;
    }

    /**
     * 
     * 0 / \ 1 2 / \ 3 4
     * 
     * @return
     */
    private TreeNode generateTree() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(final int x) {
            val = x;
        }
    }
}
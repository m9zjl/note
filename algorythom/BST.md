# binary search tree

---

[TOC]

## 1. pre order traversal

```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode lastVisited = null;
    while (curr != null || !stack.isEmpty()) {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        TreeNode node = stack.peek();
        if (node.right == null || lastVisited == node.right) {
            stack.pop();
            result.add(node.val);
            lastVisited = node;
        }
        // lastedVisited != node.right; && 
        else {
            curr = node.right;
        }
    }
    return result;
}
```

## 2. inorder traversal

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while (curr != null || !stack.isEmpty()) {
        while(curr !=null){
            stack.push(curr);
            curr = curr.left;
        }
        // curr == null now
        curr = stack.pop();
        result.add(curr.val);
        curr = curr.right;
    }
    return result;
}
```

## 3. postorder traversal

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    TreeNode curr = root;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    while (curr != null || !stack.isEmpty()) {
        while(curr !=null){
            stack.push(curr);
            curr = curr.left;
        }
        // curr == null now
        curr = stack.pop();
        result.add(curr.val);
        curr = curr.right;
    }
    List<Integer> reversed  = new ArrayList<>();
    for(int i = result.size()-1;i>=0;i--){
        reversed.add(result.get(i)); 
    }
    return reversed;
}
```

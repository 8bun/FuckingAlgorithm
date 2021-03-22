package 二叉树后序遍历非递归实现;

import java.util.Stack;

/**
 * 后序遍历：双栈法，和层次遍历（双队列）很相似，唯一区别在于层次遍历用的是队列，后序遍历用的是栈。
 * @author cwq
 * @since 2021/03/21
 */

public class Solution {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    private static void postOrder(TreeNode root) {
        if(root != null) {
            Stack<TreeNode> s1 = new Stack<>();
            Stack<TreeNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                root = s1.pop();
                s2.push(root);
                if(root.left != null) {
                    s1.push(root.left);
                }
                if (root.right != null) {
                    s1.push(root.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.println(s2.pop().val + " ");
            }
        }
    }
}

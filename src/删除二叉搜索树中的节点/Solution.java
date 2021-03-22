package 删除二叉搜索树中的节点;

import javax.swing.tree.TreeNode;

/**
 * @author cwq
 * @since 2021/03/20
 * @link https://leetcode-cn.com/problems/delete-node-in-a-bst/
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
    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) {
            return null;
        }
        // 把root删除
        if(root.val == key) {
            if(root.left == null) {
                return root.right;
            }
            if(root.right == null) {
                return root.left;
            }
            // 找到右子树最小的结点，或左最大也可
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        }
        else if(root.val > key) {
            root.left = deleteNode(root.left, key);
        }
        else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode getMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}

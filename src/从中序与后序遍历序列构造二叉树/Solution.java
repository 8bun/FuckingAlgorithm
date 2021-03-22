package 从中序与后序遍历序列构造二叉树;

/**
 * 树中没有重复的元素
 * @author cwq
 * @since 2021/03/19
 * @link https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {

        if(inStart > inEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + index - inStart);
        root.right = build(inorder, index + 1, inEnd, postorder, postStart + inEnd - index - 1, postEnd - 1);
        return root;
    }
}

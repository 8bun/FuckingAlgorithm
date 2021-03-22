package 二叉搜索树中第K小的元素;


/**
 * @author cwq
 * @since 2021/03/19
 * @link https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
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
    int res = 0;
    int rank = 0;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    private void traverse(TreeNode root, int k) {
        if(root == null) {
            return;
        }
        traverse(root.left, k);
        rank++;
        if(rank == k) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }
}

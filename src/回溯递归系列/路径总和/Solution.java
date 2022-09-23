package 回溯递归系列.路径总和;

/**
 * https://leetcode.cn/problems/path-sum/
 */
public class Solution {
    private class TreeNode {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root != null) {
            if (root.val == targetSum && root.left == null && root.right == null) {
                return true;
            } else {
                boolean leftHas = false, rightHas = false;
                if (root.left != null) {
                    leftHas = hasPathSum(root.left, targetSum - root.val);
                }
                if (root.right != null) {
                    rightHas = hasPathSum(root.right, targetSum - root.val);
                }
                return leftHas || rightHas;
            }
        }
        return false;
    }
}